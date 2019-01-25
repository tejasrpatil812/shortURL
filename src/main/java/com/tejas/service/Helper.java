package com.tejas.service;

import java.net.URI;
import java.net.URISyntaxException;

public class Helper {
	
	//CONSTANTS
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final Integer BASE = ALPHABET.length();
	public static final Integer oneDay=(1000 * 60 * 60 * 24);
	
	//MAKING 7 LETTER STRING
	public static String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append('a');
	    }
	    sb.append(inputString);
	    return sb.toString();
	}
	
	public static String toBase62(long i) {
		StringBuilder sb = new StringBuilder("");
		if (i == 0) {
			return "a";
		}
		while (i > 0) {
			i = toBase62(i, sb);
		}
		return sb.reverse().toString();
	}
	
	private static long toBase62(long i, final StringBuilder sb) {
		int rem = (int)i % BASE;
		sb.append(ALPHABET.charAt(rem));
		return i / BASE;
	}
	
	public static long toBase10(String str) {
		int i=0;
		while(str.charAt(i)=='a')
			i++;
		return toBase10(new StringBuilder(str.substring(i)).reverse().toString().toCharArray());
	}

	private static long toBase10(char[] chars) {
		long n = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			n += toBase10(ALPHABET.indexOf(chars[i]), i);
		}
		return n;
	}

	private static long toBase10(long n, int pow) {
		return n *  (long)Math.pow(BASE, pow);
	}
	
	//EXTRACTING DOMAIN NAME
	public static String getDomainName(String url){
	    URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String domain = uri.getHost();
	    domain = domain.startsWith("www.") ? domain.substring(4) : domain;
	    return domain.substring(0,domain.length()-4);
	}

}
