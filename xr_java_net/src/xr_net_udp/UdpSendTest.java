package xr_net_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * ����һ��UDP���Ͷ�  ���ڷ�������
 * 
 *  �����ȿ����ն�  �����Ͷ˵����ݻᶪʧ
 * */
public class UdpSendTest {

	// �ǵ�һ��Ҫ�׳��쳣
	public static void main(String[] args) throws Exception {

		// ����һ��UDP���� ����ֱ�Ӵ��ݶ˿ں�
		DatagramSocket ds = new DatagramSocket();

		// �ṩ����
		byte[] buf = " i am Sender".getBytes();

		// �����ݷ�װ�����ݰ���
		// DatagramPacket dp = new DatagramPacket(buf, buf.length,
		// InetAddress.getByName("183.161.57.117"), 8023);
		DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 8023);

		// ��������
		ds.send(dp);

		// �ر���Դ
		ds.close();
	}

}
