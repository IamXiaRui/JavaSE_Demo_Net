package xr_net_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * ����TCP ʵ�ּ򵥵Ĳ�����½����
 * 
 * �û������� ʱ �����û������� ֻ�ܵ�½����
 * 
 * �û����� ���� ��¼�ɹ�
 * 
 * 
 * �ͻ��� 
 * */
public class TcpLoginClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {

		// ����Socekt���� ָ������ �� �˿ں�
		Socket s = new Socket(InetAddress.getLocalHost(), 10005);

		// ���ռ���¼�����Ϣ
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// �����ݷ��͵������
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		// ���շ���˷�������Ϣ
		BufferedReader buf = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// ֻ�ܳ��Ե�½����
		for (int i = 0; i < 3; i++) {
			// ��һ�� ���Ϊ�� ������
			String line = br.readLine();
			if (line == null) {
				break;
			}
			// ��Ϊ�� ���͵������ ����Ƿ�����û�
			pw.println(line);

			// ��ȡ����˷�������Ϣ ����������ɹ������� ���ӡ�ɹ�
			String str = buf.readLine();
			if (str.contains("�ɹ�")) {
				System.out.println("��¼��ʾ:  " + str);
			}
		}

		br.close();
		s.close();
	}
}
