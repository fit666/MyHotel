package com.hero.hotel.service;

        import java.util.List;

        import com.hero.hotel.pojo.House;


public interface HouseService {

    // 查询 当前所有的房间
    public List<House> findRoomAllStatus();

    //  查询当前可已入住的房间
    public List<House> findOkRooms();

    //查询 当前 不能入住的房间
    public List<House> findNoRooms();

    // 查询当前 脏的房间
    public List<House> findZangRooms();

    // 查询当前维修的房间
    public List<House> findRepairRooms();

    //下架房间
    public String stopRoom(Integer id);

    //上架房间
    public String startRoom(Integer id);

    /**
     *根据房间类型增加房间*/
    public String addRoom(Integer typeid, Integer number);

    // 通过给定的时间范围 查询出 不同类型房间剩余可用的数量
//	public List<Map> findRoomTypeNum(String time1, String time2);

    // 添加 需要维修的房间
    public Boolean addRepairRoom(Integer Houseid);

	public Boolean updataRoom(Integer id);
}