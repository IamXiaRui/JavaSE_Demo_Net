package xr_net_tcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
/*
 * 利用TCP 实现图片的复制
 * 
 * 客户端
 * */
public class TcpCopyPicClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建Socket对象   指定主机  和 端口号  
		Socket s = new Socket(InetAddress.getLocalHost(), 10001);

		//读取文件流
		FileInputStream fi = new FileInputStream("E:\\AllTestFile\\copy_one.png");

		//数据传输到服务端
		OutputStream os = s.getOutputStream();

		//读取文件
		byte[] b = new byte[1024];

		int len = 0;

		while ((len = fi.read(b)) != -1) {
			os.write(b, 0, len);
		}

		//结束标记符
		s.shutdownOutput();

		//接收服务端传来的消息
		InputStream is = s.getInputStream();

		byte[] buf = new byte[1024];

		String str = new String(buf, 0, is.read(buf));

		System.out.println(str);

		s.close();
		fi.close();
	}

}
