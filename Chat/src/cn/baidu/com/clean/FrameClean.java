package cn.baidu.com.clean;

public class FrameClean extends java.awt.Frame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		new FrameClean().showFrame();
		
	}

	public void showFrame() {
		setLocation(400, 300);
		this.setSize(300, 300);
		setVisible(true);

	}

}
