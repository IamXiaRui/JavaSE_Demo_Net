package xr_net_tcp;

/*
 * ʹ��TCP�����ļ�  
 * 
 * �ͻ���
 * 
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpCopyFileClientTest {

	public static void main(String[] args) throws Exception {

		// ����Socket���� ָ�������� �� �˿ں�
		Socket s = new Socket(InetAddress.getLocalHost(), 10000);

		// ָ���ļ���Ϊ��ȡ��
		BufferedReader br = new BufferedReader(new FileReader("E:\\AllTestFile\\CopyFileTest.txt"));

		// �����ݴ�����������
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		String line = null;

		while ((line = br.readLine()) != null) {
			pw.println(line);
		}

		s.shutdownOutput(); // ���������� ��ֹһֱ���ڵȴ�״̬
		// pw.println("886"); ���Ҳ�����ǽ������ ���ǿ��ܻ���ļ������ظ� ����ʹ��

		// ��ȡ����� ��������Ϣ
		BufferedReader buf = new BufferedReader(new InputStreamReader(s.getInputStream()));

		String str = buf.readLine();
		System.out.println(str);

		br.close();
		s.close();
	}

}
