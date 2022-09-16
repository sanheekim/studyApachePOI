package com.study.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataList01 {
	
	public static Map<String,Object> getDefaultData(){
		Map<String,Object> excelTitle = new HashMap<String,Object>();
		
		// 단일 타이틀
		Map<String,Object> field1 = new HashMap<String,Object>();
		field1.put("key", "love");
		field1.put("name", "사랑");
		field1.put("rowspan", 2);
		field1.put("width", 15);
		excelTitle.put((String)field1.get("key"), field1);
		
		Map<String,Object> field2 = new HashMap<String,Object>();
		field2.put("key", "happy");
		field2.put("name", "행복");
		field2.put("rowspan", 2);
		field2.put("width", 15);
		excelTitle.put((String)field2.get("key"), field2);
		
		Map<String,Object> field3 = new HashMap<String,Object>();
		field3.put("key", "wish");
		field3.put("name", "소망");
		field3.put("rowspan", 2);
		field3.put("width", 15);
		excelTitle.put((String)field3.get("key"), field3);
		
		Map<String,Object> field4 = new HashMap<String,Object>();
		field4.put("key", "amaze");
		field4.put("name", "놀라게 하다");
		field4.put("rowspan", 2);
		field4.put("width", 15);
		excelTitle.put((String)field4.get("key"), field4);
		
		Map<String,Object> field5 = new HashMap<String,Object>();
		field5.put("key", "friendship");
		field5.put("name", "우정");
		field5.put("rowspan", 2);
		field5.put("width", 15);
		excelTitle.put((String)field5.get("key"), field5);
		
		Map<String,Object> field6 = new HashMap<String,Object>();
		field6.put("key", "smile");
		field6.put("name", "미소");
		field6.put("rowspan", 2);
		field6.put("width", 15);
		excelTitle.put((String)field6.get("key"), field6);
		
		Map<String,Object> field7 = new HashMap<String,Object>();
		field7.put("key", "hope");
		field7.put("name", "희망");
		field7.put("rowspan", 2);
		field7.put("width", 15);
		excelTitle.put((String)field7.get("key"), field7);
		
		Map<String,Object> field8 = new HashMap<String,Object>();
		field8.put("key", "world");
		field8.put("name", "세계");
		field8.put("rowspan", 2);
		field8.put("width", 15);
		excelTitle.put((String)field8.get("key"), field8);
		
		Map<String,Object> field9 = new HashMap<String,Object>();
		field9.put("key", "touch");
		field9.put("name", "감동");
		field9.put("rowspan", 2);
		field9.put("width", 15);
		excelTitle.put((String)field9.get("key"), field9);
		
		Map<String,Object> field10 = new HashMap<String,Object>();
		field10.put("key", "surprised");
		field10.put("name", "놀란");
		field10.put("rowspan", 2);
		field10.put("width", 15);
		excelTitle.put((String)field10.get("key"), field10);
		
		Map<String,Object> field11 = new HashMap<String,Object>();
		field11.put("key", "kind");
		field11.put("name", "친절한");
		field11.put("rowspan", 2);
		field11.put("width", 15);
		excelTitle.put((String)field11.get("key"), field11);
		
		Map<String,Object> field12 = new HashMap<String,Object>();
		field12.put("key", "try");
		field12.put("name", "노력하다");
		field12.put("rowspan", 2);
		field12.put("width", 15);
		excelTitle.put((String)field12.get("key"), field12);
		
		Map<String,Object> field13 = new HashMap<String,Object>();
		field13.put("key", "excited");
		field13.put("name", "극도로 신나는");
		field13.put("rowspan", 2);
		field13.put("width", 15);
		excelTitle.put((String)field13.get("key"), field13);
		
		// 복합 타이틀
		Map<String,Object> field14 = new HashMap<String,Object>();
		field14.put("key", "fruits");
		field14.put("name", "과일들");
		field14.put("rowspan", 0);
		field14.put("colspan", 3);
		excelTitle.put((String)field14.get("key"), field14);
		Map<String,Object> field14_1 = new HashMap<String,Object>();
		field14_1.put("key", "apple");
		field14_1.put("name", "사과");
		field14_1.put("rowspan", 1);
		field14_1.put("width", 10);
		Map<String,Object> field14_2 = new HashMap<String,Object>();
		field14_2.put("key", "orange");
		field14_2.put("name", "귤");
		field14_2.put("rowspan", 1);
		field14_2.put("width", 10);
		Map<String,Object> field14_3 = new HashMap<String,Object>();
		field14_3.put("key", "kiwii");
		field14_3.put("name", "키위");
		field14_3.put("rowspan", 1);
		field14_3.put("width", 10);
		List<Map<String,Object>> field14ChildList = new ArrayList<Map<String,Object>>();
		field14ChildList.add(field14_1);
		field14ChildList.add(field14_2);
		field14ChildList.add(field14_3);
		field14.put("childList", field14ChildList);
		excelTitle.put((String)field14.get("key"), field14);
		
		Map<String,Object> field15 = new HashMap<String,Object>();
		field15.put("key", "interested");
		field15.put("name", "흥미로운");
		field15.put("rowspan", 2);
		field15.put("width", 15);
		excelTitle.put((String)field15.get("key"), field15);
		
		Map<String,Object> field16 = new HashMap<String,Object>();
		field16.put("key", "relieved");
		field16.put("name", "안도하는");
		field16.put("rowspan", 2);
		field16.put("width", 15);
		excelTitle.put((String)field16.get("key"), field16);
		
		Map<String,Object> field17 = new HashMap<String,Object>();
		field17.put("key", "peaceful");
		field17.put("name", "평화로운");
		field17.put("rowspan", 2);
		field17.put("width", 15);
		excelTitle.put((String)field17.get("key"), field17);
		
		Map<String,Object> field18 = new HashMap<String,Object>();
		field18.put("key", "grateful");
		field18.put("name", "기쁜");
		field18.put("rowspan", 2);
		field18.put("width", 15);
		excelTitle.put((String)field18.get("key"), field18);
		
		Map<String,Object> field19 = new HashMap<String,Object>();
		field19.put("key", "joyful");
		field19.put("name", "즐거운");
		field19.put("rowspan", 2);
		field19.put("width", 15);
		excelTitle.put((String)field19.get("key"), field19);
		
		Map<String,Object> field20 = new HashMap<String,Object>();
		field20.put("key", "calm");
		field20.put("name", "평온한");
		field20.put("rowspan", 2);
		field20.put("width", 15);
		excelTitle.put((String)field20.get("key"), field20);
		
		return excelTitle;
	}

}
