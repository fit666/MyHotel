package com.hero.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.pojo.NowUserInfo;
import com.hero.hotel.service.UserInfoService;

@Controller
@RequestMapping("/userInfor")
public class UserInfoController {

	@Resource
	private UserInfoService userInforService;

	@GetMapping("/findUserMessage")
	public ModelAndView findUserMessage(String phoneNumber) {

		return null;

	}

	@GetMapping("/findUserInfo")
	public ModelAndView findUserInfo() {

		ModelAndView modelAndView = new ModelAndView();
		List<NowUserInfo> nowUserInfos = userInforService.findUserInfo();
		modelAndView.addObject("nowUserInfos", nowUserInfos);
		modelAndView.setViewName("backstage-html/nowUserInfo.html");
		// System.out.println(nowUserInfos);
		return modelAndView;
	}

}
