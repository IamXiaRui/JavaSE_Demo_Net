package xr_net_tcp;

import java.io.File;
import java.io.FileNotFoundException;
/*
 * 利用TCP  实现图片的复制
 * 
 * 服务端   并发处理客户端请求
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

		while (true) {
			// 绑定客户端的Socket
			Socket s = ss.accept();

			// 创建并发处理客户端请求的服务端
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
			// 数据读取流
			InputStream is = s.getInputStream();
			// 将读取的数据写入文件中
			FileOutputStream fo = new FileOutputStream(file);

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

		} catch (FileNotFoundException e)

		{
			System.out.println(e.toString());
		} catch (IOException e)

		{
			System.out.println(e.toString());
		}
	}
}
