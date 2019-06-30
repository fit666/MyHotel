package com.hero.hotel.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hero.hotel.pojo.User;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.hero.hotel.pojo.Vip;

public interface UserDao {
	@Select("select account,password,tel,monetary,infoid from t_user where account=#{account} and flag=1")
	public User querySelf(int account);

	@Update("update t_user set password=#{password} where tel=#{tel}")
	public int updatePwd(User user);

	// 查询所有用户
	@Select("select * from t_user where roleid=1 and flag!=0")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "account", property = "account"),
			@Result(column = "tel", property = "tel"), @Result(column = "createtime", property = "createtime"),
			@Result(column = "monetary", property = "monetary"),
			@Result(column = "tel", property = "info", one = @One(select = "com.hero.hotel.dao.InfoDao.findById")),
			@Result(column = "monetary", property = "vip", one = @One(select = "com.hero.hotel.dao.VipDao.findById")) })
	public List<User> findAll();

	// 查询数据库用户名（注册或登录时使用）
	@Select("select * from t_user where account=#{account}  and flag=1")
	public User findAccountByAccount(User user);

	// 插入注册的信息到数据库（注册时使用）
	@Insert("insert into t_user(account,password,tel,roleid) values(#{account},#{password},#{tel},#{roleid})")
	public boolean insertAccount(User user);

	// 停用vip账号
	@Update("update t_user set flag=2 where id=#{id}")
	public boolean vipStop(Integer id);

	// 启用vip账号
	@Update("update t_user set flag=1 where id=#{id}")
	public boolean vipStart(Integer id);

	// 删除用户
	@Update("update t_user set flag=0 where id=#{id}")
	public boolean userDelete(Integer id);

	// 获取所有已删除的会员
	@Select("select * from t_user where roleid=1 and flag=0")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "account", property = "account"),
			@Result(column = "tel", property = "tel"), @Result(column = "createtime", property = "createtime"),
			@Result(column = "monetary", property = "monetary"),
			@Result(column = "tel", property = "info", one = @One(select = "com.hero.hotel.dao.InfoDao.findById")),
			@Result(column = "monetary", property = "vip", one = @One(select = "com.hero.hotel.dao.VipDao.findById")) })
	public List<User> findAllDeletedVips();

	// 查询账户通过手机号
	@Select("select * from t_user where tel=#{tel} and flag=1")
	public User findUserByTel(User user);

	// 查vip信息
	@Select("select * from t_vip where id=#{id} and flag=1 ")
	public Vip findVipByID(Integer id);

	// 查找用户和手机号是否存在(忘记密码使用)
	@Select("select * from t_user where account=#{account} and tel=#{tel} and flag=1 ")
	public User findUserByAccountTel(User user);

	@Update("update t_user set password=#{password} where account=#{account} and tel=#{tel} and flag=1 ")
	public Boolean updatePass(User user);

	// 查找vip
	@Select("select max(id) from t_vip where vmoney<=#{money} and flag=1")
	public Integer findVipId(BigDecimal money);

	// 查找用户名查找角色
	@Select("select t_role.rolename from t_user inner join t_role on t_user.roleid=t_role.id where t_user.account=#{account} and t_user.flag=1 ")
	public String findRoleByAccount(String account);

	// 根据手机号查找角色
	@Select("select t_role.rolename from t_user inner join t_role on t_user.roleid=t_role.id where t_user.tel=#{tel} and t_user.flag=1 ")
	public String findRoleByTel(String tel);
	
	//查找账号角色id
	@Select("select roleid from t_user where account=#{account} and flag=1 ")
	public Integer findRoleId(User user);

}