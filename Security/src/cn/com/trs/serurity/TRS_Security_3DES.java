package cn.com.trs.serurity;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;

public class TRS_Security_3DES {

	public static void main(String[] args) {
		JDK3DES();
	}
	
	private static String aaa ="I LOVE YOU";
	
	public static void JDK3DES(){
		
		try {
			//生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(168);
//			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encoded = secretKey.getEncoded();
			
			//转换key
			DESedeKeySpec desKeySpec = new DESedeKeySpec(encoded);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			Key convertGenerateSecret = factory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertGenerateSecret);
			byte[] doFinal = cipher.doFinal(aaa.getBytes());
			System.out.println("JDk版本的3DES加密结果:" + Hex.encodeHexString(doFinal));
			
			//解密 
			cipher.init(Cipher.DECRYPT_MODE, convertGenerateSecret);
			byte[] resource = cipher.doFinal(doFinal);
			
			System.out.println("JDK版本的3DES解密结果:" + new String(resource));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
