package com.java.main.domain;

/**
CREATE TABLE T_JONG_MOK (
	CODE VARCHAR(30) PRIMARY KEY, 
	NAME VARCHAR(100) NOT NULL ,
	DESCRIPTION VARCHAR(10000) NULL 
);
ALTER TABLE T_JONG_MOK CONVERT TO CHARACTER SET UTF8; 
 * */
public class JongMok {
	private String code ; 
	
	private String name ;
	
	private String description ; 
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
