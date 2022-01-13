package ir.wikilinux.present.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class APIUtils {

	
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
			token=getToGetMethodToken(request);
			break;
		case "POST":
			// for delete quotation from first and last 
			token = getToPostMethodToken(json).replaceAll("\"", "");
			break;
		case "PUT":
			// for delete quotation from first and last 
			token = getToPostMethodToken(json).replaceAll("\"", "");
		}
		
	

		return token;
		
	}
}
