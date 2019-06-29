package com.hero.hotel.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.InfoService;
import com.hero.hotel.service.UserService;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private InfoService infoService;

	@RequestMapping("/userInfo")
	@ResponseBody
	public Map<String,Object> showInfo(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Object obj = request.getSession().getAttribute("user");
		User user = (User) obj;
		if(obj==null) {
			return null;
		}
		List<Info> infos =new ArrayList<Info>();
		System.out.println(user);
		infos =	infoService.queryInfo(user.getId());
		System.out.println(infos);
		map.put("user", user);
		map.put("infos",infos);
		return map;
	}

	public UserService getUserService() {
		return userService;

	}


	@RequestMapping("/rpwd")
	@ResponseBody
	public String resetPwd(String pwd,HttpServletRequest request) {
		String result="修改失败";
		String newpwd=(new SimpleHash("MD5",pwd,null,1024)).toString();
		System.out.println(newpwd);
		Object obj =request.getSession().getAttribute("user");
		User user = (User) obj;
		user.setPassword(newpwd);
		if(userService.updatePwd(user)!=0) {
			result="修改成功";
		}
		return result;
	}
	@RequestMapping("/isLogin")
	@ResponseBody
	public String isLogin(HttpServletRequest request) {
		String result="失败";
		Object obj =request.getSession().getAttribute("user");
		if(obj!=null) {
			result="成功";
		}
		return result;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/*
	 * 查询所有会员
	 */
	@RequestMapping("/findAllVip")
	public ModelAndView findAllVip(){
		ModelAndView mav=new ModelAndView();
		List<User> vips=userService.findAllVip();
		mav.addObject("allVips", vips);
		mav.setViewName("/backstage-html/vip-list.html");
		return mav;
	}

	
	 //账号密码登录（图形验证码）
	
    @RequestMapping("/login")
    @ResponseBody
	public String login(User user,HttpSession session) {
    	String result="登录失败";
		result=userService.login(user,session);
    	return result;
	}
    
  //手机号动态码登录
    @RequestMapping("/loginTel")
    @ResponseBody
	public String loginTel(User user,HttpSession session) {
    	String result="登录失败";
    	result=userService.loginTel(user, session);
		return result;
	}
    
	// 注册
	@RequestMapping("/register")
	@ResponseBody
	public String register(User user,HttpSession session) {
		System.out.println(user);
		String result = "注册失败";
		result = userService.register(user,session);
		return result;
	}

	// 获取手机动态验证码
	@RequestMapping("/code")
	@ResponseBody
	public String getCode(User user,HttpSession session) {
		String result = "短信发送失败";
		result = userService.sendMessage(user,session);
		return result;
	}
	/**
	 * 忘记密码
	 * @param id
	 * @return
	 */
	@RequestMapping("/forget")
	@ResponseBody
	public String forgetPass(User user,HttpSession session) {
		String result="修改失败";
		result=userService.forgetPass(user,session);
		return result;
	}

	/*
	 * 停用用户账号
	 */
	@RequestMapping("/vipStop")
	@ResponseBody
	public String vipStop(Integer id){
		return userService.vipStop(id);
	}
	/*
	 * 启用用户账号
	 */
	@RequestMapping("/vipStart")
	@ResponseBody
	public String vipStart(Integer id){
		return userService.vipStart(id);
	}
	/*
	 * 删除会员
	 */
	@RequestMapping("/userDelete")
	@ResponseBody
	public String userDelete(Integer id){
		return userService.userDelete(id);
	}
	/*
	 * 获取所有已删除的会员
	 */
	@RequestMapping("/findAllDeletedVips")
	public ModelAndView findAllDeletedVips(){
		ModelAndView mav=new ModelAndView();
		List<User> vips=userService.findAllDeletedVips();
		mav.addObject("allVips", vips);
		mav.setViewName("/backstage-html/vip-deleted.html");
		return mav;
	}
}
