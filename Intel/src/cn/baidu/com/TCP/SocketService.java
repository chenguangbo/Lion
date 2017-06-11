package cn.baidu.com.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {

	public static void main(String[] args) {

		try {
			ServerSocket ss = new ServerSocket(1314);
			Socket socket = ss.accept();// 获取客户端的连接对象

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] b = new byte[1024];
			int i = in.read(b);//这里一定要把   b  放里
			/* 千万别这么写       这么写     会出现死锁现象   至于为什么死锁    这里写不明白     要真想知道再找我吧 */
			StringBuffer sb = new StringBuffer();
//			while ( (i = in.read(b))!=-1) {
//				sb.append(new String(b,0,i));
//			}
//			System.out.println(sb.toString());
//			int j = 0;
//			while ((j=in.read(b))!=-1) {
//				System.out.println(new String(b,0,j));
//			}
			System.out.println(new String(b,0,i));
			//System.out.println(sb);
			out.write("I LIKE YOU TOO".getBytes());
			out.flush();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
