package ir.wikilinux.serverside.entity;

public class Product {

	private int id;
	private long price;
	private String name;
	private int count;
	
	
	
	
	public Product(int id, long price, String name, int count) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.count = count;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
