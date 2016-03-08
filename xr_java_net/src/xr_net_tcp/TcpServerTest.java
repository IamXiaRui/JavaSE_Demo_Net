package xr_net_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ����TCP ʵ�ַ�������ݽ��չ���
 * 
 * */
public class TcpServerTest {

	public static void main(String[] args) throws Exception {
		// ��������˵�Socket ��ָ���ͻ��˵Ķ˿ں�
		ServerSocket ss = new ServerSocket(8025);

		// ��ȡ���ӹ����Ŀͻ��˶���
		Socket s = ss.accept();

		// �õ��ͻ��˷�����������Ϣ��
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int length = in.read(buf);

		System.out.println(new String(buf, 0, length));

		// ��ͻ��˷��ͷ�����Ϣ
		OutputStream out = s.getOutputStream();
		out.write("i am ruixia".getBytes());

		s.close(); // ���Թرտͻ����еĶ���
		ss.close();// ��ѡ���� һ��������˲��ر�

	}

}
