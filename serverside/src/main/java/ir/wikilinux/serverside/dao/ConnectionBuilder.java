package ir.wikilinux.serverside.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBuilder {

	
	private final static String url = "jdbc:mysql://localhost:3306/cmsjava";
	private final static String username = "root";
	private final static String password= "nu11device";
	
	
	public static Connection getConnection() 
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url,username,password);
			return connection;
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		
		}
		return null;
	}
}
