package com.hero.hotel.service;

import com.hero.hotel.pojo.Vip;

import java.util.List;

public interface VipService {

	
	public List<Vip> queryAllVip();

	//获取所有的会员等级信息
	List<Vip> findAllVips();
	//修改vip信息
	String editVip(Vip vip);
	// 删除vip
	String vipDel(Vip vip);
	//添加vip
	boolean addVip(Vip vip);

}
