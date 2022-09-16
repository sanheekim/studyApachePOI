package com.study.excel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("homeService")
public class HomeService {
	
	@Autowired
	private HomeDao homeDao;
	
	public List<Map<String,Object>> getExcelDownloadView(Map<String,Object> vo) throws Exception {
		return homeDao.selectExcelDownloadView(vo);
	}

}
