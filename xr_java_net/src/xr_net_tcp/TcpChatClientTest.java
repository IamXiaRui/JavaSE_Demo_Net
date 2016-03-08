package xr_net_tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ����TCP ʵ�ּ�QQ����Ĺ���
 * �ͻ���  
 * */
public class TcpChatClientTest {

	public static void main(String[] args) throws Exception {

		// ����Socket���� ָ������ �� �˿ں�
		Socket s = new Socket(InetAddress.getLocalHost(), 9999);

		// ��ȡ����¼��Ķ�ȡ��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// ������д�뵽Socket����� ���͸������
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		// ���շ���˷��ص���Ϣ�Ķ�ȡ��
		BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));

		String line = null;

		while ((line = br.readLine()) != null) {
			if ("�ټ�".equals(line)) {
				System.out.println("�������");
				break;
			}
			// �����ݷ��͵������
			bufw.write(line);
			// ע�� һ��Ҫ���뻻�н����� һ��Ҫˢ�»����� ��Ϊ�˷���������ʽ���� �ᵼ�³���һֱ�ȴ�
			bufw.newLine();
			bufw.flush();

			// Ҳ���� ʹ�� PrintReader �� PrintWriter ʡ���˻��з� �� ˢ�� println()�����Դ����к�ˢ��

			String str = bufr.readLine();
			System.out.println("����� �� " + str);

		}
		br.close();
		s.close();
	}

}
