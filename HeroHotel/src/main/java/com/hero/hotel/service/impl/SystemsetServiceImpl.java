package com.hero.hotel.service.impl;

import javax.annotation.Resource;

import com.hero.hotel.pojo.SystemSet;
import org.springframework.stereotype.Service;

import com.hero.hotel.dao.SystemsetDao;
import com.hero.hotel.service.SystemsetService;

@Service("systemsetService")
public class SystemsetServiceImpl implements SystemsetService{
	@Resource
	private SystemsetDao systemsetDao;
	/*
	 * 查询计费规则和会员查询规则(non-Javadoc)
	 * @see com.hero.hotel.service.SystemsetService#findRules()
	 */
	@Override
	public SystemSet findRules() {
		return systemsetDao.findRules();
	}
	/*
	 * 跟新系统设置(non-Javadoc)
	 * @see com.hero.hotel.service.SystemsetService#updateSet(com.hero.hotel.pojo.SystemSet)
	 */
	@Override
	public boolean updateSet(SystemSet set) {
		
		return systemsetDao.updateSet(set);
	}

}
