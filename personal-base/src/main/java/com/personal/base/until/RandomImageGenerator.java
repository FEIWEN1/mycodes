package com.personal.base.until;

import java.awt.*;
import java.awt.image.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import org.apache.commons.lang.RandomStringUtils;

import com.github.cage.Cage;
import com.github.cage.GCage;

/**
 * 随即图片生成器 该类用于用户注册时候需要用户根据图片内容进行填写正确后方可注册
 * 
 * @author hujihong
 */
public class RandomImageGenerator {

	// 随即生成包含验证码的字符串
	public static String random() {
		// 因为o和0,l和1很难区分,所以,去掉大小写的o和l
		String str = "";
		// "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";//初始化种子
		str = "0123456789abcdefghijkmnpqrstuvwxyz";// 初始化种子
		// str = "0123456789";//初始化种子
		return RandomStringUtils.random(4, str);// 返回4为的字符串
	}

	/**
	 * 根据要求的数字生成图片,背景为白色,字体大小16,字体颜色黑色粗体
	 * 
	 * @param num
	 *            要生成的数字
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public static void render(String num, OutputStream out) throws IOException {
		if (num.getBytes().length > 4) {
			throw new IllegalArgumentException(
					"The length of param num cannot exceed 4.");
		}
		// 设定宽度和高度
		int width = 60;
		int height = 20;
		// 在内存中创建图象
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics2D g = (Graphics2D) bi.getGraphics();
		// 画边框
		java.util.Random random = new java.util.Random();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 设置字体
		Font mFont = new Font("Tahoma", Font.BOLD, 14);
		g.setFont(mFont);
		g.setColor(Color.BLACK);// 设置字体颜色
		// 画认证码,每个认证码在不同的水平位置
		String str1[] = new String[4];
		for (int i = 0; i < str1.length; i++) {
			str1[i] = num.substring(i, i + 1);
			int w = 0;
			int x = (i + 1) % 3;
			// 随即生成验证码字符的水平偏移量
			if (x == random.nextInt(3)) {
				w = 5 - random.nextInt(2);
			} else {
				w = 5 + random.nextInt(2);
			}
			// 随即生成颜色
			Color color1 = new Color(random.nextInt(180), random.nextInt(180),
					random.nextInt(180));
			g.setColor(color1);
			g.drawString(str1[i], 10 * i + 10, 15);
		}
		// 随机产生干扰点,并用不同的颜色表示，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			Color color1 = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));
			g.setColor(color1); // 随即画各种颜色的点
			g.drawOval(x, y, 0, 0);
		}
		// 画干扰线
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			Color color1 = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));

			g.setColor(color1); // 随即画各种颜色的线
			g.drawLine(x, y, x1, y1);
		}
		// 图像生效
		g.dispose();
		// 输出页面
		ImageIO.write(bi, "jpg", out);
	}

	public static void main(String[] args) throws IOException {
		// String num = random();
		// System.out.println(num);
		// render(num, new FileOutputStream("D:\\test.jpg"));
		// System.out.println("Image generated.");
		// BufferedImage image = render("1234");
		// image.getGraphics().dispose();

		Cage cage = new GCage();

		OutputStream os = new FileOutputStream("JPEG", false);
		try {
			cage.draw("1234", os);
		} finally {
			os.close();
		}

	}

	public static BufferedImage render(String num) {
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		// g.setColor(getRandColor(200, 250));

		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));

		// 随机产生干扰点,并用不同的颜色表示，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			Color color1 = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));
			g.setColor(color1); // 随即画各种颜色的点
			g.drawOval(x, y, 0, 0);
		}
		// 画干扰线
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			Color color1 = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));

			g.setColor(color1); // 随即画各种颜色的线
			g.drawLine(x, y, x1, y1);
		}

		// 画认证码,每个认证码在不同的水平位置
		for (int i = 0; i < num.length(); i++) {

			String rand = num.substring(i, i + 1);
			int w = 0;
			int x = (i + 1) % 3;

			// 随即生成验证码字符的水平偏移量
			if (x == random.nextInt(3)) {
				w = 5 - random.nextInt(2);
			} else {
				w = 5 + random.nextInt(2);
			}
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);

		}
		return image;

	}

	private static Color getRandColor(int fc, int bc) {
		// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
