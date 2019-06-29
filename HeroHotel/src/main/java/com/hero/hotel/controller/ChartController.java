package com.hero.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.service.ChartService;

@Controller
@RequestMapping("/chart")
public class ChartController {

	@Resource
	private ChartService chartService;

	@GetMapping("/findMoney")
	public ModelAndView findMoneyInAndOut(String startTime, String endTime) {

		System.out.println(startTime);
		System.out.println(endTime);

		return null;
	}

	// 根据天查询
	@GetMapping("/findMoneyDay")
	public ModelAndView findMoneyDay() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map = chartService.findMoneyDay();
		modelAndView.addObject("map", map);
		modelAndView.setViewName("backstage-html/chart.html");
		return modelAndView;

	}

	// 根据 月查询()
	@GetMapping("/findMoneyMonth")
	public ModelAndView findMoneyMonth() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map = chartService.findMoneyMonth();
		modelAndView.addObject("map", map);
		modelAndView.setViewName("backstage-html/chart.html");
		return modelAndView;

	}

	// 根据 时间范围 查询
	@GetMapping("/findMoneyTimeScope")
	public ModelAndView findMoneyTimeScope(String startTime, String endTime) {

		// System.out.println("-------------------------------------");
		// System.out.println(startTime + "-------" + endTime);
		// System.out.println("+++++++++++++++++++++++++++++");

		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map = chartService.findMoneyTimeScope(startTime, endTime);
		modelAndView.addObject("map", map);
		modelAndView.setViewName("backstage-html/chart.html");
		return modelAndView;
	}

}
