
package com.hero.hotel.pojo;

import java.util.Date;

public class Comment {
    private Integer id;    //id
    private Integer orderid;
    private Integer userid;
    private Integer flag;
    private Date createtime;
    private String message;
    private Integer total;//总条数
    private String name;//昵称
    private String ordernumber;
    
    
    
    

    public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	

    @Override
	public String toString() {
		return "Comment [id=" + id + ", orderid=" + orderid + ", userid=" + userid + ", flag=" + flag + ", createtime="
				+ createtime + ", message=" + message + ", total=" + total + ", name=" + name + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comment(Integer id, Integer orderid, Integer userid, Integer flag, Date createtime, String message) {
        this.id = id;
        this.orderid = orderid;
        this.userid = userid;
        this.flag = flag;
        this.createtime = createtime;
        this.message = message;
    }

    public Comment() {
    }
}
