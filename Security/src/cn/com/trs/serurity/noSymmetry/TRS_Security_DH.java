package cn.com.trs.serurity.noSymmetry;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.crypto.params.KeyParameter;

import com.sun.org.apache.bcel.internal.util.Objects;

public class TRS_Security_DH {

	private static String str = "I LOVE YOU" ;
	// 非对称加密算法 DH
	public static void main(String[] args) {
		jdkDH();
	}

	public static void jdkDH() {

		try {
			/*发送方
			 * */
			KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);  
		    KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
		    byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();//发送方公钥     要发送给接收方(可以通过  网络/文件....等等 )

		    /*
		     * 接受方
		     */
		    //2/初始化接收方秘钥
		    KeyFactory receiveKeyFactory = KeyFactory.getInstance("DH");
		    X509EncodedKeySpec x509encodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
		    //生成接收方的公钥
		    PublicKey receivePublicKey = receiveKeyFactory.generatePublic(x509encodedKeySpec);
		    
		    DHParameterSpec dhParameterSpec = ((DHPublicKey)receivePublicKey).getParams();
		    //创建可以创建密钥对的对象(KeyPair)
		    KeyPairGenerator receiveKeyParameterGenerator = KeyPairGenerator.getInstance("DH");
		    receiveKeyParameterGenerator.initialize(dhParameterSpec);//初始化KeyPairGenerator
		    //初始化密钥对
		    KeyPair receiveKeyPair =  receiveKeyParameterGenerator.generateKeyPair();
		    //生成接收方私钥
		    PrivateKey receivePrivateKey = receiveKeyPair.getPrivate();
		    //接收方公钥  字节型
		    byte[] receivePublicKeyEnc = receiveKeyPair.getPublic().getEncoded();
		    
		    
		    //秘钥构建
		    KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
		    receiverKeyAgreement.init(receivePrivateKey);
		    receiverKeyAgreement.doPhase(receivePublicKey, true);
		    SecretKey receiverDESKey = receiverKeyAgreement.generateSecret("DES");
		    
		    
		    KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
		    x509encodedKeySpec = new X509EncodedKeySpec(receivePublicKeyEnc);
		    PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509encodedKeySpec);
		    KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
		    senderKeyAgreement.init(senderKeyPair.getPrivate());
		    senderKeyAgreement.doPhase(senderPublicKey, true);
		    SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");
		    if (Objects.equals(receiverDESKey, senderDesKey)) {
				System.out.println("秘钥相同");
			}
		    
		    
		    
		    //加密
		    Cipher cipher = Cipher.getInstance("DES");
		    cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
		    byte[] result = cipher.doFinal(str.getBytes());
		    System.out.println("DH非对称加密算法加密结果: " + Base64.encodeBase64String(result));
		    
		    //解密
		    cipher.init(Cipher.DECRYPT_MODE, receiverDESKey);
		    result = cipher.doFinal(result);
		    System.out.println("DH非对称加密算法解密结果: " + new String(result));
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
