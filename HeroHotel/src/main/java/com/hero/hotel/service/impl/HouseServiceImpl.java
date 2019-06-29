package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hero.hotel.pojo.House;
import org.springframework.stereotype.Service;

import com.hero.hotel.dao.HouseDao;
import com.hero.hotel.service.HouseService;

@Service("houseService")
public class HouseServiceImpl implements HouseService {

	@Resource
	private HouseDao houseDao;

	// @Resource
	// private OrderInforDao orderInforDao;

	// 查询所有房间状态
	@Override
	public List<House> findRoomAllStatus() {
		return houseDao.findRoomAllStatus();
	}

	@Override
	public List<House> findOkRooms() {
		return houseDao.findRooms(1);
	}

	@Override
	public List<House> findNoRooms() {
		return houseDao.findRooms(0);
	}

	@Override
	public List<House> findZangRooms() {
		return houseDao.findRooms(2);
	}
	@Override
	public List<House> findRepairRooms() {
		return houseDao.findRooms(3);
	}

	/*
	 * 下架房间(non-Javadoc)
	 * @see com.hero.hotel.service.HouseService#stopRoom(java.lang.Integer)
	 */
	@Override
	public String stopRoom(Integer id) {
		boolean r=houseDao.stopRoom(id);
		if(r){
			return "success";
		}
		return "失败，请联系工作人员";
	}
	/*
	 * 上架房间(non-Javadoc)
	 * @see com.hero.hotel.service.HouseService#startRoom(java.lang.Integer)
	 */
	@Override
	public String startRoom(Integer id) {
		boolean r=houseDao.stertRoom(id);
		if(r){
			return "success";
		}
		return "失败，请联系相关工作人员";
	}
	/*
	 * 根据房间类型增加房间(non-Javadoc)
	 * @see com.hero.hotel.service.HouseService#addRoom(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String addRoom(Integer typeid, Integer number) {
		String result="添加成功";
		for(int i=0;i<number;i++){
			boolean b=houseDao.addRoom(typeid);
			if(!b){
				result="添加失败，请联系相关工作人员";
			}
		}
		return result;
	}

	

	@Override
	public Boolean addRepairRoom(Integer Houseid) {
	//  通过房间id修改 房间状态为 维修  3
		Boolean  flag = houseDao.changeHouseTypeByHouseid(3, Houseid);
		return flag;
	}

	@Override
	public Boolean updataRoom(Integer id) {
		return houseDao.changeHouseTypeByHouseid(1, id);
	}
	
//	 @Override
//	 public List<Map> findRoomTypeNum(String time1, String time2) {
//	
//	 long day = 0;
//	 java.text.SimpleDateFormat format = new
//	 java.text.SimpleDateFormat("yyyy-MM-dd");
//	 java.util.Date beginDate = null;
//	 java.util.Date endDate;
//	 try {
//	 beginDate = format.parse(time1);
//	 endDate = format.parse(time2);
//	 day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
//	 System.out.println("相隔的天数=" + day);
//	 } catch (Exception e) {
//	 // TODO 自动生成 catch 块
//	 e.printStackTrace();
//	 }
//	 Calendar c = Calendar.getInstance();
//	
//	 List<String> times = new ArrayList<String>();
//	 for (int i = 0; i < day; i++) {
//	
//	 c.setTime(beginDate);
//	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置你想要的格式
//	 c.add(Calendar.DAY_OF_MONTH, i);// 今天+1天
//	 String dateStr = df.format(c.getTime());
//	 times.add(dateStr);
//	
//	 System.out.println(dateStr);
//	 }
//	
//	 // List<String> rids = roomDao.findOrderInforRid(time);
//	 List<List> roomids1 = new ArrayList<List>();
//	 for (int i = 0; i < times.size(); i++) {
//	 System.out.println(times.get(i) + "---------------");
//	 List<Integer> rids = orderInforDao.findOrderInforRid(times.get(i));
//	
//	 if (rids != null) {
//	 roomids1.add(rids);
//	 }
//	
//	 }
//	
//	 List<List> roomids2 = new ArrayList<List>();
//	 for (int i = 0; i < times.size(); i++) {
//	 // System.out.println(times.get(i)+"---------------");
//	 List<Integer> rids = orderInforDao.findOrderInforRid(times.get(i));
//	
//	 roomids2.add(rids);
//	 }
//	 System.out.println(roomids1);
//	
//	 return null;
//	 }

}
