package com.infrrd.testfrogforapi.base;

import static io.restassured.RestAssured.given;

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


public class BaseTest
{
    private String testClassPath = System.getProperty( "user.dir" );
    private static ExtentReports report;
    private static ExtentTest test;
    protected String baseURI;
    protected String basePath;
    protected String endPoint;
    protected Map<String, Object> headerMap = new HashMap<String, Object>();
    protected Map<String, Object> queryParamMap = new HashMap<String, Object>();
    protected Map<String, Object> pathParamMap = new HashMap<String, Object>();
    protected Object pojo;
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


	public void initializeReports()
    {
        String reportPath = testClassPath + "\\testfrogreport.html";
        report = new ExtentReports( reportPath );
      
    }


    public Response get()
    {
        RestAssured.baseURI = baseURI;
        RestAssured.basePath = basePath;
        Response response = given().headers( headerMap ).queryParams( queryParamMap ).pathParams( pathParamMap ).when()
            .get( endPoint );
        return response;

    }


    @SuppressWarnings ( { "unchecked", "rawtypes" })
    public Response post( Class c )
    {

        ObjectMapper objectMapper = new ObjectMapper();
        Response response = null;
        try {

            pojo = objectMapper.readValue( new File( testClassPath + jsonFilePath ), c );
            RestAssured.baseURI = baseURI;
            RestAssured.basePath = basePath;
            response = given().headers( headerMap ).queryParams( queryParamMap ).pathParams( pathParamMap ).body( pojo ).when()
                .post( endPoint );

        } catch ( JsonParseException e ) {
            e.printStackTrace();
        } catch ( JsonMappingException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return response;
    }


    @SuppressWarnings ( { "unchecked", "rawtypes" })
    public Response patch( Class c )
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = null;
        try {

            pojo = objectMapper.readValue( new File( testClassPath + jsonFilePath ), c );
            RestAssured.baseURI = baseURI;
            RestAssured.basePath = basePath;
            response = given().headers( headerMap ).queryParams( queryParamMap ).pathParams( pathParamMap ).body( pojo ).when()
                .patch( endPoint );

        } catch ( JsonParseException e ) {
            e.printStackTrace();
        } catch ( JsonMappingException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return response;
    }

}
