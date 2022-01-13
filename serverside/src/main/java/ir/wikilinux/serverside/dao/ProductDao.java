package ir.wikilinux.serverside.dao;

import java.util.List;

import ir.wikilinux.serverside.entity.Product;

public interface ProductDao {
	
	
	
	int changeProductPrice(int id , int newPrice) ;
	
	int changeProductName(int id,String newName) ;
	
	int createProduct(Product product) ;
	
	Product getProduct(int id) ;
	
	List<Product> findWithName(String name) ;

	List<Product> getAllProduct() ;

	int updateAllProperties(int id , String name , 	int price , int count);
	
	int updateCount(int id,int count);
	
	int deleteProduct(int id) ;

}
