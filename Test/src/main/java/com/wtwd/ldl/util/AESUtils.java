package com.wtwd.ldl.util;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author ldaoliang
 * @Date 2019/10/25 0025 下午 5:27
 * @Description 有关AES加密的
 **/
public class AESUtils {

	//算法
	private static final String algorithm = "AES";
	private static final String algorithm_model = "AES/CBC/PKCS5Padding";

	//加密key
	private static final String key = "landaoliang!@#$%";   //8字节  key16位

	private static final String key1 = "landaoliangnihaoshuaiaxiexielxl~";   //16字节   key32位


	public static void main(String[] args) throws  Exception{
		String encrypt = AESUtils.encrypt("123456");
		System.out.println(encrypt);

		String decrypt = AESUtils.decrypt("u3K2n+1Ebxv9gqBw3YNxAw==");
		System.out.println(decrypt);
	}

	/**
	 * Description: 简单密钥加密
	 * @param data data
	 * @return 加密后的值 24位
	 * @throws Exception Exception
	 */
	public static String encrypt(String data) throws Exception {
		// Cipher负责完成加密或解密工作,生成Cipher对象,指定其支持的算法
		Cipher cipher = Cipher.getInstance(algorithm);
		// 生成特定的密钥
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		cipher.init(Cipher.ENCRYPT_MODE, skey);
		byte[] result = cipher.doFinal(data.getBytes());
		//org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(result);// 22位，去等号
		return Base64.getEncoder().encodeToString(result);
	}

	/**
	 * Description: 简单密钥解密
	 * @param ciphertext 密文
	 * @return 解密后的值
	 * @throws Exception Exception
	 */
	public static String decrypt(String ciphertext) throws Exception {
		//byte[] old = org.apache.commons.codec.binary.Base64.decodeBase64(ciphertext);
		byte[] ciphertextByte = Base64.getDecoder().decode(ciphertext);
		// Cipher负责完成加密或解密工作,生成Cipher对象,指定其支持的算法
		Cipher cipher = Cipher.getInstance(algorithm);
		// 生成特定的密钥
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		cipher.init(Cipher.DECRYPT_MODE, skey);
		byte[] result = cipher.doFinal(ciphertextByte);
		return new String(result);
	}

	/*
	 * 加密，32位
	 */
	public static String encrypt1(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] key = hexStr2Byte(key1);
		SecretKeySpec sKey = new SecretKeySpec(key, algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, sKey);
		byte[] encrypted = cipher.doFinal(data.getBytes());
		return byte2HexStr(encrypted);
	}

	/*
	 * 解密
	 */
	public static String decrypt1(String ciphertext) throws Exception {
		byte[] tmp = hexStr2Byte(ciphertext);
		byte[] key = hexStr2Byte(key1);
		SecretKeySpec sKey = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, sKey);
		byte[] decrypted = cipher.doFinal(tmp);
		return new String(decrypted);
	}

	/**
	 * 带向量加密，使用CBC模式，需要一个向量iv，可增加加密算法的强度
	 * @param plaintext 待加密的字符串
	 * @return 加密后的值
	 */
	public static String encrypt2(String plaintext, String key, String ivParameter) throws Exception{
		Cipher cipher = Cipher.getInstance(algorithm_model);
		SecretKeySpec skeySpec = new SecretKeySpec(hexStr2Byte(key), algorithm);
		IvParameterSpec iv = new IvParameterSpec(hexStr2Byte(ivParameter));
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(hexStr2Byte(plaintext));
		return byte2HexStr(encrypted);
	}

	/**
	 * 带向量解密
	 * @param ciphertext 密文
	 * @return 解密后的值
	 */
	public static String decrypt2(String ciphertext, String key, String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm_model);
		SecretKeySpec skey = new SecretKeySpec(hexStr2Byte(key), algorithm);
		IvParameterSpec iv = new IvParameterSpec(hexStr2Byte(ivParameter));
		cipher.init(Cipher.DECRYPT_MODE, skey, iv);
		byte[] decrypted = cipher.doFinal(hexStr2Byte(ciphertext));
		return byte2HexStr(decrypted);
	}

	/*
	 * 转为二进制
	 */
	public static byte[] hexStr2Byte(String secret_key) {
		if (secret_key.length() < 1)
			return null;
		byte[] encrypted = new byte[secret_key.length() / 2];
		for (int i = 0; i < secret_key.length() / 2; i++) {
			int high = Integer.parseInt(secret_key.substring(i * 2, i * 2 + 1), 16);//偶数位
			int low = Integer.parseInt(secret_key.substring(i * 2 + 1, i * 2 + 2), 16);//奇数位
			encrypted[i] = (byte) (high * 16 + low);
		}
		return encrypted;
	}

	/*
	 * 转为十六进制字符串
	 */
	public static String byte2HexStr(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

}
