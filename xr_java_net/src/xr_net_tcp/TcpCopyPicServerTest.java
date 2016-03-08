package xr_net_tcp;

import java.io.File;
import java.io.FileNotFoundException;
/*
 * ����TCP  ʵ��ͼƬ�ĸ���
 * 
 * �����   ��������ͻ�������
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

		while (true) {
			// �󶨿ͻ��˵�Socket
			Socket s = ss.accept();

			// ������������ͻ�������ķ����
			new Thread(new PicServer(s)).start();
		}
	}
}

class PicServer implements Runnable {

	private Socket s;

	public PicServer(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {

		int count = 1;
		try {

			File file = new File("E:\\AllTestFile\\TcpCopyPic-" + count + ".png");

			while (file.exists()) {
				file = new File("E:\\AllTestFile\\TcpCopyPic-" + (count++) + ".png");
			}
			// ���ݶ�ȡ��
			InputStream is = s.getInputStream();
			// ����ȡ������д���ļ���
			FileOutputStream fo = new FileOutputStream(file);

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

		} catch (FileNotFoundException e)

		{
			System.out.println(e.toString());
		} catch (IOException e)

		{
			System.out.println(e.toString());
		}
	}
}
