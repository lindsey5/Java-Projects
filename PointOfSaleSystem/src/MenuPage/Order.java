package MenuPage;

public class Order {
	private String name;
	private double unitprice;
	private int quantity;
	private double price;
	private String size,category;
	
	Order(String name,double unitprice,int quantity,double price,String size,String category){
		this.name=name;
		this.unitprice=unitprice;
		this.quantity=quantity;
		this.price=price;
		this.size=size;
		this.category=category;
	}
	
	String getName() {
		return name;
	}
	
	double getUnitPrice() {
		return unitprice;
	}
	
	int getQuantity() {
		return quantity;
	}
	
	double getPrice() {
		return price;
	}
	
	String getSize() {
		return size;
	}
	
	String getCategory() {
		return category;
	}
	
	void setQuantity(int quantity) {
		this.quantity+=quantity;
	}
	
	void setPrice(double price) {
		this.price+=price;
	}

}
