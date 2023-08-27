package Model;
import java.util.HashMap;



public class Cart {
	HashMap <String, Integer> cart = new HashMap<String, Integer>();
	
	
	public Cart() {
		
	}
	
	public HashMap<String, Integer> getCart() {
		return cart;
	}

	public void setCart(HashMap<String, Integer> cart) {
		this.cart = cart;
	}

	
	
	public int getItemQuantity(String id) {
		return cart.get(id);
	}
	
	public boolean addItem(String itemID,int qunatity) {
		return cart.put(itemID, qunatity) == null;
	}
	public boolean updateQuantity(String itemID, int newQunatity) {
		return cart.replace(itemID, newQunatity) != null;
	}
	
	public void clearCart() {
		cart.clear();
	}

}
