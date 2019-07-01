package com.hero.hotel.dao;

import com.hero.hotel.pojo.Info;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hero.hotel.pojo.Info;
import org.apache.ibatis.annotations.Insert;




public interface InfoDao {
	//根据电话查找用户信息
	@Select("select * from t_info where tel=#{tel} and flag=1")
	public Info findById(String tel);
	//根据infoid查找
	@Select("select * from t_info where infoid=#{infoid} and flag=2")
	public Info findByInfoid(Integer infoid);
	//更新管理员的个人信息
	@Update("update t_info set tel=#{tel},uname=#{uname},sex=#{sex} where infoid=#{infoid}")
	public boolean updateManagerMessage(Info info);
	//添加管理员的个人信息
	@Insert("insert into t_info(uname,sex,tel,idcard,userid,flag) values(#{uname},#{sex},#{tel},#{idcard},#{userid},2)")
	public boolean addInfo(Info info);
	//获取新插入的个人信息的id
	@Select("select infoid from t_info where tel=#{tel} and flag=2")
	public Integer findInfoidByMid(String tel);
	
	@Select("select infoid,uname,sex,idcard,tel from t_info where userid=#{infoid}")
	public List<Info> queryInfoByTel(Integer id);


}
