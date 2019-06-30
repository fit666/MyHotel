package com.hero.hotel.service.impl;

import java.math.BigDecimal;
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
	/*
	 * //将新的房间类型保存到数据库(non-Javadoc)
	 * @see com.hero.hotel.service.HouseTypeService#addHouseType(java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String)
	 */
	@Override
	public String addHouseType(String hname, String serve, String breakfast, BigDecimal price, String newFileName) {
		HouseType ht=new HouseType();
		ht.setBreakfast(breakfast);
		ht.setHname(hname);
		ht.setPrice(price);
		ht.setServe(serve);
		ht.setImgurl(newFileName);
		String result="";
		boolean flag=houseTypeDao.addHouseType(ht);
		
		if(flag){
			result="添加成功";
		}else{
			result="添加失败，请联系管理员";
		}
		return result;
	}
}
