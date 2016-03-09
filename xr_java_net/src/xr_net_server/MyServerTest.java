package xr_net_server;

/*
 * 自定义服务端
 * 
 * 在浏览器中打开 http://IP : 端口号  即可访问
 * 
 * */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerTest {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(10240);

		Socket s = ss.accept();

		System.out.println(s.getInetAddress().getHostAddress() + " - 已经连接");

		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		pw.println("自定义客户端");

		s.close();

		ss.close();
	}

}
