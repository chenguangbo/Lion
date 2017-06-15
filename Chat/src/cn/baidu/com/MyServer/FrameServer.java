package cn.baidu.com.MyServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FrameServer {
	private ServerSocket ss;
	private List<Socket> socket = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
		new FrameServer().start();
	}

	public void start() throws IOException {
		System.out.println("我正在监听端口1314");
		ss = new ServerSocket(1314);//监听本机1314端口
		System.out.println("一个客户端连接到了服务器(我)    IP地址为:" + InetAddress.getLocalHost().getHostAddress());
		while (true) { // 定义循环接受客户端联机
			//如果多线程   必须要循环接受客户端的链接
			Socket s = ss.accept(); // 接受服务器端的链接
			socket.add(s);
			System.out.println("有一个客户端连接");
			Client client = new Client(s);// 创建执行执行类
			new Thread(client).start();// 分配线程执行当前这个链接的数据

		}

	}

	/*
	 * 执行具体的 业务逻辑的类
	 */
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
					String read = new String(b, 0, i);
					System.out.println(read);// 在console中打印控制端传来的信息
					for (Socket c : socket) {// 变量socket集合 给所有在线用户发送客户端传来的信息
						OutputStream out = c.getOutputStream();// 获取输出流
						out.write(read.getBytes());// 向客户端发送数据
						out.flush();// 刷新流
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
