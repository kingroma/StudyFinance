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
//				��¥ ���� ���Ϻ� �ð� �� ���� �ŷ���
//				2020.08.07 57,500 500 57,900 58,400 57,100 18,160,383
				String str = tds.text();
				if ( str != null && str.trim().length() > 0 && !str.startsWith("��¥")) {
					String split[] = str.split(" ");
					
					Gambling gb = new Gambling();
					gb.setCode(code);
					gb.setDate(_replace(split[0])); // ��¥ 
					gb.setJongGa(_replace(split[1])); // ���� 
					gb.setGab(_replace(split[2])); // ���Ϻ� 
					gb.setSiga(_replace(split[3])); // �ð� 
					gb.setGoga(_replace(split[4])); // �� 
					gb.setJeoGa(_replace(split[5])); // ���� 
					gb.setAmount(_replace(split[6])); // �ŷ���
					
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

