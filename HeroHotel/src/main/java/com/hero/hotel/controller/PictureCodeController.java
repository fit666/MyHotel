package com.hero.hotel.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dsna.util.images.ValidateCode;

/**
 * 验证码
 */
@Controller
@RequestMapping("/code")
public class PictureCodeController extends HttpServlet {
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request,HttpServletResponse response) {
		ValidateCode code =new ValidateCode(117,38,4,30);
		//获取验证码的值
		String codeValue=code.getCode();
		System.out.println(codeValue);
		//将验证码存入session作用域中
		HttpSession session=request.getSession();
		session.setAttribute("codeValue", codeValue);
		ByteArrayOutputStream out=new ByteArrayOutputStream(); 	
		try {
			boolean flag=ImageIO.write(code.getBuffImg(),"JPEG",out);
			byte[] by=out.toByteArray();
			response.getOutputStream().write(by);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} 
