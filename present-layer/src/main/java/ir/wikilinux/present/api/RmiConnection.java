package ir.wikilinux.present.api;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ir.wikilinux.serverside.bz.ProductServices;

public class RmiConnection {

	private static ProductServices INSTANCE = null;
	
	private static ProductServices getProductServices() {
		
		
		try {
			Registry registry = LocateRegistry.getRegistry(2003);
			
			ProductServices productServices = (ProductServices) registry.lookup("product");
			
			return productServices;
			
		} catch (RemoteException | NotBoundException e) {
			
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public static ProductServices getInstance() {
		
		if (INSTANCE==null) {
			
			INSTANCE = getProductServices();
		}
		
		return INSTANCE;
		
		
	}
	
}
