package xr_net_tcp;

/*
 * ����TCP  ʵ��ͼƬ�ĸ���
 * 
 * �����
 * 
 * */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpCopyPicServerTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// ��������Socket���� ��ָ���˿ں�
		ServerSocket ss = new ServerSocket(10001);

		// �󶨿ͻ��˵�Socket
		Socket s = ss.accept();
		// ���ݶ�ȡ��
		InputStream is = s.getInputStream();
		// ����ȡ������д���ļ���
		FileOutputStream fo = new FileOutputStream("E:\\AllTestFile\\TcpCopyPic.png");

		// ��ȡ�õ����ļ�����
		byte[] b = new byte[1024];

		int len = 0;

		while ((len = is.read(b)) != -1) {
			fo.write(b, 0, len);
		}

		// ���͸��ͻ��˵���Ϣ��
		OutputStream os = s.getOutputStream();

		os.write("���Ƴɹ�".getBytes());

		fo.close();
		ss.close();
		s.close();
	}
}
