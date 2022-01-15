package ir.wikilinux.present.api;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import ir.wikilinux.present.api.jwt.JWTUtil;
import ir.wikilinux.serverside.entity.Product;

public class APIUtils {


	public static final String[] USER_ROLES = {"user"};
	public static final String[] ADMIN_ROLES = {"admin","superuser","user"};
	
	private static String getToGetMethodToken(HttpServletRequest request) {
		

		
		String token = request.getParameter(EndPoint.JWT_FIELD_NAME);
		
		if (token == null || token.equals(""))
		{
			return null;
		}
		
		/*
		 * when send query with Get method + symbol encoded and change to space so we should replace space with +
		 * */
		
		return token.replaceAll(" ", "+"); 
	}
	
	private static String getToPostMethodToken(String json)  {

		
		Gson gson = new Gson();
		
		JsonObject object = gson.fromJson(json, JsonObject.class);
		JsonElement element = object.get(EndPoint.JWT_FIELD_NAME);
		if (!element.toString().equals("")) {
			return element.toString();
		}
		return null;
	}
	
	public static String getTokenString(HttpServletRequest request , HttpServletResponse response,String json) throws IOException {
		
		
		
		// call method base on HTTP Method Request to get Token
		String token = null;
		switch (request.getMethod()) {
		case "GET":
		case "DELETE":
			token=getToGetMethodToken(request);
			break;
		case "POST":
		case "PUT":
			// for delete quotation from first and last 
			token = getToPostMethodToken(json).replaceAll("\"", "");
			break;
		}
		
	
		return token;
		
	}
	
	
public static boolean havePermission(String token,String role) {
		
		Gson gson = new Gson();
		String paylod = JWTUtil.getPayload(token);
		
		JsonObject jsonObject;
		try {
			jsonObject = gson.fromJson(paylod, JsonObject.class);

		} catch (JsonSyntaxException e) {

			e.printStackTrace();
			return false;
		
		}
		JsonElement jsonElement = jsonObject.get("role");
		
		if (jsonElement == null) {
			
			return false;
		}
		
			
		if (jsonElement.getAsString().toLowerCase().equals(role) || jsonElement.getAsString().toLowerCase().equals("admin"))
		{
			
			return true;
			
		}
		return false;
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
 *
 * 
 */

public static Product jsonFieldValidator(String json) {
	
	
	//first check json string is valid 
	
	Gson gson = new Gson();
	
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

	
	return product;
}

}
