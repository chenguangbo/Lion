package cn.baidu.com.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameServer {

	public static void main(String[] args) {

		new FrameServer().start();
	}

	boolean started = false;

	public void start() {
		Socket socket = null;
		ServerSocket ss = null;
		try {
			while (true) {
				try {
					ss = new ServerSocket(1314);
					started = true;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("服务器已启动!");
				}
				socket = ss.accept();
				System.out.println("有一个clean链接:" + socket.getLocalAddress().getHostAddress());
				while (started) {
					Client client = new Client(socket);
					new Thread(client).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public class Client implements Runnable {

		private Socket socket;
		private InputStream in;

		public Client(Socket s) {
			try {
				this.socket = s;
				in = socket.getInputStream();
				started = false ;
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
				try {
					socket.close();
					Thread thread = Thread.currentThread();
					thread.interrupt();// 终止当前线程
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						socket.close();
						Thread thread = Thread.currentThread();
						thread.interrupt();// 终止当前线程
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
		}

	}
}
