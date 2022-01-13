package ir.wikilinux.serverside.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ir.wikilinux.serverside.entity.Product;

public class ProductDaoImplMysql implements ProductDao{
	
	
	private final Connection connection = ConnectionBuilder.getConnection();
	
	private final int IS_NULL = -1;
	private final int ERROR = 0;

	@Override
	public int changeProductPrice(int id,int newPrice)  {

		if (connection == null)
		{
			return IS_NULL;
		}
		try(PreparedStatement ps = connection.prepareStatement("update products set price=? where id=?");) {
			
			ps.setInt(1, newPrice);
			ps.setInt(2, id);
			int success = ps.executeUpdate();
			return success;
		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		return ERROR;
	}

	@Override
	public int changeProductName(int id,String newName)  {

		if (connection == null)
		{
			return IS_NULL;
		}
		try(PreparedStatement ps = connection.prepareStatement("update products set name=? where id=?");) {
			
			ps.setString(1, newName);
			ps.setInt(2, id);
			int success = ps.executeUpdate();
			return success;
		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		return ERROR;
	}
		
	

	@Override
	public int createProduct(Product product)  {

		if (product == null || connection == null) 
		{
			return IS_NULL;
		}
		
		int productId = product.getId();
		int price = product.getPrice();
		int count = product.getCount();
		String name = product.getName();
		try(PreparedStatement ps = connection.prepareStatement("insert into products (product_id,name,price,count) "
				+ "values(?,?,?,?);");) {
			
			ps.setInt(1, productId);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setInt(4, count);
			int success = ps.executeUpdate();
			return success;
		} catch (Exception e) {
			
				System.out.println(e.getMessage());
				return 11;
		}
	}

	@Override
	public Product getProduct(int id)  {
		
		if (connection == null)
		{
			return null;
		}
		try(PreparedStatement ps = connection.prepareStatement("select * from products where product_id=?");) {
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			Product product = null;
			while(rs.next())
			{
				int productId = rs.getInt("product_id");
				int price = rs.getInt("price");
				int count= rs.getInt("count");
				String name = rs.getString("name");
				product = new Product(productId, name, price, count);
			}
			
			return product;

		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Product> findWithName(String name)  {
		
		if (connection == null)
		{
			return null;
		}
		try(PreparedStatement ps = connection.prepareStatement("select * from products where name like ? ");) {
			
			ps.setString(1, "%"+name+"%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Product>  products= new ArrayList<>();
			Product product = null;
			while(rs.next())
			{
				int productId = rs.getInt("product_id");
				int price = rs.getInt("price");
				int count= rs.getInt("count");
				String nameDb = rs.getString("name");
				product = new Product(productId, nameDb, price, count);
				products.add(product);
			}
			
			return products;

		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		return null;
	
	}

	@Override
	public int deleteProduct(int id)  {

		if ( connection == null) 
		{
			return IS_NULL;
		}
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM products WHERE product_id = ?");) {
			
			ps.setInt(1, id);
			int success = ps.executeUpdate();
			return success;
		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		return ERROR;		
	}
	
	
	
	
	
	@Override
	public List<Product> getAllProduct() 
	{
	
		
		if (connection == null)
		{
			return null;
		}
		try(PreparedStatement ps = connection.prepareStatement("select * from products order by product_id asc");) {
			
			
			ResultSet rs = ps.executeQuery();
			List<Product>  products= new ArrayList<>();
			Product product = null;
			while(rs.next())
			{
				int productId = rs.getInt("product_id");
				int price = rs.getInt("price");
				int count= rs.getInt("count");
				String nameDb = rs.getString("name");
				product = new Product(productId, nameDb, price, count);
				products.add(product);
			}
			
			return products;

		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		return null;
	
	}
	
	@Override
	public int updateAllProperties(int id , String name , int price , int count)
	{
	

		if (connection == null)
		{
			return IS_NULL;
		}

		Product product = getProduct(id);
		
		int success = 0;
		
		if (product == null)
		{
			
			product = new Product(id, name, price, count);
			success = createProduct(product);
			
			return success;
		}else 
		{
			try(PreparedStatement ps = connection.prepareStatement("UPDATE products SET name = ? , price = ? , count = ? WHERE product_id = ?");) {
				
				ps.setString(1,name);
				ps.setInt(2, price);
				ps.setInt(3, count);
				ps.setInt(4, id);
				success = ps.executeUpdate();
				return success;
			} catch (Exception e) {
				
					System.out.println(e.getMessage());
			}
		}

		return 0;
		
	}
	

	@Override
	public int updateCount(int id,int count) {

		
		if (connection == null)
		{
			return IS_NULL;
		}
		
		try(PreparedStatement ps = connection.prepareStatement("UPDATE products SET  count = ? WHERE product_id = ?");) {
			
			ps.setInt(1, count);
			ps.setInt(2, id);
			int success = ps.executeUpdate();
			return success;
		} catch (Exception e) {
			
				System.out.println(e.getMessage());
		}
		
		return ERROR;
	}
	
	
	}
	
	
	
	
	


