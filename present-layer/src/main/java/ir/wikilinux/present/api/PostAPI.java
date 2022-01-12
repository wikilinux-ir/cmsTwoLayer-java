package ir.wikilinux.present.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.entity.Product;

public class PostAPI {
	
	
	
	public  String GetBody(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
	
		
		BufferedReader reader = request.getReader();
		StringBuffer jsonString = new StringBuffer();
		String line;
		while((line=reader.readLine()) != null) {
			
			jsonString.append(line);
		}
		
		
		
		
		return jsonString.toString();
		
		
	}
	
	
	/**
	 * 
	 * 
	 * this method check a json string is valid or not valid 
	 * 
	 * @return Product
	 * @param String 
	 * @param PrintWriter
	 * 
	 */
	
	public  Product validator(String json,PrintWriter out) {
		
		
		//first check json string is valid 
		Gson gson = new Gson();
		
		try {
			
			Object object = gson.fromJson(json, Object.class);
			
		} catch (Exception e) {
		
			return null;

		}
		
		// second dynamic find all fields of a Product and check not null
		
		
		Class clazz = Product.class;
		
		Field[] fields = clazz.getDeclaredFields();
		
		Set<String>	listOfFieldsName = new HashSet<String>();
	
		for(Field field:fields)
		{
			listOfFieldsName.add(field.getName());
		}
		
		
		// create jsonObject to access json Keys
		
		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		
		boolean condition = true;
		
		for(String str:listOfFieldsName) {
			
			
			JsonElement JsonElement = jsonObject.get(str);
			if (JsonElement == null) {
				condition = false;
				listOfFieldsName.remove(str);
			}
			
			
		}
		
		if (listOfFieldsName.size() < 4) {
			
			out.println("the fields not valid <br> please check and retry" + listOfFieldsName.stream());
		}
		Product product = null;
		if (condition) {
			
			product = gson.fromJson(json, Product.class);
		}
		
		return product;
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
