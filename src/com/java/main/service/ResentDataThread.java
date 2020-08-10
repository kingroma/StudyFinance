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
	private static final String WEEK_URL = "https://finance.naver.com/item/sise_day.nhn?page=1&code=";
	
	private static final String TODAY_URL = "https://finance.naver.com/item/main.nhn?code=";
	
	private String code = null ; 
	
	private GamblingService gs = new GamblingService(); 
	
	public ResentDataThread(String code) {
		this.code = code ; 
	}
	
	@Override
	public void run() {
		weekData();	
		todayData();
	}
	
	private void weekData() {
		try {
			Document doc = HttpBringer.bringDocument(WEEK_URL + code);
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
	
	private void todayData() {
		// https://finance.naver.com/item/main.nhn?code=005930
		Gambling g = new Gambling();
		g.setCode(code);
		try {
			Document doc = HttpBringer.bringDocument(TODAY_URL + code);
			// today
			Elements elements = doc.getElementsByClass("today");
			Element e = elements.get(0);
			
			Elements blinds = e.getElementsByClass("blind");
			g.setJongGa(_replace(blinds.get(0).text()));
			g.setGab(_replace(blinds.get(1).text()));
			
			// no_info
			elements = doc.getElementsByClass("no_info");
			e = elements.get(0);
			
			blinds = e.getElementsByClass("blind");
			g.setGoga(_replace(blinds.get(1).text()));
			g.setAmount(_replace(blinds.get(3).text()));
			g.setSiga(_replace(blinds.get(4).text()));
			g.setJeoGa(_replace(blinds.get(5).text()));
			
			
			e = doc.getElementById("time");
			g.setDate(_replace(doc.getElementById("time").text().substring(0,10)));
			
			System.out.println(g);
			gs.save(g);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String _replace(String str){
		if ( str != null ) {
			str = str.replaceAll(",", "").replaceAll("\\.", "");
		}
		return str;
	}
	
}

