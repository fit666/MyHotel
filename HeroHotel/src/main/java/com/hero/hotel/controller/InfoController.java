package com.hero.hotel.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.InfoService;

@Controller
@RequestMapping("/info")
public class InfoController {
	@Resource
	private InfoService infoService;
	
	
	@RequestMapping("/addInfo")
	@ResponseBody
	public String addInfo(Info info,HttpServletRequest request) {
		String result ="添加失败";
		Object obj = request.getSession().getAttribute("user");
		System.out.println(info);
		User user = (User) obj;
		if(obj==null) {
			return null;
		}
		info.setUserid(user.getId());
		if(infoService.addInfo(info)!=0) {
			result="添加成功";
		}
		return result;
	}
}
