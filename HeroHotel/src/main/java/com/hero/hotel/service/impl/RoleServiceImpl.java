package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hero.hotel.pojo.Role;
import org.springframework.stereotype.Service;

import com.hero.hotel.dao.RoleDao;
import com.hero.hotel.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;
	@Override
	public List<Role> findAllRoles() {
		
		return roleDao.findAllRoles();
	}

}
