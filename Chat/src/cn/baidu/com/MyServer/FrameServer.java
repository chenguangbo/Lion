package cn.baidu.com.MyServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameServer {
	private ServerSocket ss;

	public static void main(String[] args) throws IOException {
		new FrameServer().start();
	}

	public void start() throws IOException {
		System.out.println("我正在监听端口1314");
		ss = new ServerSocket(1314);
		System.out.println("一个客户端连接到了服务器(我)    IP地址为:" + InetAddress.getLocalHost().getHostAddress());
		while (true) {   //定义循环接受客户端联机
			Socket s = ss.accept(); //接受服务器端的链接
			System.out.println("有一个客户端连接");
			Client client = new Client(s);//创建执行执行类
			new Thread(client).start();//分配线程执行当前这个链接的数据

		}

	}

	class Client implements Runnable {

		private Socket s;
		private InputStream in;

		public Client(Socket s) {
			this.s = s;
			try {
				in = s.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) != -1) {
					System.out.println(new String(b, 0, i));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
