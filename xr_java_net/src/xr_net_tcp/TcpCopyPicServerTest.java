package xr_net_tcp;

/*
 * 利用TCP  实现图片的复制
 * 
 * 服务端
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
		// 创建服务Socket对象 并指定端口号
		ServerSocket ss = new ServerSocket(10001);

		// 绑定客户端的Socket
		Socket s = ss.accept();
		// 数据读取流
		InputStream is = s.getInputStream();
		// 将读取的数据写入文件中
		FileOutputStream fo = new FileOutputStream("E:\\AllTestFile\\TcpCopyPic.png");

		// 读取得到的文件数据
		byte[] b = new byte[1024];

		int len = 0;

		while ((len = is.read(b)) != -1) {
			fo.write(b, 0, len);
		}

		// 发送给客户端的消息流
		OutputStream os = s.getOutputStream();

		os.write("复制成功".getBytes());

		fo.close();
		ss.close();
		s.close();
	}
}
