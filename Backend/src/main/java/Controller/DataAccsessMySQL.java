package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			//TODO hash password
			Connection connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);

			String sqlQuery = "INSERT INTO `database`.`accounts` (`username`, `hashed_password`, `first_name`, `last_name`, `address`, `phone_number`, `province`, `country`, `billing_address`, `postal_code`, `permissions`,`email`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";


			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);

			prepStatment.setString(1, newUser.getUserName());
			prepStatment.setString(2, password);
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

			prepStatment.execute();


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


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


		return account;
	}

	@Override
	public boolean isPasswordCorrect(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPerm(String premissions, Account userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePassword(String UserName, String newPassword) {
		// TODO Auto-generated method stub

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



		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


		return i;




	}


	@Override
	public ArrayList<Item> getCatalogue() {


		ArrayList<Item> catalogue = new ArrayList<Item>();

		Connection connection;
		try {
			connection = DriverManager.getConnection(connectionUrl,dbUsername, dbPassword);


			String sqlQuery = "SELECT * FROM ITEMS";
			PreparedStatement prepStatment = connection.prepareStatement(sqlQuery);


			ResultSet results = prepStatment.executeQuery();

			while(results.next()) {

				Item  i = new Item();

				i.setID(results.getString("item_id"));
				i.setItemName(results.getString("item_name"));
				i.setCategory("category");
				i.setFutureAvailability(results.getDate("futureAvailability"));
				i.setPrice(results.getDouble("price"));
				i.setCurrentQuantity(results.getInt("ammount_in_stock"));

				catalogue.add(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}


		return catalogue;
	}

	@Override
	public boolean updateUser(Account UpdatedUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateItem(Item updatedItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> getSalesHistory(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCartForAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}


}
