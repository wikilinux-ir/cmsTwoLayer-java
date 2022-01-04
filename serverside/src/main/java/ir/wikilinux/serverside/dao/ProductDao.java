package ir.wikilinux.serverside.dao;

import java.rmi.*;
import java.util.*;

import ir.wikilinux.serverside.entity.Product;

public interface ProductDao {
	
	
	
	int changeProductPrice(int id , int newPrice) ;
	
	int changeProductName(int id,String newName) ;
	
	int createProduct(Product product) ;
	
	Product getProduct(int id) ;
	
	List<Product> findWithName(String name) ;

	List<Product> getAllProduct() ;

	
	int deleteProduct(int id) ;

}
