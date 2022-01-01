package ir.wikilinux.serverside.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import ir.wikilinux.serverside.entity.Product;

public class Test {

	public static void main(String[] args) throws RemoteException {
		
		
		ProductDao dao = new ProductDaoImplMysql();
		Product product = new Product(2, "iphone", 1000010, 21);
		
//		int result = dao.changeProductName(1, "laptop");
		
//		dao.createProduct(product);
		
//		Product product2 = dao.getProduct(2);
//		System.out.println(product2.getCount());
//
//		dao.deleteProduct(0);
		
		ArrayList<Product> products = (ArrayList<Product>) dao.findWithName("xia");
		
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product3 = (Product) iterator.next();
			System.out.println(product3.getName());

			
		}
	}
	

}
