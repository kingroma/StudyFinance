package com.java.main.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MyQueryMapper {
	
	public static String basicQueryToMappingQuery(String query, Object entity) {
		String result = null ;
		String q = query;
		try {
			if ( q != null && entity != null ) { 
				
				while ( q.indexOf("{") > 0 ) { 
					String str = q.substring( q.indexOf("{") , q.indexOf("}")+1 );
					String key = str.replace("{", "").replace("}","").trim();
					String methodName = "get" + key.toUpperCase().charAt(0) + key.substring(1,key.length());
					
					Object value = entity.getClass().getDeclaredMethod(methodName).invoke(entity, null);
					String className = value.getClass().getSimpleName();
					String replace = null ; 
					
					switch(className) {
					case "String" : 
						replace = "'" + value + "'";
						break;
					default :
						replace = (String)value;
						break;
					}
					
					q = q.replace(str, replace);
				}
				
				result = q ; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result ; 
	}
}
