package com.java.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.java.main.bringer.ExcelBringer;

public class JongMokService {
	
	public static Map<String,String> init(String path){
		Map<String,String> map = new HashMap<String,String>();
		
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
}
