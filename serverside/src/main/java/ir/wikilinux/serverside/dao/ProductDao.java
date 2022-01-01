package ir.wikilinux.serverside.dao;

import java.rmi.*;
import java.util.*;

import ir.wikilinux.serverside.entity.Product;

public interface ProductDao extends Remote{
	
	
	
	int changeProductPrice(int id , int newPrice) throws RemoteException;
	
	int changeProductName(int id,String newName) throws RemoteException;
	
	int createProduct(Product product) throws RemoteException;
	
	Product getProduct(int id) throws RemoteException;
	
	List<Product> findWithName(String name) throws RemoteException;
	
	int deleteProduct(int id) throws RemoteException;

}
