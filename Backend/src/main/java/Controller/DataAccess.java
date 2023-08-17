package Controller;
import java.util.ArrayList;

import Controller.DataAccess.UserNotFound;
import Model.Account;
import Model.Cart;
import Model.Item;
import Model.Order;

public interface DataAccess {
	
	public class UserNotFound extends Exception{
		public UserNotFound(String userName) {
			super("the user '"+userName+"' was not found");
		}
	}
		
	public boolean addUser(Account newUser, String password, String permissions);
	
	public Account getUser(String username);
	
	public boolean isPasswordCorrect(String userName, String password) throws UserNotFound;
	
	public boolean isPerm(String premissions, Account userName) throws UserNotFound;
	
	public void updatePassword(String UserName, String newPassword);
	
	public boolean addItem(Item newItem);
	
	public Item getItem(String id);
	
	public ArrayList<Item> getCatalogue();
	
	public boolean updateUser(String maybeOldusername,Account updatedUser);
	
	public boolean updateItem(Item updatedItem);
	
	public ArrayList<Order> getSalesHistory(String userName);
	
	public Cart getCartForAccount (Account account);
}
