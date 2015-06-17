package com.tour.suse.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CodeImageUtil {

	public final static String[] chars = { "a", "b", "d", "c","d","e",
		"f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z" };

	public static final int Wigth = 60;
	public static final int hight = 30;
	public static final int line = 5;
	public static final int count = 4;
	public static final int size = 20;

	public static Map<String, BufferedImage> getImage() {
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		StringBuffer sb = new StringBuffer();
		BufferedImage img = new BufferedImage(Wigth, hight,
				BufferedImage.TYPE_INT_RGB);
		Random r = new Random();
		Graphics g = img.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Wigth, hight);
		for (int i = 0; i < 4; i++) {
			g.setColor(getColor());

			g.setFont(new Font("楷体", Font.BOLD + Font.ITALIC, r.nextInt(10)
					+ size));
			String s = chars[r.nextInt(chars.length)];

			g.drawString(s, i * (Wigth / count), 20);
			sb.append(s);

		}
		for (int i = 0; i < 5; i++) {
			g.setColor(getColor());
			g.drawLine(r.nextInt(Wigth), r.nextInt(Wigth), r.nextInt(Wigth),
					r.nextInt(Wigth));
		}
		map.put(sb.toString(), img);
		return map;
	}

	public static InputStream createImage(BufferedImage bufferedImage) {
		InputStream inputStream = null;
		ByteArrayOutputStream bot = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bot);
		try {
			jpeg.encode(bufferedImage);

			inputStream = new ByteArrayInputStream(bot.toByteArray());
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	public static Color getColor() {
		Random r = new Random();
		Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		return color;
	}

}
