package com.infrrd.testfrog.runner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.infrrd.testfrog.helper.ExcelHelper;

public class TestFrog {

	public static String filePath;
	private String[] args;
	private Map<String, String> environmentWriterMap;

	public TestFrog(String[] args) {
		this.args = args;
		argsMapper();
		environmentWriter();
		filePath = environmentWriterMap.get("f");
	}

	public void run() {
		List<XmlSuite> suites = XMLBuilder.build(filePath);
		TestcaseResultWriter writer = new TestcaseResultWriter(filePath);
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.run();
	}

	private void environmentWriter() {
		int rowNum;
		ExcelHelper helper = new ExcelHelper(environmentWriterMap.get("f"));
		for (Map.Entry<String, String> entry : environmentWriterMap.entrySet()) {
			if (entry.getKey().equals("b")) {
				rowNum = helper.getRowNumber("Environment", "Variable", "browser");
				if (rowNum > 0) {
					helper.writeCellValue("Environment", rowNum, "Value", entry.getValue());
				} else {
					System.out.println("Browser field is missing");
					System.exit(0);
				}
			} else if (entry.getKey().equals("r")) {
				rowNum = helper.getRowNumber("Environment", "Variable", "run");
				if (rowNum > 0) {
					helper.writeCellValue("Environment", rowNum, "Value", entry.getValue());
				} else {
					System.out.println("Run field is missing");
					System.exit(0);
				}
			} else if (entry.getKey().equals("i")) {
				rowNum = helper.getRowNumber("Environment", "Variable", "include");
				if (rowNum > 0) {
					helper.writeCellValue("Environment", rowNum, "Value", entry.getValue());
				} else {
					System.out.println("Include field is missing");
					System.exit(0);
				}
				
			} else if (entry.getKey().equals("e")) {
				rowNum = helper.getRowNumber("Environment", "Variable", "exclude");
				if (rowNum > 0) {
					helper.writeCellValue("Environment", rowNum, "Value", entry.getValue());
				} else {
					System.out.println("Exclude field is missing");
					System.exit(0);
				}
				
			}
		}
	}

	private void argsMapper() {
		if (args.length == 0) {
			System.out.println("Test frog didnot receive any arguments");
			System.exit(0);
		} else {
			environmentWriterMap = new HashMap<String, String>();
			for (String paramAsString : args) {
				String[] paramAsArray = paramAsString.split("=");
				environmentWriterMap.put(paramAsArray[0].trim(), paramAsArray[1].trim());
			}
			int flag = 0;
			for (Map.Entry<String, String> entry : environmentWriterMap.entrySet()) {
				if (entry.getKey().equalsIgnoreCase("f")) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				System.out.println("Test frog didnot receive configuration file");
				System.exit(0);
			}

		}
	}

}
