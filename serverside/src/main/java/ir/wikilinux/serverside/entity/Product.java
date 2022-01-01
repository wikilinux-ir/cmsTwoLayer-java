package ir.wikilinux.serverside.entity;

public class Product {

	private int id;
	private String name;
	private long price;
	private int count;
	
	
	public Product(int id, String name, long price, int count) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
}
