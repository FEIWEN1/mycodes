package com.personal.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * TODO:文件上传
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月30日/上午11:11:29
 * 
 */
@Controller
@RequestMapping("/file/demo")
public class DemoUploadController {

	/**
	 * 上传文件的两种方法：1.用参数接收 2.解析request
	 * @throws IOException 
	 */
	/**
	 * TODO:参数接收形式上传文件
	 * 
	 * @param mfile
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value={"/upload"},method=RequestMethod.POST)
	@RequiresPermissions("file:download")
	public String uploadFile(@RequestParam("file") CommonsMultipartFile mfile,
			HttpServletRequest request, HttpSession session) throws IOException {
		String relPath = session.getServletContext().getRealPath("/");
		if (!mfile.isEmpty()) {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream("../" + mfile.getOriginalFilename()));
			InputStream in = mfile.getInputStream();
			BufferedInputStream bin = new BufferedInputStream(in);
			int n = 0;
			byte[] b = new byte[1024];
			while ((n = bin.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			bos.flush();
			bos.close();
			bin.close();
		}
		return "/success";
	}
	/**
	 * TODO:request上传文件
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value={"/uploadOfRequest"})
	@RequiresPermissions("file:download")
	public String uploadFileRequest(@RequestParam CommonsMultipartFile file,
			HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {

		//定义解析器去解析request
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(session.getServletContext());
		//如果request是multipart类型
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			//获取文件名
			Iterator<String> it=multiRequest.getFileNames();
			while(it.hasNext()){
				//获取MultipartFile类型文件
				MultipartFile fileDetail=multiRequest.getFile(it.next());
				if(fileDetail!=null){
					String fileName="../"+fileDetail.getOriginalFilename();
					File localFile=new File(fileName);
					//将上传文件写入到指定文件
					fileDetail.transferTo(localFile);
				}
			}
		}
		return "/scuess";
	}
}
