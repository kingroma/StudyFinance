package com.java.main.domain;

import com.java.main.util.DomainUtil;

/**
CREATE TABLE T_GAMBLING (
	CODE VARCHAR(30) NOT NULL , 
	DATE VARCHAR(8) NOT NULL , 
	GAB VARCHAR(30) NOT NULL , 
	SIGA VARCHAR(30) NOT NULL , 
	GOGA VARCHAR(30) NOT NULL ,  
	JEO_GA VARCHAR(30) NOT NULL , 
	JONG_GA VARCHAR(30) NOT NULL , 
	AMOUNT VARCHAR(30) NOT NULL ,
	PRIMARY KEY ( CODE , DATE ) 
);
ALTER TABLE T_GAMBLING CONVERT TO CHARACTER SET UTF8; 
 * */
public class Gambling {
	private String code ; 
	
	private String date;

	private String gab;

	private String siga;

	private String goga;
	
	private String jeoGa ;

	private String jongGa;

	private String amount;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getGab() {
		return gab;
	}

	public void setGab(String gab) {
		this.gab = gab;
	}

	public String getSiga() {
		return siga;
	}

	public void setSiga(String siga) {
		this.siga = siga;
	}

	public String getGoga() {
		return goga;
	}

	public void setGoga(String goga) {
		this.goga = goga;
	}

	public String getJongGa() {
		return jongGa;
	}

	public void setJongGa(String jongGa) {
		this.jongGa = jongGa;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getJeoGa() {
		return jeoGa;
	}

	public void setJeoGa(String jeoGa) {
		this.jeoGa = jeoGa;
	}

	@Override
	public String toString() {
		return DomainUtil.changeObjValueToString(this);
	}
}
