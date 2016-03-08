package xr_net_tcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
/*
 * ����TCP ʵ��ͼƬ�ĸ���
 * 
 * �ͻ���
 * */
public class TcpCopyPicClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//����Socket����   ָ������  �� �˿ں�  
		Socket s = new Socket(InetAddress.getLocalHost(), 10001);

		//��ȡ�ļ���
		FileInputStream fi = new FileInputStream("E:\\AllTestFile\\copy_one.png");

		//���ݴ��䵽�����
		OutputStream os = s.getOutputStream();

		//��ȡ�ļ�
		byte[] b = new byte[1024];

		int len = 0;

		while ((len = fi.read(b)) != -1) {
			os.write(b, 0, len);
		}

		//������Ƿ�
		s.shutdownOutput();

		//���շ���˴�������Ϣ
		InputStream is = s.getInputStream();

		byte[] buf = new byte[1024];

		String str = new String(buf, 0, is.read(buf));

		System.out.println(str);

		s.close();
		fi.close();
	}

}
