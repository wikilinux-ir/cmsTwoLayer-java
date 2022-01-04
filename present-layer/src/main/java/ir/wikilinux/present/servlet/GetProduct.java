package ir.wikilinux.present.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.wikilinux.present.utiliti.ServerInformation;
import ir.wikilinux.serverside.bz.ProductServices;
import ir.wikilinux.serverside.entity.Product;

@WebServlet("/GetProduct")
public class GetProduct extends HttpServlet{


	/*just for test rmi connection*/
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			
		int id = Integer.parseInt(req.getParameter("id"));
		
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.write(1);
			Registry registry = LocateRegistry.getRegistry(ServerInformation.HOSTNAME, ServerInformation.PORT);
			out.write(2);

			ProductServices productServices = null;
			try {
				productServices = (ProductServices) registry.lookup(ServerInformation.MODEL);
				out.write(3);

				Product product = productServices.getProduct(id);
				
				ArrayList<Product> products = (ArrayList<Product>) productServices.findWithName("x");
				
				for (Iterator iterator = products.iterator(); iterator.hasNext();) {
					
					Product product1 = (Product) iterator.next();
					
					
				}
				
				out.write(product.getName() + product.getCount());
				
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
			
		
		

	}
	
	
}
