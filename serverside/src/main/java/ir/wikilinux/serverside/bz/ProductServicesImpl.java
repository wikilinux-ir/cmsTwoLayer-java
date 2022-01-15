package ir.wikilinux.serverside.bz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ir.wikilinux.serverside.dao.ProductDao;
import ir.wikilinux.serverside.dao.ProductDaoImplMysql;
import ir.wikilinux.serverside.entity.Product;

public class ProductServicesImpl extends UnicastRemoteObject implements ProductServices,Serializable{
	
	public ProductServicesImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	private final ProductDao dao = new ProductDaoImplMysql();

	@Override
	public int changeProductPrice(int id, int newPrice) throws RemoteException {
		
		return dao.changeProductPrice(id, newPrice);
		
	}

	@Override
	public int changeProductName(int id, String newName) throws RemoteException {

		
		return dao.changeProductName(id, newName);
	}

	@Override
	public int createProduct(Product product) throws RemoteException {
		
		return dao.createProduct(product);
	}

	@Override
	public Product getProduct(int id) throws RemoteException {
		
		return dao.getProduct(id);
	}

	@Override
	public List<Product> findWithName(String name) throws RemoteException {
		
		return dao.findWithName(name);
	}

	@Override
	public int deleteProduct(int id) throws RemoteException {

		return dao.deleteProduct(id);
	}

	@Override
	public List<Product> getAllProducts(){
		
		return dao.getAllProduct();
	}
	
	@Override
	public int updateAllProperties(int id , String name , 	int price , int count) {
		

		return dao.updateAllProperties(id, name, price, count);
	}
	@Override
	public int updateCount(int id,int count) {
		
		return dao.updateCount(id, count);
	}
}
