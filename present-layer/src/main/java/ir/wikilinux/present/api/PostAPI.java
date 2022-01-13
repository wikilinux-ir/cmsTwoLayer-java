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
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

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
	
	
	/**
	 * 
	 * 
	 * this method check a json string is valid or not valid 
	 * this method check all product field and if json contains all 
	 * fields create product and return else return null
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
		
		
		//check json syntax is valid
		try {
			JsonElement isValidSyntaxElement= JsonParser.parseString(json);

		} catch (JsonSyntaxException e) {

			return null;
			
		}
		
		// create jsonObject to access json Keys
		
		boolean condition = true;

		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		
		
		
		for (String string : listOfFieldsName) {
			
			
		}
		
		int fieldCounter = 0;
		for(String str:listOfFieldsName) 
		{
			
			
			JsonElement JsonElement = jsonObject.get(str);
			

			
			//check for json contains keys
			if (!jsonObject.has(str)) 
			{
				condition = false;
				return null;
			}
			
			// check to keys have value if have not return
			if (JsonElement.isJsonNull() || JsonElement.getAsString().equals("") || JsonElement == null ) {
				condition = false;
				listOfFieldsName.remove(str);
				return null;


			}
			
			
		}
		
		Product product = gson.fromJson(json, Product.class);
//		
//		if (listOfFieldsName.size() < 4) {
//			
//			out.println("the fields not valid <br> please check and retry" + listOfFieldsName.stream());
//		}
		
		
//		Product product = null;
//		if (condition) {
//			
//			product = gson.fromJson(json, Product.class);
//		}
		
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
