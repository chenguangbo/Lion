package cn.baidu.com.clean;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;

public class FrameClean extends java.awt.Frame {

	private static final long serialVersionUID = 1L;

	private TextArea ta = new TextArea();
	private TextField tf = new TextField();
	
	public static void main(String[] args) {
		
		new FrameClean().showFrame();
		
	}

	public void showFrame() {
		setLocation(450, 300);//相对屏幕位置     左右  , 上下
		this.setSize(100, 300); 
		add(tf,BorderLayout.SOUTH);
		add(ta, BorderLayout.NORTH);
		pack();//压紧       去除中间的空白部分   
		setVisible(true);
		
		
	}

}
