package com.java.main.bringer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpBringer {
	public static Document bringDocument(String url) throws Exception{
		Document doc = null ; 
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			throw e;
		}
		return doc ; 
	}
	
	public static String bringHtml(String url)  throws Exception {
		return bringDocument(url).html();
	}
	
	public static String bringText(String url)  throws Exception {
		return bringDocument(url).text();
	}
}
