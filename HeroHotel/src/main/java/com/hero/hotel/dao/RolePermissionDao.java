package com.hero.hotel.dao;

import java.util.List;

import com.hero.hotel.pojo.Role;
import com.hero.hotel.pojo.RolePermission;
import com.hero.hotel.pojo.ThePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface RolePermissionDao {
	//获取所有权限
	@Select("select * from t_permission where flag=1") 
	public List<ThePermission> findAllPermissions();
	
	//修改管理员权限(删除原来的权限)
	@Delete("delete from t_rolepermission where rid=#{id}")
	public boolean deleteBefore(Role data);
	//修改管理员权限（新增修改的权限）
	@Insert("insert into t_rolepermission(rid,pid) values(#{rid},#{pid})")
	public boolean insertChangePermission(RolePermission rp);

}
