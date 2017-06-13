package cn.com.trs.serurity;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;

public class TRS_Security_DES {

	public static void main(String[] args) {
		JDKDES();
	}
	
	private static String aaa ="I LOVE YOU";
	
	public static void JDKDES(){
		
		try {
			//����key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encoded = secretKey.getEncoded();
			
			//ת��key
			DESKeySpec desKeySpec = new DESKeySpec(encoded);
			SecretKeyFactory	factory = SecretKeyFactory.getInstance("DES");
			Key convertGenerateSecret = factory.generateSecret(desKeySpec);
			
			//����
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertGenerateSecret);
			byte[] doFinal = cipher.doFinal(aaa.getBytes());
			System.out.println("JDk�汾��DES���ܽ��:" + Hex.encodeHexString(doFinal));
			
			//���� 
			cipher.init(Cipher.DECRYPT_MODE, convertGenerateSecret);
			byte[] resource = cipher.doFinal(doFinal);
			
			System.out.println("JDK�汾��DES���ܽ��:" + new String(resource));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	
}
