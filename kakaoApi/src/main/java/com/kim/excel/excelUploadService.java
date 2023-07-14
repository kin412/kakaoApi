package com.kim.excel;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class excelUploadService {

	public List<Map<String, String>> excelReadServiceFunc(MultipartFile excelFile, String filePath) throws IllegalStateException, IOException {
		
		System.out.println("------------------excelReadServiceFunc-------------------");
		
		// 한글 파일명 깨짐 방지를 위한 작업
		// 실무라면 여기에 uuid 까지 적용하는게 맞음
		String origName = new String(excelFile.getOriginalFilename().getBytes("8859_1"),"UTF-8");
		
	    if(excelFile==null || excelFile.isEmpty()){
	        throw new RuntimeException("엑셀파일을 선택 해 주세요.");
	    }
	    
	    File destFile = new File(filePath, origName);
	    
	    excelFile.transferTo(destFile);
	    
	    
	    //데이터 추출
	    excelReadOption excelReadOption = new excelReadOption();
	    excelReadOption.setFilePath(destFile.getAbsolutePath());
	    excelReadOption.setOutputColumns("A","B","C","D");
	    excelReadOption.setStartRow(2);
	    
	    List<Map<String, String>>excelContent =excelRead.read(excelReadOption);
		
	    return excelContent;
	    
	}
	
}
