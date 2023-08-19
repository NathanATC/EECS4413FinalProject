package Model;
import java.awt.Image;
import java.sql.Date;



public class Item {
	private String id = null;
	private String itemName = null;
	private String image = null;
	private String category = null;
	private String description = null;
	private int currentQuantity = 0;
	private double price = 0.0;
	private Date futureAvailability = null;
	
	public Item() {
		
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public Date getFutureAvailability() {
		return futureAvailability;
	}
	public void setFutureAvailability(Date futureAvailability) {
		this.futureAvailability = futureAvailability;
	}
	
	
}
