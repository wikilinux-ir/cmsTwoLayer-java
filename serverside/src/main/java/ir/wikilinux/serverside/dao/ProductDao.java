package ir.wikilinux.serverside.dao;

import java.rmi.*;

import ir.wikilinux.serverside.entity.Product;

public interface ProductDao extends Remote{
	
	
	int createProduct(Product product) throws RemoteException;
	Product getProduct(int id) throws RemoteException;
	
	Product[] findWithName(String name) throws RemoteException;
	
	
	
	void deleteProduct(int id) throws RemoteException;

}
