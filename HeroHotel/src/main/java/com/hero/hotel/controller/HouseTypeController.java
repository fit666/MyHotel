
package com.hero.hotel.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
import com.alibaba.fastjson.JSONObject;
/*import com.wdg.util.BuildJsonOfObject;*/
 
import org.springframework.web.servlet.ModelAndView;


import com.hero.hotel.pojo.HouseType;
import com.hero.hotel.service.HouseTypeService;



@Controller
@RequestMapping("/housetype")
public class HouseTypeController {
	@Autowired
	private HouseTypeService houseTypeService;
	/*
	 * 获取所有的房间类型及其对应的房间
	 */
	@RequestMapping("/allHouses")
	public ModelAndView allHouses(){
		ModelAndView mav=new ModelAndView();
		//查询所有的房间
		List<HouseType> allHouses=houseTypeService.findAllHouses();
		mav.addObject("allHouses", allHouses);
		mav.setViewName("/backstage-html/house-list.html");
		
		return mav;
	}
	
	/*
	 * 获取所有的房间类型
	 */
	@RequestMapping("/findAllType")
	public ModelAndView findAllType(){
		ModelAndView mav=new ModelAndView();
		List<HouseType> allType=houseTypeService.findAllType();
		
		mav.addObject("allType", allType);
		mav.setViewName("/backstage-html/house-add.html");
		
		return mav;
	}
	
	@RequestMapping("/showAll")
	@ResponseBody
	public List<HouseType> showHouseType() {
		List<HouseType> types = houseTypeService.queryAllType();
		return types;
	}
	
	/*
	 * 接收房间图片
	 */
/*	@RequestMapping(method = RequestMethod.POST, path = "/pictureUp")
	public String upload(@RequestParam("file")MultipartFile files,HttpServletRequest request, HttpServletResponse response) throws IOException {
		  JSONObject json=new JSONObject();
		  response.setCharacterEncoding("utf-8");
		  String msg = "添加成功";
		  log.info("-------------------开始调用上传文件upload接口-------------------");
		  try{
		  String name = files.getOriginalFilename();
		  String path = this.getClass().getClassLoader().getResource("/").getPath();
		  int index = path.indexOf("Shopping");
		  path = path.substring(0, index + "Shopping".length()) + "/WebContent/resources/upload/";
		  path = path + File.separator + name;
		  File uploadFile = new File(path);
		  files.transferTo(uploadFile);
		  }catch(Exception e){
		   msg="添加失败";
		  }
		  log.info("-------------------结束调用上传文件upload接口-------------------");
		  json.put("msg", msg);
		  return BuildJsonOfObject.buildJsonOfJsonObject(json);
		 }*/
		 
}

