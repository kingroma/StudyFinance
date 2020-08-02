package com.java.main.main;

import java.util.Map;

import com.java.main.service.JongMokService;

public class Main {
	// https://marketdata.krx.co.kr/mdi#document=040602
	public static void main(String[] args) {
		String url = "https://mvnasdasd";
		String path ="C:\\Users\\kingrome\\Desktop\\study\\StudyFinance\\data\\data_20200802.xls";
		
		try {
			Map<String,String> map = JongMokService.init(path);
			System.out.println(map.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
