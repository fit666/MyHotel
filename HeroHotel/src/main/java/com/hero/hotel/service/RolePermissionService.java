package com.hero.hotel.service;

import com.hero.hotel.pojo.Role;
import com.hero.hotel.pojo.ThePermission;

import java.util.List;

public interface RolePermissionService {

	List<ThePermission> findAllPermissions();
	//修改管理员的权限
	String changePermissions(Role data);

}
