package com.java.main.main;

import java.sql.Connection;

import com.java.main.database.MyConnection;
import com.java.main.domain.Gambling;
import com.java.main.mapper.MyQueryMapper;
import com.java.main.service.ResentDataThread;

public class Main {
	public static void main(String[] args) {
		Gambling g = new Gambling();
		g.setDate("99991231");
		
		Connection connection = MyConnection.getConnection();
		String str = MyQueryMapper.basicQueryToMappingQuery("SELECT * FROM {date}", g);
		System.out.println(str);
		
		ResentDataThread rdt = new ResentDataThread("005930");
		rdt.start();
	}

}
