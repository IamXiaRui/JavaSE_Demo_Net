package com.sax.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	private HashMap<String, String> hashMap = null;   //�洢������������������
	private List<HashMap<String,String>> list = null;  //�洢���н�������������
	private String Tag = null;        //���ڽ�����Ԫ�صı�ǩ
	private String Value = null;     //���ڽ�����Ԫ�ص�����
	private String Name = "";       //������ǰ�Ľڵ�����

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
		//��������һ����ǩ��ʱ�� �������������
		list = new ArrayList<HashMap<String,String>>();
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//�������ĵ���ͷ��ʱ�򣬵����������
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
		//������Ԫ�����ݵ�ʱ�򣬵����������
		if(Tag!=null&&hashMap!=null){
			Value = new String(ch, start, length);
			if(!Value.trim().equals("")&&!Value.trim().equals("\n")&&Value!=null){
				hashMap.put(Tag, Value);
			}
		}
		Tag = null;   //����ǰ�ڵ�����Ӧ��ֵȫ����ֵΪ�գ�������һ���ڵ�Ķ�ȡ
		Value = null;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		//����ȡ�ڵ������ʱ�� �����������
		if(qName.equals(Name)){
			list.add(hashMap);
			hashMap = null;
		}
	}
}
