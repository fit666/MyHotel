package com.hero.hotel.service;

import java.util.List;

import com.hero.hotel.pojo.Info;

public interface InfoService {
	//添加新的信息
	boolean addManagerInfo(Info info);
	
	public List<Info> queryInfo(Integer integer);
	
	public int addInfo(Info info);

}
