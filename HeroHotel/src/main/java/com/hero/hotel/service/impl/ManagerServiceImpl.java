package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hero.hotel.dao.InfoDao;
import com.hero.hotel.dao.ManagerDao;
import com.hero.hotel.realm.CustomizedToken;
import com.hero.hotel.service.ManagerService;
import com.woniu.hotel.enump.LoginType;

@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService{
	private static final String MANAGER_LOGIN_TYPE = LoginType.MANAGER.toString();
	@Resource
	private ManagerDao managerDao;
	@Resource
	private InfoDao infoDao;

	/*
	 * 通过用户账号查找密码(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#findManagerPwd(java.lang.Integer)
	 */

	@Override
	public User findManagerPwd(String account) {
		// TODO Auto-generated method stub
		return managerDao.findManagerPwd(account);
	}
	/*
	 * 获取所有管理员信息(non-Javadoc)
	 * @see com.hero.hotel.service.UserService#findAllManagers()
	 */
	@Override
	public List<User> findAllManagers() {
		
		return managerDao.findAllManagers();
	}
	/*
	 * 将禁用的管理员改为启用(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#enable(java.lang.Integer)
	 */
	@Override
	public boolean enable(Integer id) {
		
		return managerDao.enable(id);
	}
	/*
	 * 把启用的管理员改为停用状态(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#unable(java.lang.Integer)
	 */
	@Override
	public boolean unable(Integer id) {
		
		return managerDao.unable(id);
	}
	/*
	 * 删除管理员(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#deleteManager(java.lang.Integer)
	 */
	@Override
	public boolean deleteManager(Integer id) {
		
		return managerDao.deleteManager(id);
	}
	/*
	 * 根据id获取管理员信息(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#findManagerById(java.lang.Integer)
	 */
	@Override
	public User findManagerById(Integer id) {
		
		return managerDao.findManagerById(id);
	}
	/*
	 * 修改manager的信息(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#updateMessage(com.hero.hotel.pojo.User, com.hero.hotel.pojo.Info)
	 */
	@Override
	public boolean updateMessage(User manager, Info info) {
		//更新管理员角色
		boolean b1=managerDao.updateManagerRole(manager);
		//更新管理员的个人信息
		boolean b2=infoDao.updateManagerMessage(info);
		if(b1 && b2){
			return true;
		}
		return false;
	}
	/*
	 * 添加新的管理员(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#addManager(com.hero.hotel.pojo.User)
	 */
	@Override
	public boolean addManager(User manager,Info info) {
		//判断管理员是否已经存在
		User mana=managerDao.findManagerPwd(manager.getAccount());
		if(mana!=null){
			return false;
		}else{

			//插入新的管理员账号信息
			boolean b2=managerDao.addManager(manager);
			if(b2){
				//获取新插入的管理员账号id
				Integer mid=managerDao.findManagerPwd(manager.getAccount()).getId();
				info.setUserid(mid);
				//插入管理员的信息
				boolean bo=infoDao.addInfo(info);
				if(bo){
					//在管理员的账号表添加infoid
					Integer infoid=infoDao.findInfoidByMid(manager.getAccount());
					System.out.println(infoid);
					//在管理员表中添加个人信息id
					manager.setInfoid(infoid);
					manager.setId(mid);
					boolean boo=managerDao.updateManagerInfo(manager);
					System.out.println(boo);
					if(boo){
						return true;
					}
				}
			}
		}
		return false;
	}
	/*
	 * 登录(non-Javadoc)
	 * @see com.hero.hotel.service.ManagerService#login(com.hero.hotel.pojo.User, java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	public String login(User user, String codeValue, HttpSession session) {
		String result="登录失败";
    	// 获取session中的验证码值
		String codeVa = (String) session.getAttribute("codeValue");
		if (codeVa.equals(codeValue)) {
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				CustomizedToken customizedToken = new CustomizedToken(user.getAccount(),user.getPassword(),
						MANAGER_LOGIN_TYPE);
				Integer rm=user.getRm();
				System.out.println(rm);
				// 记住我
				if (rm != null) {
					customizedToken.setRememberMe(true);
				}
				try {
					currentUser.login(customizedToken);
					//把当前manager的id存在session中
					Integer id=findManagerPwd(user.getAccount()).getId();
					session.setAttribute("managerId",id);
					result="success";
				} catch (IncorrectCredentialsException ice) {
					System.out.println("用户名/密码不匹配！");
					result="用户名或密码错误，请重新输入";
				} catch (LockedAccountException lae) {
					System.out.println("账户已被冻结！");
					result="账户被冻结";
				} catch (AuthenticationException ae) {
					System.out.println(ae.getMessage());
					result="认证出现异常";
				}
			}
		}else{
			return "验证码错误";
		}
		return result;
	}
}
