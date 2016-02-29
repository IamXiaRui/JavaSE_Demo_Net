package com.sax.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.sax.http.HttpUtils;
import com.sax.servers.SaxServers;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "http://192.168.56.1:8080/web_myxml_sax/person.xml";
		InputStream inputStream = HttpUtils.getXML(path);
		try {
			List<HashMap<String,String>> list = SaxServers.XMLreader(inputStream, "person");  //调用解析类
			for(HashMap<String, String> hashmap:list){    //遍历list
				System.out.println(hashmap.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
