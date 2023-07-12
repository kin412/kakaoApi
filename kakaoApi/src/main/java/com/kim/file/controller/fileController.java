package com.kim.file.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kim.file.fileService;


@Controller
public class fileController {
	
	@Autowired
	private fileService fileService1;
	
	String filePath = "C:\\Users\\min\\Documents\\upload"; //경로 변경
	
	@RequestMapping(value = "fileList", method = RequestMethod.GET)
	public String fileList(Model model) {
		
		File listFile = new File(filePath);
		
		String listFileName[] = listFile.list();
		System.out.println("listFileName : " + listFileName[0]);
		
		File listFileLink[] = listFile.listFiles();
		System.out.println("listFileLink : " + listFileLink[0].toString());
		
		String listFile2[][] = new String[listFileName.length][2];
		/*for(int i=0; i<listFileLink.length; i++) {
			listFileLink2[i] = listFileLink[i].toString();
		}*/
		
		for(int i=0; i<listFileName.length; i++) {
			listFile2[i][0] = listFileName[i];
			listFile2[i][1] = listFileLink[i].toString();
		}
		
		model.addAttribute("listFile", listFile2);
		//model.addAttribute("fileLink", listFileLink2);
		
		return "fileList";
	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest multiRequest) throws IllegalStateException, IOException {
		System.out.println("fileUpload in");
		System.out.println("controller multipartrequest.entrySet() : " + multiRequest.getFileMap().entrySet());
		//String path = System.getProperty("user.dir");
        //System.out.println("현재 작업 경로: " + path);
        
		fileService1.uploadFile(multiRequest);
		
		return "redirect:fileList";
	}
	
	@RequestMapping(value = "fileDown", method = RequestMethod.GET)
	public String fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("fileDown in");
		System.out.println("fileName param request.getAttribute(\"fileName\") : " + request.getParameter("fileName"));
		fileService1.fileDown(request.getParameter("fileName"),request, response);
		
		return"fileList";
	}
}
