package com.hero.hotel.pojo;
/*
 * 	信息表
 */

import java.util.List;

public class Info {
/*    private Integer infoid;//用户信息id
    private Integer flag;
    private String tel;//手机号
    private String uname;//姓名
    private String sex;//性别
    private String idcard;//身份证号
    private Integer userid;*/
    private List<Order> orders;
    
	private Integer infoid;
	private String tel;
	private String uname;
	private String sex;
	private String idcard;
	private Integer userid;
	private Integer flag;
    
	public Integer getInfoid() {
		return infoid;
	}
	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "Info [infoid=" + infoid + ", flag=" + flag + ", tel=" + tel + ", uname=" + uname + ", sex=" + sex
				+ ", idcard=" + idcard + ", userid=" + userid + ", orders=" + orders + "]";
	}
    
    
    
}
