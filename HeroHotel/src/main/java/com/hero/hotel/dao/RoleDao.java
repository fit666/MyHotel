package com.hero.hotel.dao;

import java.util.List;

import com.hero.hotel.pojo.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleDao {
	
	@Select("select * from t_role where flag=1") 
	public List<Role> findAllRoles();
	
	//根据角色id获取角色信息
	@Select("select * from t_role where id=#{id} and flag=1")
	public Role findRoleById(Integer id);
}
