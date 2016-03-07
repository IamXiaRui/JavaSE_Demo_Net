package xr_net_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.DefaultDesktopManager;

/*
 * 定义一个UDP接收端  用于接收数据
 * 
 * 必须先开接收端  否则发送端的数据会丢失
 * 
 * */
public class UdpReceiveTest {

	public static void main(String[] args) throws Exception {

		// 创建UDP服务 指定端口号 端口号必须与发送端端口号一致
		DatagramSocket ds = new DatagramSocket(8023);

		// 创建字节数组 用于存入数据
		byte[] buf = new byte[1024];

		// 将数据存入数据包中
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		// 接收数据
		ds.receive(dp);

		// 得到数据 并打印至控制台
		String ip = dp.getAddress().toString();
		String data = new String(dp.getData(), 0, dp.getLength());
		int port = dp.getPort();
		System.out.println("IP Address : " + ip + "  Data : " + data + "  Port : " + port);

		// 关闭资源
		ds.close();

	}

}
