
package com.hero.hotel.pojo;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 	订单项表
 */
public class OrderItem {

	private Integer id;// 订单项id
	private Integer typeid;// 房间类型id
	private Integer orderid;// 订单id
	private Integer flag;
	private Integer quantity;// 房间数量
	// 房间数量类型修改为intteger,入住时间类型修改为string
	private Date starttime;// 入住时间
	// 居住天数类型修改为Integer
	private Integer day;// 居住天数
	// 添加属性离开时间
	private Date endtime;
	// 价格修改为double类型
	private BigDecimal price;// 价格
	// 添加字段房间id
	private Integer houseid;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", typeid=" + typeid + ", orderid=" + orderid + ", flag=" + flag + ", quantity="
				+ quantity + ", starttime=" + starttime + ", day=" + day + ", endtime=" + endtime + ", price=" + price
				+ ", houseid=" + houseid + "]";
	}

	public OrderItem(Integer id, Integer typeid, Integer orderid, Integer flag, Integer quantity, Date starttime,
			Integer day, Date endtime, BigDecimal price, Integer houseid) {
		super();
		this.id = id;
		this.typeid = typeid;
		this.orderid = orderid;
		this.flag = flag;
		this.quantity = quantity;
		this.starttime = starttime;
		this.day = day;
		this.endtime = endtime;
		this.price = price;
		this.houseid = houseid;
	}

	public OrderItem() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}

}
