package xr_net_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 利用TCP 实现简单的并发登陆功能
 * 
 * 用户不存在 时 返回用户名错误 只能登陆三次
 * 
 * 用户存在 返回 登录成功
 * 
 * 
 * 客户端 
 * */
public class TcpLoginClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {

		// 创建Socekt对象 指定主机 和 端口号
		Socket s = new Socket(InetAddress.getLocalHost(), 10005);

		// 接收键盘录入的信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 将数据发送到服务端
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		// 接收服务端发来的信息
		BufferedReader buf = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// 只能尝试登陆三次
		for (int i = 0; i < 3; i++) {
			// 读一行 如果为空 则跳出
			String line = br.readLine();
			if (line == null) {
				break;
			}
			// 不为空 则发送到服务端 检测是否存在用户
			pw.println(line);

			// 获取服务端发来的信息 如果包含“成功”字样 则打印成功
			String str = buf.readLine();
			if (str.contains("成功")) {
				System.out.println("登录提示:  " + str);
			}
		}

		br.close();
		s.close();
	}
}
