package xr_net_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.DefaultDesktopManager;

/*
 * ����һ��UDP���ն�  ���ڽ�������
 * 
 * �����ȿ����ն�  �����Ͷ˵����ݻᶪʧ
 * 
 * */
public class UdpReceiveTest {

	public static void main(String[] args) throws Exception {

		// ����UDP���� ָ���˿ں� �˿ںű����뷢�Ͷ˶˿ں�һ��
		DatagramSocket ds = new DatagramSocket(8023);

		// �����ֽ����� ���ڴ�������
		byte[] buf = new byte[1024];

		// �����ݴ������ݰ���
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		// ��������
		ds.receive(dp);

		// �õ����� ����ӡ������̨
		String ip = dp.getAddress().toString();
		String data = new String(dp.getData(), 0, dp.getLength());
		int port = dp.getPort();
		System.out.println("IP Address : " + ip + "  Data : " + data + "  Port : " + port);

		// �ر���Դ
		ds.close();

	}

}
