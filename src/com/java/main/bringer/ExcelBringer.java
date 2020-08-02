package com.java.main.bringer;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBringer {
//	public static bringExcelMap(String path) throws Exception{
//		getWorkbook(path);
//	}
	
	public static Workbook getWorkbook(String path) throws Exception{
		Workbook workbook = null ; 
		
		try (FileInputStream stream = new FileInputStream(path)) {
			if (path.endsWith("xls")) {
				workbook = new HSSFWorkbook(stream);
			} else if (path.endsWith("xlsx") ) {
				workbook = new XSSFWorkbook(stream);
			}
		} catch (Throwable e) {
			throw e;
		}
		
		return workbook ; 
	}
	
	public static Cell getCell(Sheet sheet , int r, int c) throws Exception {
		Cell cell = null ; 
		
		Row row = sheet.getRow(r);
		cell = row.getCell(c);
		
		return cell ; 
	}
}
