package com.hero.hotel.service;

import com.hero.hotel.pojo.HouseType;

import java.util.List;

public interface HouseTypeService {
	
	public List<HouseType> queryAllType();

	public List<HouseType> findAllHouses();

	public List<HouseType> findAllType();
}
