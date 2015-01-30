package com.prometheus.hoc.user.util;

import com.sun.jersey.core.util.Base64;

public class SimpleCipherUtil {
	
	private static final String KEY = "bubuwork_hoc";
	
	public static String encrypt(final String text) {
		return new String(Base64.encode(xor(text.getBytes())));
	}

	public static String decrypt(final String hash) {
		try {
			return new String(xor(Base64.decode(hash.getBytes())), "UTF-8");
		} catch (java.io.UnsupportedEncodingException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private static byte[] xor(final byte[] input) {
		final byte[] output = new byte[input.length];
		final byte[] secret = KEY.getBytes();
		int spos = 0;
		for (int pos = 0; pos < input.length; ++pos) {
			output[pos] = (byte) (input[pos] ^ secret[spos]);
			spos += 1;
			if (spos >= secret.length) {
				spos = 0;
			}
		}
		return output;
	}
}
