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
	// �ǶԳƼ����㷨 DH
	public static void main(String[] args) {
		jdkDH();
	}

	public static void jdkDH() {

		try {
			/*���ͷ�
			 * */
			KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);  
		    KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
		    byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();//���ͷ���Կ     Ҫ���͸����շ�(����ͨ��  ����/�ļ�....�ȵ� )

		    /*
		     * ���ܷ�
		     */
		    //2/��ʼ�����շ���Կ
		    KeyFactory receiveKeyFactory = KeyFactory.getInstance("DH");
		    X509EncodedKeySpec x509encodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
		    //���ɽ��շ��Ĺ�Կ
		    PublicKey receivePublicKey = receiveKeyFactory.generatePublic(x509encodedKeySpec);
		    
		    DHParameterSpec dhParameterSpec = ((DHPublicKey)receivePublicKey).getParams();
		    //�������Դ�����Կ�ԵĶ���(KeyPair)
		    KeyPairGenerator receiveKeyParameterGenerator = KeyPairGenerator.getInstance("DH");
		    receiveKeyParameterGenerator.initialize(dhParameterSpec);//��ʼ��KeyPairGenerator
		    //��ʼ����Կ��
		    KeyPair receiveKeyPair =  receiveKeyParameterGenerator.generateKeyPair();
		    //���ɽ��շ�˽Կ
		    PrivateKey receivePrivateKey = receiveKeyPair.getPrivate();
		    //���շ���Կ  �ֽ���
		    byte[] receivePublicKeyEnc = receiveKeyPair.getPublic().getEncoded();
		    
		    
		    //��Կ����
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
				System.out.println("��Կ��ͬ");
			}
		    
		    
		    
		    //����
		    Cipher cipher = Cipher.getInstance("DES");
		    cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
		    byte[] result = cipher.doFinal(str.getBytes());
		    System.out.println("DH�ǶԳƼ����㷨���ܽ��: " + Base64.encodeBase64String(result));
		    
		    //����
		    cipher.init(Cipher.DECRYPT_MODE, receiverDESKey);
		    result = cipher.doFinal(result);
		    System.out.println("DH�ǶԳƼ����㷨���ܽ��: " + new String(result));
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
