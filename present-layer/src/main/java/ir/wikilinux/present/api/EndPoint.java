package ir.wikilinux.present.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.wikilinux.serverside.entity.Product;

@WebServlet("/api/rest")
public class EndPoint extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		GetAPI.Get(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PostAPI postAPI = new PostAPI();
		
		String jsonString = postAPI.GetBody(req, resp);
		
		
		Product product = postAPI.validator(jsonString, resp.getWriter());
		
		if (product != null) 
		{
			
			int result = postAPI.registerProduct(product);
			if (result == 11) {
				resp.setStatus(409);
				resp.getWriter().println("{\"status\" : 409 \n \"detail\" : \" this product id is duplicate\" }");
			}else {
				resp.setStatus(201);
				resp.getWriter().println("{\"status\" : 201 }");
			}
		}
		else 
		{
			resp.setStatus(400);
			resp.getWriter().println("{\"status\" : 400 }");
		}
	
		
	}
}