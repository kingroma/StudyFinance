package com.java.main.main;

import java.sql.Connection;

import com.java.main.database.MyConnection;
import com.java.main.domain.Gambling;

public class Main {
	public static void main(String[] args) {
//		ResentDataThread rdt = new ResentDataThread("005930");
//		rdt.start();
//		MyXmlMapperReader;
		Gambling g = new Gambling();
		g.setDate("99991231");
		
		Connection connection = MyConnection.getConnection();
	}

}
