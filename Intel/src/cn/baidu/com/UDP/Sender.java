package cn.baidu.com.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DatagramPacket(byte[] buf, int length, InetAddress address, int port) 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
 * 
 * 
 * 
 * @author chenguangbo
 *
 */
public class Sender {

	public static void main(String[] args) {

		try {
			String str = "I LIKE YOU"; //准备发送的数据
			byte[] bs = str.getBytes();//转换传输要的格式
			InetAddress localHost = InetAddress.getLocalHost();//获取接收方的地址对象
			DatagramPacket dp = new DatagramPacket(bs, bs.length, localHost, 9999);//将所有信息打包
			//创建发送方对象
			DatagramSocket ds = new DatagramSocket();
			ds.send(dp);    //发送准备好的要发送的包
			System.out.println("发送方发送完毕");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}

}
