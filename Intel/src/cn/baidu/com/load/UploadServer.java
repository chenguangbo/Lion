package cn.baidu.com.load;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1314);
			Socket socket = ss.accept();
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			byte[] b = new byte[1024];
			int i = 0;
			FileOutputStream fileOut = new FileOutputStream("1.txt");
			while ((i=in.read(b))!=-1) {
				System.out.println(new String(b,0,i));
				fileOut.write(b, 0, i);
			}
			
			out.write("我收到信息了".getBytes());
			out.flush();
			ss.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
