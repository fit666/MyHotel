package com.hero.hotel.controller;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hero.hotel.pojo.Vip;
import com.hero.hotel.service.VipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.pojo.Vip;
import com.hero.hotel.service.VipService;

@Controller
@RequestMapping("/vip")
public class VipController {
	@Autowired
	private VipService vipService;
	@RequestMapping("/allVips")
	@ResponseBody
	private List<Vip> showAllVip() {

		return vipService.queryAllVip();
	}
	/*
	 * 获取所有的vip信息
	 */
	@RequestMapping("/findLevel")
	public ModelAndView findLevel(){
		ModelAndView mav=new ModelAndView();
		List<Vip> vips=vipService.findAllVips();
		mav.addObject("vips", vips);
		mav.setViewName("/backstage-html/viplevel-list.html");
		return mav;
	}
	/*
	 * 修改vip信息
	 */
	@RequestMapping("/editVip")
	@ResponseBody
	public String editVip(@ModelAttribute Vip vip){
		//判断数据格式是否正确
		if(vip.getDiscount()==null || vip.getVmoney()==null){
			return "数据格式不正确";
		}
		return vipService.editVip(vip);
	}
	/*
	 * 删除vip
	 */
	@RequestMapping("/vipDel")
	@ResponseBody
	public String vipDel(@ModelAttribute Vip vip){
		return vipService.vipDel(vip);
	}
	/*
	 * 添加vip
	 */
	@RequestMapping("/addVip")
	public ModelAndView addVip(@ModelAttribute Vip vip){
		ModelAndView mav=new ModelAndView();
	
		boolean re=vipService.addVip(vip);
		if(re){
			List<Vip> vips=vipService.findAllVips();
			mav.addObject("vips", vips);
			mav.setViewName("/backstage-html/viplevel-list.html");
		}
		return mav;
	}

}
