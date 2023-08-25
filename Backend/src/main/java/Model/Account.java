package Model;
import java.util.ArrayList;

public class Account {
	
	private String username = null;
	private String email = null;
	private String address = null;
	private String phoneNumber = null;
	private String province = null;
	private String country = null;
	private String firstName = null;
	private String lastName = null;
	private String billingAddress = null;
	private String postalCode = null;
	private ArrayList <String> allOrderIDs = new ArrayList<String>();
	private Cart currentCart = new Cart();
	
	public Account(){
		
	}
	
	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public ArrayList<String> getAllOrderIDs() {
		return allOrderIDs;
	}

	public void setAllOrderIDs(ArrayList<String> allOrderIDs) {
		this.allOrderIDs = allOrderIDs;
	}

	public Cart getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(Cart currentCart) {
		this.currentCart = currentCart;
	}

}
