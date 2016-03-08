package xr_net_tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 利用TCP 实现简单QQ聊天的功能
 * 客户端  
 * */
public class TcpChatClientTest {

	public static void main(String[] args) throws Exception {

		// 创建Socket对象 指定主机 和 端口号
		Socket s = new Socket(InetAddress.getLocalHost(), 9999);

		// 读取键盘录入的读取流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 将数据写入到Socket输出流 发送给服务端
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		// 接收服务端返回的信息的读取流
		BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));

		String line = null;

		while ((line = br.readLine()) != null) {
			if ("再见".equals(line)) {
				System.out.println("聊天结束");
				break;
			}
			// 将数据发送到服务端
			bufw.write(line);
			// 注意 一定要加入换行结束符 一定要刷新缓冲区 因为此方法是阻塞式方法 会导致程序一直等待
			bufw.newLine();
			bufw.flush();

			// 也可以 使用 PrintReader 和 PrintWriter 省略了换行符 和 刷新 println()方法自带换行和刷新

			String str = bufr.readLine();
			System.out.println("服务端 ： " + str);

		}
		br.close();
		s.close();
	}

}
