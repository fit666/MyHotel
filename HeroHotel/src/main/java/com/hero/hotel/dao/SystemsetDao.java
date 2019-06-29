package com.hero.hotel.dao;

import com.hero.hotel.pojo.SystemSet;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SystemsetDao {
	//获取系统设置表的信息
	@Select("select * from t_systemset")
	public SystemSet findRules();
	//更新系统设置
	@Update("update t_systemset set findvipmessagebyname=#{findvipmessagebyname},findvipmessagebyid=#{findvipmessagebyid},moneytoint=#{moneytoint},alldiscountrate=#{alldiscountrate} where id=1")
	public boolean updateSet(SystemSet set);

}
