package com.hero.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hero.hotel.pojo.NowUserInfo;


public interface UserInfoDao {

	@Select("select  l.id, l.houseid, l.typeid,i.tel,i.uname,i.sex,	i.idcard from t_livenotes l,t_info i WHERE l.infoid = i.infoid")
	public List<NowUserInfo> findUserInfo();

}
