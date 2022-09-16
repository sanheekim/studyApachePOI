package com.study.excel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface HomeDao {
	
	List<Map<String,Object>> selectExcelDownloadView(Map<String,Object> paramMap) throws SQLException;

}
