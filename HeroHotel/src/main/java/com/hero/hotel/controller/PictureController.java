package com.hero.hotel.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*@RequestMapping("/picture")*/
public class PictureController {

	// 保存文件路径
	private File fileUp;
	// 文件类型
	private String fileUpContentType;
	// 文件名
	private String fileUpFileName;

	public File getFileUp() {
		return fileUp;
	}

	public void setFileUp(File fileUp) {
		this.fileUp = fileUp;
	}

	public String getFileUpContentType() {
		return fileUpContentType;
	}

	public void setFileUpContentType(String fileUpContentType) {
		this.fileUpContentType = fileUpContentType;
	}

	public String getFileUpFileName() {
		return fileUpFileName;
	}

	public void setFileUpFileName(String fileUpFileName) {
		this.fileUpFileName = fileUpFileName;
	}

	/*
	 * 处理请求的方法
	 */
	@RequestMapping("/upload")
	public String upload(String imgurl) {
		System.out.println("==========----------");
		// System.out.println("保存文件："+fileUp);
		// System.out.println("文件类型："+fileUpContentType);
		// System.out.println("文件名:"+fileUpFileName);
		String result = "input";
		// 将文件存放到服务器指定的文件夹里
		// 获取文件夹路径
		String path = ServletActionContext.getServletContext().getRealPath("/images");
		// 创建文件夹
		File images = new File(path);
		if (!images.exists()) {
			images.mkdirs();
		}
		// 上传文件的新文件名
		String fileName = UUID.randomUUID().toString() + fileUpFileName.substring(fileUpFileName.lastIndexOf("."));

		// 获取新文件的全路径、文件名
		File dest = new File(images, fileName);
		// 保存文件
		try {
			FileUtils.copyFile(fileUp, dest);
			// 将文件路径存在数据库
			String newPath = fileName;
			// 调用service向对应的表中更新数据
			/*UserService userService = new UserService();
			boolean re = userService.insertImg(newPath);
			if (re) {
				result = "success";
			}*/
		} catch (IOException e) {
			e.printStackTrace();

		}

		/*
		 * String path=ServletActionContext.getServletContext().getRealPath("");
		 * File root=new File(path); //得到父路径 File webapps=root.getParentFile();
		 * System.out.println(webapps); //获取images的File File images=new
		 * File(webapps,"images"); if(!images.exists()) { images.mkdirs(); }
		 * //新文件路径 File newfile=new File(images,fileUpFileName);
		 * System.out.println(newfile); //存放文件 try { FileUtils.copyFile(fileUp,
		 * newfile); } catch (IOException e) { e.printStackTrace(); }
		 */
		return result;
	}
}
