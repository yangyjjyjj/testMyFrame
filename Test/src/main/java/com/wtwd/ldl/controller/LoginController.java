package com.wtwd.ldl.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author ldaoliang
 * @Date 2019/10/24 0024 上午 11:16
 * @Description TODO
 **/
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private Producer captchaProducer;

	@RequestMapping("/")
	public String showHome(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Object object = SecurityContextHolder.getContext().getAuthentication().toString();
		logger.info("当前登录用户："+name);
		logger.info(""+object);
		return "home.html";
	}

	@GetMapping("/admin")
	@ResponseBody
	public String testAdmin(){
		return "admin权限";
	}

	@GetMapping("/user")
	@ResponseBody
	public String testUser(){
		return "user权限";
	}

	@RequestMapping("/login/error")
	public void loginFail(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		AuthenticationException authenticationException =
				//通过源码看到，把异常信息放在了这个属性中
				(AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

		try{
			response.getWriter().write(authenticationException.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@GetMapping("/getKaptchaImage")
	public void getKaptchaImage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		//request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		//将验证码存到session
		HttpSession session = request.getSession();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

}
