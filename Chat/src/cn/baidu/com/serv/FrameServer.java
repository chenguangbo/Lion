package cn.baidu.com.serv;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameServer {

	boolean started = false;
	ServerSocket ss = null;

	public static void main(String[] args) {
		new FrameServer().start();
	}

	public void start() {

		try {
			ss = new ServerSocket(1314);
		} catch (BindException e) {
			System.out.println("端口正在使用中...");
			System.out.println("清关的程序并重新运行服务器!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			started = true;
			while (started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("一个客户端连接到了服务器(我)    IP地址为:" + InetAddress.getLocalHost().getHostAddress());
				new Thread(c).start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	class Client implements Runnable {

		private Socket s;
		private DataInputStream dis = null;
		private boolean bConnected = false;
		private InputStream in = null ;
		public Client(Socket s) {
			this.s = s;
			try {
				in = s.getInputStream();
				dis = new DataInputStream(s.getInputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			try {
				while (bConnected) {
					System.out.println("正在接收"+InetAddress.getLocalHost().getHostAddress()+"  传过来的参数");
//					String str = dis.readUTF();
					byte[] b = new byte[1024];
					int i = 0 ;
					while ((i=in.read(b))!=-1) {
						System.out.println(new String(b,0,i));
					}
//					in.read(b);
//					System.out.println(str);
				}

			} catch (EOFException e) {  //socket链接断开异常
				System.out.println("客户端连接断开!");
			} catch (IOException e) {
				System.out.println("catch里面的东西走没走?");
				e.printStackTrace();
			} finally {
				try {
					if (dis != null) {
						dis.close();
						System.out.println("服务器端的流断开!");
					}
					if (s != null) {
						s.close();
						System.out.println("socket关闭");
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("出现异常了!");
				}
			}

		}

	}

}
