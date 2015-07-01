package com.shbestwin.followupmanager.common.util;

import java.util.Locale;

/**
 * @author sandy
 * @version $Revision: 1.1 $ 建立日期 2012-9-11
 */
public class HexBinary {

	/**
	 * Creates a clone of the given byte array.
	 */
	public static byte[] getClone(byte[] pHexBinary) {
		byte[] result = new byte[pHexBinary.length];
		System.arraycopy(pHexBinary, 0, result, 0, pHexBinary.length);
		return result;
	}

	/**
	 * Converts the string <code>pValue</code> into an array of hex bytes.
	 */
	public static byte[] encode(String pValue) {
		if ((pValue.length() % 2) != 0) {
			throw new IllegalArgumentException("A HexBinary string must have even length.");
		}
		byte[] result = new byte[pValue.length() / 2];
		int j = 0;
		for (int i = 0; i < pValue.length();) {
			byte b;
			char c = pValue.charAt(i++);
			char d = pValue.charAt(i++);
			if (c >= '0' && c <= '9') {
				b = (byte) ((c - '0') << 4);
			} else if (c >= 'A' && c <= 'F') {
				b = (byte) ((c - 'A' + 10) << 4);
			} else if (c >= 'a' && c <= 'f') {
				b = (byte) ((c - 'a' + 10) << 4);
			} else {
				throw new IllegalArgumentException("Invalid hex digit: " + c);
			}
			if (d >= '0' && d <= '9') {
				b += (byte) (d - '0');
			} else if (d >= 'A' && d <= 'F') {
				b += (byte) (d - 'A' + 10);
			} else if (d >= 'a' && d <= 'f') {
				b += (byte) (d - 'a' + 10);
			} else {
				throw new IllegalArgumentException("Invalid hex digit: " + d);
			}
			result[j++] = b;
		}
		return result;
	}

	/**
	 * Converts the byte array <code>pHexBinary</code> into a string.
	 */
	public static String decode(byte[] pHexBinary) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < pHexBinary.length; i++) {
			byte b = pHexBinary[i];
			byte c = (byte) ((b & 0xf0) >> 4);
			if (c <= 9) {
				result.append((char) ('0' + c));
			} else {
				result.append((char) ('A' + c - 10));
			}
			c = (byte) (b & 0x0f);
			if (c <= 9) {
				result.append((char) ('0' + c));
			} else {
				result.append((char) ('A' + c - 10));
			}
		}
		return result.toString();
	}

	/**
	 * Converts the byte array <code>pHexBinary</code> into a string.
	 */
	public static String decodeWithNo0(byte[] pHexBinary) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < pHexBinary.length; i++) {
			byte b = pHexBinary[i];
			byte c = (byte) ((b & 0xf0) >> 4);
			if (c <= 9) {
				result.append((char) (c));
			} else {
				result.append((char) ('A' + c - 10));
			}
			c = (byte) (b & 0x0f);
			if (c <= 9) {
				result.append((char) (c));
			} else {
				result.append((char) ('A' + c - 10));
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String a = "12";
		System.out.println(decode(a.getBytes()));
		String ab = "00000000000100000000000001560000000000015611041501112233447C00000603A00002";
		System.out.println("::::::::::[" + HexBinary.decode(ab.getBytes()) + "]");
	}

	public static final String bytesToHexStringPrint(byte[] bArray, int start, int len) {
		StringBuffer sb = new StringBuffer();
		String sTemp;
		for (int i = start; i < len; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase(Locale.CHINA)).append(" ");
		}
		return sb.toString();
	}
	
	public static final String bytesToHexStringPrint(byte[] bArray) {
		StringBuffer sb = new StringBuffer();
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase(Locale.CHINA)).append(" ");
		}
		return sb.toString();
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString((int) (0xFF & bArray[i]));
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase(Locale.CHINA));
		}
		System.out.println(sb.toString() + "----");
		return sb.toString();
	}

	public static final String bytesToHexString(byte[] bArray, int start, int len) {
		StringBuffer sb = new StringBuffer();
		String sTemp;
		for (int i = start; i < len; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase(Locale.CHINA));
		}
		return sb.toString();
	}

	/**
	 * 转换short为byte
	 * 
	 * @param s
	 *            需要转换的short
	 * @param index
	 */
	public static byte[] putShort(short s, int index) {
		byte[] b = new byte[2];
		b[index + 1] = (byte) (s >> 8);
		b[index + 0] = (byte) (s >> 0);
		return b;
	}

	/**
	 * 通过byte数组取到short
	 * 
	 * @param b
	 * @param index
	 *            第几位开始取
	 * @return
	 */
	public static short getShort(byte[] b, int index) {
		return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
	}
}
