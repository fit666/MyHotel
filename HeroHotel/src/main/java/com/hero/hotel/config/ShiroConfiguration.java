package com.hero.hotel.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;

import com.hero.hotel.realm.ManagerRealm;
import com.hero.hotel.realm.UserRealm;
import com.hero.hotel.realm.CustomizedModularRealmAuthenticator;


@Configuration
public class ShiroConfiguration {
	//配置加密
	@Bean
	public CredentialsMatcher matcher() {
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		//加密类型
		matcher.setHashAlgorithmName("MD5");
		//加密次数
		matcher.setHashIterations(1024);
		return matcher;
	}
	
	
	
	//1创建自定义realm
		@Bean
		public UserRealm accountrealm(CredentialsMatcher matcher) {
			System.out.println("创建myrealm");

			UserRealm accountRealm=new UserRealm();
			//设置加密及次数
			accountRealm.setCredentialsMatcher(matcher);
			return accountRealm;
		}
		@Bean
		public ManagerRealm managerRealm(){
			System.out.println("创建ManagerRealm");
			ManagerRealm managerRealm=new ManagerRealm();
			return managerRealm;
		}
		@Bean 
		public AtLeastOneSuccessfulStrategy atl(){
			System.out.println("创建AtLeastOneSuccessfulStrategy");
			AtLeastOneSuccessfulStrategy atlo=new AtLeastOneSuccessfulStrategy();
			return atlo;
		}
		
		//配置自定义认证器
		@Bean 
		public CustomizedModularRealmAuthenticator authenticator(AtLeastOneSuccessfulStrategy at){
			System.out.println("配置自定义认证器");
			CustomizedModularRealmAuthenticator cmra=new CustomizedModularRealmAuthenticator();
			//配置认证策略
			cmra.setAuthenticationStrategy(at);
			return cmra;
		}
		
		
		//2创建安全管理器
		@Bean
		public SecurityManager securityManager(UserRealm userRealm,ManagerRealm managerRealm,CustomizedModularRealmAuthenticator cmra) {
			System.out.println("创建安全管理器");
			DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
			//配置自定义的authenticator
			securityManager.setAuthenticator(cmra);
			//将realm保存在集合
			Collection<Realm> realms=new ArrayList<>();
			realms.add(userRealm);
			realms.add(managerRealm);
			//在安全管理器中添加realm
			securityManager.setRealms(realms);
			return securityManager;
		}

		
		
	//3创建shiro过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("创建过滤器");
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		//配置安全管理器
		bean.setSecurityManager(securityManager);
		//配置拦截器
		//LinkedHashMap是一个根据某种规则有序的hashmap
		Map<String,String> map=new LinkedHashMap<String,String>();
		//配置无需认证的资源
		map.put("/index.html","anon");
		map.put("/login.html", "anon");
		map.put("/static/**", "anon");
		map.put("/login.html", "anon");
		map.put("/register", "anon");
		map.put("/login", "anon");
		map.put("/druid/**", "anon");
		//配置登出
		map.put("/logout", "logout");
		
		//配置需要认证的资源
//		map.put("/**", "authc");
		bean.setFilterChainDefinitionMap(map);
		//配置登录页面
		bean.setLoginUrl("/login.html");
		
		/*//配置登录成功后的页面
		bean.setSuccessUrl("/main.html");*/
		
		//配置未授权的	页面
		bean.setUnauthorizedUrl("/login.html");
		
		return bean;
	}
	
	
	
	
}
