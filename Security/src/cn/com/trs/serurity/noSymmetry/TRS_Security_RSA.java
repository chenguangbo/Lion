package cn.com.trs.serurity.noSymmetry;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.tomcat.util.codec.binary.Base64;

public class TRS_Security_RSA {

	private static String str = "I LOVE YOU";

	public static void main(String[] args) {

		JDKRSA();
		
	}

	public static void JDKRSA() {

		try {
			
			//1.��ʼ�� ���ͷ���Կ
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
			RSAPrivateKey  rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
			System.out.println("��Կ:  " + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
			System.out.println("˽Կ:  " + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
			
			//2.˽Կ����      ��Կ����     ---- ����
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE,privateKey);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("˽Կ����/��Կ����----����:" + Base64.encodeBase64String(result));
			
			//3.˽Կ����/��Կ���� ---- ����
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(result);
			System.out.println("˽Կ����/��Կ���� ---- ����: " + new String(result));
			
			
			
			
			// 4.��Կ����/˽Կ���� ---- ����
			X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
			PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
			Cipher cipher2 = Cipher.getInstance("RSA");
			cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
			byte[] result2 = cipher2.doFinal(str.getBytes());
			System.out.println("��Կ����/˽Կ����  ---- ����: " + new String(result2));
			
			//5.˽Կ����/��Կ����  ----  ����
			PKCS8EncodedKeySpec pcks8EncodeKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory5 = KeyFactory.getInstance("RSA");
			PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher5 = Cipher.getInstance("RSA");
			cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
			byte[] result5 = cipher5.doFinal(result2);
			System.out.println("��Կ����/˽Կ���� ---- ���� :" +  new String(result5));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
