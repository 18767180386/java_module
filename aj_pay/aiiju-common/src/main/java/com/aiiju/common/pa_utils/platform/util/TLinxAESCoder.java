package com.aiiju.common.pa_utils.platform.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TLinxAESCoder {
	private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static String KEY_ALGORITHM = "AES";

	
	//hex2bin后进行AES解密
	public static String decrypt(String sSrc, String sKey) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("ASCII"),
				KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(2, skeySpec);
		byte[] encrypted1 = hex2byte(sSrc);
		byte[] original = cipher.doFinal(encrypted1);
		return new String(original,"UTF-8");
	}

	public static String encrypt(String sSrc, String sKey) throws Exception {
		//System.out.println("接口发送信息：" + sSrc);
		SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("ASCII"),
				KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(1, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
		return byte2hex(encrypted);
	}

	private static byte[] hex2byte(String strhex) {
		if (strhex == null)
			return null;

		int l = strhex.length();
		if (l % 2 == 1)
			return null;

		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; ++i)
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);

		return b;
	}

	//二进制数组转十六进制字符串 （已优化）
	private static String byte2hex(byte[] result) {
   		StringBuffer sb = new StringBuffer(result.length * 2);
   		for (int i = 0; i < result.length; i++) {
      		int hight = ((result[i] >> 4) & 0x0f);
      		int low = result[i] & 0x0f;
      		sb.append(hight > 9 ? (char) ((hight - 10) + 'a') : (char) (hight + '0'));
      		sb.append(low > 9 ? (char) ((low - 10) + 'a') : (char) (low + '0'));
   		}
   		return sb.toString();
	}

	//二进制数组转十六进制字符串（速度慢）
	private static String byte2hex1(byte[] b) {
   		String hs = "";
   		String stmp = "";
   		for (int n = 0; n < b.length; ++n) {
      		stmp = Integer.toHexString(b[n] & 0xFF);
      		if (stmp.length() == 1)
         		hs = hs + "0" + stmp;
      		else
         		hs = hs + stmp;
   		}

   		return hs.toUpperCase();
	}


	public static void main(String[] args) throws Exception {
		String data = "14C8CF803C30D28322A8791C369EF0DD5EE45B9A068F1A73290E4CBE579966AFA7731A5DCA7B1EF0944EBAEA669F2C98BBE638B74AF08399CE6335CB79C87B1D92B370143BC9149795B60A3F4CC1892D046702BBA50CDD66D58059E7B0AED5649F0EB7D55AE10729E49BE739149C26C34BCF801E2A52A31E2AFC2184B6D0706CFEB43BC8DA7EDDAB5335E32F4AD286A441F44F6BC24131D5537C5C14CEFD9D2D";
		String str = null;
		try {
			str = decrypt(data,"5ff69401d22ad8570f614757133c1a51");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(str);

		//System.out.println( UUID.randomUUID().toString().replace("-",""));
//		for (int i=0;i<100;i++){
//			KeyGenerator kg = KeyGenerator.getInstance("AES");
//			kg.init(256);//要生成多少位，只需要修改这里即可128, 192或256
//			SecretKey sk = kg.generateKey();
//			//System.out.println(sk);
//			byte[] b = sk.getEncoded();
//			System.out.println(b.length);
//		}
   
	}
}
