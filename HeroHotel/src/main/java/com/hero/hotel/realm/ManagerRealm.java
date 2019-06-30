package com.hero.hotel.realm;

import com.hero.hotel.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import com.hero.hotel.service.ManagerService;

public class ManagerRealm extends AuthorizingRealm{
	@Autowired
	private ManagerService managerService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("正在授权");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//获取账号
		String account=(String)token.getPrincipal();
		User manager=managerService.findManagerPwd(account);
		System.out.println(manager);
		if(manager==null){
			return null;
		}
		SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(manager.getAccount(),manager.getPassword(),getName());
				
		return info;
	}

}
