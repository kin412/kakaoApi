package com.kim.excel.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kim.excel.excelFileDataDownService;
import com.kim.excel.excelFileFormCreateService;
import com.kim.excel.excelUploadService;

@Controller
public class excelController {
	
	@Autowired
	private excelUploadService excelUploadService;
	
	@Autowired
	private excelFileFormCreateService excelFileFormCreateService;
	
	@Autowired
	private excelFileDataDownService excelFileDataDownService;
	
	String filePath = "C:\\Users\\min\\Documents\\upload"; //경로 변경

	@RequestMapping(value = "fileUploadEx", method = RequestMethod.POST)
	public String excelUpload(MultipartHttpServletRequest request, HttpServletResponse res) throws IllegalStateException, IOException{
		System.out.println("excelUpload in");
		request.setCharacterEncoding("utf-8");
		
		MultipartFile excelFile =request.getFile("fileEx");
		
		//엑셀 파일 저장 및 파일 데이터 읽기
		List<Map<String, String>> excelContent = excelUploadService.excelReadServiceFunc(excelFile, filePath);
		
		System.out.println("---------------------");
	    for(Map<String, String> article: excelContent){
	        System.out.println(article.get("A"));
	        System.out.println(article.get("B"));
	        System.out.println(article.get("C"));
	        System.out.println(article.get("D"));
	        
	    }
	    
	    //엑셀 파일 양식 생성
	    //excelFileFormCreateService.makeFile(excelContent);
	    
	    //poi 액셀 파일 생성
	    excelFileDataDownService.excleFileDataCreate(excelContent, res, filePath);
	    

        return "redirect:fileList";
		
	}
	
}
