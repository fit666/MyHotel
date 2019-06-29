package com.hero.hotel.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.Role;
import com.hero.hotel.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.Role;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.ManagerService;
import com.hero.hotel.service.RoleService;
import com.hero.hotel.utils.RegexUtil;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	
	 @Autowired
	 private ManagerService managerService;
	 @Autowired
	 private RoleService roleService;
	
	 
    @RequestMapping("/login")
    @ResponseBody
	public String login(User user, String codeValue, HttpSession session) {
    
    	String result=managerService.login(user,codeValue,session);
    	
		return result;
	}
	 
	 

	 
	/*
	 * 获取所有管理员
	 */
	@RequestMapping("/findAllManagers")
	public ModelAndView findAllManagers(){
		ModelAndView mav=new ModelAndView();
		List<User> managers=managerService.findAllManagers();
		mav.addObject("managers",managers);
		mav.setViewName("/backstage-html/manager-list.html");
		return mav;
	}
	/*
	 * 把停用的管理员改为启用状态
	 */
	@RequestMapping("/enable")
	public String enable(@RequestParam("id") Integer id){
		//修改flag状态
		boolean result=managerService.enable(id);
		if(result){
			return "success";
		}
		return "defeat";
	}
	/*
	 * 把启用的管理员改为停用状态
	 */
	@RequestMapping("/unable")
	public String unable(@RequestParam("id") Integer id){
		//修改flag状态
		boolean result=managerService.unable(id);
		if(result){
			return "success";
		}
		return "defeat";
	}
	/*
	 * 删除管理员
	 */
	@RequestMapping("/deleteManager")
	public String deleteManager(@RequestParam("id") Integer id){
		//修改flag状态
		boolean result=managerService.deleteManager(id);
		if(result){
			return "success";
		}
		return "defeat";
	}
	/*
	 * 根据id找到管理员信息和角色
	 */
	@RequestMapping("/findManager")
	public ModelAndView findManager(@RequestParam("id") Integer id){
		ModelAndView mav=new ModelAndView();
		Map<String,Object> map=new HashMap<>();
		//获取管理员信息
		User manager=managerService.findManagerById(id);
		//获取所有角色
		List<Role> roles=roleService.findAllRoles();

		mav.addObject("manager", manager);
		mav.addObject("roles", roles);
		mav.setViewName("/backstage-html/manager-edit.html");
		return mav;
	}
	/*
	 * 修改manager的信息
	 */
	@RequestMapping("/updateMessage")
	public ModelAndView updateMessage(String uname,Integer infoid,Integer id,String sex,String tel,Integer roleid){
		ModelAndView mav=new ModelAndView();
		User manager=new User();
		manager.setRoleid(roleid);
		manager.setId(id);
		Info info=new Info();
		info.setInfoid(infoid);
		info.setUname(uname);
		info.setSex(sex);
		info.setTel(tel);
		manager.setInfo(info);
		System.out.println(manager+","+info);
		//更新管理员信息
		boolean result=managerService.updateMessage(manager,info);
		System.out.println(result+"-----------------------");
		if(result){
			System.out.println("修改成功");
			mav.addObject("result","修改成功");
			//获取管理员信息
			manager=managerService.findManagerById(id);
			//获取所有角色
			List<Role> roles=roleService.findAllRoles();

			mav.addObject("manager", manager);
			mav.addObject("roles", roles);
			mav.setViewName("/backstage-html/manager-edit2.html");
		}
		return mav;
		
	}
	/*
	 * 获取所有角色返回到添加管理员页面
	 */
	@RequestMapping("/getAllRoles")
	public ModelAndView getAllRoles(){
		ModelAndView mav=new ModelAndView();
		List<Role> roles=roleService.findAllRoles();
		mav.addObject("roles",roles);
		mav.setViewName("/backstage-html/manager-add.html");
		return mav;
	}
	/*
	 * 添加管理员
	 */
	@RequestMapping("/addManager")
	public ModelAndView addManager(String name,String password,String sex,String tel,String idcard,Integer roleid){
		ModelAndView mav=new ModelAndView();
		//验证手机号是否正确
		if(!tel.matches(RegexUtil.REGEX_MOBILE)){
			return mav;
		}
		//验证密码
		if(!password.matches(RegexUtil.REGEX_PASSWORD)){
			return mav;
		}
		//验证身份证号
		if(!idcard.matches(RegexUtil.REGEX_ID_CARD)){
			return mav;
		}
		User manager=new User();
		manager.setAccount(tel);
		manager.setPassword(password);
		manager.setTel(tel);
		Date date=new Date();
		manager.setCreatetime(date);
		manager.setRoleid(roleid);
		
		Info info=new Info();
		info.setIdcard(idcard);
		info.setSex(sex);
		info.setTel(tel);
		info.setUname(name);

	

		boolean b1=managerService.addManager(manager,info);
		if(b1){
			List<User> managers=managerService.findAllManagers();
			System.out.println(managers);
			mav.addObject("managers",managers);
			mav.setViewName("/backstage-html/manager-list.html");
		}else{
			mav.addObject("result","添加失败，该账号已注册");
			mav.addObject("manager", manager);
			mav.addObject("info", info);
			List<Role> roles=roleService.findAllRoles();
			
			mav.addObject("roles",roles);
			mav.setViewName("/backstage-html/manager-add2.html");
		}
		return mav;
	}
	
	
}
