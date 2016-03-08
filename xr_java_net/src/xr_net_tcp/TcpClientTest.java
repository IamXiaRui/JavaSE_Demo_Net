package xr_net_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/*
 * 利用TCP 实现客户端数据发送功能
 * 
 * 需要先开服务端  否则客户端将无法发送数据
 * */
public class TcpClientTest {

	public static void main(String[] args) throws Exception {
		// 建立Socket服务 指定主机号 和 端口号
		Socket s = new Socket(InetAddress.getLocalHost(), 8025);

		// 得到输入流
		OutputStream out = s.getOutputStream();
		out.write("i am xiarui".getBytes());

		// 接收服务端 反馈的数据信息流
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int length = in.read(buf);

		System.out.println(new String(buf, 0, length));

		s.close();

	}

}
