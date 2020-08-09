package com.java.main.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DomainUtil {
	public static String convertMethodName(String field) {
		return 
				"get" + 
				Character.toUpperCase(field.charAt(0)) + 
				field.substring(1,field.length());
	}
	
	public static String changeObjValueToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append(obj.getClass().getName());
		sb.append(" ");
		for ( Field f : obj.getClass().getDeclaredFields() ) {
			try {
				String fieldName = f.getName();
				String methodName = convertMethodName(f.getName());
				Method m = obj.getClass().getMethod(methodName, null);
				Object o = m.invoke(obj, null);
				sb.append(fieldName);
				sb.append("=");
				sb.append(o);
				sb.append(", ");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		sb.delete(sb.length()-2, sb.length());
		
		sb.append(" ] ");
		
		return sb.toString();
	}
}
