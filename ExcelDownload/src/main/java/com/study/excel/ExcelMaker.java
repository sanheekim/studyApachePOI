package com.study.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

// 엑셀 폼에 대한 파일 생성
// gray bold title, black line
@Component("excelMaker")
public class ExcelMaker extends AbstractView {
	
	// 서버에 생성할 경우
	public void buildExcelDocumentInServer(Map<String,Object> model) {
		try {
			renderMergedOutputModel(model,null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExcelMakerData excelMakerData = (ExcelMakerData) model.get("EXCEL_DOWNLOAD_DATA");
		SXSSFWorkbook workbook = null;
		ServletOutputStream out = null;
		FileOutputStream fileOutput = null;
		try {
			workbook = new SXSSFWorkbook();
			SXSSFSheet sheet = null;
			
			CellStyle styleDefault = getDefaultStyle(workbook);
			CellStyle styleMultiTitle = getMultiTitleStyle(workbook);
			CellStyle styleBody = getBodyStyle(workbook);
			
			int sheetNum = 0;
			for (ExcelViewCode type : excelMakerData.getExcelViewCodeList()) {
				excelMakerData.makeInitData(type.getCode());
				
				// 시트 생성
				sheet = createSheet(workbook, sheetNum++, type.getName(), excelMakerData.getSelectedFieldWidth());
				
				// 멀티 타이틀 생성
				createMultiColumnLabel(styleDefault, styleMultiTitle, sheet, excelMakerData.getSelectedFieldList(), excelMakerData.getSelectedFieldWidth());
				
				// 내용 body 생성
				String[] fieldKeyArr = excelMakerData.getSelectedFieldKey();
				List<Map<String,Object>> excelRowData = excelMakerData.getExcelDataList(type.getCode());
				if(excelRowData != null) {
					int rowNum = 2; // 타이틀이 2개 이상이라서 2부터
					for (Map<String,Object> rank : excelRowData) {
						createPageRankRow(styleBody, sheet, fieldKeyArr, rank, rowNum++);
					}
				}
			}
			
			if (excelMakerData.getServerFilePath()!=null && response==null) {
				File filePath = new File(excelMakerData.getServerFilePath());
				if(!filePath.exists()) {
					filePath.mkdirs();
				}
				fileOutput = new FileOutputStream(excelMakerData.getServerFilePath() + excelMakerData.getFileName() + ".xlsx");
				workbook.write(fileOutput);
				fileOutput.close();
			}
			
			if (response != null) {
				response.setHeader("Content-Disposition", "attachment; fileName=\"" + excelMakerData.getFileName() + ".xlsx\";");
				out = response.getOutputStream();
				workbook.write(out);
				if(out != null) {
					out.close();
				}
			}
			workbook.dispose();
			workbook.close();
		}  catch (IOException e) {
			throw e;
		} finally {
			if (workbook != null) {
				workbook.dispose();
				workbook.close();
			}
			if (fileOutput != null) {
				fileOutput.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	
	
	// 새로운 시트 생성
	private SXSSFSheet createSheet(SXSSFWorkbook workbook, int sheetIndex, String sheetName, int[] fieldWidthArr) {
		SXSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetIndex, sheetName);
		for (int i=0; i < fieldWidthArr.length; i++) {
			sheet.setColumnWidth(i, 256 * fieldWidthArr[i]);
		}
		return ((SXSSFSheet) sheet);
	}
	
	// 타이틀이 복수일 경우 병합 필요함
	private void createMultiColumnLabel(CellStyle style1, CellStyle style, SXSSFSheet sheet, List<Map<String,Object>> selectedFieldList, int[] fieldWidthArr) {
		SXSSFRow firstRow = sheet.createRow(0);
		
		SXSSFCell cell = null;
		for (int i=0; i < fieldWidthArr.length; i++) {
			cell = firstRow.createCell(i);
			cell.setCellValue("");
			cell.setCellStyle(style1);
		}
		
		SXSSFRow firstRow1 = sheet.createRow(1);
		for (int i=0; i < fieldWidthArr.length; i++) {
			cell = firstRow1.createCell(i);
			cell.setCellValue("");
			cell.setCellStyle(style1);
		}
		
		for(Map<String,Object>row : selectedFieldList) {
			int rowspan = Integer.parseInt(row.get("rowspan").toString());
			if(rowspan==0) {
				createColspanLabel(style,sheet,row);
			} else if (rowspan == 1) {
				createLabel(style,sheet,row);
			} else if (rowspan == 2) {
				createRowspanLabel(style,sheet,row);
			}
		}
	}
	
	private void createColspanLabel(CellStyle style, SXSSFSheet sheet, Map<String,Object>fieldTitleInfo) {
		int location = Integer.parseInt(fieldTitleInfo.get("cell").toString());
		int colspan = Integer.parseInt(fieldTitleInfo.get("colspan").toString());
		sheet.addMergedRegion(new CellRangeAddress(0,0,location,(location+colspan-1)));
		
		SXSSFRow firstRow = sheet.getRow(0);
		SXSSFCell cell = null;
		cell = firstRow.createCell(location);
		cell.setCellStyle(style);
		cell.setCellValue((String)fieldTitleInfo.get("name"));
	}
	
	private void createLabel(CellStyle style, SXSSFSheet sheet, Map<String,Object>fieldTitleInfo) {
		int location = Integer.parseInt(fieldTitleInfo.get("cell").toString());
		
		SXSSFRow firstRow = sheet.getRow(1);
		SXSSFCell cell = firstRow.createCell(location);
		cell.setCellStyle(style);
		cell.setCellValue((String)fieldTitleInfo.get("name"));
	}
	
	private void createRowspanLabel(CellStyle style, SXSSFSheet sheet, Map<String,Object>fieldTitleInfo) {
		int location = Integer.parseInt(fieldTitleInfo.get("cell").toString());
		sheet.addMergedRegion(new CellRangeAddress(0,1,location,location));
		
		SXSSFRow firstRow = sheet.getRow(0);
		SXSSFCell cell = null;
		cell = firstRow.createCell(location);
		cell.setCellStyle(style);
		cell.setCellValue((String)fieldTitleInfo.get("name"));
		
	}
	
	// 데이터를 토대로 행 생성
	private void createPageRankRow(CellStyle style, SXSSFSheet sheet, String[] fieldKeyArray, Map<String, Object> rank, int rowNum) {
		SXSSFRow row = sheet.createRow(rowNum);
		SXSSFCell cell = null;
		int celNum = 0;
		for (String key : fieldKeyArray) {
			cell = row.createCell(celNum++);
			cell.setCellStyle(style);
			if("#COUNT#".equals(key)) {
				cell.setCellValue(rowNum);
			} else if (key.startsWith("#ZERO")) {
				cell.setCellValue(0);
			} else if(rank.get(key) instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) rank.get(key);
				cell.setCellValue(value.doubleValue());
			} else if(rank.get(key) instanceof Date) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
				String today = formatter.format((Date)rank.get(key));
				cell.setCellValue(today);
			} else {
				if (rank.get(key)!=null) {
					cell.setCellValue(String.valueOf(rank.get(key)));
				} else {
					cell.setCellValue("");
				}
			}
		}
	}
	
	private CellStyle getDefaultStyle(SXSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}
	
	// 제목에 사용할 스타일 생성
	private CellStyle getMultiTitleStyle(SXSSFWorkbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);
		
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}
	
	// body에 사용할 스타일 생성
	private CellStyle getBodyStyle(SXSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}
	
}
