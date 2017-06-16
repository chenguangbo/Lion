package cn.baidu.com.clean;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FrameClean extends java.awt.Frame {

	private static final long serialVersionUID = 1L;
	private TextArea ta = new TextArea();
	private TextField tf = new TextField();

	private Socket socket;
	// private OutputStream out;
	private DataOutputStream out;
	private InputStream in;

	public static void main(String[] args) {
		new FrameClean().showFrame();
	}

	// 显示窗口
	public void showFrame() {
		setLocation(450, 300);// 相对屏幕位置 左右 , 上下
		this.setSize(100, 300);
		add(tf, BorderLayout.SOUTH);// 下方的框
		add(ta, BorderLayout.NORTH);// 上面的框
		pack();// 压紧 去除中间的空白部分
		this.addWindowListener(new WindowAdapter() {// 关闭功能按钮
			@Override
			public void windowClosing(WindowEvent e) {
				disConnect();
				System.exit(0);// 关闭窗口
			}
		});
		tf.addActionListener(new TextAreaListener());// 添加回车事件监听器
		setVisible(true);// 是否显示窗口
		connect();// 链接Socket

		Clean c = new Clean(socket);
		new Thread(c).start();
	}

	// 释放资源方法
	public void disConnect() {
		try {
			socket.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 链接服务器方法
	public void connect() {

		try {
			InetAddress localHost = InetAddress.getLocalHost();
			socket = new Socket(localHost, 1314); // 如果socket创建成功怎表示 三次握手完成
													// (已经连接到服务器)
			out = new DataOutputStream(socket.getOutputStream());
			in = socket.getInputStream();
			System.out.println("我成功链接到了服务器!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 创建监听器内部类 回车显示到上面的栏目
	private class TextAreaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String text = tf.getText();// 获取下方输入框中的值
				//ta.setText(socket.getLocalSocketAddress() + "说:" + text);// 将下方的输入框文字添加到上方
				tf.setText("");
				System.out.println(text);
				out.write(text.getBytes());// 向服务器发送输入的数据
				out.flush();// 刷新流

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

	class Clean implements Runnable {

		private Socket s;

		public Clean(Socket s) {
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
				int i = 0;
				byte[] b = new byte[1024];
				while ((i = in.read(b)) != -1) {
					String take = new String(b, 0, i);
					System.out.println("从服务器端接收到" + take);
					ta.setText(take);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 定义接受服务端信息的方法
		public void take() {
			try {
				int i = 0;
				byte[] b = new byte[1024];
				while ((i = in.read(b)) != -1) {
					String take = new String(b, 0, i);
					System.out.println("从服务器端接收到" + take);
					ta.setText(take);
					tf.setText(take);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
