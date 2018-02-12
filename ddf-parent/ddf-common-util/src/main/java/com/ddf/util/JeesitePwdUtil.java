
package com.ddf.util;

public class JeesitePwdUtil {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		
		       //先MD5
				plainPassword = EncoderHandler.encodeByMD5(plainPassword);
				//jeesitepwd
				String plain = Encodes.unescapeHtml(plainPassword);
				byte[] salt = Digests.generateSalt(SALT_SIZE);
				byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
				String entryptPwd = Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
				return entryptPwd;
	}
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		        //先MD5
				plainPassword = EncoderHandler.encodeByMD5(plainPassword);
				//jeesitepwd
				String plain = Encodes.unescapeHtml(plainPassword);
				byte[] salt = Encodes.decodeHex(password.substring(0,16));
				byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
				return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
}
