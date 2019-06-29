package com.hero.hotel.service;

import com.hero.hotel.pojo.SystemSet;

public interface SystemsetService {
	//获取系统设置表的数据
	SystemSet findRules();

	boolean updateSet(SystemSet set);

}
