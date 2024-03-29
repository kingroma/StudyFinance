package com.java.main.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.java.main.database.MyConnection;
import com.java.main.domain.Gambling;
import com.java.main.mapper.MyQueryMapper;

public class GamblingService {
	public GamblingService(){ }
	
	private static final String SELECT_CODE_AND_DATE_QUERY = "SELECT * FROM T_GAMBLING WHERE CODE = {code} AND DATE = {date}";
	
	private static final String UPDATE_QUERY = 
			"UPDATE T_GAMBLING SET "
			+ "	GAB = {gab} , "
			+ "	SIGA = {siga} , "
			+ " GOGA = {goga} , "
			+ "	JEO_GA = {jeoGa} , "
			+ " JONG_GA = {jongGa} , "
			+ "	AMOUNT = {amount} "
			+ "WHERE CODE = {code} AND "
			+ "DATE = {date}";
	
	private static final String INSERT_QUERY = 
			"INSERT INTO T_GAMBLING ("
			+ "CODE , DATE , GAB , "
			+ "SIGA , GOGA , JEO_GA , "
			+ "JONG_GA , AMOUNT "
			+ ") VALUES ("
			+ "{code} , {date} , {gab} , "
			+ "{siga} , {goga} , {jeoGa} , "
			+ "{jongGa} , {amount} "
			+ ")";
	public void save(Gambling gb){
		try {
			Connection conn = MyConnection.getConnection();
			boolean isUpdate = false ; 
			
			String query1 = MyQueryMapper.basicQueryToMappingQuery(SELECT_CODE_AND_DATE_QUERY, gb);
			System.out.println(query1);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			if ( rs.next() ) { 
				isUpdate = true ; 
			}
			
			String query2 = null ; 
			if ( !isUpdate ) { 
				query2 = MyQueryMapper.basicQueryToMappingQuery(INSERT_QUERY, gb);
			} else { 
				query2 = MyQueryMapper.basicQueryToMappingQuery(UPDATE_QUERY, gb); 
			}
			System.out.println(query2);
			
			PreparedStatement pstmt = conn.prepareStatement(query2);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
