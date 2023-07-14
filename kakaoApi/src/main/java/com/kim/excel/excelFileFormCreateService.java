package com.kim.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class excelFileFormCreateService {
	
	public void makeFile(List<Map<String, String>> excelContent) {
		
		//저장 파일명
		String saveFileName = "만든 다운로드 파일 양식";
		
		//sheet 갯수
		String[] sheetNameArr = {"testSheet1","testSheet2"};
		
		//header 임시
		String[] headerNameTmp = {};
		String[] headerName1 = {"no","grade","name","sal"};
		String[] headerName2 = {"aa","bb","cc","dd"};
		
		//엑셀 만들기 시작
		@SuppressWarnings("resource")
		SXSSFWorkbook wb = new SXSSFWorkbook();
		//HSSFWorkbook wb =  new HSSFWorkbook();
		
		//sheet 별 만들기 for
		for(int i = 0; i < sheetNameArr.length; i++) {
			
			SXSSFSheet sheet = (SXSSFSheet) wb.createSheet(sheetNameArr[i]);
			
			Row row = null;
			Cell cell = null;
			
			// 헤더 만들기 시작
			// 테이블 헤더용 스타일
			CellStyle headerStyle = wb.createCellStyle();
			//가는 경계선
			headerStyle.setBorderTop(BorderStyle.THIN);
			headerStyle.setBorderBottom(BorderStyle.THIN);
			headerStyle.setBorderRight(BorderStyle.THIN);
			headerStyle.setBorderLeft(BorderStyle.THIN);
			//데이터는 가운데 정렬
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			headerStyle.setLocked(true);
			//폰트설정
			Font headerFont = wb.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);
			//헤더생성
			row = sheet.createRow(0);
			
			
			//Sheet별 조건 걸기
			if(i==0) {
				headerNameTmp = headerName1;
			}else if(i==1) {
				headerNameTmp = headerName2;
				
			}
			
			//header 넣어주기
			for(int j = 0; j <headerNameTmp.length; i++) {
				cell = row.createCell(j);
				cell.setCellStyle(headerStyle);
				cell.setCellValue(headerNameTmp[j]);
			}
			
			//기본 셀 넓이 지정 (글자 수 비례)
			for(int x = 0; x <sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
				sheet.setColumnWidth(x, ((256*sheet.getRow(0).getCell(x).getStringCellValue().length())+1500));
			}
			
			//각 셀별 제약조건 걸어주기
			DataValidationHelper validationHelper = sheet.getDataValidationHelper();
			
			//공통 header 제약조건 걸기 => 셀변경 불가
			DataValidationConstraint headerConstraint = validationHelper.createExplicitListConstraint(new String[] {""});
			
			CellRangeAddressList headerRegions = new CellRangeAddressList(0,0,-1,-1);
			DataValidation headerValidation = validationHelper.createValidation(headerConstraint, headerRegions);
			
			headerValidation.setSuppressDropDownArrow(false);
			headerValidation.createErrorBox("tip", "변경이 불가 합니다.");
			headerValidation.setShowErrorBox(true);
			headerValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
			headerValidation.setEmptyCellAllowed(false);
			
			//첫번째 시트
			if(i==0) {
				DataValidationConstraint addressConstraint = validationHelper.createFormulaListConstraint("테스트1!$E$2:$E$10000");
				//Constraint를 적용시킬 범위를 설정한다.(시작row, 끝row, 시작column, 끝colunm)
				CellRangeAddressList addressRegions = new CellRangeAddressList(1, 10000, 0 , 0);
				//Validation에 만든 Constraint와 Regions(범위)를 옵션으로 넣어준다.
				DataValidation addressValidation = validationHelper.createValidation(addressConstraint, addressRegions);
				//드롭다운 사용여부
				addressValidation.setSuppressDropDownArrow(true);
				//에러 메시지 설정
				addressValidation.createErrorBox("tip", "입력값이 바르지 않습니다.");
				//에러 박스 노출 설정
				addressValidation.setShowErrorBox(true);
				//에러 스타일 설정
				addressValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
				//공백 유효 설정
				addressValidation.setEmptyCellAllowed(false);
			}
			
			
		}
		
		
	}
	
}
