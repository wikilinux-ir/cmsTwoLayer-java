package ir.wikilinux.present.api;

import java.rmi.RemoteException;

import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.entity.Product;

public class PutAPI {
	
	
	
	public static int registerProduct(Product product)
	{
		
		ProductServices productServices = RmiConnection.getInstance();
		int result = 2;
		int id = product.getId();
		int price = product.getPrice();
		int count = product.getCount();
		String name = product.getName();

		
		try {

			result =  productServices.updateAllProperties(id, name, price, count);
			

			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}

		
		return result;
	}
	

}
