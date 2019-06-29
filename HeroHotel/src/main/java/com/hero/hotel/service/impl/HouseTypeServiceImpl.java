package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hero.hotel.pojo.HouseType;
import org.springframework.stereotype.Service;

import com.hero.hotel.dao.HouseTypeDao;
import com.hero.hotel.service.HouseTypeService;
@Service
public class HouseTypeServiceImpl implements HouseTypeService {
	@Resource
	private HouseTypeDao houseTypeDao;
	@Override
	public List<HouseType> queryAllType() {
		// TODO Auto-generated method stub
		return houseTypeDao.queryAllType();
	}
	/*
	 * 查询所有房间信息(non-Javadoc)
	 * @see com.hero.hotel.service.HouseService#findAllHouses()
	 */
	@Override
	public List<HouseType> findAllHouses() {
		
		return houseTypeDao.findAllHouses();
	}
	/*
	 * 获取所有的房间类型(non-Javadoc)
	 * @see com.hero.hotel.service.HouseTypeService#findAllType()
	 */
	@Override
	public List<HouseType> findAllType() {
		
		return houseTypeDao.findAllType();
	}
}
