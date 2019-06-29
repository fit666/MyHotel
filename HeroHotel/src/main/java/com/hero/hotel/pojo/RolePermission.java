package com.hero.hotel.pojo;

public class RolePermission {
	private Integer rid;//角色id
	private Integer pid;//权限id

	public RolePermission(Integer rid, Integer pid) {
		super();
		this.rid = rid;
		this.pid = pid;
	}
	public RolePermission() {
		super();
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "RolePermission [rid=" + rid + ", pid=" + pid + "]";
	}
	
	
	
}
