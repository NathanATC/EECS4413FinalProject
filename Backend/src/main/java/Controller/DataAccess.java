package Controller;
import java.util.ArrayList;

import Model.Account;
import Model.Cart;
import Model.Item;
import Model.Order;

public interface DataAccess {
		
	public boolean addUser(Account newUser, String password, String permissions);
	
	public Account getUser(String username);
	
	public boolean isPasswordCorrect(String userName, String password);
	
	public boolean isPerm(String premissions, Account userName);
	
	public void updatePassword(String UserName, String newPassword);
	
	public boolean addItem(Item newItem);
	
	public Item getItem(String id);
	
	public ArrayList<Item> getCatalogue();
	
	public boolean updateUser(Account UpdatedUser);
	
	public boolean updateItem(Item updatedItem);
	
	public ArrayList<Order> getSalesHistory(String userName);
	
	public Cart getCartForAccount (Account account);
}
