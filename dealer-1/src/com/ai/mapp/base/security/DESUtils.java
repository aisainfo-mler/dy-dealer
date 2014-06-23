package com.ai.mapp.base.security;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtils {
	
	public static final Log log = LogFactory.getLog(DESUtils.class);
	
	/**
	 * 报表json密钥
	 */
	private final static String DES = "DES";
	
	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		System.out.println("数据长度："+(src.length%8)+",密钥长度："+key.length);
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding","BC");
//		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		System.out.println("解密数据长度："+(src.length%8)+",密钥长度："+key.length);
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding","BC");
//		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String hex,String pwd) {
		try {
			System.out.println("解密前："+hex);
			String data = new String(decrypt(hex2byte(hex.getBytes("UTF-8")), pwd.getBytes()), "UTF-8");
//			String data = new String(decrypt(decryptBASE64(hex), pwd.getBytes()), "UTF-8");
			System.out.println("解密后："+data);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data,String pwd) {
		try {
			System.out.println("加密前："+data);
			String hex =  byte2hex(encrypt(data.getBytes("UTF-8"), pwd.getBytes()));
//			String hex =  encryptBASE64(encrypt(data.getBytes("UTF-8"), pwd.getBytes()));
			System.out.println("加密后："+hex);
			
			return hex;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 二行制转字符串
	 * 
	 * @param b
	 * 
	 * @return
	 */

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;

		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	public static void main(String[] args) throws Exception{
		try{
			
			
		String hex1 = "CB91C8637CDC22DC8CEF1E9EAF1376EE1B8E0907A1D14AB4495368BFCD2C24514D492D124E6928F6F879AB61A50037801D10A51A5D5AC774DC4E193EDD57B704AA6E79DF4A4F27FAC0AC927B70EEC5ECE2A80CB3DED9A7ED97B65B431F0273DEC0AC927B70EEC5EC41AD4F3B77C0831775D1ECCEB43D47FEA459AC862D65CF78A7701C3EAFA8CAED150B1F1FC78BED14EA53F4C1E9ED2496B7F9E88D548B38756940D86D1F6B8A51B7CA36A2AB36CC33";
		String hex2 = "CB91C8637CDC22DC8CEF1E9EAF1376EE1B8E0907A1D14AB4495368BFCD2C24514D492D124E6928F6F879AB61A50037801D10A51A5D5AC774DC4E193EDD57B704AA6E79DF4A4F27FAC0AC927B70EEC5ECE2A80CB3DED9A7ED97B65B431F0273DEC0AC927B70EEC5EC41AD4F3B77C0831775D1ECCEB43D47FEA459AC862D65CF78A7701C3EAFA8CAED150B1F1FC78BED14EA53F4C1E9ED2496B7F9E88D548B38756940D86D1F6B8A5131787629B4EC906E";
		
		String data = DESUtils.decrypt(hex2, "_mapp_hz_server_");
		
		System.out.println(data);
		
//		System.out.println(hex);
//		String hex_bak = "CB91C8637CDC22DC8CEF1E9EAF1376EE3C8E44F8D5EA4D1F495368BFCD2C2451A31FFA2582358FC7E2AB8A74EC322967A008F392636B331250D01FEC52AA5318BB3DA83DA32AC65923E5BF853421655F5AC191EE3D5B48E544A312E12FE18D5E5063465E38C6A0C1CE0DEE5905D3E1CCB29F29DA2DDA0AA91F7A754BA3FCE8256D7ABE9F33BE4AF28432D1556A56046D0DF2AAF5F3E5C50925F2A8FA9E18BD1390388072018CEC6FD5CD75BF8476B0A49E8BB6E42603621EFC056187A6E271EC36D333C1E41F436989B88B885E5EBB1697C62806609A8B5E4772781C041EC38AD62E96937DBB038DD8336B104780F4AD6D5B1CFFCE06B64ABB6BC12C8C2233BB639E21A2BE7321D1CBDD7951F67013BC9D52105F6EC67349149EA61512E4592BA672092E08BCB47560D2D3811FFE21D7DBF7130933FEFBDA88924D701260077510C41565CC6B7368BC1ABB178B335F018E911F2F4920F73AC62204BBAA46F0975A2B99DC0AB646A570F4B17EAA6E02882EED71D0849F93875B8150C6C433DA82";

//		hex = 			 "CB91C8637CDC22DC8CEF1E9EAF1376EE3C8E44F8D5EA4D1F495368BFCD2C2451A31FFA2582358FC7E2AB8A74EC322967A008F392636B331250D01FEC52AA5318BB3DA83DA32AC65923E5BF853421655F5AC191EE3D5B48E544A312E12FE18D5E5063465E38C6A0C1CE0DEE5905D3E1CCB29F29DA2DDA0AA91F7A754BA3FCE8256D7ABE9F33BE4AF28432D1556A56046DE1523B4F1092BE7D25F2A8FA9E18BD1390388072018CEC6FD5CD75BF8476B0A49E8BB6E42603621EFC056187A6E271EC36D333C1E41F436989B88B885E5EBB1697C62806609A8B5E4772781C041EC38AD62E96937DBB038DD8336B104780F4AD6D5B1CFFCE06B64ABB6BC12C8C2233BB639E21A2BE7321D1CBDD7951F67013BC9D52105F6EC67349149EA61512E4592BA672092E08BCB47560D2D3811FFE21D7DBF7130933FEFBDA88924D701260077510C41565CC6B7368BC1ABB178B335F018E911F2F4920F73AC62204BBAA46F0975A2B99DC0AB646A570F4B17EAA6E02882EED71D0849F93875B8150C6C433DA82";
		
//		String base64 = DESUtils.encryptBASE64(hex.getBytes());
//		
//		System.out.println(hex.length()+"===base64:"+base64.length());
//		
//		
//		
//		System.out.println(DESUtils.decrypt(new String(DESUtils.decryptBASE64(base64)), "_mapp_hz_server_"));
//		
//		System.out.println("=========");
//		
//		System.out.println(hex_bak.equals(hex));
		
//		String hex = "74D756A86453DB07023CDA3033C3E56E";
		
//		System.out.println(decrypt(hex, pwd));
		
		
//		String data = "我了个阿很大";
//		String pwd = "mlermler";
//		
//		String hex = encrypt(data, pwd);
//		
//		System.out.println("密文："+hex);
//		
//		System.out.println("解密："+decrypt(hex, pwd));
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}