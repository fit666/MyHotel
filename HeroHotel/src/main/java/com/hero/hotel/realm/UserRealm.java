package com.hero.hotel.realm;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.hero.hotel.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hero.hotel.dao.UserDao;
import com.hero.hotel.utils.RegexUtil;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("正在授权");
		//获取登录账号(手机号或账号)
		String account=(String) principals.getPrimaryPrincipal();
	       //判断账号是手机号或者账号
		String role="";
		if(account.matches(RegexUtil.REGEX_MOBILE)) {
			//根据手机号查询账户角色
			 role=userDao.findRoleByTel(account);
			System.out.println("用户角色"+role);
		}else {
			//根据账号查询账户角色
			 role=userDao.findRoleByAccount(account);
			System.out.println("用户角色"+role);
		}
		
		Set<String> roles = new HashSet<>();
		roles.add(role);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
		  return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取token中的账号
		String account=(String) token.getPrincipal();
		System.out.println("account:"+account);
		System.out.println("正在认证");
		System.out.println("realm:"+this.getName());
		//判断账号是手机号还是用户名，账号为字母开头，手机号为11位纯数字
		if(account.matches(RegexUtil.REGEX_MOBILE)) {
			//手机登录
			//从session中获取动态码
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			        .getRequestAttributes()).getRequest();
			String tpl_value=(String) request.getSession().getAttribute("tpl_value");
			System.out.println("tpl_value:"+tpl_value);
			//将验证码加密与realm保持一致
			String code= new SimpleHash("MD5",tpl_value,null,1024).toString();
			SimpleAuthenticationInfo info=
		new SimpleAuthenticationInfo(account,code,getName());
			return info;
		}else {
			//账号密码登录
			//从数据库中查找密码
			System.out.println("账号密码验证");
			User u=new User();
			u.setAccount(account);
			User user=userDao.findAccountByAccount(u);
			
			SimpleAuthenticationInfo info=
					new SimpleAuthenticationInfo(account,user.getPassword(),getName());
			return info;
		}
	}


}
