package com.study.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelMakerData {

	private String fileName;
	private String serverFilePath; // 파일이 생성될 서버 위치
	
	private List<ExcelViewCode> excelViewCodeList = new ArrayList<ExcelViewCode>();
	private Map<String, List<String>> excelFieldList = new HashMap<String, List<String>>();
	private Map<String, List<Map<String, Object>>> excelDataMap = new HashMap<String, List<Map<String, Object>>>();
	
	private List<Map<String, Object>> selectedFieldList = null;
	private int[] fieldWidth = null;
	private String[] fieldKey = null;
	
	public void setAllData(ExcelViewCode excelViewCode, List<String> excelFieldList, List<Map<String, Object>> excelDataMap) {
		putExcelViewCodeList(excelViewCode);
		putExcelFieldList(excelViewCode.getCode(), excelFieldList);
		putExcelDataMap(excelViewCode.getCode(), excelDataMap);
	}
	
	public String getFileName() {
		return fileName;
	}
	
	// 엑셀 파일명은 확장자 없이 영문으로만 만들어야 함
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getServerFilePath() {
		return serverFilePath;
	}

	public void setServerFilePath(String serverFilePath) {
		this.serverFilePath = serverFilePath;
	}
	
	public List<Map<String, Object>> getExcelDataList(String key){
		if(this.excelDataMap != null) {
			return excelDataMap.get(key);
		}
		return null;
	}
	
	private void putExcelViewCodeList(ExcelViewCode excelViewCodeList) {
		if(this.excelViewCodeList==null) {
			this.excelViewCodeList = new ArrayList<ExcelViewCode>();
		}
		this.excelViewCodeList.add(excelViewCodeList);
	}
	
	private void putExcelFieldList(String key, List<String> excelFieldData) {
		if(this.excelFieldList==null) {
			this.excelFieldList = new HashMap<String, List<String>>();
		}
		this.excelFieldList.put(key, excelFieldData);
	}
	
	private void putExcelDataMap(String key, List<Map<String, Object>> excelData) {
		if(this.excelDataMap==null) {
			this.excelDataMap = new HashMap<String, List<Map<String, Object>>>();
		}
		this.excelDataMap.put(key, excelData);
	}
	

	public List<ExcelViewCode> getExcelViewCodeList() {
		return excelViewCodeList;
	}
	
	public String[] getFieldTitle(String code) {
		String[] fieldTitle = null;
		switch (ExcelViewCode.search(code)) {
		case BASIC_INFO :
			break;
		default:
			break;
		}
		return fieldTitle;
	}
	
	public void makeInitData(String code) {
		Map<String, Object> excelManyTitles = new HashMap<String,Object>();
		switch (ExcelViewCode.search(code)) {
		case EMOTION_EXCEL_LIST:
			excelManyTitles = ExcelDataList01.getDefaultData();
			break;
		default:
			break;
		}
		
		// 실제 표현 데이터
		List<String> excelFieldList = this.excelFieldList.get(code);
		
		List<Map<String, Object>> selectedFieldList = new ArrayList<Map<String,Object>>();
		int coll = 0;
		for(String rowKey : excelFieldList) {
			@SuppressWarnings("unchecked")
			Map<String,Object> rowMap = (Map<String,Object>)excelManyTitles.get(rowKey);
			if(rowMap != null) {
				int rowspan = Integer.parseInt(rowMap.get("rowspan").toString());
				if (rowspan == 0) {
					rowMap.put("cell", coll++);
					selectedFieldList.add(rowMap);
					@SuppressWarnings("unchecked")
					List<Map<String,Object>> childList = (List<Map<String,Object>>)rowMap.get("childList");
					if(childList!=null && childList.size()>0) {
						coll--; // 자식은 부모의 coll부터 사용 해야 함.
						for(Map<String, Object> rowChildMap : childList) {
							rowChildMap.put("cell", coll++);
							selectedFieldList.add(rowChildMap);
						}
					}
				} else if(rowspan==1) {
					
				} else if(rowspan==2) {
					rowMap.put("cell", coll++);
					selectedFieldList.add(rowMap);
				}
			}
		}
		this.selectedFieldList = selectedFieldList;
		
		int[] selectedFieldWidth = new int[coll];
		String[] selectedFieldKey = new String[coll];
		int order = 0;
		for(Map<String, Object> rowMap : selectedFieldList) {
			int rowspan = Integer.parseInt(rowMap.get("rowspan").toString());
			if(rowspan > 0) {
				selectedFieldWidth[order] = Integer.parseInt(rowMap.get("width").toString());
				selectedFieldKey[order] = (String)rowMap.get("key");
				order++;
			}
		}
		this.fieldWidth = selectedFieldWidth;
		this.fieldKey = selectedFieldKey;
	}
	
	public int[] getSelectedFieldWidth() {
		return fieldWidth;
	}
	
	public String[] getSelectedFieldKey() {
		return fieldKey;
	}
	
	public List<Map<String,Object>> getSelectedFieldList(){
		return selectedFieldList;
	}
	
}
