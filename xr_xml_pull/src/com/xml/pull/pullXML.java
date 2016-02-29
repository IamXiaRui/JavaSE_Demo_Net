package com.xml.pull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.events.StartDocument;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.xml.person.Person;

public class pullXML {

	public pullXML() {
		// TODO Auto-generated constructor stub
	}

	public static List<Person> paresXML(InputStream inputStream,String encode){
		List<Person> list = null;
		Person person = null ;
		try {
			//ʵ����һ������������
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			//ʵ����һ��������
			XmlPullParser parser = factory.newPullParser();
			//���¼���ӵ���������
			parser.setInput(inputStream,encode);
			//�����¼�����
			int eventType = parser.getEventType();
			while(eventType!=XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<Person>();
					break;

				case XmlPullParser.START_TAG:
					if("person".equals(parser.getName())){
						person = new Person();
						int id = Integer.parseInt(parser.getAttributeValue(0));
						person.setId(id);
					}else if("name".equals(parser.getName())){
						String name = parser.nextText();
						person.setName(name);
					}else if("age".equals(parser.getName())){
						int age = Integer.parseInt(parser.nextText());
						person.setAge(age);
					}
					break;

				case XmlPullParser.END_TAG:
					if("person".equals(parser.getName())){
						list.add(person);
						person = null;
					}
					break;
				}
			}



		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		return list;

	}

}
