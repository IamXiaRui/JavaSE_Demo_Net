package xr_net_server;

/*
 * �Զ�������
 * 
 * ��������д� http://IP : �˿ں�  ���ɷ���
 * 
 * */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerTest {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(10240);

		Socket s = ss.accept();

		System.out.println(s.getInetAddress().getHostAddress() + " - �Ѿ�����");

		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		pw.println("�Զ���ͻ���");

		s.close();

		ss.close();
	}

}
