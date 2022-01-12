package ir.wikilinux.serverside.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.bz.ProductServicesImpl;

public class Server {
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		

		ProductServices services = new ProductServicesImpl();
		
		Registry registry = LocateRegistry.createRegistry(2003);
		
		registry.rebind("product", services);
		
		
		System.out.println("running");
		
	}

}
