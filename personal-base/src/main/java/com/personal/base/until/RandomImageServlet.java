package com.personal.base.until;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.github.cage.Cage;
import com.github.cage.GCage;

/**
 * 用于产生随即图片以防止非法攻击
 * 
 * @author hujihong
 */
public class RandomImageServlet extends HttpServlet {
	private static final long serialVersionUID = -4105987112054451210L;

	public final static String RANDOM_LOGIN_KEY = "RANDOM_LOGIN_KEY";

	public void init() throws ServletException {
		System.setProperty("java.awt.headless", "true");
	}

	public static String getRandomLoginKey(HttpServletRequest req) {
		return (String) req.getSession().getAttribute(RANDOM_LOGIN_KEY);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		HttpSession ssn = req.getSession(true);
		if (ssn != null) {
			String randomString = RandomImageGenerator.random();// 生成种子

			ssn.setAttribute("rand", randomString);// 将种子放到session里面

			// 产生BufferImage
			Cage cage = new GCage();
			cage.draw(randomString, response.getOutputStream());

		}
	}
}
