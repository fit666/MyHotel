package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hero.hotel.pojo.Role;
import com.hero.hotel.pojo.RolePermission;
import com.hero.hotel.pojo.ThePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hero.hotel.dao.RolePermissionDao;
import com.hero.hotel.service.RolePermissionService;

@Service("rolePermissionService")
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService{
	@Resource
	private RolePermissionDao rolePermissionDao;
	/*
	 * 查询所有权限(non-Javadoc)
	 * @see com.hero.hotel.service.RolePermissionService#findAllPermissions()
	 */
	@Override
	public List<ThePermission> findAllPermissions() {
		
		return rolePermissionDao.findAllPermissions();
	}
	
	/*
	 * 修改管理员的权限(non-Javadoc)
	 * @see com.hero.hotel.service.RolePermissionService#changePermissions(com.hero.hotel.pojo.RolePermission)
	 */
	@Override
	public String changePermissions(Role data) {
		String result="修改成功";
		//删除原来的权限
		boolean re1=rolePermissionDao.deleteBefore(data);
		if(!re1){
			result="修改失败";
		}
		//获取角色id
		Integer rid=data.getId();
		//获取权限对象
		List<ThePermission> pids=data.getPermission();
		RolePermission rp=new RolePermission();
		rp.setRid(rid);
		for(ThePermission pid:pids){
			Integer pId=pid.getId();
			rp.setPid(pId);
			//添加新的权限
			boolean re2=rolePermissionDao.insertChangePermission(rp);
			if(!re2){
				result="修改失败";
			}
			
		}
		return result;
	}
	
}
