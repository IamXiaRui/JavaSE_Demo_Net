package com.sax.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	private HashMap<String, String> hashMap = null;   //存储单个解析的完整对象
	private List<HashMap<String,String>> list = null;  //存储所有解析的完整对象
	private String Tag = null;        //正在解析的元素的标签
	private String Value = null;     //正在解析的元素的内容
	private String Name = "";       //解析当前的节点名称

	public List<HashMap<String, String>> getList() {
		return list;
	}

	public MyHandler(String Name) {
		// TODO Auto-generated constructor stub
		this.Name = Name;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		//当读到第一个标签的时候 ，触发这个方法
		list = new ArrayList<HashMap<String,String>>();
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//当读到文档开头的时候，调用这个方法
		if(qName.equals(Name)){
			hashMap = new HashMap<String , String >();
		}
		if(attributes!=null&&hashMap!=null){
			for(int i=0;i<attributes.getLength();i++){
				hashMap.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		Tag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		//当读到元素内容的时候，调用这个方法
		if(Tag!=null&&hashMap!=null){
			Value = new String(ch, start, length);
			if(!Value.trim().equals("")&&!Value.trim().equals("\n")&&Value!=null){
				hashMap.put(Tag, Value);
			}
		}
		Tag = null;   //将当前节点所对应的值全部赋值为空，进行下一个节点的读取
		Value = null;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		//当读取节点结束的时候 调用这个方法
		if(qName.equals(Name)){
			list.add(hashMap);
			hashMap = null;
		}
	}
}
