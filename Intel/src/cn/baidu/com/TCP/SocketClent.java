package cn.baidu.com.TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Socket(InetAddress address, int port)
 * 
 * @author chenguangbo
 *
 */

public class SocketClent {

	public static void main(String[] args) {

		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			Socket s = new Socket(inetAddress, 1314);
			OutputStream out = s.getOutputStream();// 获取输出路
			InputStream in = s.getInputStream();// 获取输入流
			
			
			out.write("I LIKE YOU".getBytes());
			out.flush();
			//发送完成
			//Thread.sleep(5000);
			//s.shutdownOutput();
			//接收服务端数据
			byte[] bs = new byte[1024];
			int read = in.read(bs);
			System.out.println(new String(bs,0,read));
			s.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
