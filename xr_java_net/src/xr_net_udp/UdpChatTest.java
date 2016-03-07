package xr_net_udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * 利用UDP 实现简单的QQ聊天
 * 
 * 因为要实现一个控制台的发送和接收  所以必须通过两个线程来完成
 * */
public class UdpChatTest {
	public static void main(String[] args) throws Exception {

		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receiveSocket = new DatagramSocket(8024);

		// 线程开始
		new Thread(new ChatSend(sendSocket)).start();
		new Thread(new ChatReceive(receiveSocket)).start();
	}
}

// 发送类 实现线程接口
class ChatSend implements Runnable {
	private DatagramSocket ds;

	// 传递的是UDP的Socket参数
	public ChatSend(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			// 创建缓冲区 录入键盘数据
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while ((line = br.readLine()) != null) {
				if ("再见".equals(line)) {
					System.out.println("聊天结束");
					break;
				}
				byte[] buf = line.getBytes();
				// 指定了端口号为8024
				DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 8024);
				ds.send(dp);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

// 接收类 同样实现了线程接口
class ChatReceive implements Runnable {
	private DatagramSocket ds;

	// 参数依然是Socket
	public ChatReceive(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			// 循环接收发送端的数据
			while (true) {
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ds.receive(dp); // 阻塞式语句
				// 得到聊天消息
				String ip = dp.getAddress().getHostAddress();
				String data = new String(dp.getData(), 0, dp.getLength());
				System.out.println("IP Address : " + ip + "  Data : " + data);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
