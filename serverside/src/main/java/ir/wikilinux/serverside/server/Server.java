package ir.wikilinux.serverside.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.*;
import java.rmi.registry.*;

import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.bz.ProductServicesImpl;

public class Server {
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		

		ProductServices services = new ProductServicesImpl();
		
		Registry registry = LocateRegistry.createRegistry(2001);
		
		registry.rebind("products", services);
		
		
		System.out.println("running");
		
	}

}
