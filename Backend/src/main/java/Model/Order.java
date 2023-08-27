package Model;
import java.sql.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order {
	String orderID = null;
	HashMap<String, Integer> orderQuanties = new HashMap<String, Integer>();
	ArrayList<Item> items = new ArrayList<Item>();
	boolean isFuffiled = false;
	String username = null;
	Date orderDate = null;
	Time orderTime = null;
	Date fullfilmentDate = null;
	Time fullfilmentTime = null;
	
	public Order() {
		
	}
	public Order(Cart c) {
		orderQuanties = new HashMap<String, Integer>();
		orderQuanties.putAll(c.getCart());
		
	
	}
	public HashMap<String, Integer> getAllQuanties(){
		return orderQuanties;
	}
	
	public int getQunanity(String id) {
		return orderQuanties.get(id);
	}
	public ArrayList<Item> getItems(){
		return items;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
