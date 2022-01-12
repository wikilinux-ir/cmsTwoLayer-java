package ir.wikilinux.present.api;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		if (!req.getParameterMap().containsKey("id") || req.getParameterMap().get("id").equals(""))
		{
			resp.setStatus(400);
			out.println("{\"status\" : \"400 Bad Request\" }");
		}
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		ProductServices productServices = RmiConnection.getInstance();
		
		Product product = productServices.getProduct(id);
		
		if (product == null) {

			resp.setStatus(404);
			out.println("{\"status\" : 404 Not Found}");
			return;

		}
		
		resp.setStatus(200);

		
		JsonElement element = gson.toJsonTree(product);
		element.getAsJsonObject().addProperty("status", 200);
		
		String productJson = gson.toJson(element) ;

		resp.setContentType("application/json");
		
		out.println(productJson);
	}
	
}
