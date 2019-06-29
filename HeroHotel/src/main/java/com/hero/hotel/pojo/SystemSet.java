package com.hero.hotel.pojo;

public class SystemSet {
	private Integer id;
	private Integer findvipmessagebyname;//是否根据姓名查找vip信息
	private Integer findvipmessagebyid;//是否能根据id查找vip信息
	private Integer moneytoint;//应收金额是否自动取整
	private double alldiscountrate;//全场打折比率
	public SystemSet(Integer id, Integer findvipmessagebyname, Integer findvipmessagebyid, Integer moneytoint,
			double alldiscountrate) {
		super();
		this.id = id;
		this.findvipmessagebyname = findvipmessagebyname;
		this.findvipmessagebyid = findvipmessagebyid;
		this.moneytoint = moneytoint;
		this.alldiscountrate = alldiscountrate;
	}
	public SystemSet() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFindvipmessagebyname() {
		return findvipmessagebyname;
	}
	public void setFindvipmessagebyname(Integer findvipmessagebyname) {
		this.findvipmessagebyname = findvipmessagebyname;
	}
	public Integer getFindvipmessagebyid() {
		return findvipmessagebyid;
	}
	public void setFindvipmessagebyid(Integer findvipmessagebyid) {
		this.findvipmessagebyid = findvipmessagebyid;
	}
	public Integer getMoneytoint() {
		return moneytoint;
	}
	public void setMoneytoint(Integer moneytoint) {
		this.moneytoint = moneytoint;
	}
	public double getAlldiscountrate() {
		return alldiscountrate;
	}
	public void setAlldiscountrate(double alldiscountrate) {
		this.alldiscountrate = alldiscountrate;
	}
	@Override
	public String toString() {
		return "SystemSet [id=" + id + ", findvipmessagebyname=" + findvipmessagebyname + ", findvipmessagebyid="
				+ findvipmessagebyid + ", moneytoint=" + moneytoint + ", alldiscountrate=" + alldiscountrate + "]";
	}
	
	
	
}
