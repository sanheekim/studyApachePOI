package com.study.excel;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.CommandMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Resource(name="homeService")
	private HomeService homeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/* home.jsp */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/* selectExcelOption.jsp */
	@RequestMapping(value = "/selectExcelOption", method = RequestMethod.POST)
	public ModelAndView selectExcelOption(HttpServletRequest request, Model model, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/selectExcelOption");
		return mv;
	}
	
	/* 엑셀 다운로드 */
	@RequestMapping(value = "/excelDownload", method = RequestMethod.POST)
	public String excelDownload(HttpServletRequest request, Model model, CommandMap commandMap) throws Exception {
		
		ExcelMakerData excelMakerData= new ExcelMakerData();
		// 엑셀 파일명
		excelMakerData.setFileName("emotionExcelList");
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		LocalDate now = LocalDate.now();
		dataMap.put("nowDate", now); // table 생성 시 date 컬럼도 만들어야 함
		
		// 체크박스 체크된 data들
		String[] checkList = request.getParameterValues("checkList");
		
		// 필드는 부모를 기준으로만 설정
		List<String> excelFieldList = new ArrayList<String>();
		if(Arrays.asList(checkList).contains("love")) {
			// 체크박스 중 love를 선택하면 보여질 엑셀 열들 (여러 개 넣는 것도 가능함)
			excelFieldList.add("love");
		}
		if(Arrays.asList(checkList).contains("happy")) {
			excelFieldList.add("happy");
		}
		if(Arrays.asList(checkList).contains("wish")) {
			excelFieldList.add("wish");
		}
		if(Arrays.asList(checkList).contains("amaze")) {
			excelFieldList.add("amaze");
		}
		if(Arrays.asList(checkList).contains("friendship")) {
			excelFieldList.add("friendship");
		}
		if(Arrays.asList(checkList).contains("smile")) {
			excelFieldList.add("smile");
		}
		if(Arrays.asList(checkList).contains("hope")) {
			excelFieldList.add("hope");
		}
		if(Arrays.asList(checkList).contains("world")) {
			excelFieldList.add("world");
		}
		if(Arrays.asList(checkList).contains("touch")) {
			excelFieldList.add("touch");
		}
		if(Arrays.asList(checkList).contains("surprised")) {
			excelFieldList.add("surprised");
		}
		if(Arrays.asList(checkList).contains("kind")) {
			excelFieldList.add("kind");
		}
		if(Arrays.asList(checkList).contains("try")) {
			excelFieldList.add("try");
		}
		if(Arrays.asList(checkList).contains("excited")) {
			excelFieldList.add("excited");
		}
		if(Arrays.asList(checkList).contains("fruits")) {
			excelFieldList.add("fruits");
		}
		if(Arrays.asList(checkList).contains("interested")) {
			excelFieldList.add("interested");
		}
		if(Arrays.asList(checkList).contains("relieved")) {
			excelFieldList.add("relieved");
		}
		if(Arrays.asList(checkList).contains("peaceful")) {
			excelFieldList.add("peaceful");
		}
		if(Arrays.asList(checkList).contains("grateful")) {
			excelFieldList.add("grateful");
		}
		if(Arrays.asList(checkList).contains("joyful")) {
			excelFieldList.add("joyful");
		}
		if(Arrays.asList(checkList).contains("calm")) {
			excelFieldList.add("calm");
		}
		
		List<Map<String,Object>> excelDataMap = homeService.getExcelDownloadView(dataMap);
		excelMakerData.setAllData(ExcelViewCode.EMOTION_EXCEL_LIST, excelFieldList, excelDataMap);
		model.addAttribute("EXCEL_DOWNLOAD_DATA",excelMakerData);
		
		return "excelMaker";
	}
	
}
