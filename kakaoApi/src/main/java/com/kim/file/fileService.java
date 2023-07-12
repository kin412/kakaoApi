package com.kim.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class fileService {

	public void uploadFile(MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException {
		
		//파일 정보를 값으로 하는 map을 가져옴
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		System.out.println(files.get("file1"));
		//files.entrySet()의 요소를 가져온다.
		Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		//MultipartFile 초기화
		MultipartFile mFile = null;
		//파일이 업로드 될 경로를 지정
		String filePath = "C:\\Users\\min\\Documents\\upload"; //업로드 폴더 경로로 지정
		
		//읽어올 요소가 있으면 true, 없으면 false 반환
		while(itr.hasNext()) {
			//iterator의 MultipartFile 요소를 가져온다.
			Map.Entry<String, MultipartFile> entry = itr.next();
			//entry의 값을 가져온다.
			mFile = entry.getValue();
			//원본 파일명, 저장 될 파일명 생성
			String fileOrgName = mFile.getOriginalFilename();
			System.out.println("fileOrgName : " + fileOrgName);
			if(!fileOrgName.isEmpty()) {
				//filePath에 해당되는 파일의 File 객체 생성
				File fileFolder = new File(filePath);
				
				if(!fileFolder.exists()) {
					//부모 폴더까지 포함하여 경로에 폴더 생성
					if(fileFolder.exists()) {
						System.out.println("file mkdir scuccess");
					}
				}
				
				File saveFile = new File(filePath, fileOrgName);
				
				//생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드 처리
				mFile.transferTo(saveFile);
			}
			
		}
		
	}
	
	public void fileDown(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("fileService fileDown in");
		
		String filePath = "C:\\Users\\min\\Documents\\upload";
		//경로와 파일명으로 파일생성
		File dFile = new File(filePath, fileName);
		//파일 길이
		int fSize = (int)dFile.length();
		
		//파일이 존재하는지 체크
		if(fSize > 0) {
			//파일명을 urlencoder 하여 attachment, content-disposition header로 설정
			String encodedFilename = "attachment; filename*="+"UTF-8"+"''"+URLEncoder.encode(fileName,"UTF-8");
			
			//ContentType 설정
			response.setContentType("application/octet-stream; charset=utf-8");
			
			//Header 설정
			response.setHeader("Content-Disposition", encodedFilename);
			
			//ContentLength 설정
			response.setContentLength(fSize);
			
			// java.io의 가장 기본 파일 입출력 클래스
			BufferedInputStream in = null; 
			BufferedOutputStream out = null; 
			
			in = new BufferedInputStream(new FileInputStream(dFile));
			out = new BufferedOutputStream(response.getOutputStream());
			
			try {
				byte[] buffer = new byte[4096];
				int bytesRead = 0;
				
				while((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				
				out.flush();
				
			}finally {
				// 현재 열려있는 in, out 스트림을 닫음. 메모리 누수 방지
				in.close();
				out.close();
			}
			
		}else {
			throw new FileNotFoundException("파일이 없습니다.");
		}	
	}
	
}
