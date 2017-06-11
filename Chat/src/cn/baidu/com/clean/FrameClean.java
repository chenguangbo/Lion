package cn.baidu.com.clean;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FrameClean extends java.awt.Frame {

	private static final long serialVersionUID = 1L;
	private TextArea ta = new TextArea();
	private TextField tf = new TextField();

	public static void main(String[] args) {
		new FrameClean().showFrame();
	}

	// 显示窗口
	public void showFrame() {
		setLocation(450, 300);// 相对屏幕位置 左右 , 上下
		this.setSize(100, 300);
		add(tf, BorderLayout.SOUTH);// 添加输入框
		add(ta, BorderLayout.NORTH);// 添加下方输入框
		pack();// 压紧 去除中间的空白部分
		this.addWindowListener(new WindowAdapter() {// 关闭功能按钮
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		tf.addActionListener(new TextAreaListener());// 添加回车事件监听器
		setVisible(true);// 是否显示
		connect();
	}

	// 链接服务器方法
	public void connect() {

		try {
			InetAddress localHost = InetAddress.getLocalHost();
			Socket s = new Socket(localHost,1314);
			System.out.println("服务器连接成功");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 创建监听器内部类
	private class TextAreaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = tf.getText();// 获取下方输入框中的值
			ta.setText(text);// 将下方的输入框文字添加到上方
			tf.setText("");
		}
	}
}
