package com.study.excel;

public enum ExcelViewCode {

	BASIC_INFO			("BASIC_LIST", "예시"),
	EMOTION_EXCEL_LIST	("EXCEL001", "엑셀 셀병합 다운로드 공부 중") // EXCEL001 이 코드가 다른 곳에 어디 들어가는지 살펴보자
	;
	
	private String code;
	private String name;
	
	private ExcelViewCode(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static ExcelViewCode search(String code) {
		for (ExcelViewCode type : ExcelViewCode.values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
