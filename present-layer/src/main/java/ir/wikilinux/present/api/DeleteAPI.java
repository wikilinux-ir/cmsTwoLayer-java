package ir.wikilinux.present.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ir.wikilinux.serverside.bz.ProductServices;

public class DeleteAPI {

	
	public static int delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		resp.setContentType(EndPoint.CONTENT_TYPE_JSON);
		

		boolean filedExists =  doExistFeild("id", req);
			
		if (!filedExists) {
			
			return -1;
		}
			
		int id = getId(req);
		
//		if (id <= 0 ) {
//			
//			resp.setStatus(HttpStatusCodes.BAD_REQUEST);
//			out.println("{\"status\" : \"400 Bad Request\", \"detail\" : \" The ID must be numeric \" }");
//			return -1;
//		}
//		
	
		ProductServices productServices = RmiConnection.getInstance();
		
		int result = productServices.deleteProduct(id);
		return result;
	}
	



	public static boolean doExistFeild(String fieldName,HttpServletRequest request) {
		
		if (!request.getParameterMap().containsKey(fieldName) || request.getParameterMap().get("id").equals(""))
		{
			return false; 
		}
		
		return true;
	}

	
	
	public static int getId(HttpServletRequest request) {
		
		int id = -1;
		try { 
			 id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
	
		}
		
		return id;
		
	}


}








