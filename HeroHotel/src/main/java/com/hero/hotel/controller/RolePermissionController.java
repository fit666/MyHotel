package com.hero.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import com.hero.hotel.pojo.Role;
import com.hero.hotel.pojo.ThePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.service.RolePermissionService;
import com.hero.hotel.service.RoleService;

@Controller
@RequestMapping("/permission")
public class RolePermissionController {
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private RoleService roleService;
	/*
	 * 获取所有角色的权限
	 */
	@RequestMapping("/findAllPermission")
	public ModelAndView findAllPermission(){//@RequestParam("id") Integer id
		ModelAndView mav=new ModelAndView();
		//获取所有角色
		List<Role> roles=roleService.findAllRoles();
		//所有角色的权限
		List<ThePermission> permissions=rolePermissionService.findAllPermissions();
		
		/*//获取指定id角色的权限
		List<RolePermission> rolePermission=rolePermissionService.findPermissionsByRid(id);
		System.out.println(rolePermission);*/
		
		mav.addObject("permissions", permissions);
		/*mav.addObject("rolePermission",rolePermission);*/
		mav.addObject("roles", roles);
		mav.setViewName("/backstage-html/manager-permission-update.html");
		
		return mav;
	}
	/*
	 * 修改管理员的权限
	 */
	@RequestMapping("/changePermission")
	public ModelAndView changePermission(@RequestParam("id") Integer id,@RequestParam("pids") Integer[] pids){
		ModelAndView mav=new ModelAndView();
		List<ThePermission> pList=new ArrayList<>();
		
		for(int i=0;i<pids.length;i++){
			ThePermission per=new ThePermission();
			per.setId(pids[i]);
			pList.add(per);
		}
		Role role=new Role();
		role.setId(id);
		role.setPermission(pList);
		String result=rolePermissionService.changePermissions(role);
		mav.addObject("result", result);
		if(result.equals("修改成功")){
			List<Role> roles=roleService.findAllRoles();
			mav.addObject("roles", roles);
			mav.setViewName("/backstage-html/manager-role2.html");
		}
		return mav;
	}
	
}
