package com.qx.level0.lang.xml.tests.examples;


import com.s8.lang.xml.api.XML_GetAttribute;
import com.s8.lang.xml.api.XML_GetElement;
import com.s8.lang.xml.api.XML_SetAttribute;
import com.s8.lang.xml.api.XML_SetElement;
import com.s8.lang.xml.api.XML_Type;

@XML_Type(name="user", sub={})
public class User extends TestClass3 {

	private String name = "none";
	
	private String password;

	private String note;
	
	public String getName() {
		return name;
	}

	@XML_SetAttribute(name="name")
	public void setName(String name) {
		this.name = name;
	}


	@XML_SetAttribute(name="password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	@XML_GetAttribute(tag="password")
	public String getPassword() {
		return password;
	}
	
	@XML_SetElement(tag="note")
	public void setNote(String note){
		this.note = note;
	}
	
	@XML_GetElement(tag="note")
	public String getNote(){
		return note;
	}
}
