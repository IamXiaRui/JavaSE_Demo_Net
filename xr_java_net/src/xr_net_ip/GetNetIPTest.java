package xr_net_ip;

/*
 * ͨ��IP�����ȡIP��ַ �� ��������
 * */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetNetIPTest {

	// ����Ҫ�׳��쳣
	public static void main(String[] args) throws UnknownHostException {
		// ������������
		InetAddress ia = InetAddress.getByName("www.baidu.com");
		// �õ�����IP��ַ
		Sop("IP Address : " + ia.getHostAddress());
		Sop("Host Name : " + ia.getHostName());

	}

	public static void Sop(Object obj) {
		System.out.println(obj);
	}
}
