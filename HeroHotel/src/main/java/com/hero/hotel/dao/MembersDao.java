package com.hero.hotel.dao;

import com.hero.hotel.pojo.Vip;
import org.apache.ibatis.annotations.Select;

;

public interface MembersDao {
	//根据id查找vip信息
	@Select("select * from members where id=#{mid}")
	public Vip findById(Integer mid);
}
