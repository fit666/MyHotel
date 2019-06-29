package com.hero.hotel.service;

import java.util.Map;

public interface ChartService {

	// 根据天查询     显示  现在 和过去 的  数据
	Map<String, Object> findMoneyDay();
	
	// 根据月查询  显示  现在到 过去 12个月的数据
	Map<String, Object> findMoneyMonth();
	
	// 根据  时间范围查询  显示  查询范围的  数据  默认 一天 为 单位
	Map<String, Object> findMoneyTimeScope(String startTime, String endTime);

}
