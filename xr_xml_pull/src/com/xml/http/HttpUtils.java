package com.xml.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * ���ʷ�������
 * @author X.R
 *
 */
public class HttpUtils {

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}

	public static InputStream getXML(String path){
		InputStream inputStream = null;
		try {
			URL url = new URL(path);
			if(url!=null){
				//������
				HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
				//������ʱ
				httpURLConnection.setReadTimeout(3000);
				//��������
				httpURLConnection.setDoInput(true);
				//���÷�ʽ
				httpURLConnection.setRequestMethod("GET");
				//������ӳɹ�
				if(httpURLConnection.getResponseCode()==200){
					inputStream = httpURLConnection.getInputStream();
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputStream;
	} 
}
