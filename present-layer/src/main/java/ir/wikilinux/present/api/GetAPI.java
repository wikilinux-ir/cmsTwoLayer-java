package ir.wikilinux.present.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.entity.Product;

public class GetAPI {

	public static void Get(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		int id = 0;
		resp.setContentType(EndPoint.CONTENT_TYPE_JSON);
		if (!req.getParameterMap().containsKey("id") || req.getParameterMap().get("id").equals(""))
		{
			resp.setStatus(HttpStatusCodes.BAD_REQUEST);
			out.println("{\"status\" : \"400 Bad Request\" ,  \"detail\" : \" The ID has not been entered or is empty \" }");
			return; 
		}
		
		try { 
			 id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			resp.setStatus(HttpStatusCodes.BAD_REQUEST);
			out.println("{\"status\" : \"400 Bad Request\", \"detail\" : \" The ID must be numeric \" }");
			return;
		}
		
		String productJsonString = null;
		ProductServices productServices = RmiConnection.getInstance();
		Product product = null ;
		resp.setStatus(HttpStatusCodes.OK);
		resp.setContentType("application/json");


		if (id > 0) {
			
			 product = productServices.getProduct(id);
			
			 //check product exist if product equals null , product not exist
			if (product == null) {

				resp.setStatus(HttpStatusCodes.NOT_FOUND);
				out.println("{\"status\" : \"404 Not Found\"}");
				return;

			}
			
			JsonElement element = gson.toJsonTree(product);
			element.getAsJsonObject().addProperty("status", HttpStatusCodes.OK);
			
			productJsonString = gson.toJson(element) ;
		}
		else {
			
			List<Product> products = productServices.getAllProducts();
			
			productJsonString = gson.toJson(products);
		}
		
	

		
		out.println(productJsonString);
	}
	
}
