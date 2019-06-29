
package com.hero.hotel.pojo;

import java.util.Date;
import java.math.BigDecimal;

/*
 * 	 用户表
 */

public class User {

	private Integer id;
	private String account;
	private String password;
	private String tel;
	private BigDecimal monetary;
	private Integer roleid;
	private Integer infoid;
	private Date createtime;
	private Integer flag;
	private Integer rm;// 记住我
	private Info info;// 个人信息
	private Vip vip;// vip等级
	private String code;// 手机验证码
	private Role role;// 管理员角色
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public BigDecimal getMonetary() {
		return monetary;
	}
	public void setMonetary(BigDecimal monetary) {
		this.monetary = monetary;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getInfoid() {
		return infoid;
	}
	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getRm() {
		return rm;
	}
	public void setRm(Integer rm) {
		this.rm = rm;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public Vip getVip() {
		return vip;
	}
	public void setVip(Vip vip) {
		this.vip = vip;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", tel=" + tel + ", monetary="
				+ monetary + ", roleid=" + roleid + ", infoid=" + infoid + ", createtime=" + createtime + ", flag="
				+ flag + ", rm=" + rm + ", info=" + info + ", vip=" + vip + ", code=" + code + ", role=" + role + "]";
	}
	public User(Integer id, String account, String password, String tel, BigDecimal monetary, Integer roleid,
			Integer infoid, Date createtime, Integer flag, Integer rm, Info info, Vip vip, String code, Role role) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.tel = tel;
		this.monetary = monetary;
		this.roleid = roleid;
		this.infoid = infoid;
		this.createtime = createtime;
		this.flag = flag;
		this.rm = rm;
		this.info = info;
		this.vip = vip;
		this.code = code;
		this.role = role;
	}
	public User() {
		super();
	}
	
	
}