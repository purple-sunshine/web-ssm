package com.heitian.ssm.arch.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 */
public class Identities {

	private static final Logger log = LoggerFactory.getLogger(Identities.class);
	private static final String RANDOM_NUM_GENERATOR_ALGORITHM_NAME = "SHA1PRNG";
	private static SecureRandom random = null;
	static {
		try {
			random = SecureRandom.getInstance(RANDOM_NUM_GENERATOR_ALGORITHM_NAME);
		} catch (java.security.NoSuchAlgorithmException e) {
			log.debug("The SecureRandom SHA1PRNG algorithm is not available on the current platform.  Using the " +
					"platform's default SecureRandom algorithm.", e);
			random = new SecureRandom();
		}
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 以62进制（字母加数字）生成19位UUID，最短的UUID
	 *
	 * @return
	 */
	public static String uuid19() {
		UUID uuid = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		sb.append(Numbers.digits(uuid.getMostSignificantBits() >> 32, 8));
		sb.append(Numbers.digits(uuid.getMostSignificantBits() >> 16, 4));
		sb.append(Numbers.digits(uuid.getMostSignificantBits(), 4));
		sb.append(Numbers.digits(uuid.getLeastSignificantBits() >> 48, 4));
		sb.append(Numbers.digits(uuid.getLeastSignificantBits(), 12));
		return sb.toString();
	}

	public static String uuid8() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(Numbers.digits[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(uuid19());
		}
	}
}
