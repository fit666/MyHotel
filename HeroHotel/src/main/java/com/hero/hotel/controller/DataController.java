package com.hero.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hero.hotel.service.DataService;

/*
 * 数据备份与还原
 */
@Controller
@RequestMapping("/data")
public class DataController {
	@Autowired
	private DataService dataService;
	/*
	 * 数据备份
	 */
	@RequestMapping("/backup")
	@ResponseBody
	public String dataBackup(){
		return dataService.dataBackup();
	}
	
	/*
	 * 数据还原
	 */
	@RequestMapping("/reduction")
	@ResponseBody
	public String dataReduction(){
		return dataService.dataReduction();
	}
}
