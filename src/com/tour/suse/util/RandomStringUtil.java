package com.tour.suse.util;

import java.util.Random;
/**
 * 工具类：产生随机字符串， RANDOM_LENGTH定义了要产生多少位的验证码， 固定产生的验证码是由数字+字母大小写构成
 * 
 * @author zq
 * 
 */
public class RandomStringUtil {

	// 定义产生4位的随机数
	public static final int RANDOM_LENGTH = 4;

	public static String getRandomStringTool() {

		char[] randoms = {'2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
				'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };

		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < RANDOM_LENGTH; i++) {
			sb.append(randoms[random.nextInt(randoms.length)]);
		}

		return sb.toString();
	}

	//获取重置密码,获取6位的随机字符串
	public static String getResetPwd() {
		int pwdLength = 6; 
		char[] randoms = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };

		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < pwdLength; i++) {
			sb.append(randoms[random.nextInt(randoms.length)]);
		}

		return sb.toString();
	}

	/*
	 * //测试代码 public static void main(String[] args) { for (int i = 0; i < 20;
	 * i++) { System.out.println(RandomStringUtil.getRandomStringTool()); } }
	 */
}
