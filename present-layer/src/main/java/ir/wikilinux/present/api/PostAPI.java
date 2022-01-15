package ir.wikilinux.present.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.entity.Product;

public class PostAPI {
	
	
	
	public String GetBody(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
	
		
		BufferedReader reader = request.getReader();
		StringBuffer jsonString = new StringBuffer();
		String line;
		while((line=reader.readLine()) != null) {
			
			jsonString.append(line);
		}
		
		
	
		
		return jsonString.toString();
		
		
	}
	
	
	
	public int registerProduct(Product product)
	{
		
		ProductServices productServices = RmiConnection.getInstance();
		int result = 0;
		try {
			
			result =  productServices.createProduct(product);
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}

}
