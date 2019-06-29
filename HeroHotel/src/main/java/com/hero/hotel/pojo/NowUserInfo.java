package com.hero.hotel.pojo;

public class NowUserInfo {

	private Integer id;// 记录id
	private Integer houseid;// 房间id
	private Integer typeid;// 房间类型id
	private Integer infoid;// 用户信息id
	private String tel;// 手机号
	private String uname;// 姓名
	private char sex;// 性别
	private String idcard;// 身份证号
	private String date;// 居住时间
	private Integer flag;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getInfoid() {
		return infoid;
	}

	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
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

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public NowUserInfo(Integer id, Integer houseid, Integer typeid, Integer infoid, String tel, String uname, char sex,
			String idcard, String date, Integer flag) {
		super();
		this.id = id;
		this.houseid = houseid;
		this.typeid = typeid;
		this.infoid = infoid;
		this.tel = tel;
		this.uname = uname;
		this.sex = sex;
		this.idcard = idcard;
		this.date = date;
		this.flag = flag;
	}

	public NowUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NowUserInfo [id=" + id + ", houseid=" + houseid + ", typeid=" + typeid + ", infoid=" + infoid + ", tel="
				+ tel + ", uname=" + uname + ", sex=" + sex + ", idcard=" + idcard + ", date=" + date + ", flag=" + flag
				+ "]";
	}

}
