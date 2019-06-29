package com.hero.hotel.dao;

import java.util.List;

import com.hero.hotel.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface ManagerDao {
	//根据账号查询密码
	@Select("select * from t_user where account=#{account} and flag=1 and roleid!=1")
	public User findManagerPwd(String account);

	// 获取所有管理员信息
	@Select("select * from t_user where roleid!=1 and flag!=0")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "account", property = "account"),
			@Result(column = "tel", property = "tel"), @Result(column = "createtime", property = "createtime"),
			@Result(column = "monetary", property = "monetary"),
			@Result(column = "infoid", property = "info",
				one = @One(select = "com.hero.hotel.dao.InfoDao.findByInfoid")),
			@Result(column = "roleid", property = "role", 
				one = @One(select = "com.hero.hotel.dao.RoleDao.findRoleById")) 
	})
	public List<User> findAllManagers();
	//将禁用的管理员改为启用
	@Update("update t_user set flag=1 where id=#{id}")
	public boolean enable(Integer id);
	//把启用的管理员改为停用状态
	@Update("update t_user set flag=2 where id=#{id}")
	public boolean unable(Integer id);
	//删除管理员
	@Update("update t_user set flag=0 where id=#{id}")
	public boolean deleteManager(Integer id);
	//根据id获取管理员信息
	@Select("select * from t_user where id=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "account", property = "account"),
		@Result(column = "tel", property = "tel"), @Result(column = "createtime", property = "createtime"),
		@Result(column = "monetary", property = "monetary"),
		@Result(column = "infoid", property = "info",
			one = @One(select = "com.hero.hotel.dao.InfoDao.findByInfoid")) 
	})
	public User findManagerById(Integer id);
	//修改管理员的角色
	@Update("update t_user set roleid=#{roleid} where id=#{id}")
	public boolean updateManagerRole(User manager);
	//插入新的管理员账号信息
	@Insert("insert into t_user(account,password,tel,createtime,roleid,infoid) values (#{account},#{password},#{tel},#{createtime},#{roleid},#{infoid})")
	public boolean addManager(User manager);
	//在管理员表中添加个人信息id
	@Update("update t_user set infoid=#{infoid} where id=#{id}")
	public boolean updateManagerInfo(User manager);
}
