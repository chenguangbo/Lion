package cn.com.trs.serurity;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class TRS_Security_AES_JDk {

	private static String str = "I LOVE YOU";
	
	public static void main(String[] args) {
		jdkAES();
	}
	public static void jdkAES(){
		
		try {
			//����key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBests = secretKey.getEncoded();
		
			//keyת��
			Key key = new SecretKeySpec(keyBests, "AES");
			
			//����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("JDK����:"+Base64.encodeBase64String(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal2 = cipher.doFinal(result);
			System.out.println("JDK����:"+new String(doFinal2));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
