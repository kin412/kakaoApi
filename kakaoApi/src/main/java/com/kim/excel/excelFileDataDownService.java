package com.kim.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class excelFileDataDownService {

	// 데이터로 엑셀 파일 생성
	public void excleFileDataCreate(List<Map<String, String>> excelContent, HttpServletResponse res, String filePath) throws IOException {
		System.out.println("--------------------excleFileDataCreate in--------------------------");
		String saveFileName = "데이터 엑셀 파일 생성";
		
		File file = new File(filePath, "다운 테스트 엑셀생성.xlsx"); // 파일 확장자 .xlsx로 고정		
		FileOutputStream fos = new FileOutputStream(file);
		
		SXSSFWorkbook wb = new SXSSFWorkbook();
		SXSSFSheet sheet = wb.createSheet(saveFileName);
		
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		//테이블 헤더용 스타일
		CellStyle headStyle = wb.createCellStyle();
		//가는 경계선
		headStyle.setBorderTop(BorderStyle.MEDIUM);
		headStyle.setBorderBottom(BorderStyle.MEDIUM);
		headStyle.setBorderRight(BorderStyle.MEDIUM);
		headStyle.setBorderLeft(BorderStyle.MEDIUM);
		//데이터는 가운데 정렬
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		
		//헤더 생성
		row = sheet.createRow(rowNo++);
		
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
	 	cell.setCellValue("AAA");
	 	cell = row.createCell(1);
		cell.setCellStyle(headStyle);
	 	cell.setCellValue("BBB");
	 	cell = row.createCell(2);
		cell.setCellStyle(headStyle);
	 	cell.setCellValue("CCC");
	 	cell = row.createCell(3);
		cell.setCellStyle(headStyle);
	 	cell.setCellValue("DDD");
			
		//기본 셀 넓이 지정
		for(int x = 0; x <sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
			sheet.setColumnWidth(x, 20*256);
		}
		
		//데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		
		bodyStyle.setAlignment(HorizontalAlignment.CENTER);
		
		//엑셀화면에 뿌려질 데이터
		for(Map<String, String>map : excelContent) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(map.get("A"));
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(map.get("B"));
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(map.get("C"));
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(map.get("D"));
		}
		
		wb.write(fos);
		if(fos != null) {
			fos.close();
		}
		
		/* response를 이용해 즉시 다운
		res.setHeader("Set-cookie", "fileDownload=true; path=/");
		res.setHeader("Content-Disposition", String.format("attachment;filename=\"+new String((saveFileName).getBytes()"));
		*/
		
	}
	
}
