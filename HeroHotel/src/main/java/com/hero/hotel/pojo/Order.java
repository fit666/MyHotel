
package com.hero.hotel.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * 	订单表
 */
public class Order {
	private Integer orderid;// 订单id
	private Integer userid;// 用户id
	private Integer flag;
	// 修改创建时间和修改时间类型为String类型
	private Date createtime;// 创建时间
	private Date updatetime;// 修改时间
	private String ordernumber;// 订单号
	private String paynumber;
	private String payway;// 支付号
	// 总价类型修改为double
	private BigDecimal total;// 总价
	// 添加字段个人信息id
	private Integer infoid;
	// 添加字段押金，留言
	private double deposit;
	private String message;
	// 订单项
	private List<OrderItem> oderItems;

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", userid=" + userid + ", flag=" + flag + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", ordernumber=" + ordernumber + ", paynumber=" + paynumber
				+ ", payway=" + payway + ", total=" + total + ", infoid=" + infoid + ", deposit=" + deposit
				+ ", message=" + message + ", oderItems=" + oderItems + "]";
	}

	public Order(Integer orderid, Integer userid, Integer flag, Date createtime, Date updatetime, String ordernumber,
			String paynumber, String payway, BigDecimal total, Integer infoid, double deposit, String message,
			List<OrderItem> oderItems) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.flag = flag;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.ordernumber = ordernumber;
		this.paynumber = paynumber;
		this.payway = payway;
		this.total = total;
		this.infoid = infoid;
		this.deposit = deposit;
		this.message = message;
		this.oderItems = oderItems;
	}

	public Order() {

	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getPaynumber() {
		return paynumber;
	}

	public void setPaynumber(String paynumber) {
		this.paynumber = paynumber;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getInfoid() {
		return infoid;
	}

	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderItem> getOderItems() {
		return oderItems;
	}

	public void setOderItems(List<OrderItem> oderItems) {
		this.oderItems = oderItems;
	}

}
