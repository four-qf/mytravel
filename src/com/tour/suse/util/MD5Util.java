package com.tour.suse.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String md5code(String s){
		if(s.equals("")||s==null){
			return "";
		}else{//不为空加密
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("md5");
				byte[] byt=md.digest(s.getBytes());
				return  Base64(byt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
	}
	//base64处理，将任意数组处理为同一长度的字符串
	public static String Base64( byte[] bty){
		BASE64Encoder base64Encoder=new BASE64Encoder();
		return base64Encoder.encode(bty);
	}
}
