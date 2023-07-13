package com.kim.excel.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kim.excel.excelUploadService;

@Controller
public class excelController {
	
	@Autowired
	private excelUploadService excelUploadService;
	
	String filePath = "C:\\Users\\min\\Documents\\upload"; //경로 변경

	@RequestMapping(value = "fileUploadEx", method = RequestMethod.POST)
	public String excelUpload(MultipartHttpServletRequest request) throws IllegalStateException, IOException{
		System.out.println("excelUpload in");
		request.setCharacterEncoding("utf-8");
		
		MultipartFile excelFile =request.getFile("fileEx");
		
		excelUploadService.excelReadServiceFunc(excelFile, filePath);

        return "redirect:fileList";
		
	}
	
}
