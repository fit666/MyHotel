package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hero.hotel.dao.UserDao;
import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.UserService;

import java.util.List;




import com.hero.hotel.pojo.Vip;
import com.hero.hotel.realm.CustomizedToken;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.utils.JuHeDemo;
import com.hero.hotel.utils.RegexUtil;
import com.woniu.hotel.enump.LoginType;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	private static final String USER_LOGIN_TYPE = LoginType.USER.toString();

	public UserDao getUserDao() {
		return userDao;
	}
	@Override
	public User queryInfo(int account) {

		return userDao.querySelf(account);
	}
	@Override
	public int updatePwd(User user) {
		// TODO Auto-generated method stub
		return userDao.updatePwd(user);
	}
	// 查找所有vip
	// 查找所有vip
	public List<User> findAllVip() {

		return userDao.findAll();
	}

	// 账号密码登录
	@Override
	public String login(User user, HttpSession session) {
		String result = "登录失败";
		// System.out.println("前端传过来的user："+user+codeValue);
		// 账号校验
		result = checkAccount(user);
		if (!result.equals("账号通过")) {
			return result;
		}
		// 密码校验
		result = passWordCheck(user);
		if (!result.equals("密码通过")) {
			return result;
		}

		// 检测账号是否存在
		User realuser = userDao.findAccountByAccount(user);
		if (realuser == null) {
			result = "该账号不存在";
			return result;
		}
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			System.out.println("进入");
			CustomizedToken customizedToken = new CustomizedToken(user.getAccount(), user.getPassword(),
					USER_LOGIN_TYPE);
			// 记住我
			if (user.getRm() == 1) {
				customizedToken.setRememberMe(true);
			}
			try {
				System.out.println("try");
				currentUser.login(customizedToken);
				result = "登录成功";
				// 将用户所有信息存入session
				// 查找用户对应的vip

				BigDecimal monetar = realuser.getMonetary();
				Double monetary = monetar.doubleValue();
				if (monetary <= 0.0) {
					int i = 1;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				} else if (monetary < 1000.0) {
					int i = 2;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				} else if (monetary < 2500.0) {
					int i = 3;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				} else if (monetary < 5000.0) {
					int i = 4;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				}
				BigDecimal monetary1 = realuser.getMonetary();
				//查找数据库中vip等级
				int i=userDao.findVipId(monetary1);
				Vip vip = userDao.findVipByID(i);
				realuser.setVip(vip);

				session.setAttribute("user", realuser);
				User user22 = (User) session.getAttribute("user");
				System.out.println("session中的：" + user22);
				System.out.println(result);
				return result;
			} catch (Exception ice) {
				result="用户名/密码不匹配！";
				return result;
			} 
		}
		return result;
	}

	// 手机号和动态码登录
	@Override
	public String loginTel(User user, HttpSession session) {
		String result = "登陆失败";
		System.out.println("前端传过来的user：" + user);
		// 手机号校验
		result = tellCheck(user);
		if (!result.equals("手机号通过")) {
			return result;
		}
		// 验证码非空校验
		if (user.getCode() == null) {
			result = "请输入短信验证码";
			return result;
		}
		// 检测手机号是否存在
		User realuser = userDao.findUserByTel(user);
		if (realuser == null) {
			result = "该手机号不存在";
			return result;
		}
		// 获取session中的验证码
		Object otpl_value = session.getAttribute("tpl_value");
		if (otpl_value == null) {
			result = "验证码失效，请重新获取";
			return result;
		}

		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			CustomizedToken customizedToken = new CustomizedToken(user.getTel(), user.getCode(), USER_LOGIN_TYPE);
			// 记住我
			if (user.getRm() == 1) {
				customizedToken.setRememberMe(true);
			}
			try {
				System.out.println("try");
				currentUser.login(customizedToken);
				result = "登录成功";
				// 将用户所有信息存入session
				// 查找用户对应的vip

				BigDecimal monetar = realuser.getMonetary();
				Double monetary = monetar.doubleValue();
				if (monetary <= 0.0) {
					int i = 1;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				} else if (monetary < 1000.0) {
					int i = 2;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				} else if (monetary < 2500.0) {
					int i = 3;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				} else if (monetary < 5000.0) {
					int i = 4;
					Vip vip = userDao.findVipByID(i);
					realuser.setVip(vip);
				}

				session.setAttribute("user", realuser);
				System.out.println(result);
				return result;
			} catch (Exception ice) {
				result="手机号/验证码不匹配！";
				return result;
			}
		}
		return result;
	}

	// 注册
	@Override
	public String register(User user, HttpSession session) {
		String result = "注册失败";
		// 账号校验
		result = checkAccount(user);
		if (!result.equals("账号通过")) {
			// result = "账号未通过";
			return result;
		}
		// 密码校验
		result = passWordCheck(user);
		if (!result.equals("密码通过")) {
			// result = "密码未通过";
			return result;
		}

		// 手机号校验
		result = tellCheck(user);
		if (!result.equals("手机号通过")) {
			// result = "手机号未通过";
			return result;
		}

		// 手机短信验证
		if (user.getCode() == null) {
			result = "请输入验证码";
			return result;
		}
		// 获取session中的验证码
		Object otpl_value = session.getAttribute("tpl_value");
		if (otpl_value == null) {
			result = "验证码失效，请重新发送";
			return result;
		}
		String tpl_value = (String) otpl_value;
		String code = user.getCode();
		if (!code.equals(tpl_value)) {
			result = "验证码不正确，请核对";
			return result;
		}

		// 数据库操作
		// 从数据库检测该账号是否可用
		User realuser = userDao.findUserByAccountTel(user);
		if (realuser != null) {
			result = "账号已存在";
			return result;
		}
		// 给账号密码加密
		user.setPassword(new SimpleHash("MD5", user.getPassword(), null, 1024).toString());
		/*
		 * // 生成创建时间 String createTime = new
		 * SimpleDateFormat("yy/MM/dd HH:mm:ss").format(new Date());
		 */
		user.setCreatetime(new Date());// 将信息插入到数据库
		// 生成创建时间
		// String createTime = new SimpleDateFormat("yy/MM/dd HH:mm:ss").format(new
		// Date());
		Date createTime = new Date();
		user.setCreatetime(createTime);// 将信息插入到数据库
		// 默认角色为1，普通用户
		user.setRoleid(1);
		boolean b = userDao.insertAccount(user);
		if (b) {
			result = "注册成功";
			return result;
		}
		return result;
	}

	// 给手机发送短信（注册或登录时使用）
	public String sendMessage(User user, HttpSession session) {
		String result = "短信发送失败";
		String mobile = user.getTel();// 获取手机号
		int tpl_id = 166892;// 短信模板
		// 获取随机动态码
		String tpl_value = "";
		for (int i = 0; i < 6; i++) {
			int k = (int) (Math.random() * 10);
			tpl_value += k;
		}
		// 将动态码放入session中
		session.setAttribute("tpl_value", tpl_value);
		boolean b1 = JuHeDemo.mobileQuery(mobile, tpl_id, tpl_value);
		// System.out.println(b1);
		if (tpl_value.length() == 6 && b1) {
			result = "短信发送成功，请注意查收";
			System.out.println("验证码：" + tpl_value);
			System.out.println(result);
			return result;
		}
		System.out.println(result);
		return result;
	}

	// 账号验证
	public String checkAccount(User user) {
		String result = "账号校验失败";
		// 非空判断
		if (user == null) {
			return result;
		}
		// 账号非空判断
		if (user.getAccount().length() == 0 || user.getAccount() == null) {
			result = "账号不能为空";
			return result;
		}

		/* 账号格式检测 */
		// 账号为大写或小写字母开头，6-20位字母或数字组成
		if (!user.getAccount().matches(RegexUtil.REGEX_ACCOUNT)) {
			result = "账号不合法，账号为大写或小写字母开头，6-20位字母或数字组成";
			return result;
		}
		result = "账号通过";
		return result;
	}

	// 密码验证
	public String passWordCheck(User user) {
		String result = "密码校验失败";
		// 非空判断
		if (user == null) {
			return result;
		}
		// 密码非空判断
		if (user.getPassword().length() == 0 || user.getAccount() == null) {
			result = "密码不能为空";
			return result;
		}
		/* 密码格式验证 */
		// 密码为数字或字母开头，6-12位字符组成
		if (!user.getPassword().matches(RegexUtil.REGEX_PASSWORD)) {
			result = "密码不合法，账号为大写或小写字母开头，6-20位字母或数字组成";
			return result;
		}
		result = "密码通过";
		return result;
	}

	// 手机验证
	public String tellCheck(User user) {
		String result = "手机号校验失败";
		// 非空判断
		if (user == null) {
			return result;
		}
		// 手机号非空判断
		if (user.getTel().length() == 0 || user.getTel() == null) {
			result = "手机号不能为空";
			return result;
		}
		/* 手机号格式验证 */
		if (!user.getTel().matches(RegexUtil.REGEX_MOBILE)) {
			result = "手机号格式不合法，目前只支持13*，14*，15*，17*，18*号段的手机号";
			return result;
		}
		result = "手机号通过";
		return result;
	}

	// 图形 验证码验证(账号登录时使用)
	public String codeCheck(User user, HttpSession session) {
		String result = "注册失败";
		// 验证码非空判断
		// 获取code
		Object ocode = session.getAttribute("code");
		if (ocode == null) {
			result = "请输入验证码";
			return result;
		}
		// 判断
		String code = (String) ocode;
		if (!user.getCode().equals(code)) {
			result = "验证码错误";
			return result;
		}
		result = "验证码通过";
		return result;
	}

	/*
	 * 停用vip账号(non-Javadoc)
	 * 
	 * @see com.hero.hotel.service.UserService#vipStop(java.lang.Integer)
	 */
	@Override
	public String vipStop(Integer id) {
		boolean boo = userDao.vipStop(id);
		if (boo) {
			return "success";
		}
		return "defeat";
	}

	/*
	 * 启用被通用的用户账号(non-Javadoc)
	 * 
	 * @see com.hero.hotel.service.UserService#vipStart(java.lang.Integer)
	 */
	@Override
	public String vipStart(Integer id) {
		boolean boo = userDao.vipStart(id);
		if (boo) {
			return "success";
		}
		return "defeat";
	}

	/*
	 * 删除用户账号(non-Javadoc)
	 * 
	 * @see com.hero.hotel.service.UserService#userDelete(java.lang.Integer)
	 */
	@Override
	public String userDelete(Integer id) {
		boolean boo = userDao.userDelete(id);
		if (boo) {
			return "success";
		}
		return "defeat";
	}

	/*
	 * 获取所有已删除的会员(non-Javadoc)
	 * 
	 * @see com.hero.hotel.service.UserService#findAllDeletedVips()
	 */
	@Override
	public List<User> findAllDeletedVips() {

		return userDao.findAllDeletedVips();
	}

	/**
	 * 忘记密码，根据账号，手机验证码判断，然后修改密码
	 */
	@Override
	public String forgetPass(User user, HttpSession session) {
		String result = "提交失败";
		// 数据格式校验
		// 校核账号
		// 账号校验
		result = checkAccount(user);
		if (!result.equals("账号通过")) {
			// result = "账号未通过";
			return result;
		}
		// 手机号校验
		result = tellCheck(user);
		if (!result.equals("手机号通过")) {
			// result = "手机号未通过";
			return result;
		}
		// 验证码非空校验
		if (user.getCode() == null) {
			result = "请输入短信验证码";
			return result;
		}

		// 判断用户名和手机号是否存在
		User realuser = userDao.findUserByAccountTel(user);
		if (realuser == null) {
			result = "该用户不存在,请核对账号和手机号";
			return result;
		}
		// 判断
		// 获取session中的验证码
		Object otpl_value = session.getAttribute("tpl_value");
		if (otpl_value == null) {
			result = "验证码失效，请重新获取";
			return result;
		}
		String tpl_value = (String) otpl_value;
		String code = user.getCode();
		if (!code.equals(tpl_value)) {
			result = "验证码不正确，请核对";
			return result;
		}

		//将插入的密码加密
		user.setPassword(new SimpleHash("MD5",user.getPassword(),null,1024).toString());
		//修改密码
		Boolean b=userDao.updatePass(user);
		if(b) {
			result = "修改成功";
			return result;
		}
		return result;
	}

}
