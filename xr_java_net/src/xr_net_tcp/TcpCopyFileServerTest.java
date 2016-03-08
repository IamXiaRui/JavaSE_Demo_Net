package xr_net_tcp;

/*
 * ʹ��TCP�����ļ�
 * 
 * �����
 * */
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpCopyFileServerTest {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(10000);

		Socket s = ss.accept();

		// �õ��ͻ��˵�IP��ַ
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "--- ������");

		// ��ȡ�ͻ��˴���������
		BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// ���ͻ��˴��������� д���ļ���
		PrintWriter pw = new PrintWriter(new FileWriter("E:\\AllTestFile\\TcpCopyFile.txt"), true);

		String line = null;

		while ((line = bf.readLine()) != null) {
			/*
			 * if ("886".equals(line)) { System.out.println("���ƽ���"); break; }
			 * ������Զ��������ǵĻ� �Ϳ��������ж�
			 */
			pw.println(line);
		}

		// ������Ϣ���ͻ���
		PrintWriter prw = new PrintWriter(s.getOutputStream(), true);
		prw.println("�ϴ��ɹ��������");
	}

}
