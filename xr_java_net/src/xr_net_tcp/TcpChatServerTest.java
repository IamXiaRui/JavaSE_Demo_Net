package xr_net_tcp;

/*
 * 利用TCP 实现简单QQ聊天的功能
 * 服务端
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServerTest {
	public static void main(String[] args) throws Exception {
		// 创建一个服务Socket对象 指定端口号
		ServerSocket ss = new ServerSocket(9999);
		// 绑定客户端的Socket
		Socket s = ss.accept();

		// 得到客户端的IP地址
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "--- 已连接");

		// 读取客户端发来的信息的读取流
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// 定义服务端对客户端的反馈信息的写入流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		String line = null;

		while ((line = br.readLine()) != null) {
			System.out.println("客户端： " + line);
			// 同样的道理 这里一定要换行结束 和 刷新
			bw.write(line.toUpperCase());
			bw.newLine();
			bw.flush();
		}
		s.close();
		ss.close();
	}

}