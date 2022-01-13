package ir.wikilinux.present.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.wikilinux.present.api.jwt.JWTValidator;
import ir.wikilinux.serverside.entity.Product;

@WebServlet("/api/rest")
public class EndPoint extends HttpServlet{
	
	
	public static final  String CONTENT_TYPE_JSON = "application/json";
	 static final String JWT_FIELD_NAME = "token";
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String token = APIUtils.getTokenString(req,resp,null);
		
		/* 
		 * validate to set token or not
		 * if not set or null return from servlet
		 */

		
		if(token == null || !JWTValidator.validator(token) ||token.equals("") ) 
		{
			resp.setStatus(HttpStatusCodes.UNAUTHORIZED);
			resp.setContentType(CONTENT_TYPE_JSON);
			resp.getWriter().println("{\n\"status\" : 401,"
					+ "\n \"detail\" : \" 401 Unauthorized\"}");
			return;
		}

			GetAPI.Get(req, resp);

		
		
	}
	
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PostAPI postAPI = new PostAPI();
		
		
		// get json posted to server
		
		String jsonString = postAPI.GetBody(req, resp);
		
		//get JWT token value from json string 
		
		String token = APIUtils.getTokenString(req,resp,jsonString);
		
		
		// Set Content type 
		resp.setContentType(CONTENT_TYPE_JSON);

		/* 
		 * validate to set token or not
		 * if not set or null return from servlet
		 */
		
		if(token == null || !JWTValidator.validator(token) ||token.equals("") ) 
		{
			resp.setStatus(HttpStatusCodes.UNAUTHORIZED);
			resp.getWriter().println("{\n\"status\" : 401,"
					+ "\n \"detail\" : \" 401 Unauthorized\"}");
			return;
		}
		
		/*
		 * if set token and valid token check to set all field for create product? If all fields are set return product
		 * */
		
		Product product = postAPI.validator(jsonString, resp.getWriter());
		
		if (product != null) 
		{
		
			/* if all fields ok register product to DB and give a result code 
			*  if result code equal 11 , it means that the product ID is duplicate
			*/
			
			int result = postAPI.registerProduct(product);
			if (result == 11) {
				resp.setStatus(HttpStatusCodes.CONFLICT);
				resp.getWriter().println("{\"status\" : 409, \n \"detail\" : \" This product ID is duplicate\" }");
			} else {
				resp.setStatus(HttpStatusCodes.CREATED);
				resp.getWriter().println("{\"status\" : 201,"
						+ "\"detail\" : \"Product Created!\" }");
			}
			
			return;

		}
		else 
		{
			resp.setStatus(HttpStatusCodes.BAD_REQUEST);
			resp.getWriter().println("{\"status\" : 400,"
					+ "\"detail\": \" One or more fields are empty or incorrect\n"
					+ "\n"
					+ " \" }");
			
			return;
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PostAPI postAPI = new PostAPI();
		
		
		// get json posted to server
		
		String jsonString = postAPI.GetBody(req, resp);
		
		String token = APIUtils.getTokenString(req,resp,jsonString);
		
		
		// Set Content type 
		resp.setContentType(CONTENT_TYPE_JSON);

		/* 
		 * validate to set token or not
		 * if not set or null return from servlet
		 */
		if(token == null || !JWTValidator.validator(token) ||token.equals("") ) 
		{
			resp.setStatus(HttpStatusCodes.UNAUTHORIZED);
			resp.getWriter().println("{\n\"status\" : 401,"
					+ "\n \"detail\" : \" 401 Unauthorized\"}");
			return;
		}
		
		
		Product product = postAPI.validator(jsonString, resp.getWriter());

		if (product == null) {
			

				resp.setStatus(HttpStatusCodes.BAD_REQUEST);
				resp.getWriter().println("{\"status\" : 400,"
						+ "\"detail\": \" One or more fields are empty or incorrect\n"
						+ "\n"
						+ " \" }");
				
				return;
			
		}

		int result =  PutAPI.registerProduct(product);

		if (result > 0) {
			
			resp.setStatus(HttpStatusCodes.ACCEPTED);
			resp.getWriter().println("{\n\"status\" : 202,"
					+ "\n \"detail\" : \" ACCEPTED\"}");
		}
	}
}
