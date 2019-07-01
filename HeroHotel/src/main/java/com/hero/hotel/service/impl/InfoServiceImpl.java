package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hero.hotel.dao.InfoDao;
import com.hero.hotel.pojo.Info;
import com.hero.hotel.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService{
	
	@Resource
	private InfoDao infoDao;
	@Override
	public List<Info> queryInfo(Integer id) {
		// TODO Auto-generated method stub
		return infoDao.queryInfoByTel(id);
	}

	@Override
	public boolean addInfo(Info info) {
		// TODO Auto-generated method stub
		
		return infoDao.addInfo(info);
	}

	@Override
	public boolean addManagerInfo(Info info) {
		// TODO Auto-generated method stub
		return false;
	}

}
