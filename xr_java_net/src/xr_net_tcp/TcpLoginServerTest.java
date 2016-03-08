package xr_net_tcp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 利用TCP 实现简单的并发登陆功能
 * 
 * 服务端
 * */
public class TcpLoginServerTest {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(10005);

		// 循环 并发处理客户端的问题
		while (true) {
			Socket s = ss.accept();
			new Thread(new LoginServer(s)).start();
		}
	}

}

// 服务端线程
class LoginServer implements Runnable {

	private Socket s;

	public LoginServer(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {

		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + " - 已连接");

		// 循环处理三次
		try {
			for (int i = 0; i < 3; i++) {

				// 接收客户端发来的信息
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

				// 接收一行 读取一行 若为空 则直接跳出
				String name = br.readLine();
				if (name == null) {
					break;
				}

				// 读取本地的用户信息
				BufferedReader buf = new BufferedReader(new FileReader("E:\\AllTestFile\\name.txt"));

				// 发送到客户端的信息
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

				String line = null;

				// 用户名检测 匹配标志位
				boolean flag = false;

				while ((line = buf.readLine()) != null) {
					// 如果匹配到相同的用户 标志位为真 跳出
					if (line.equals(name)) {
						flag = true;
						break;
					}
				}

				// 匹配结果处理 反馈给客户端
				if (flag) {
					System.out.println(name + " - 已登录");
					pw.println(name + " - 登录成功");
					break;
				} else {
					System.out.println(name + " - 尝试登陆");
					pw.println(name + " - 用户不存在");
				}
			}
			s.close();
		} catch (Exception e) {
			System.out.println("登录失败");
		}
	}

}
