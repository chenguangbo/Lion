package cn.com.trs.serurity;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TRS_Security {

	private static String str = "I Love You ,You Too Love Me";

	public static void main(String[] args) {
		// jdkBase64();
		// jdkBase();
		// commonsBase64();
		// bcBase64();
		bcJieMi();

	}

	public static void jdkBase64() {
		byte[] decode = Base64.decode(str);
		System.out.println(decode);
		String encode = Base64.encode(decode);
		System.out.println(encode);
	}

	public static void jdkBase() {
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			String encode = encoder.encode(str.getBytes());
			System.out.println("encode:" + encode);

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decode = decoder.decodeBuffer(encode);
			System.out.println("decode:" + new String(decode));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void commonsBase64() {
		byte[] encodeBase64 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(str.getBytes());
		byte[] decodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(encodeBase64);

		System.out.println("解密：" + new String(decodeBase64));
		System.out.println("加密：" + new String(encodeBase64));

	}

	public static void bcBase64() {

		byte[] encode = org.bouncycastle.util.encoders.Base64.encode(str.getBytes());
		System.out.println("加密：" + new String(encode));
		byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
		System.out.println("解密：" + new String(decode));

	}

	public static void bcJieMi() {
		byte[] decode = org.bouncycastle.util.encoders.Base64
				.decode("SSBMb3ZlIFlvdSAsWW91IFRvbyBMb3ZlIE1l".trim().getBytes());
		System.err.println(new String(decode));
	}

}
