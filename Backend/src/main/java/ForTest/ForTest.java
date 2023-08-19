package ForTest;

import java.util.ArrayList;

import Controller.DataAccsessMySQL;
import Model.Account;
import Model.Item;

public class ForTest {


	public static void main(String[] args) {
//		Account a = new Account();
//		a.setUserName("sam123");
//		a.setFirstName("sam");
//		a.setBillingAddress("4 street street");
//		a.setAddress("4 street street");
//		a.setCountry("Canada");
//		a.setProvince("Ontario");
//		a.setLastName("sammul");
//		a.setEmail("a@gmail.com");
//		a.setPhoneNumber("123-123-123");
//		a.setPostalCode("V4M46");
//		
//		DataAccsessMySQL d = new DataAccsessMySQL();
//		
//		System.out.println(d.addUser(a, "pretendThisIsAlsoHashed", "Customer"));
		
//		Item i = new Item();
//		
//		i.setID("789234");
//		i.setItemName("Food flavoured Food");
//		i.setCategory("Food");
//		i.setCurrentQuantity(400);
//		i.setPrice(0.25);
//		i.setDescription("is food that tastes like food");
	
		
		DataAccsessMySQL d = new DataAccsessMySQL();
		
		ArrayList<Item> allItems = d.getCatalogue("","");
		
		for(Item i: allItems) {
			System.out.println(i.getItemName()+" $"+i.getPrice());
		}

	}

}
