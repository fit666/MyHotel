package com.hero.hotel.controller;

import java.util.List;

import com.hero.hotel.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	/*
	 * 获取所有角色
	 */
	@RequestMapping("/findAll")
	public ModelAndView findAllRoles(){
		ModelAndView mav=new ModelAndView();
		List<Role> roles=roleService.findAllRoles();
		mav.addObject("roles", roles);
		mav.setViewName("/backstage-html/manager-role.html");
		return mav;
	}
}
