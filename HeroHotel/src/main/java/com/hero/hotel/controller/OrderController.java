package com.hero.hotel.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.pojo.HouseType;
import com.hero.hotel.pojo.Info;
import com.hero.hotel.pojo.LiveNotes;
import com.hero.hotel.pojo.Order;
import com.hero.hotel.pojo.OrderItem;
import com.hero.hotel.pojo.User;
import com.hero.hotel.pojo.Vip;
import com.hero.hotel.service.OrderService;
import com.hero.hotel.utils.RegexUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;

	@RequestMapping("/addorder")
	public ModelAndView addOrder(Double discount, Info info, Order order, OrderItem orderItem,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
		// 入住时间
		int day = (int) (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
		// 计算总价:根据从前端获取的房间数量和房间价格，再从会员表中获取的折扣
		// 从作用域中获取登录账号id
		// 获取入住天数
		int id = 1;// 还未获取
		// 插入个人信息表
				Info info2 = orderService.findId(info.getIdcard());
				if (info2!=null) {
					order.setInfoid(info2.getInfoid());// 存入个人信息id
				} else {
					orderService.addInfo(info);
					Info info3 = orderService.findId(info.getIdcard());
					order.setInfoid(info3.getInfoid());// 存入个人信息id
				}

		// 从前台查询顾客信息直接获取对应的折扣
		double total1;
		// 总价
		// 查询房间单价
		HouseType houseType = orderService.findPriceByTypeid(orderItem.getTypeid());
		BigDecimal b = houseType.getPrice();
		int price = b.intValue();
		total1 = price * orderItem.getQuantity() * discount * day + order.getDeposit();
		BigDecimal total = new BigDecimal(total1);
		order.setTotal(total);// 存入总价
		// 获取订单生成时间
		Date date = new Date();
		order.setCreatetime(date);// 存入生成时间
		order.setUpdatetime(date);// 存入修改时间
		// 订单编号
		String orderNumber = "" + System.currentTimeMillis() + id + new Random().nextInt(10);
		order.setOrdernumber(orderNumber);// 存入订单编号
		orderService.addOrder(order);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 插入房间记录表
		/*
		 * 查询可用的房间
		 */
		// 查询所有房间id
		LiveNotes liveNotes = new LiveNotes();
		liveNotes.setTypeid(orderItem.getTypeid());

		List<String> allDay = new ArrayList<>();
		// 保留前端传入的入住时间
		Date date3 = date1;
		while (date1.getTime() != date2.getTime()) {

			allDay.add(sdf.format(date1));
			date1 = new Date(date1.getTime() + 24 * 60 * 60 * 1000);
		}
		// 所有房间id
		List<Integer> roomIds = orderService.findAllRoomsByTypeid(orderItem.getTypeid());
		// 将日期转换为字符串
		for (int i = 0; i < allDay.size(); i++) {
			liveNotes.setDate(allDay.get(i));
			// 查询已经入住的房间id
			List<Integer> liveRoomIds = orderService.findAllliveRoomsByTypeid(liveNotes);
			roomIds.removeAll(liveRoomIds);
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("backstage-html/add-oder.html");
		if (roomIds.size() <= 0) {
			return model;
		}
		// 查询个人信息id
		Order order2 = orderService.findIdByOrderNumber(orderNumber);
		Info newinfo = orderService.findId(info.getIdcard());

		// 将个人信息id加入入住信息
		if (roomIds.size() >= orderItem.getQuantity()) {

			liveNotes.setInfoid(newinfo.getInfoid());
			// 将可入住房间加入入住信息表
			int roomnumber = orderItem.getQuantity();
			for (int i = 0; i < roomnumber; i++) {
				liveNotes.setHouseid(roomIds.get(i));
				orderItem.setPrice(houseType.getPrice());// 插入价格
				orderItem.setOrderid(order2.getOrderid());// 存入订单id
				orderItem.setStarttime(date3);// 存入入住时间
				orderItem.setEndtime(date2);
				orderItem.setQuantity(1);
				orderItem.setDay(day);// 存入入住天数
				orderItem.setHouseid(liveNotes.getHouseid());
				orderService.addOrderItem(orderItem);
				// 订单根据订单id获取所有订单项id
				List<OrderItem> orderItemids = orderService.findOrderItemByOrderid(order2.getOrderid());
				liveNotes.setOrderItemid(orderItemids.get(i).getId());
				for (int k = 0; k < allDay.size(); k++) {
					liveNotes.setDate(allDay.get(k));
					orderService.addLiveNotes(liveNotes);

				}
			}

		}
		model.setViewName("backstage-html/add-oder.html");
		return model;
	}
	
	// 查找所有房间类型
		@RequestMapping("/findalltype")
		@ResponseBody
		public List<HouseType> findAllType() {
			List<HouseType> types = orderService.findAllType();
			return types;
		}


	// 查找某位客人的所有订单记录
	@RequestMapping("/findorder")
	public ModelAndView findOrder(Info info) {
		ModelAndView model = new ModelAndView();
		List<Info> infos = orderService.findOrder(info);
		model.addObject("infos", infos);
		model.setViewName("backstage-html/findOrder.html");
		return model;
	}

	// 查询所有订单记录
	@RequestMapping("/findallorder")
	public ModelAndView findAllOrder() {
		ModelAndView model = new ModelAndView();
		List<Info> infos = orderService.findAllOrders();
		model.addObject("infos", infos);
		model.setViewName("backstage-html/findOrder.html");
		return model;
	}

	// 查询修改订单信息
	@RequestMapping("/findupdate")
	public ModelAndView findupdate(Integer id) {
		ModelAndView model = new ModelAndView();
		model = orderService.findUpdateOrder(id);
		model.setViewName("backstage-html/updateorder.html");
		return model;
	}

	// 修改订单信息
	@RequestMapping("/updateorder")
	public ModelAndView updateOder(Info info, Order order, OrderItem orderItem,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		orderItem.setStarttime(date1);
		orderItem.setEndtime(date2);
		Date date = new Date();// 获取当前修改订单时间
		order.setUpdatetime(date);
		model = orderService.updateOrder(info, order, orderItem);
		List<Info> infos = orderService.findAllOrders();
		model.addObject("infos", infos);
		model.setViewName("backstage-html/findOrder.html");
		return model;
	}

	// 结账 要跳 那些页面 你来定
	@RequestMapping("/settleAccounts")
	public ModelAndView settleAccounts(Integer id, Integer houseid) {
		Boolean flag = orderService.settleAccounts(id, houseid);
		/*
		 * lining
		 */
		ModelAndView model = new ModelAndView();
		if (flag) {
			List<Info> infos = orderService.findAllOrders();
			model.addObject("infos", infos);
			model.setViewName("backstage-html/findOrder.html");
		}
		return model;
	}

	// 取消订单 要跳 那些页面 你来定
	@RequestMapping("/canceOrder")
	public ModelAndView canceOrder(Integer id, Integer houseid) {
		Boolean flag = orderService.settleAccounts(id, houseid);
		ModelAndView model = new ModelAndView();
		if (flag) {
			List<Info> infos = orderService.findAllOrders();
			model.addObject("infos", infos);
			model.setViewName("backstage-html/findOrder.html");
		}
		return model;

	}

	
	
	

	@RequestMapping("/allOrders")
	@ResponseBody
	public List<Order> queryAllOrder(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("user");
		User user = (User) obj;
		List<Order> orders = orderService.findOrders(user.getId());

		return orders;
	}
	
	
	
	
	
	//根据时间段检索房间信息  code by sxj
		@RequestMapping("/findHouseByDays")
		@ResponseBody
		public Integer[] findHouseByDays(Date livetime,Integer days,HttpSession session){

			//获得 入住时间的时间戳
			long times=livetime.getTime();
			//用一个数组装下所有日期
			List<String> todays=new ArrayList<>();
			for (int i = 0; i < days; i++) {
				long newTimes=times+1000*60*60*24*i;
				String ymd = new SimpleDateFormat("yyyy-MM-dd").format(new Date(newTimes)).toString();
				todays.add(ymd);
			}

			//用一个简单的数组装，下标是房间类型
	        Integer[] houseNumberAbleIive = new Integer[5];
			houseNumberAbleIive[0]=0;
	        for (int i = 1; i < 5 ; i++) {  //i是房间类型
	            Integer houseNumber=orderService.findHouseNumberByTypeid(todays,i);
	            houseNumberAbleIive[i]=houseNumber;
	        }
			//把这个数组装下的日期传进session，订单用
			session.setAttribute("timeslot",todays);
			return houseNumberAbleIive;
		}
		//提交用户的预订单，code by sxj
		@RequestMapping("/createorder")
		@ResponseBody
		public String createOrder(String message,String hn,String name,String tel,String sex,String idcard,HttpSession session) throws ParseException {
			List<Integer> housenumber= JSONArray.fromObject(hn);  //真的好厉害啊，那前端数组处理的很漂亮

	        //获得当前时间currenttime
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	        String currenttime=df.format(new Date()).toString();// new Date()为获取当前系统时间

			//随机生成一个订单号id
			Random random=new Random();
			int rannum= (int)(random.nextDouble()*(99999-10000 + 1))+ 10000;
			long date = new Date().getTime();
			String ordernumber = String.valueOf(rannum) + "" + String.valueOf(date);

			//从session直接拿出要用的时间段
			List<String> todays=(List)session.getAttribute("timeslot");


			String result = "";
			User user = (User) session.getAttribute("user");
			System.out.println("user:"+user);
			if (user==null){
				result="请先登录";
				return result;
			}
			System.out.println("useraaa");
			Integer id = user.getId();
			System.out.println("用户的id:"+id);
			Vip vip = (Vip) user.getVip();
			System.out.println(vip.getDiscount());

			//如果这个用户已经有订单了，需要先将这个订单处理了
			List<Integer> flags = orderService.findFlagById(id);
			System.out.println(flags);
			for (int i = 0; i < flags.size(); i++) {
				if(flags.get(i).equals(2)){
					result="你还有订单未处理";
					return result;
				}
			}

			//在service里面去一次性把所有业务处理了
			//Integer id = 1;  //从session获得一个id


			Double discount = vip.getDiscount();   //从session获得会员等级得到折扣


			if(!tel.matches(RegexUtil.REGEX_MOBILE)){
				result="手机号码格式不正确";
				return result;
			}
			else if(!idcard.matches(RegexUtil.REGEX_ID_CARD)){
				result="身份证格式不正确";
				return result;
			}else if(name.equals("")){
				result="请输入姓名";
				return result;
			}else if(housenumber.get(1).equals(0)&&housenumber.get(2).equals(0)&&housenumber.get(3).equals(0)&&housenumber.get(4).equals(0)){
				result="请添加住房信息";
				return result;
			}

			else {
				orderService.orderSubmit(ordernumber,currenttime,name,sex,tel,idcard,
						todays,housenumber,id,discount,message);
				result="订单生产";
			}

	 		return result;
		}
	

}
