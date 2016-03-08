package xr_net_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/*
 * ����TCP ʵ�ֿͻ������ݷ��͹���
 * 
 * ��Ҫ�ȿ������  ����ͻ��˽��޷���������
 * */
public class TcpClientTest {

	public static void main(String[] args) throws Exception {
		// ����Socket���� ָ�������� �� �˿ں�
		Socket s = new Socket(InetAddress.getLocalHost(), 8025);

		// �õ�������
		OutputStream out = s.getOutputStream();
		out.write("i am xiarui".getBytes());

		// ���շ���� ������������Ϣ��
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int length = in.read(buf);

		System.out.println(new String(buf, 0, length));

		s.close();

	}

}
