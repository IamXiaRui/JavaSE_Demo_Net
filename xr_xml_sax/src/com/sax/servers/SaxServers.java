package com.sax.servers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.sax.handler.MyHandler;

public class SaxServers {

	public SaxServers() {
		// TODO Auto-generated constructor stub
	}

	public static List<HashMap<String, String>> XMLreader(InputStream inputStream,String Name){

		SAXParserFactory factory = SAXParserFactory.newInstance();  //��������
		try {
			SAXParser saxParser = factory.newSAXParser();     //��ȡһ��������
			MyHandler myHandler = new MyHandler(Name);        //ʵ����һ��DefalutHandler����
			saxParser.parse(inputStream, myHandler);          
			inputStream.close();
			return myHandler.getList();
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

		return null;
	}
}
