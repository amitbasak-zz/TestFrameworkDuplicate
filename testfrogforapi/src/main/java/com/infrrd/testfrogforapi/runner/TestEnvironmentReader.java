package com.infrrd.testfrogforapi.runner;

import java.util.HashMap;
import java.util.Map;

import com.infrrd.testfrogforapi.helper.ExcelHelper;

public class TestEnvironmentReader {

	

	public static Map<String, String> environmentConfigurationMap;

	public static void reader(String filePath){
		
		environmentConfigurationMap = new HashMap<String, String>();
		
		String sheetName = "Environment";
		
		ExcelHelper ExcelHelper = new ExcelHelper(filePath);
		int rowCount = ExcelHelper.getRowCount(sheetName);
				
		int rowNum;
		
		for(rowNum = 1 ; rowNum <= rowCount ; rowNum ++){
			environmentConfigurationMap.put(ExcelHelper.getCellData(sheetName, rowNum, 1),ExcelHelper.getCellData(sheetName, rowNum, 2));
		}
		
	}

}
