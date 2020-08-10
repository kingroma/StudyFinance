package com.java.main.main;

import java.sql.Connection;
import java.util.Map;

import com.java.main.database.MyConnection;
import com.java.main.domain.Gambling;
import com.java.main.mapper.MyQueryMapper;
import com.java.main.service.JongMokService;
import com.java.main.service.ResentDataThread;

public class Main {
	public static void main(String[] args) {
		String code = "005930";
//		ResentDataThread rdt = new ResentDataThread("005930");
//		rdt.start();
		
		Map<String,String> map = JongMokService.getMap();
		int idx = 0 ; 
		for ( String key : map.keySet() ) { 
			code = key ; 
			String name = map.get(key);
			String description = JongMokService.getJongMokInformation(key);
			System.out.println(description);
			JongMokService.insert(code, name,description);
			
			idx++ ; 
			if ( idx > 10 ) break ; 
		}
	}

	
	private static void insert() {
		Gambling g = new Gambling();
		g.setDate("99991231");
		
		Connection connection = MyConnection.getConnection();
		String str = MyQueryMapper.basicQueryToMappingQuery("SELECT * FROM {date}", g);
		System.out.println(str);
		
		ResentDataThread rdt = new ResentDataThread("005930");
		rdt.start();
	}
	
	private static void jongMokInsert() {
		Map<String,String> map = JongMokService.getMap();
		
		for ( String key : map.keySet() ) {
//			System.out.println(key + " / " + map.get(key));
			String name = map.get(key);
			JongMokService.insert(key, name,null);
		}
	}
}
