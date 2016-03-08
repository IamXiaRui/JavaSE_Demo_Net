package xr_net_tcp;

/*
 * 使用TCP复制文件
 * 
 * 服务端
 * */
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpCopyFileServerTest {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(10000);

		Socket s = ss.accept();

		// 得到客户端的IP地址
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "--- 已连接");

		// 读取客户端传来的数据
		BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// 将客户端传来的数据 写入文件中
		PrintWriter pw = new PrintWriter(new FileWriter("E:\\AllTestFile\\TcpCopyFile.txt"), true);

		String line = null;

		while ((line = bf.readLine()) != null) {
			/*
			 * if ("886".equals(line)) { System.out.println("复制结束"); break; }
			 * 如果是自定义结束标记的话 就可以这样判断
			 */
			pw.println(line);
		}

		// 反馈信息给客户端
		PrintWriter prw = new PrintWriter(s.getOutputStream(), true);
		prw.println("上传成功，请查收");
	}

}
