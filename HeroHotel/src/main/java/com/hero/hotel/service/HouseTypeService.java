package com.hero.hotel.service;

import com.hero.hotel.pojo.HouseType;

import java.math.BigDecimal;
import java.util.List;

public interface HouseTypeService {
	
	public List<HouseType> queryAllType();

	public List<HouseType> findAllHouses();

	public List<HouseType> findAllType();
	//将新的房间类型保存到数据库
	public String addHouseType(String hname, String serve, String breakfast, BigDecimal price, String newFileName);
}
