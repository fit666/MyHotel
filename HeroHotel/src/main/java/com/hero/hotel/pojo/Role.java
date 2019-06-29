package com.hero.hotel.pojo;

import java.util.List;

/*
 * 	角色表
 */
public class Role {
    private Integer id;//角色id
    private String rolename;//角色名
    private String describe;//角色权限描述
    private Integer flag;
    private List<ThePermission> permission;//管理员的权限
	
    public Role(Integer id, String rolename, String describe, Integer flag) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.describe = describe;
		this.flag = flag;
	}
    

	public List<ThePermission> getPermission() {
		return permission;
	}


	public void setPermission(List<ThePermission> permission) {
		this.permission = permission;
	}


	public Role() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", describe=" + describe + ", flag=" + flag
				+ ", permission=" + permission + "]";
	}


}
