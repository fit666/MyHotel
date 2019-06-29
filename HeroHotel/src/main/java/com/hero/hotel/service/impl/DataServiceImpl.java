package com.hero.hotel.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.hero.hotel.service.DataService;
@Service("dataService")
public class DataServiceImpl implements DataService{
	/*
	 * 数据备份(non-Javadoc)
	 * @see com.hero.hotel.service.DataService#dataBackup()
	 */
	@Override
	public String dataBackup() {
		// 数据库导出
		String user = "root"; // 数据库帐号
		String password = "123456"; // 登陆密码
		String database = "sb"; // 需要备份的数据库名
		String filepath = "d:\\sb.sql"; // 备份的路径地址
		String stmt1 = "mysqldump " + database + " -u " + user + " -p"
		+ password + " --result-file=" + filepath;
		
		try {
			Runtime.getRuntime().exec(stmt1);
			System.out.println("数据已导出到文件" + filepath + "中");
			return "success";
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return "defeat";
	}
	/*
	 * 数据还原(non-Javadoc)
	 * @see com.hero.hotel.service.DataService#dataReduction()
	 */
	@Override
	public String dataReduction() {
		String filepath = "d:\\sb.sql"; // 备份的路径地址
		//新建数据库test
		String stmt1 = "mysqladmin -u root -p123456 create sb";
		String stmt2 = "mysql -u root -p123456 sb< " + filepath;
		String[] cmd = { "cmd", "/c", stmt2 };
		try {
			Runtime.getRuntime().exec(stmt1);
			Runtime.getRuntime().exec(cmd);
			System.out.println("数据已从 " + filepath + " 导入到数据库中");
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "defeat";
	}

}
