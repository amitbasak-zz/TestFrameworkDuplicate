package com.infrrd.testfrogforapi.base;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baseURI = url.getProtocol() + "://" + url.getHost();
		basePath = url.getPath().replaceAll("//", "/");
		try {

			String[] queries = url.getQuery().split("&");

			for (String query : queries) {
				String[] split = query.split("=");
				queryParamMap.put(split[0], split[1]);
			}

		} catch (NullPointerException e) {

		}
		RestAssured.baseURI = baseURI;
	}

	public Response get() {
		uriCreator();

		Response response = given().headers(headerMap).queryParams(queryParamMap).when().get(basePath);

		return response;

	}

	@SuppressWarnings("unchecked")
	public Response post() {

		uriCreator();

		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {

			pojo = objectMapper.readValue(new File(testClassPath + jsonFilePath), (Class<Object>) POJOclass);
			RestAssured.baseURI = baseURI;
			response = given().headers(headerMap).queryParams(queryParamMap).body(pojo).when().post(basePath);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings({ "unchecked" })
	public Response patch() {
		uriCreator();
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {

			pojo = objectMapper.readValue(new File(testClassPath + jsonFilePath), (Class<Object>) POJOclass);
			RestAssured.baseURI = baseURI;
			response = given().headers(headerMap).queryParams(queryParamMap).body(pojo).when().patch(basePath);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

}
