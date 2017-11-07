package com.infrrd.testfrogforapi.runner;

import org.testng.ITestResult;

import com.infrrd.testfrogforapi.helper.ExcelHelper;

public class TestcaseResultWriter{
	
	private static String filePath;
	private ExcelHelper helper;
	
	TestcaseResultWriter(String filePath) {
		this.filePath = filePath;
	}
	
	public TestcaseResultWriter() {
		// TODO Auto-generated constructor stub
	}

	public void writeResult(ITestResult result, String status, String screenshotPath){
		helper = new ExcelHelper(filePath);
		String className = result.getMethod().getTestClass().getName();
		String methodName = result.getMethod().getMethodName();
		int rowCount = helper.getRowCount("Testcase");
		int rowNum;
		for(rowNum = 2 ; rowNum < rowCount ; rowNum ++){
			String tempClassName = helper.getCellData("Testcase", rowNum, "Testcase Classpath");
			if(tempClassName.equals(className)){
				String tempMethodName = helper.getCellData("Testcase", rowNum, "Method name");
				if(tempMethodName.equals(methodName)){
					break;
				}
			}
		}
		helper.writeCellValue("Testcase", rowNum, "Status", status);
		if(status.equalsIgnoreCase("Pass")){
			
			helper.setFont("Testcase", rowNum, "Status", "green", true, false);
			helper.setCellColour("Testcase", rowNum, "Status", "green");
		}else if(status.equalsIgnoreCase("fail")){
			
			helper.setFont("Testcase", rowNum, "Status", "red", true, false);
			helper.setCellColour("Testcase", rowNum, "Status", "red");
		}else if(status.equalsIgnoreCase("skip")){
			
			helper.setFont("Testcase", rowNum, "Status", "yellow", true, false);
			helper.setCellColour("Testcase", rowNum, "Status", "yellow");
		}
		
		if(!screenshotPath.equalsIgnoreCase("NA")){
			helper.writeCellValue("Testcase", rowNum, "Screenshot", screenshotPath);
		}
		
	}

}
