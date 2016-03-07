package xr_net_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * 定义一个UDP发送端  用于发送数据
 * 
 *  必须先开接收端  否则发送端的数据会丢失
 * */
public class UdpSendTest {

	// 记得一定要抛出异常
	public static void main(String[] args) throws Exception {

		// 创建一个UDP服务 可以直接传递端口号
		DatagramSocket ds = new DatagramSocket();

		// 提供数据
		byte[] buf = " i am Sender".getBytes();

		// 将数据封装到数据包中
		// DatagramPacket dp = new DatagramPacket(buf, buf.length,
		// InetAddress.getByName("183.161.57.117"), 8023);
		DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 8023);

		// 发送数据
		ds.send(dp);

		// 关闭资源
		ds.close();
	}

}
