package com.infrrd.testfrogforapi.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.infrrd.testfrogforapi.runner.TestEnvironmentReader;
import com.infrrd.testfrogforapi.helper.ExcelHelper;

public class TestcaseReader {

	public static Map<String, List<String>> TestcaseMap;
	private static ExcelHelper ExcelHelper;

	public static void runSelect(String filePath) {
		String run = TestEnvironmentReader.environmentConfigurationMap.get("Run");
		ExcelHelper = new ExcelHelper(filePath);
		if (run.equalsIgnoreCase("All")) {
			allRun();
		} else if (run.equalsIgnoreCase("Selected")) {
			selectRun();
		} else if (run.equalsIgnoreCase("Pass")) {
			statusRun(run);
		} else if (run.equalsIgnoreCase("Fail")) {
			statusRun(run);
		} else if (run.equalsIgnoreCase("Skip")) {
			statusRun(run);
		} else {
			System.out.println("Invalid option");
			System.exit(0);
		}

	}

	private static boolean includeGroup(int rowNum) {
		int flag = 0;
		String includeAsString = TestEnvironmentReader.environmentConfigurationMap.get("Include");
		if (!includeAsString.isEmpty()) {
			String[] includeAsArray = includeAsString.split(",");
			for (String groupName : includeAsArray) {
				if (ExcelHelper.getCellData("Testcase", rowNum, "Group").toUpperCase()
						.contains(groupName.trim().toUpperCase())) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	private static boolean excludeGroup(int rowNum) {
		int flag = 0;
		String excludeAsString = TestEnvironmentReader.environmentConfigurationMap.get("Exclude");
		if (!excludeAsString.isEmpty()) {
			String[] excludeAsArray = excludeAsString.split(",");
			for (String groupName : excludeAsArray) {
				if (ExcelHelper.getCellData("Testcase", rowNum, "Group").toUpperCase()
						.contains(groupName.trim().toUpperCase())) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private static void allRun() {
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		int counter = 0;
		int classCounter = 0;
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			counter = 0;
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if (tempTestcaseClasspath.equals(testcaseClasspath)) {
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					if (includeGroup(rowNum) && !excludeGroup(rowNum)) {
						listOfMethods.add(methodName);
						counter++;
					}
				}
			}

			if (counter != 0) {
				classCounter++;
				TestcaseMap.put(testcaseClasspath, listOfMethods);
			}

		}
		if(classCounter == 0){
			System.out.println("No test case found");
			System.exit(0);
		}
		System.out.println(
				"Running all test cases [" + TestEnvironmentReader.environmentConfigurationMap.get("Include") + "]");
	}

	private static void selectRun() {
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		int counter = 0;
		int classCounter=0;
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			counter = 0;
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if (tempTestcaseClasspath.equals(testcaseClasspath)) {
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					String run = ExcelHelper.getCellData(sheetName, rowNum, "Run");
					if (run.equalsIgnoreCase("Y")) {
						if (includeGroup(rowNum) && !excludeGroup(rowNum)) {
							listOfMethods.add(methodName);
							counter++;
						}
					}
				}
			}
			if (counter != 0) {
				classCounter++;
				TestcaseMap.put(testcaseClasspath, listOfMethods);
			}

		}
		if(classCounter == 0){
			System.out.println("No test case found");
			System.exit(0);
		}
		System.out.println("Running selected test cases ["
				+ TestEnvironmentReader.environmentConfigurationMap.get("Include") + "]");
	}

	private static void statusRun(String status) {
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		int counter = 0;
		int classCounter=0;
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			counter = 0;
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if (tempTestcaseClasspath.equals(testcaseClasspath)) {
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					String run = ExcelHelper.getCellData(sheetName, rowNum, "Status");
					if (run.equalsIgnoreCase(status)) {
						if (includeGroup(rowNum) && !excludeGroup(rowNum)) {
							listOfMethods.add(methodName);
							counter++;
						}
					}
				}
			}
			if (counter != 0) {
				classCounter++;
				TestcaseMap.put(testcaseClasspath, listOfMethods);
			}

		}
		if(classCounter == 0){
			System.out.println("No test case found");
			System.exit(0);
		}
		System.out.println("Running " + status + " test cases ["
				+ TestEnvironmentReader.environmentConfigurationMap.get("Include") + "]");
	}

}
