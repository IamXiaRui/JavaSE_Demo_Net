package xr_net_tcp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ����TCP ʵ�ּ򵥵Ĳ�����½����
 * 
 * �����
 * */
public class TcpLoginServerTest {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(10005);

		// ѭ�� ��������ͻ��˵�����
		while (true) {
			Socket s = ss.accept();
			new Thread(new LoginServer(s)).start();
		}
	}

}

// ������߳�
class LoginServer implements Runnable {

	private Socket s;

	public LoginServer(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {

		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + " - ������");

		// ѭ����������
		try {
			for (int i = 0; i < 3; i++) {

				// ���տͻ��˷�������Ϣ
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

				// ����һ�� ��ȡһ�� ��Ϊ�� ��ֱ������
				String name = br.readLine();
				if (name == null) {
					break;
				}

				// ��ȡ���ص��û���Ϣ
				BufferedReader buf = new BufferedReader(new FileReader("E:\\AllTestFile\\name.txt"));

				// ���͵��ͻ��˵���Ϣ
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

				String line = null;

				// �û������ ƥ���־λ
				boolean flag = false;

				while ((line = buf.readLine()) != null) {
					// ���ƥ�䵽��ͬ���û� ��־λΪ�� ����
					if (line.equals(name)) {
						flag = true;
						break;
					}
				}

				// ƥ�������� �������ͻ���
				if (flag) {
					System.out.println(name + " - �ѵ�¼");
					pw.println(name + " - ��¼�ɹ�");
					break;
				} else {
					System.out.println(name + " - ���Ե�½");
					pw.println(name + " - �û�������");
				}
			}
			s.close();
		} catch (Exception e) {
			System.out.println("��¼ʧ��");
		}
	}

}
