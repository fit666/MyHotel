package com.hero.hotel.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface LiveNotesDao {

	@Update("UPDATE t_livenotes SET flag = #{flag} WHERE orderItemid = #{orderItemid}")
	public Boolean changeType(@Param("flag")Integer flag,@Param("orderItemid")Integer orderItemid);

}
