package Model;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

public class Order {
	String orderID = null;
	HashMap<String, Integer> order = null;
	boolean isFuffiled = false;
	Account account = null;
	Date orderDate = null;
	Time orderTime = null;
	Date fullfilmentDate = null;
	Time fullfilmentTime = null;
	
	public Order() {
		
	}
	public Order(Cart c) {
		order = new HashMap<String, Integer>();
		order.putAll(c.getCart());
		
	
	}
	
	public int getQunanity(String id) {
		return order.get(id);
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public boolean isFuffiled() {
		return isFuffiled;
	}

	public void setFuffiled(boolean isFuffiled) {
		this.isFuffiled = isFuffiled;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Time getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}

	public Date getFullfilmentDate() {
		return fullfilmentDate;
	}

	public void setFullfilmentDate(Date fullfilmentDate) {
		this.fullfilmentDate = fullfilmentDate;
	}

	public Time getFullfilmentTime() {
		return fullfilmentTime;
	}

	public void setFullfilmentTime(Time fullfilmentTime) {
		this.fullfilmentTime = fullfilmentTime;
	}
}
