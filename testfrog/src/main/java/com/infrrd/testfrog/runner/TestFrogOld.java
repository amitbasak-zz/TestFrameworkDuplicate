package com.infrrd.testfrog.runner;


import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestFrogOld {

	public static String filePath;
	
	public TestFrogOld(String filePath){
		TestFrogOld.filePath = filePath;
	}
	
	public void run(){
		List<XmlSuite> suites = XMLBuilder.build(filePath);
		TestcaseResultWriter writer = new TestcaseResultWriter(filePath);
		TestNG testng = new TestNG();
    	testng.setXmlSuites(suites);
    	testng.run();
	}
	
}
