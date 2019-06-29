package com.woniu.hotel.enump;
//登录类型
//普通用户登录，管理员登录

public enum LoginType {
	USER("User"),  MANAGER("Manager");
	 
    private String type;
 
    private LoginType(String type) {
        this.type = type;
    }
 
    @Override
    public String toString() {
        return this.type.toString();
    }

}
