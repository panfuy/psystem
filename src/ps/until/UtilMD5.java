package ps.until;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class UtilMD5 {
	public static final String HEX_NUMS_STR = "0123456789ABCDEF";
	public static final Integer SALT_LENGTH = Integer.valueOf(12);

	public static void main(String args[]) throws Exception {
		System.out.println(getEncryptedPwd("admin"));
		System.out.println(getEncryptedPwd("admin").length());
		System.out.println(validPassword("x",
				"6DB8463C2561610D49B739AA8878B062B16F74DC9B01D6867603B695"));

	}

	public static byte[] hexStringToByte(String hex) {
		int len = hex.length() / 2;
		byte result[] = new byte[len];
		char hexChars[] = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) ("0123456789ABCDEF".indexOf(hexChars[pos]) << 4 | "0123456789ABCDEF"
					.indexOf(hexChars[pos + 1]));
		}
		return result;
	}

	public static String byteToHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xff);
			if (hex.length() == 1)
				hex = (new StringBuilder(String.valueOf('0'))).append(hex)
						.toString();
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 效验MD5加密的字符
	 * 
	 * @param password
	 *            加密前的字符串
	 * @param passwordInDb
	 *            加密后的字符串
	 * @return 是否
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean validPassword(String password, String passwordInDb)
			throws Exception {
		byte pwdInDb[] = hexStringToByte(passwordInDb);
		byte salt[] = new byte[SALT_LENGTH.intValue()];
		System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH.intValue());
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(salt);
		md.update(password.getBytes("UTF-8"));
		byte digest[] = md.digest();
		byte digestInDb[] = new byte[pwdInDb.length - SALT_LENGTH.intValue()];
		System.arraycopy(pwdInDb, SALT_LENGTH.intValue(), digestInDb, 0,
				digestInDb.length);
		return Arrays.equals(digest, digestInDb);
	}

	/**
	 * MD5加密字符串
	 * 
	 * @param password
	 *            需要加密的字符串
	 * @return 返回加密后的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedPwd(String password) throws Exception {
		byte pwd[] = null;
		SecureRandom random = new SecureRandom();
		byte salt[] = new byte[SALT_LENGTH.intValue()];
		random.nextBytes(salt);
		MessageDigest md = null;
		md = MessageDigest.getInstance("MD5");
		md.update(salt);
		md.update(password.getBytes("UTF-8"));
		byte digest[] = md.digest();
		pwd = new byte[digest.length + SALT_LENGTH.intValue()];
		System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH.intValue());
		System.arraycopy(digest, 0, pwd, SALT_LENGTH.intValue(), digest.length);
		return byteToHexString(pwd);
	}
}
