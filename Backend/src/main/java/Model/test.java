package Model;

public class test {

	public static void main(String[] args) {
		Item apple = new Item();
		apple.setID("123");
		apple.setItemName("apple");
		apple.setPrice(1.0);
		
		Cart c = new Cart();
		

		
		c.addItem(apple.getID(), 1);
		
		System.out.println(c.getItemQuantity("123"));
		
		c.updateQuantity(apple.getID(), 5);
		
		System.out.println(c.getItemQuantity("123"));
		
		Order o = new Order(c);
		
		c.clearCart();
		
		
		
		
		System.out.println(o.getQunanity("123"));

	}

}
