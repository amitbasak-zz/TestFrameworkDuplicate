package com.infrrd.testfrog.customlistener;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.infrrd.testfrog.base.BaseTest;
import com.infrrd.testfrog.runner.TestEnvironmentReader;
import com.relevantcodes.extentreports.LogStatus;

public class CustomWebDriverEventListner extends BaseTest implements WebDriverEventListener{
	
	static Logger log = Logger.getLogger(CustomWebDriverEventListner.class.getName());
		

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
		int waitDuration = Integer.parseInt(TestEnvironmentReader.environmentConfigurationMap.get("Wait Duration"));
		WebDriverWait wait = new WebDriverWait(driver, waitDuration);
		String loaderxpath = TestEnvironmentReader.environmentConfigurationMap.get("Loader xpath"); 
		
		if(!loaderxpath.equalsIgnoreCase("na")){
			try{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loaderxpath)));
			}catch(Exception e){
				logging("Loader animation took time to disappear");
			}
		}
			
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e){
			logging("Not able to locate element " + by.toString() + " due to " + e.toString());
		}
			
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver)  {
		if(!element.isEnabled()){
			logging("Element is not enabled : " +  element.toString());
		}
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		logging("Clicked on element: "+element.toString());
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		logging("Changed value of element: "+element.toString());
		
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		
		
	}
	
	private void logging(String message){
		log.info(message);
		getTest().log(LogStatus.INFO, message);
		Reporter.log(message);
	}

}
