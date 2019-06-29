package com.hero.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.OutputStream;
	import java.net.URLEncoder;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.UUID;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.apache.commons.io.FilenameUtils;
	import org.springframework.beans.factory.annotation.Autowired;
/*	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
	import org.springframework.web.multipart.commons.CommonsMultipartFile;

	import cn.gson.ams.dao.file.FileDao;
	import cn.gson.ams.util.AjaxResult;*/

	/*@Controller
	@RequestMapping("/picture")
	public class UploadController {
	    // 文件存储的根目录,放在程序运行的发布目录中，一般是在tomcat的安装同目录下
	    String rootPath = "/upload/";

	    @Autowired
	    FileDao fileDao;

	    Map<String, Object> fileIdMap = new HashMap<>();

	    *//**
	     * 参数定义 CommonsMultipartFile 接收文件上传内容
	     * AjaxResult  表示传达的是ajax数据
	     * @param file是不能改变的，因为webuploader是以file参数往后台   传文件的；ff是自定义的文件实体
	     * @return
	     * @throws IOException
	     * @throws IllegalStateException
	     *//*
	    @ResponseBody
	    @RequestMapping(method = RequestMethod.POST, path = "upload/up")
	    public AjaxResult upload(@RequestParam(name = "modelPath", required = false) String modelPath,
	            @RequestParam(name = "file", required = false) CommonsMultipartFile file,
	            @RequestParam(defaultValue = "public") String model, HttpServletRequest request,
	            cn.gson.ams.entity.file.File ff) throws IllegalStateException, IOException {

	        // 判断是否有文件
	        if (file != null && !file.isEmpty()) {
	            // 获取文件的原始名称
	            String oldName = file.getOriginalFilename();
	            // 获取文件大小
	            Long fileSize = file.getSize();
	            // 获取文件的原始流
	            // f.getInputStream()
	            // 获取文件的类型
	            System.out.println(file.getContentType());

	            // 组装文件存储路径
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd");
	            String dateStr = sdf.format(new Date());
	            String filePath = rootPath + model + File.separator + dateStr;

	            // 创建目录
	            File f = new File(filePath);
	            if (!f.exists()) {
	                f.mkdirs();
	            }

	            // 生成一个新的不会重复的文件名
	            // 1.获取后缀
	            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
	            // 2.生成新的文件名
	            String newFileName = UUID.randomUUID().toString() + "." + suffix;
	            // 把上传的文件存储指定位置
	            file.transferTo(new File(f, newFileName));
	            String FilePath = rootPath + model + File.separator + dateStr + File.separator + newFileName;

	            ff.setFileModel(model);
	            ff.setFileName(oldName);
	            ff.setFilePath(FilePath.replace("\\", "/"));
	            ff.setFileSize(fileSize);
	            ff.setFileType(suffix);
	            fileDao.save(ff);
	            fileIdMap.put("fileId", ff.getFileId());
	        } else {
	            System.out.println("上传失败！！");
	        }
	        return new AjaxResult(fileIdMap);
	    }

	    // 文件下载,表示/upload后面接的任何路径都会进入到这里
	    @RequestMapping("/**")
	    public void view(HttpServletResponse response, HttpServletRequest request, cn.gson.ams.entity.file.File ff)
	            throws Exception {
	        String filePath = request.getServletPath().replaceFirst("/upload/", "");
	        File file = new File(rootPath, filePath);

	        if (file.exists()) {
	            List<cn.gson.ams.entity.file.File> list = fileDao.findByFilePath(request.getServletPath());
	            String fileName = list.get(0).getFileName();

	            // 设置下载文件的名称,如果想直接在想查看就注释掉，因为要是文件原名才能下载，不然就只能在浏览器直接浏览无法下载
	            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

	            // 把文件输出到浏览器
	            OutputStream os = response.getOutputStream();
	            FileInputStream fs = new FileInputStream(file);
	            byte[] b = new byte[1024];
	            int len = 0;
	            while ((len = fs.read(b)) > 0) {
	                os.write(b, 0, len);
	            }
	            fs.close();
	            os.flush();
	        } else {
	            response.sendError(404);
	        }
	    }
	*/
//}
