package xr_net_tcp;

/*
 * 使用TCP复制文件  
 * 
 * 客户端
 * 
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpCopyFileClientTest {

	public static void main(String[] args) throws Exception {

		// 创建Socket对象 指定主机名 和 端口号
		Socket s = new Socket(InetAddress.getLocalHost(), 10000);

		// 指定文件作为读取流
		BufferedReader br = new BufferedReader(new FileReader("E:\\AllTestFile\\CopyFileTest.txt"));

		// 将数据传送至服务器
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		String line = null;

		while ((line = br.readLine()) != null) {
			pw.println(line);
		}

		s.shutdownOutput(); // 定义结束标记 防止一直处于等待状态
		// pw.println("886"); 这个也可以是结束标记 但是可能会和文件内容重复 谨慎使用

		// 读取服务端 反馈的信息
		BufferedReader buf = new BufferedReader(new InputStreamReader(s.getInputStream()));

		String str = buf.readLine();
		System.out.println(str);

		br.close();
		s.close();
	}

}
