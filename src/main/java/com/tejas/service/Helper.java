package com.tejas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class Helper {
	
	static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	static final Integer BASE = ALPHABET.length();
	
	@Autowired 
	private static RedisTemplate< String, Integer > template;

	public static Integer getValue( final String key ) {
	    return template.opsForValue().get( key );
	}

	public static void setValue( final String key, final Integer value ) {
	    template.opsForValue().set( key, value );
	}
	
	public static void incrValue(final String key) {
		template.opsForValue().increment(key, 1);
	}
	
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
	
	public static String toBase62(int i) {
		StringBuilder sb = new StringBuilder("");
		if (i == 0) {
			return "a";
		}
		while (i > 0) {
			i = toBase62(i, sb);
		}
		return sb.reverse().toString();
	}
	
	private static int toBase62(int i, final StringBuilder sb) {
		int rem = i % BASE;
		sb.append(ALPHABET.charAt(rem));
		return i / BASE;
	}
	
	public static int toBase10(String str) {
		int i=0;
		while(str.charAt(i)=='a')
			i++;
		return toBase10(new StringBuilder(str.substring(i)).reverse().toString().toCharArray());
	}

	private static int toBase10(char[] chars) {
		int n = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			n += toBase10(ALPHABET.indexOf(chars[i]), i);
		}
		return n;
	}

	private static int toBase10(int n, int pow) {
		return n * (int) Math.pow(BASE, pow);
	}

}
