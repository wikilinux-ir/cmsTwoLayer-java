package ir.wikilinux.present.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import ir.wikilinux.present.utiliti.ServerInformation;
import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.entity.Product;


@WebServlet("/AllProduct")
public class GetAllProducts extends HttpServlet{


	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		
		response.setContentType("application/json");
		
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		
		try {
			
			
			out=response.getWriter();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Product> products =null;
		ProductServices productServices = null ;
		try {
			
			Registry registry = LocateRegistry.getRegistry(ServerInformation.HOSTNAME,ServerInformation.PORT);
			productServices = (ProductServices) registry.lookup(ServerInformation.MODEL);
			
			products = productServices.getAllProducts();

			
		} catch (Exception e) {
			
				e.printStackTrace();
		}
		
		
		Gson gson=new Gson();
		
		String jsonString =gson.toJson(products);
		
		out.println(jsonString);		
		
	}
	
}
