package com.java.main.service;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.java.main.bringer.HttpBringer;

// https://finance.naver.com/item/sise_day.nhn?page=1&code=005930
public class ResentDataThread extends Thread{
	private static final String URL = "https://finance.naver.com/item/sise_day.nhn?page=1&code=";
	
	private String code = null ; 
	
	public ResentDataThread(String code) {
		this.code = code ; 
	}
	
	@Override
	public void run() {
		try {
			Document doc = HttpBringer.bringDocument(URL + code);
			Elements es = doc.getElementsByTag("table");
			Element table = es.get(0);
			Element tbody = table.children().get(0);
			Elements trs = tbody.children();
			
			for ( int i = 0 ; i < trs.size() ; i ++ ) { 
				Element tr = trs.get(i);
				Elements tds = tr.children();  
				// 날짜 종가 전일비 시가 고가 저가 거래량
				if ( tds.text() != null && tds.text().trim().length() > 0 ) 
					System.out.println(tds.text());
				

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

