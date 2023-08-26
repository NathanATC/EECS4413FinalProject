package Controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.spec.PBEKeySpec;

import Model.Account;
import Model.Cart;
import Model.Item;
import Model.Order;

public class DataAccsessMySQL implements DataAccess{

	private final String connectionUrl = "jdbc:mysql://localhost:3306/database";
	private final String dbUsername = "root";
	private final String dbPassword = "4413";


	public DataAccsessMySQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String generateSalt() {
		StringBuilder builder = new StringBuilder();
		SecureRandom r = new SecureRandom();

		while(builder.length() < 32) {	
			char newChar = (char) r.nextInt((125 - 33 + 1) + 33);
			builder.append(newChar);
		}


		return builder.toString();

	}
//
//	//based on:https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
//	private String hashAndSalt(String password, String salt) {
//		MessageDigest messageDigest;
//		try {
//			messageDigest = MessageDigest.getInstance("SHA-512");//to encrypt
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		byte[] bytesOfHash = messageDigest.digest((password+salt).getBytes());
//
//		//convert decimal bytes to hexadecimal
//		StringBuilder sb = new StringBuilder();
//		for(byte b: bytesOfHash) {
//			sb.append(Integer.toHexString(b));
//		}
//
//
//		return sb.toString();
//	}



	/**
	 * @param Account the Account object of the user to be added to the database
	 * @param the accounts password (will be hashed by the DAO)
	 * 
	 * Adds a new user to the database. 
	 * 
	 * @return True if successfully added, false otherwise. Note: will also return false if a SQL exception is thrown
	 * 
	 */
	@Override
	public boolean addUser(Account newUser, String password, String permissions) {
		try {
			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "INSERT INTO `database`.`accounts` (`username`, `hashed_password`, `first_name`, `last_name`, `address`, `phone_number`, `province`, `country`, `billing_address`, `postal_code`, `permissions`,`email`,`salt`)"
					+ " VALUES (?, SHA2(?, 512), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);

			prepStatment.setString(1, newUser.getUserName());
			prepStatment.setString(3, newUser.getFirstName());
			prepStatment.setString(4, newUser.getLastName());
			prepStatment.setString(5, newUser.getAddress());
			prepStatment.setString(6, newUser.getPhoneNumber());
			prepStatment.setString(7, newUser.getProvince());
			prepStatment.setString(8, newUser.getCountry());
			prepStatment.setString(9, newUser.getBillingAddress());
			prepStatment.setString(10, newUser.getPostalCode());
			prepStatment.setString(11, permissions);
			prepStatment.setString(12, newUser.getEmail());

			//authentication stuff
			String salt = generateSalt();
			
			prepStatment.setString(2, password+salt);
			prepStatment.setString(13, salt);

			

			prepStatment.execute();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}




		return true;
	}



	/**
	 * @param the username of the account needed
	 * 
	 * connects to the database and returns an account object associated with the passed username
	 * 
	 * @return An account bean object containing information on the user
	 */
	@Override
	public Account getUser(String username) {
		Account account = new Account();

		try {
			//TODO add cart
			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "SELECT * FROM ACCOUNTS WHERE username = ?";
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);

			prepStatment.setString(1, username);

			ResultSet results = prepStatment.executeQuery();
			results.next();

			account.setUserName(results.getString("username"));
			account.setFirstName(results.getString("first_name"));
			account.setLastName(results.getString("last_name"));
			account.setAddress(results.getString("address"));
			account.setBillingAddress("billing_address");
			account.setCountry(results.getString("country"));
			account.setProvince(results.getString("province"));
			account.setPostalCode(results.getString("postal_code"));
			account.setEmail(results.getString("email"));

			prepStatment.execute();

			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


		return account;
	}
	
	

	@Override
	public boolean isPasswordCorrect(String userName, String password)  {
		try {
			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);
			
			String sqlQuery = "SELECT IF(SHA2(CONCAT(? ,salt), 512) = hashed_password,TRUE,FALSE)"
					+ "FROM Accounts WHERE username = ?;";
			
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			
			
			prepStatment.setString(2, userName);
			prepStatment.setString(1, password);
			
			
			ResultSet results = prepStatment.executeQuery();
			
			if(!(results.isBeforeFirst()))
				return false;
			
			results.next();
			
			
			
			
			
			boolean isTrue = results.getBoolean(1);
			
			connection.close();
			
			return isTrue;
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		
	}

	@Override
	public boolean isPerm(String premissions, Account user) throws UserNotFound{
		String sqlQuery = "SELECT permissions "
				+ "FROM Accounts WHERE username = ?;";

		Connection connection;
		try {
			connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			prepStatment.setString(1, user.getUserName());

			ResultSet results = prepStatment.executeQuery();

			if(!(results.isBeforeFirst()))
				throw new UserNotFound(user.getUserName());

			results.next();
			
			connection.close();

			
			return results.getString(1).equals(premissions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	

	@Override
	public void updatePassword(String username, String newPassword) {
		try {

			String sqlQuery = "UPDATE ACCOUNTS SET hashed_password = SHA2(?, 512), Salt = ? WHERE username = ?";

			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);

			String salt = generateSalt();




			prepStatment.setString(1, newPassword+salt);
			prepStatment.setString(2, salt);
			prepStatment.setString(3, username);

			prepStatment.execute();
			
			connection.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public boolean addItem(Item newItem) {
		try {

			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "INSERT INTO `database`.`items` (`item_iD`, `item_name`, `category`, `description`, `ammount_in_stock`, `price`, `futureAvailability`) VALUES (?, ?, ?, ?, ?, ?,?);";


			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			prepStatment.setString(1, newItem.getID());
			prepStatment.setString(2, newItem.getItemName());
			prepStatment.setString(3, newItem.getCategory());
			prepStatment.setString(4, newItem.getDescription());
			prepStatment.setInt(5, newItem.getCurrentQuantity());
			prepStatment.setDouble(6, newItem.getPrice());

			prepStatment.setDate(7, newItem.getFutureAvailability());


			prepStatment.execute();

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}




		return true;
	}


	@Override
	public Item getItem(String id) {
		Item i = new Item();

		try {

			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "SELECT * FROM ITEMS WHERE item_iD = ?";
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);

			prepStatment.setString(1, id);

			ResultSet results = prepStatment.executeQuery();

			results.next();

			i.setID(results.getString("item_id"));
			i.setItemName(results.getString("item_name"));
			i.setCategory("category");
			i.setFutureAvailability(results.getDate("futureAvailability"));
			i.setPrice(results.getDouble("price"));
			i.setCurrentQuantity(results.getInt("ammount_in_stock"));

			connection.close();


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


		return i;

		



	}


	@Override
	public ArrayList<Item> getCatalogue(String filterType, String filter) {

		
		ArrayList<Item> catalogue = new ArrayList<Item>();

		Connection connection;
		try {
			connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);
			String sqlQuery = "SELECT * FROM ITEMS";
			if(!filterType.isEmpty() && !filter.isEmpty()) {
				if(filterType.equals("cat")) {
					sqlQuery = "SELECT * FROM ITEMS WHERE items.category = \"" + filter + "\"";
				} else {
					sqlQuery = "SELECT * FROM ITEMS WHERE items.brand = \"" + filter + "\"";
				}
				
			}
			
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);


			ResultSet results = prepStatment.executeQuery();

			while(results.next()) {

				Item  i = new Item();

				i.setID(results.getString("item_id"));
				i.setItemName(results.getString("item_name"));
				i.setDescription(results.getString("description"));	
				i.setBrand(results.getString("brand"));
				i.setCategory(results.getString("category"));
				i.setFutureAvailability(results.getDate("futureAvailability"));
				i.setPrice(results.getDouble("price"));
				i.setCurrentQuantity(results.getInt("ammount_in_stock"));
				i.setImage(results.getString("image_path"));
				System.out.println("sdf"+results.getString("brand"));
				catalogue.add(i);
			}
			
			connection.close();


		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}


		return catalogue;
	}

	@Override
	public boolean updateUser(String maybeOldUsername ,Account updatedUser) {
		try {

			String sqlQuery = "UPDATE `accounts` SET `username` = ?,"
					+ "`first_name` = ?, `last_name` = ?, `email` = ?, "
					+ "`address` = ?, `phone_number` = ?, `province` = ?, "
					+ "`country` = ?, `billing_address` = ?, `postal_code` = ? "
					+ "WHERE `username` = ?;";

			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			
			prepStatment.setString(1, updatedUser.getUserName());
			prepStatment.setString(2, updatedUser.getFirstName());
			prepStatment.setString(3, updatedUser.getLastName());
			prepStatment.setString(4, updatedUser.getEmail());
			prepStatment.setString(5, updatedUser.getAddress());
			prepStatment.setString(6, updatedUser.getPhoneNumber());
			prepStatment.setString(7, updatedUser.getProvince());
			prepStatment.setString(8, updatedUser.getCountry());
			prepStatment.setString(9, updatedUser.getBillingAddress());
			prepStatment.setString(10, updatedUser.getPostalCode());
			prepStatment.setString(11, maybeOldUsername);

			prepStatment.execute();
			
			connection.close();

			
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateItem(Item updatedItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> getSalesHistory() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			
			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);
			String sqlQuery = "SELECT * FROM (ORDERS JOIN ORDER_CONTENT ON ORDERS.ORDER_ID = ORDER_CONTENT.ORDER_ID)  JOIN ITEMS ON Items.Item_ID = Order_Content.Item_ID  ORDER BY ORDERS.ORDER_ID;";
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			
			
			
			ResultSet results = prepStatment.executeQuery();
			
			String lastID = "";
			while(results.next()) {
				System.out.println(lastID+" "+results.getString("order_ID"));
				
				
				if (lastID.equals(results.getString("order_ID")) == false) {
					Order order = new Order();
					order.setOrderID(results.getString("order_ID"));
					order.setUsername(results.getString("customer_username"));
					order.setFuffiled(results.getBoolean("is_Fulfilled"));
					order.setOrderDate(results.getDate("order_date"));
					order.setOrderTime(results.getTime("order_time"));
					orders.add(order);
				}
				orders.get(orders.size() - 1).getAllQuanties().put(results.getString("item_id"), results.getInt("quantity"));
				Item  i = new Item();

				i.setID(results.getString("item_id"));
				i.setItemName(results.getString("item_name"));
				i.setDescription(results.getString("description"));	
				i.setBrand(results.getString("brand"));
				i.setCategory(results.getString("category"));
				i.setFutureAvailability(results.getDate("futureAvailability"));
				i.setPrice(results.getDouble("price"));
				i.setCurrentQuantity(results.getInt("ammount_in_stock"));
				i.setImage(results.getString("image_path"));
				
				orders.get(orders.size() - 1).getItems().add(i);
				
				lastID = results.getString("order_ID");
				
				connection.close();

				
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return orders;
	}
	
	


	
	public boolean addToCart(String user, String itemId, int qty) {
        try {

            Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);
            String sqlQuery ="";

            sqlQuery = "SELECT * FROM database.current_cart WHERE costomer_username = ? AND item_id = ?;";
            PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
            prepStatment.setString(1, user);
            prepStatment.setString(2, itemId);
            prepStatment.execute();
            ResultSet r = prepStatment.getResultSet();
            if(r.next()) {
                System.out.println("update");
                int tmpQ =  r.getInt("Quantity");

                System.out.println(tmpQ);
                sqlQuery = "UPDATE database.current_cart SET Quantity = ? WHERE costomer_username = ? AND item_id = ?;";
                prepStatment = connection.prepareStatement(sqlQuery);
                prepStatment.setInt(1, tmpQ + qty);
                prepStatment.setString(2, user);
                prepStatment.setString(3, itemId);
                prepStatment.execute();
            } else {
                sqlQuery = "INSERT INTO database.current_cart (costomer_username, item_id, Quantity) VALUES (?,?,?);";
                prepStatment = connection.prepareStatement(sqlQuery);
                prepStatment.setString(1, user);
                prepStatment.setString(2, itemId);
                prepStatment.setInt(3, qty);
                prepStatment.execute();
            }

			connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



	public boolean clearCart(String user) {
		try {

			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "DELETE FROM `database`.`current_cart` WHERE costomer_username=?;";

			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			prepStatment.setString(1, user);
			prepStatment.execute();
			
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public ArrayList<Item> getCart(String user) {
		ArrayList<Item> tmp = new ArrayList<Item>();
		try {

			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "SELECT * FROM current_cart as x inner join items as y ON x.item_id = y.item_iD AND x.costomer_username = ?";
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			prepStatment.setString(1, user);
			prepStatment.execute();
			ResultSet r = prepStatment.getResultSet();
			while(r.next()) {
				Item i = new Item();
				
				i.setID(r.getString("item_id"));
				i.setItemName(r.getString("item_name"));
				i.setDescription(r.getString("description"));	
				i.setBrand(r.getString("brand"));
				i.setCategory(r.getString("category"));
				i.setFutureAvailability(r.getDate("futureAvailability"));
				i.setPrice(r.getDouble("price"));
				i.setImage(r.getString("image_path"));
				i.setCurrentQuantity(r.getInt("Quantity"));
				tmp.add(i);
			}
			
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmp;
	}
	
	public ArrayList<Item> topItems(int topWhat){
		ArrayList<Item> topItems =  new ArrayList<Item>();
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "SELECT  *, SUM(Items.price * ORDER_CONTENT.Quantity) AS moneyMade FROM (ORDERS JOIN ORDER_CONTENT ON ORDERS.ORDER_ID = ORDER_CONTENT.ORDER_ID)  JOIN ITEMS ON Items.Item_ID = Order_Content.Item_ID  Group by items.item_ID ORDER BY moneyMade DESC;";
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);
			
			ResultSet r = prepStatment.executeQuery();
			
			while(r.next() && topItems.size() < topWhat) {
				Item i = new Item();
				
				i.setID(r.getString("item_id"));
				i.setItemName(r.getString("item_name"));
				i.setDescription(r.getString("description"));	
				i.setBrand(r.getString("brand"));
				i.setCategory(r.getString("category"));
				i.setFutureAvailability(r.getDate("futureAvailability"));
				i.setPrice(r.getDouble("price"));
				i.setImage(r.getString("image_path"));
				i.setCurrentQuantity(r.getInt("Quantity"));
				
				topItems.add(i);
				
			}
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return topItems;
	}

	public void addOrdertoDataBase(String username) {
		String sqlQuery = "INSERT INTO ORDERS(CUSTOMER_USER_NAME,ORDER_DATE,ORDER_TIME,is_fulfilled) VALUES(?,?,?,?)";
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);
			
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery,PreparedStatement.RETURN_GENERATED_KEYS);
			
			prepStatment.setString(1, username);
			
			java.sql.Date currDate = new java.sql.Date(System.currentTimeMillis());
			prepStatment.setDate(2, currDate);
			
			java.sql.Time currTime = new java.sql.Time(System.currentTimeMillis());
			prepStatment.setTime(3, currTime);
			
			prepStatment.setBoolean(4, false);
			
			
			prepStatment.execute();
			
			ResultSet rs = prepStatment.getGeneratedKeys();
			rs.next();
			int orderID = rs.getInt(1);
			
			sqlQuery = "INSERT INTO ORDER_CONTENT  (order_ID,item_ID,quantity) "
					+ "SELECT ? ,item_id, quantity FROM CURRENT_CART "
					+ "Where costomer_username = ?";
			
			prepStatment = connection.prepareStatement(sqlQuery);
			prepStatment.setInt(1, orderID);
			prepStatment.setString(2, username);
			prepStatment.execute();
			
			this.clearCart(username);
			
			connection.close();
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}

