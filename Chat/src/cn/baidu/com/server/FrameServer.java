package cn.baidu.com.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameServer {

	public static void main(String[] args) {

		try {
			while (true) {
				ServerSocket ss = new ServerSocket(1314);
				Socket socket = ss.accept();
				System.out.println("有一个clean链接");
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
