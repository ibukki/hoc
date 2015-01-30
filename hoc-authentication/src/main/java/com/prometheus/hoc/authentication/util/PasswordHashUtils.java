package com.prometheus.hoc.authentication.util;
/* 
 * Password Hashing With PBKDF2 (http://crackstation.net/hashing-security.htm).
 * Copyright (c) 2013, Taylor Hornby
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

import java.security.SecureRandom;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

import com.prometheus.hoc.authentication.GeneralSecurityException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class PasswordHashUtils {
	public static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final String SALT_ALGORITHM = "SHA1PRNG";
	public static final int SALT_BYTE_SIZE = 64;
	public static final int HASH_BYTE_SIZE = 64;
	public static final int ITERATION_COUNT = 1000;

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 * 
	 * @param password
	 *            the password to hash
	 * @param salt
	 *            the salt
	 * @return a salted PBKDF2 hash of the password
	 * @throws GeneralSecurityException
	 */
	public static byte[] generateHash(String password, byte[] salt)
			throws GeneralSecurityException {
		byte[] encryptedPassword = null;

		if (salt == null) {
			salt = generateSalt();
		}

		encryptedPassword = pbkdf2(password.toCharArray(), salt,
				ITERATION_COUNT, HASH_BYTE_SIZE);

		return encryptedPassword;
	}

	/**
	 * generate salt with CSPRNG
	 * 
	 * @return the salt
	 * @throws GeneralSecurityException
	 */
	private static byte[] generateSalt() throws GeneralSecurityException {
		byte[] salt = new byte[SALT_BYTE_SIZE];
		SecureRandom random;
		try {
			random = SecureRandom.getInstance(SALT_ALGORITHM);
			random.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			throw new GeneralSecurityException(
					"NoSuchAlgorithmException thrown " + e.getMessage(),
					e.getCause());
		}
		return salt;
	}

	/**
	 * Computes the PBKDF2 hash of a password.
	 * 
	 * @param password
	 *            the password to hash.
	 * @param salt
	 *            the salt
	 * @param iterationCount
	 *            the iteration count (slowness factor)
	 * @param bytes
	 *            the length of the hash to compute in bytes
	 * @return the PBDKF2 hash of the password
	 * @throws GeneralSecurityException
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt,
			int iterationCount, int bytes) throws GeneralSecurityException {
		byte[] passwordHash = null;
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount,
				bytes * 8);
		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance(HASH_ALGORITHM);
			passwordHash = skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new GeneralSecurityException("NoSuchAlgorithmException"
					+ e.getMessage(), e.getCause());
		} catch (InvalidKeySpecException e) {
			throw new GeneralSecurityException("InvalidKeySpecException"
					+ e.getMessage(), e.getCause());
		}

		return passwordHash;
	}

	/**
	 * Validates a password using given salt.
	 * 
	 * @param password
	 *            the password to check
	 * @param salt
	 *            the same salt used to generate the stored password
	 * @param storedPassword
	 *            the hash of the valid password
	 * @return true if the password is correct, false if not
	 * @throws GeneralSecurityException
	 */
	public static boolean validatePassword(String password, byte[] salt,
			byte[] storedPassword) throws GeneralSecurityException {
		boolean isValid = false;
		byte[] passwordHash = generateHash(password, salt);
		if (Arrays.equals(passwordHash, storedPassword)) {
			isValid = true;
		}
		return isValid;
	}

//	/**
//	 * Compares two byte arrays in length-constant time. This comparison method
//	 * is used so that password hashes cannot be extracted from an on-line
//	 * system using a timing attack and then attacked off-line.
//	 * 
//	 * @param a
//	 *            the first byte array
//	 * @param b
//	 *            the second byte array
//	 * @return true if both byte arrays are the same, false if not
//	 */
//	private static boolean slowEquals(byte[] a, byte[] b) {
//		int diff = a.length ^ b.length;
//		for (int i = 0; i < a.length && i < b.length; i++)
//			diff |= a[i] ^ b[i];
//		return diff == 0;
//	}
//
//	/**
//	 * Converts a string of hexadecimal characters into a byte array.
//	 * 
//	 * @param hex
//	 *            the hex string
//	 * @return the hex string decoded into a byte array
//	 */
//	private static byte[] fromHex(String hex) {
//		byte[] binary = new byte[hex.length() / 2];
//		for (int i = 0; i < binary.length; i++) {
//			binary[i] = (byte) Integer.parseInt(
//					hex.substring(2 * i, 2 * i + 2), 16);
//		}
//		return binary;
//	}
//
//	/**
//	 * Converts a byte array into a hexadecimal string.
//	 * 
//	 * @param array
//	 *            the byte array to convert
//	 * @return a length*2 character string encoding the byte array
//	 */
//	private static String toHex(byte[] array) {
//		BigInteger bi = new BigInteger(1, array);
//		String hex = bi.toString(16);
//		int paddingLength = (array.length * 2) - hex.length();
//		if (paddingLength > 0)
//			return String.format("%0" + paddingLength + "d", 0) + hex;
//		else
//			return hex;
//	}

}