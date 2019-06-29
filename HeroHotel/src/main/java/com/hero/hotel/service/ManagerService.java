package com.hero.hotel.service;

import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.User;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface ManagerService {
	//通过账号查找密码
	 User findManagerPwd(String account);
	 //获取所有管理员
	List<User> findAllManagers();
	//将禁用的管理员改为启用
	boolean enable(Integer id);
	//把启用的管理员改为停用状态
	boolean unable(Integer id);
	//删除管理员
	boolean deleteManager(Integer id);
	//根据id获取管理员信息
	User findManagerById(Integer id);
	//修改manager的信息
	boolean updateMessage(User manager, Info info);
	//添加新的管理员
	boolean addManager(User manager, Info info);
	//登录
	String login(User user, String codeValue, HttpSession session);
}
