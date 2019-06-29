package com.hero.hotel.dao;

import java.util.List;

import com.hero.hotel.pojo.House;
import com.hero.hotel.pojo.LiveNotes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hero.hotel.pojo.House;
import com.hero.hotel.pojo.LiveNotes;

public interface HouseDao {

	@Select("select * from t_House")
	public List<House> findRoomAllStatus();

	@Select("select * from t_House where flag=#{flag}")
	public List<House> findRooms(Integer flag);

	@Select("select roomid from t_orderinfor where today=#{time} and flag=0")
	public List<Integer> findOrderInforRid(String time);

	// 修改 房间状态
	@Update("UPDATE t_house SET flag = #{flag} WHERE id = #{houseid} ")
	public Boolean changeHouseTypeByHouseid(@Param("flag") Integer flag, @Param("houseid") Integer houseid);

	@Select("select * from t_livenotes where typeid=#{typeid}")
	List<LiveNotes> findHouseByType(Integer typeid);

	@Select("select id from t_house where typeid=#{typeid}")
	List<Integer> findHouseidByType(Integer typeid);

	@Insert("insert into t_livenotes(houseid,typeid,date,infoid,flag) values(#{houseid},#{typeid},#{s},#{infoid},1)")
	public void addDay(Integer houseid, int typeid, String s, Integer infoid);

	//根据房间类型id查询所有对应的房间
	@Select("select * from t_house where typeid=#{typeid}")
	public List<House> findAllByTypeid(Integer typeid);

	//下架房间
	@Update("update t_house set flag=0 where id=#{id}")
	public boolean stopRoom(Integer id);

	//上架房间
	@Update("update t_house set flag=1 where id=#{id}")
	public boolean stertRoom(Integer id);

	//根据房间类型添加房间
	@Insert("insert into t_house(typeid) values(#{typeid}) ")
	public boolean addRoom(Integer typeid);
}