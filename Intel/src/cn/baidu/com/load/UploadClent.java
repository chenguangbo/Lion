package cn.baidu.com.load;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UploadClent {

	public static void main(String[] args) {

		try {
			
			FileInputStream fileIn = new FileInputStream(new File("C:\\Users\\chenguangbo\\Desktop\\maven_ssh.txt"));
			
			InetAddress host = InetAddress.getLocalHost();
			Socket s = new Socket(host,1314);
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i=fileIn.read(b))!=-1) {
				out.write(b, 0, i);
				out.flush();
			}
			s.shutdownOutput();
			int read = in.read(b);
			System.out.println(new String(b));
			s.close();
			fileIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
