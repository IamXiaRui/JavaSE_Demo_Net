package com.xml.test;

import java.util.List;

import com.xml.http.HttpUtils;
import com.xml.person.Person;
import com.xml.pull.pullXML;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "http://192.168.56.1:8080/web_myxml_sax/person.xml";
		List<Person> list = pullXML.paresXML(HttpUtils.getXML(path), "utf-8");
		for(Person person:list){
			System.out.println(person.toString());
		}

	}

}
