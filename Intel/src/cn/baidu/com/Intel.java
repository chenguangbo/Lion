package cn.baidu.com;

import java.net.InetAddress;

public class Intel {

	public static void main(String[] args) {
		try {
			// byte[] bye = new byte[]{(byte)192,(byte)168,0,(byte)100};
			// InetAddress address =
			// InetAddress.getByAddress(bye);//根据ip获取链接地址对象
			// System.out.println(address);
			InetAddress localHost = InetAddress.getLocalHost();// 获取本机地址对象
			byte[] address = localHost.getAddress(); // 获取原始IP
			InetAddress byAddress = InetAddress.getByAddress(address); // 根据给定原始ip获取地址对象
			// boolean equals = localHost.equals(byAddress);

			// InetAddress byName = InetAddress.getByName("PC-c");
			// System.out.println(byName.equals(byAddress)); //true
			// String canonicalHostName = byAddress.getCanonicalHostName();
			// //PC-c
			// System.out.println(canonicalHostName);

			InetAddress localHost2 = InetAddress.getLocalHost();
			String hostAddress = localHost2.getHostAddress();
			System.out.println(hostAddress);
			String hostName = localHost2.getHostName();
			System.out.println(hostName);

			String string = localHost2.toString();
			System.err.println(string);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
