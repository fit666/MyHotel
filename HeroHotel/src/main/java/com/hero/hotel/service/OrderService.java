package com.hero.hotel.service;

import java.text.ParseException;
import java.util.List;

import com.hero.hotel.pojo.Order;

import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.pojo.HouseType;
import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.LiveNotes;
import com.hero.hotel.pojo.Order;
import com.hero.hotel.pojo.OrderItem;
import com.hero.hotel.pojo.User;
import com.hero.hotel.pojo.Vip;

public interface OrderService {
	public List<Order> queryAllOrder(int uid);

	// 订单表插入数据
	public void addOrder(Order order);

	// 订单项插入数据
	public void addOrderItem(OrderItem orderItem);

	// 个人信息表插入数据
	public void addInfo(Info info);

	// 查询个人信息id
	public Info findId(String idcard);

	// 入住日志表插入数据
	public void addLiveNotes(LiveNotes liveNotes);

	// 根据账号id获取消费金额，通过消费金额获取对应的会员折扣
	public User findMonetaryByid(Integer id);

	public Vip findDiscountByMonetary(double vmoney);

	// 查询订单id,根据订单编号查找
	public Order findIdByOrderNumber(String orderNumber);

	// 根据订单id查询所有订单项
	public List<OrderItem> findOrderItemByOrderid(Integer orderid);

	// 查找房间价格
	public HouseType findPriceByTypeid(Integer typeid);

	// 查找该类型的所有房间，查找当天入住日志表中该类房间已经入住的房间，
	public List<Integer> findAllRoomsByTypeid(Integer typeid);

	public List<Integer> findAllliveRoomsByTypeid(LiveNotes liveNotes);

	// 查询所有订单记录
	public List<Info> findAllOrders();

	// 查询某个角色的所有订单
	public List<Info> findOrder(Info info);

	// 查询需要修改的订单信息
	public ModelAndView findUpdateOrder(Integer id);

	// 修改订单信息
	public ModelAndView updateOrder(Info info, Order order, OrderItem orderItem);

	// 查询所有房间类型
	public List<HouseType> findAllType();

	// 结账
	public Boolean settleAccounts(Integer orderItemid, Integer houseid);

	// 取消订单
	public Boolean canceOrder(Integer orderItemid, Integer houseid);

	public Integer findHouseNumberByTypeid(List<String> todays, Integer typeid);

	public void orderSubmit(String id, String currenttime, String name, String sex, String tel, String idcard,
			List<String> todays, List<Integer> housenumber, Integer uid, Double discount, String messgage)
			throws ParseException;

	public Order queryByOrderNumber(String ordernumber);

	public Order findOrderByUserid(Integer userid);

	public List<Integer> findFlagById(Integer id);

	public void InsertPay(Order order);

	public List<Order> findOrders(Integer id);
}
