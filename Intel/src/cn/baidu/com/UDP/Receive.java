package cn.baidu.com.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receive {

	public static void main(String[] args) {

		try {
			byte[] bs = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bs, bs.length);// 创建接收的容器

			// 创建接收的人并告诉他在那个路口看着 监控本机端口
			DatagramSocket ds = new DatagramSocket(9999);
			ds.receive(dp);// 接收数据
			byte[] data = dp.getData();//获取接收的数据
			System.out.println(new String(data));

			int i = dp.getLength();
			System.out.println(i);
			
			InetAddress address = dp.getAddress();//获取发送方的地址对象
			String add = address.getHostAddress();//获取ip地址
			System.out.println(add);//打印传输方的地址
			int port = dp.getPort();//计算机自动分配的
			System.out.println(port+"----"+ds.getLocalPort()+"-----"+ds.getPort());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
