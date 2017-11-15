package com.infrrd.testfrogforapi.base;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import com.infrrd.testfrogforapi.customlistener.CustomITestListener;
import com.infrrd.testfrogforapi.runner.TestEnvironmentReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	private String testClassPath = System.getProperty("user.dir");
	private static ExtentReports report;
	private static ExtentTest test;
	private URL url;
	protected String URI;
	private String baseURI;
	private String basePath;
	protected Object POJOclass;
	protected Map<String, Object> headerMap = new HashMap<String, Object>();
	private Map<String, Object> queryParamMap = new HashMap<String, Object>();
	private Object pojo;
	protected String jsonFilePath;
	private static String Environment;
	private static Logger log = Logger.getLogger(BaseTest.class.getName());
	protected Response response;
	protected String userName;
	protected String password;

	private static void setEnvironment() {
		Environment = TestEnvironmentReader.environmentConfigurationMap.get("Environment").toLowerCase();
	}

	public static String getEnvironment() {
		setEnvironment();
		return Environment;
	}

	public static ExtentReports getReport() {
		return report;
	}

	public static ExtentTest getTest() {
		return test;
	}

	protected static void setTest(ExtentTest test) {
		BaseTest.test = test;
	}

	public void initializeReports() {
		String reportPath = testClassPath + "/testfrogreport.html";
		report = new ExtentReports(reportPath);

	}

	private void uriCreator() {

		try {
			url = new URL(URI);
			baseURI = url.getProtocol() + "://" + url.getHost();
			basePath = url.getPath().replaceAll("//", "/");
			String[] queries = url.getQuery().split("&");
			queryParamMap.clear();
			for (String query : queries) {
				String[] split = query.split("=");
				queryParamMap.put(split[0], split[1]);
			}

		} catch (NullPointerException e) {

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void get() {
		response = null;
		uriCreator();
		logging("Hitting GET request to " + URI, "info");
		if  ((userName == null) && (password == null))  {
			response = given().baseUri(baseURI).headers(headerMap).queryParams(queryParamMap).when().get(basePath);
		} else {
			response = given().baseUri(baseURI).auth().basic(userName, password).headers(headerMap)
					.queryParams(queryParamMap).when().get(basePath);
		}
	}

	@SuppressWarnings("unchecked")
	public void post() {
		response = null;
		uriCreator();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String path = (testClassPath + jsonFilePath);
			pojo = objectMapper.readValue(new File(path), (Class<Object>) POJOclass);
			logging("Hitting POST request to " + URI, "info");
			if ((userName == null) && (password == null)) {
				response = given().baseUri(baseURI).headers(headerMap).body(pojo).when().post(basePath);
			} else {
				response = given().baseUri(baseURI).auth().basic(userName, password).headers(headerMap).body(pojo)
						.when().post(basePath);
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked" })
	public void patch() {
		response = null;
		uriCreator();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			pojo = objectMapper.readValue(new File(testClassPath + jsonFilePath), (Class<Object>) POJOclass);
			logging("Hitting PATCH request to " + URI, "info");
			if ((userName == null) && (password == null)) {
				response = given().baseUri(baseURI).headers(headerMap).body(pojo).patch(basePath);
			} else {
				response = given().baseUri(baseURI).auth().basic(userName, password).headers(headerMap).body(pojo)
						.patch(basePath);
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void waitTime() {
		long waitTime = response.getTime() + 1000;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void logging(String message, String logType) {
		try {
			Reporter.log(message);

			if (logType.equalsIgnoreCase("info")) {
				log.info(message);
				getTest().log(LogStatus.INFO, message);
			} else if (logType.equalsIgnoreCase("fail")) {
				log.error(message);
				getTest().log(LogStatus.FAIL, message);
			} else if (logType.equalsIgnoreCase("pass")) {
				log.info(message);
				getTest().log(LogStatus.PASS, message);
			} else if (logType.equalsIgnoreCase("skip")) {
				log.info(message);
				getTest().log(LogStatus.SKIP, message);
			}
		} catch (Exception e) {

		}
	}

}
