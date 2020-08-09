package com.java.main.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	private static MyConnection INSTANCE = null ; 
	
	private Connection conn = null ; 
	
	public static Connection getConnection() {
		if ( INSTANCE == null ) { 
			INSTANCE = new MyConnection();
		}
		return INSTANCE.getConn() ; 
	}
	
	public MyConnection() {
		
	}
	
	private Connection getConn() {
		if ( conn == null ) { 
			try {
				Class.forName("com.mysql.jdbc.Driver");
			
				String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
				conn = DriverManager.getConnection(url,"root","4235");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.conn ; 
	}
	
	
	
}
