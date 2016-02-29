package com.xml.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.xml.dom.DomXML;
import com.xml.http.HttpUtils;
import com.xml.person.Person;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "http://192.168.56.1:8080/web_myxml_sax/person.xml";
		InputStream inputStream = HttpUtils.getXML(path);
		DomXML domXML = new DomXML();
		List<Person> list;
		try {
			list = domXML.domXml(inputStream);
			for(Person person:list){
				System.out.println(person.toString());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
