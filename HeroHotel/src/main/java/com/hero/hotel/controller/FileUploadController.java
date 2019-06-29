package com.hero.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hero.hotel.utils.FileUtils;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class FileUploadController {

	private  ResourceLoader RSLOADER;

	@Autowired
	public FileUploadController(ResourceLoader resourceLoader) {
		this.RSLOADER = resourceLoader;
	}

	@Value("${web.upload-path}")
	private String path;

	/**
	 * 跳转到文件上传页面
	 * 
	 * @return
	 */
	@RequestMapping("test")
	public String toUpload() {
		return "test";
	}

	/**
	 *
	 * @param file
	 *            要上传的文件
	 * @return
	 */
	@RequestMapping("/fileUpload")
	public String upload(@RequestParam("imgurl") MultipartFile imgurl, 
			@RequestParam("hname") String hname,
			@RequestParam("serve") String serve,
			@RequestParam("breakfast") String breakfast,
			@RequestParam("price") BigDecimal price,
			Map<String, Object> map) {
		
		System.out.println(imgurl+",,"+hname+",,"+serve+",,"+breakfast+",,"+price);
		
		// 要上传的目标文件存放路径
		String localPath = path;
		// 上传成功或者失败的提示
		String msg = "";

		if (!FileUtils.upload(imgurl, localPath, imgurl.getOriginalFilename()).equals("defeat")) {
			//获取新的文件名
			String newFileName=FileUtils.upload(imgurl, localPath, imgurl.getOriginalFilename());
			
			// 上传成功，给出页面提示
			msg = "上传成功！";
		} else {
			msg = "上传失败！";

		}

		// 显示图片
		map.put("msg", msg);
		map.put("fileName", imgurl.getOriginalFilename());

		return "/backstage-html/house-add.html";
	}

	/**
	 * 显示单张图片
	 * 
	 * @return
	 */
	@RequestMapping("show")
	public ResponseEntity showPhotos(String fileName) {

		try {
			// 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
			return ResponseEntity.ok(RSLOADER.getResource("file:" + path + fileName));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}