package com.surfo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * May 22, 20152:56:33 PM
 * @author fengxk
 * @version 1.0
 */
public class MD5Util {

	
	public static String encode(String src){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(src.getBytes());
			StringBuilder builder = new StringBuilder();
			for (byte b : result) {
				int num = b & 0xff;
				String str = Integer.toHexString(num);
				if(str.length() == 1){
					builder.append(0);
				}
				
				builder.append(str);
			}
			
			return builder.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String md5 = MD5Util.encode("123456");
		System.out.println(md5);

	}

}
