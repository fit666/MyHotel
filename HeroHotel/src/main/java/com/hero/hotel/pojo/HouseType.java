
package com.hero.hotel.pojo;

import java.math.BigDecimal;
import java.util.List;
/*
 * 	房间类型表
 */
public class HouseType {
   
    
private List<House> houses;//房间类型对应的所有房间
    
	private Integer typeid;
	private String hname;
	private String serve;
	private String breakfast;
	private BigDecimal price;
	private String imgurl;
	private Integer flag;
	public HouseType(List<House> houses, Integer typeid, String hname, String serve, String breakfast, BigDecimal price,
			String imgurl, Integer flag) {
		super();
		this.houses = houses;
		this.typeid = typeid;
		this.hname = hname;
		this.serve = serve;
		this.breakfast = breakfast;
		this.price = price;
		this.imgurl = imgurl;
		this.flag = flag;
	}
	public HouseType() {
		super();
	}
	public List<House> getHouses() {
		return houses;
	}
	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getServe() {
		return serve;
	}
	public void setServe(String serve) {
		this.serve = serve;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
	
}
