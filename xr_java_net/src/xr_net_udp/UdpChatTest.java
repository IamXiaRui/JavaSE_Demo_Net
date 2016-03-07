package xr_net_udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * ����UDP ʵ�ּ򵥵�QQ����
 * 
 * ��ΪҪʵ��һ������̨�ķ��ͺͽ���  ���Ա���ͨ�������߳������
 * */
public class UdpChatTest {
	public static void main(String[] args) throws Exception {

		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receiveSocket = new DatagramSocket(8024);

		// �߳̿�ʼ
		new Thread(new ChatSend(sendSocket)).start();
		new Thread(new ChatReceive(receiveSocket)).start();
	}
}

// ������ ʵ���߳̽ӿ�
class ChatSend implements Runnable {
	private DatagramSocket ds;

	// ���ݵ���UDP��Socket����
	public ChatSend(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			// ���������� ¼���������
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while ((line = br.readLine()) != null) {
				if ("�ټ�".equals(line)) {
					System.out.println("�������");
					break;
				}
				byte[] buf = line.getBytes();
				// ָ���˶˿ں�Ϊ8024
				DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 8024);
				ds.send(dp);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

// ������ ͬ��ʵ�����߳̽ӿ�
class ChatReceive implements Runnable {
	private DatagramSocket ds;

	// ������Ȼ��Socket
	public ChatReceive(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			// ѭ�����շ��Ͷ˵�����
			while (true) {
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ds.receive(dp); // ����ʽ���
				// �õ�������Ϣ
				String ip = dp.getAddress().getHostAddress();
				String data = new String(dp.getData(), 0, dp.getLength());
				System.out.println("IP Address : " + ip + "  Data : " + data);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
