package xr_net_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 利用TCP 实现服务端数据接收功能
 * 
 * */
public class TcpServerTest {

	public static void main(String[] args) throws Exception {
		// 建立服务端的Socket 并指定客户端的端口号
		ServerSocket ss = new ServerSocket(8025);

		// 获取连接过来的客户端对象
		Socket s = ss.accept();

		// 得到客户端发来的数据信息流
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int length = in.read(buf);

		System.out.println(new String(buf, 0, length));

		// 向客户端发送反馈信息
		OutputStream out = s.getOutputStream();
		out.write("i am ruixia".getBytes());

		s.close(); // 可以关闭客户端中的对象
		ss.close();// 可选操作 一般服务器端不关闭

	}

}
