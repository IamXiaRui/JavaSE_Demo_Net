package xr_net_ip;

/*
 * 通过IP对象获取IP地址 和 主机名称
 * */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetNetIPTest {

	// 必须要抛出异常
	public static void main(String[] args) throws UnknownHostException {
		// 填入主机名称
		InetAddress ia = InetAddress.getByName("www.baidu.com");
		// 得到主机IP地址
		Sop("IP Address : " + ia.getHostAddress());
		Sop("Host Name : " + ia.getHostName());

	}

	public static void Sop(Object obj) {
		System.out.println(obj);
	}
}
