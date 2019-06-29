package com.hero.hotel.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hero.hotel.dao.OrderDao;
import com.hero.hotel.service.ChartService;

@Service("chartService")
public class ChartServiceImpl implements ChartService {

	@Resource
	private OrderDao orderDao;

	// 每一天的统计
	@Override
	public Map<String, Object> findMoneyDay() {
		return findMap(10);
	}

	// 每个月的统计
	@Override
	public Map<String, Object> findMoneyMonth() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> Time = new ArrayList<String>();
		List<Double> InMoney = new ArrayList<Double>();
		List<Double> OutMoney = new ArrayList<Double>();
		// 获取 12月份
		Calendar cal = Calendar.getInstance();
		// 如果当前日期大于二月份的天数28天或者29天会导致计算月份错误，会多出一个三月份，故设置一个靠前日期解决此问题
		cal.set(Calendar.DAY_OF_MONTH, 1);
		for (int i = 12; i > 0; i--) {
			// 逐次往前推1个月
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);

			String time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
			// 加到 时间 list中
			Time.add(time);
			// 输出测试
			// System.out.println(time);
			// 数据库中查询出
			Double in = orderDao.findMoneyMonthIn(time + "-01");
			if (in == null) {
				in = 0.0;
			}
			InMoney.add(in);
			Double out = orderDao.findMoneyMonthOut(time + "-01");
			if (out == null) {
				out = 0.0;
			}
			OutMoney.add(out);
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + i);
			// System.out.println(time + ":" + orderDao.findMoneyMonthIn(time +
			// "-01") + "压缩包点卡速度就会把空间");
		}
		map.put("Time", Time);
		map.put("InMoney", InMoney);
		map.put("OutMoney", OutMoney);

		return map;
	}

	@Override
	public Map<String, Object> findMoneyTimeScope(String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 接收时间 转换 成 date类型 并计算出相隔天数
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate;
		try {
			beginDate = format.parse(startTime);
			endDate = format.parse(endTime);
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			System.out.println("相隔的天数=" + day);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		map = findMap((int) day);

		return map;
	}

	// 范围查询

	public Map<String, Object> findMap(int day) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		// new Date()为获取当前系统时间
		// System.out.println("为获取当前系统时间:" + df.format(new Date()));
		// 数据库中查数据
		// 循环查询出数据 没一天的 收入 退款
		Calendar c = Calendar.getInstance();
		List<String> dayTime = new ArrayList<String>();
		List<Double> dayInMoney = new ArrayList<Double>();
		List<Double> dayOutMoney = new ArrayList<Double>();

		for (int i = day; i >= 0; i--) {
			c.setTime(new Date());
			c.add(Calendar.DAY_OF_MONTH, -i);
			dayTime.add(df.format(c.getTime()));
			Double in = orderDao.findMoneyDayIn(c.getTime());
			if (in == null) {
				in = 0.0;
			}
			dayInMoney.add(in);
			Double out = orderDao.findMoneyDayOut(c.getTime());
			if (out == null) {
				out = 0.0;
			}
			dayOutMoney.add(out);
		}
		map.put("Time", dayTime);
		map.put("InMoney", dayInMoney);
		map.put("OutMoney", dayOutMoney);

		return map;
	}

}
