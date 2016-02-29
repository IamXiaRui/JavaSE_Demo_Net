package com.xml.dom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.xml.person.Person;

public class DomXML {

	public static List<Person> domXml(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException{
		List<Person> list = new ArrayList<Person>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document document = builder.parse(inputStream);
			//根节点
			org.w3c.dom.Element element = document.getDocumentElement();   
			NodeList personNodes = element.getElementsByTagName("person");
			//遍历父节点
			for(int i=0;i<personNodes.getLength();i++){
				org.w3c.dom.Element personElement = (org.w3c.dom.Element) personNodes.item(i);
				//必须在循环里面实例化Person类  不然只能出现一个结果
				Person person = new Person();
				person.setId(Integer.parseInt(personElement.getAttribute("id")));
				//子节点
				NodeList childNodes = personElement.getChildNodes();
				//遍历子节点
				for(int j=0;j<childNodes.getLength();j++){
					//如果当前节点是元素节点的话
					if(childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
						if("name".equals(childNodes.item(j).getNodeName())){
							person.setName(childNodes.item(j).getFirstChild().getNodeValue());
						}else if("age".equals(childNodes.item(j).getNodeName())){
							person.setAge((Integer.parseInt(childNodes.item(j).getFirstChild().getNodeValue())));
						}
					}
				}
				list.add(person);
			}
		return list;
	}

}
