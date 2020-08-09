package com.java.main.service;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.java.main.bringer.HttpBringer;
import com.java.main.domain.Gambling;

// https://finance.naver.com/item/sise_day.nhn?page=1&code=005930
public class ResentDataThread extends Thread{
	private static final String URL = "https://finance.naver.com/item/sise_day.nhn?page=1&code=";
	
	private String code = null ; 
	
	private GamblingService gs = new GamblingService(); 
	
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
//				날짜 종가 전일비 시가 고가 저가 거래량
//				2020.08.07 57,500 500 57,900 58,400 57,100 18,160,383
				String str = tds.text();
				if ( str != null && str.trim().length() > 0 && !str.startsWith("날짜")) {
					String split[] = str.split(" ");
					
					Gambling gb = new Gambling();
					gb.setCode(code);
					gb.setDate(_replace(split[0])); // 날짜 
					gb.setJongGa(_replace(split[1])); // 종가 
					gb.setGab(_replace(split[2])); // 전일비 
					gb.setSiga(_replace(split[3])); // 시가 
					gb.setGoga(_replace(split[4])); // 고가 
					gb.setJeoGa(_replace(split[5])); // 저가 
					gb.setAmount(_replace(split[6])); // 거래량
					
					gs.save(gb);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String _replace(String str){
		return str.replaceAll(",", "").replaceAll("\\.", "");
	}
	
}

