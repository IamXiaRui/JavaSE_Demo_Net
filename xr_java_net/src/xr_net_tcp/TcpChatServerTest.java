package xr_net_tcp;

/*
 * ����TCP ʵ�ּ�QQ����Ĺ���
 * �����
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServerTest {
	public static void main(String[] args) throws Exception {
		// ����һ������Socket���� ָ���˿ں�
		ServerSocket ss = new ServerSocket(9999);
		// �󶨿ͻ��˵�Socket
		Socket s = ss.accept();

		// �õ��ͻ��˵�IP��ַ
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "--- ������");

		// ��ȡ�ͻ��˷�������Ϣ�Ķ�ȡ��
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// �������˶Կͻ��˵ķ�����Ϣ��д����
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		String line = null;

		while ((line = br.readLine()) != null) {
			System.out.println("�ͻ��ˣ� " + line);
			// ͬ���ĵ��� ����һ��Ҫ���н��� �� ˢ��
			bw.write(line.toUpperCase());
			bw.newLine();
			bw.flush();
		}
		s.close();
		ss.close();
	}

}