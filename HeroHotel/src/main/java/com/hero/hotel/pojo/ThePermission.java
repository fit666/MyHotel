package com.hero.hotel.pojo;

public class ThePermission {
	private Integer id;
	private String pname;//权限名
	private Integer rid;//2表示前台管理，3表示系统管理
	private int flag;
	public ThePermission(Integer id, String pname, int flag) {
		super();
		this.id = id;
		this.pname = pname;
		this.flag = flag;
	}
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public ThePermission() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "ThePermission [id=" + id + ", pname=" + pname + ", flag=" + flag + "]";
	}
	
	
}
