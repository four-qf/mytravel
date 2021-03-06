package com.tour.suse.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 工具类：生成图片代码
 * @author zq
 *
 */
public class CreateImage {
	
	/**
	 * 	actionUsedStr  这个字符串存储本次用到的验证随机字符串
	 */
	public static String actionUsedStr = "";
	
	/**
	 * 生成验证码图片
	 * @return  BufferedImage  图片
	 */
	public static BufferedImage getImage() {
		
		//获取创建此次图片所要用到的验证码,即随机字符串
		String confirmCode = RandomStringUtil.getRandomStringTool();
		// 将本次用到的随机字符串存储起来
		actionUsedStr = confirmCode;
		
		//验证码长度
		int codeLength=RandomStringUtil.RANDOM_LENGTH;
		//字体大小
		int fSize = 15;
		int fWidth = fSize + 1;
		//图片宽度
		int width = codeLength * fWidth + 6;
		//图片高度
		int height = fSize * 2 + 1;
		
		//图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.createGraphics();
		
		//设置背景色
		graphics.setColor(Color.WHITE);
		//填充背景
		graphics.fillRect(0, 0, width, height);
		//设置边框颜色
		graphics.setColor(Color.LIGHT_GRAY);
		//边框字体样式
		graphics.setFont(new Font("Arial", Font.BOLD, height -2));
		//绘制边框
		graphics.drawRect(0, 0, width - 1, height - 1);
		
		//绘制噪点
		Random random = new Random();
		//设置噪点颜色
		graphics.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < codeLength * 6; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			//绘制1*1大小的矩形
			graphics.drawRect(x, y, 1, 1);
		}
		
		//绘制验证码
		int codeY = height - 10;
		
		//设置字体颜色和样式
		graphics.setColor(new Color(19,148,246));
		graphics.setFont(new Font("Georgia", Font.BOLD, fSize));
		for(int i = 0; i < codeLength;i++){
			graphics.drawString(String.valueOf(confirmCode.charAt(i)), i * 16, codeY);
		}
		
		//关闭资源
		graphics.dispose();
		
		return image;
	}
	
	/**
	 * 返回验证码图片的流格式
	 * @return  ByteArrayInputStream 图片流
	 */
	public static ByteArrayInputStream getImageAsInputStream() {
		BufferedImage image = getImage();
		return convertImageToStream(image);
	}
	
	/**
	 * 将BufferedImage转换成ByteArrayInputStream
	 * @param image	图片
	 * @return	ByteArrayInputStream流
	 */
	public static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
		try {
			jpeg.encode(image);
			byte[] bts = bos.toByteArray();
			inputStream = new ByteArrayInputStream(bts);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageFormatException e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}
	
	public static Map<String, BufferedImage> getImageCode(){
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(actionUsedStr, getImage());
		return map;
	}
}
