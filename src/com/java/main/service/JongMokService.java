package com.java.main.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.nodes.Document;

import com.java.main.bringer.ExcelBringer;
import com.java.main.bringer.HttpBringer;
import com.java.main.database.MyConnection;
import com.java.main.domain.JongMok;
import com.java.main.mapper.MyQueryMapper;

public class JongMokService {
	private static Map<String,String> map = new HashMap<String,String>();
	
	private static String DEFAULT_PATH = "data/data_20200802.xls";
	
	public static void init() {
		if ( map.size() == 0 ) { 
			init(DEFAULT_PATH);
		}
	}
	
	public static Map<String,String> init(String path){
		
		try {
			Workbook workbook = ExcelBringer.getWorkbook(path);
			Sheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rows = sheet.rowIterator();;
			while ( rows.hasNext() ){
				
				Row row = rows.next();
				
				map.put(
						row.getCell(0).getStringCellValue(),
						row.getCell(1).getStringCellValue()
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map ; 
	}
	
	public static Map<String, String> getMap() {
		init();
		return map;
	}

	public static String getName(String code){
		init();
		return map.get(code);
	}
	
	private static final String SELECT_CODE_QUERY = 
			"SELECT * FROM T_JONG_MOK WHERE CODE = {code}" ;
	
	private static final String INSERT_QUERY = 
			"INSERT INTO T_JONG_MOK (CODE , NAME, DESCRIPTION) VALUES ( {code} , {name} ,{description})";
	
	private static final String UPDATE_QUERY = 
			"UPDATE T_JONG_MOK SET "
			+ "	NAME = {name} , "
			+ "	DESCRIPTION = {description} "
			+ "WHERE CODE = {code}";
	
	public static void insert(String code , String name, String description) {
		init();
		JongMok jm = new JongMok() ; 
		jm.setCode(code);
		jm.setName(name);
		jm.setDescription(description);
		
		try {
			Connection conn = MyConnection.getConnection();
			JongMok temp = select(code);
			
			String query = null; 
			if ( temp == null || temp.getName() == null ) {
				query = MyQueryMapper.basicQueryToMappingQuery(INSERT_QUERY, jm);
			} else { 
				query = MyQueryMapper.basicQueryToMappingQuery(UPDATE_QUERY, jm);
			}
			
			System.out.println(query);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JongMok select(String code) {
		JongMok jm = new JongMok();
		jm.setCode(code);
		try {
			Connection conn = MyConnection.getConnection();
			String query = MyQueryMapper.basicQueryToMappingQuery(SELECT_CODE_QUERY, jm);
			System.out.println(query);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String name = rs.getString(2);
				jm.setName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jm;
	}
	
	private static final String TODAY_URL = "https://finance.naver.com/item/main.nhn?code=";
	public static String getJongMokInformation(String code) {
		String result = null ; 
		// summary_info
		try {
			String key = "summary_info";
			Document doc = HttpBringer.bringDocument(TODAY_URL + code);
			result = ( doc.getElementById(key) != null ? doc.getElementById(key).text() : null ) ;
			
			if ( result != null ) { 
				result = result.replace("기업개요", "").substring(0,result.indexOf("출처 :")).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result ; 
	}
}
