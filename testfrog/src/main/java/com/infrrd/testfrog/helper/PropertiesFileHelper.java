package com.infrrd.testfrog.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHelper {

	
	
	private Properties loadProperties(String filePath) {
		Properties prop = new Properties();
		try{
			InputStream input = new FileInputStream(filePath);
			prop.load(input);
		}catch(IOException e){
			e.printStackTrace();
		}
		return prop;
	}
	
	public String readProperty(String filePath, String key){
		String value;
		Properties prop = loadProperties(filePath);
		value = prop.getProperty(key);
		return value;
		
	}

}
