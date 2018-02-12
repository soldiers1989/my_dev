package com.ddf.util;

import java.security.MessageDigest;

/***
 * MD5 SHA1 加密类
 * @author HongQuan
 */

public class EncoderHandler {

	private static final String ALGORITHM = "MD5";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * encode string
	 *
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * encode By MD5
	 *
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		String str = "account=13166296663&aim=自用&appid=100&avatarurl=http://p5.qhimg.com/t01c8a7a8bdceb36fb4.jpg&birthday=19871020&budget=150万&city=上海&cnname=黄文杰&country=中国&curradd=上海市宝山区沪太路&currhouse=无&district=宝山区&do=perfectinfo&email=271288508@qq.com&emephone=13585560293&emergency=黄文杰2&enname=huangwenjie&expectedtime=2&familyincome=200000&favoritecity=上海&favoriteprovince=上海&gender=男&income=100000&industry=IT&ipaddress=58.247.94.202&job=IT&mobile=13166296663&nickname=哈哈呵呵&province=上海&version=1.0.0&wechat=jevon1020";
		System.out.println("MD5  :" + EncoderHandler.encodeByMD5("这是个问题"));
//		System.out.println("MD5  :" + EncoderHandler.encode("MD5", str));
//		System.out.println("SHA1 :" + EncoderHandler.encode("SHA1", str));
	}

}