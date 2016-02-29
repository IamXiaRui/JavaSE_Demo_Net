package com.xml.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * 访问服务器类
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
				//打开连接
				HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
				//设置延时
				httpURLConnection.setReadTimeout(3000);
				//设置输入
				httpURLConnection.setDoInput(true);
				//设置方式
				httpURLConnection.setRequestMethod("GET");
				//如果连接成功
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
