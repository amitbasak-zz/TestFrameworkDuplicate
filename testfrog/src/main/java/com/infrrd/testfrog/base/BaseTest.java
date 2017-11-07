package com.infrrd.testfrog.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.infrrd.testfrog.customlistener.CustomWebDriverEventListner;
import com.infrrd.testfrog.helper.ExcelHelper;
import com.infrrd.testfrog.helper.FileStructureHelper;
import com.infrrd.testfrog.helper.PropertiesFileHelper;
import com.infrrd.testfrog.runner.TestEnvironmentReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {

	
	private EventFiringWebDriver driver;
	private WebDriver baseDriver;
	private String frameworkClassPath = System.getenv("DRIVER_HOME");
	private String testClassPath = System.getProperty("user.dir");
	private static ExtentReports report;
	private static ExtentTest test;

	

	public static ExtentReports getReport() {
		return report;
	}

	public static ExtentTest getTest() {
		return test;
	}
	
	public static void setTest(ExtentTest test) {
		BaseTest.test = test;
	}

	public String getFrameworkClassPath() {
		return frameworkClassPath;
	}

	public String getTestClassPath() {
		return testClassPath;
	}
	
	public EventFiringWebDriver getDriver() {
		return driver;
	}

	public void initializeDriver() {

		initializeReports();

		setbrowser(TestEnvironmentReader.environmentConfigurationMap.get("Browser"));

		driver = new EventFiringWebDriver(baseDriver);
		CustomWebDriverEventListner listener = new CustomWebDriverEventListner();
		driver.register(listener);
	}

	public void killDriver() {
		driver.quit();
	}

	public void gotoTestURL() {

		driver.get(TestEnvironmentReader.environmentConfigurationMap.get("Test URL"));

	}

	private void setbrowser(String browser) {
		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("windows")) {
				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", frameworkClassPath + "/geckodriver.exe");
					baseDriver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", frameworkClassPath + "/chromedriver.exe");
					baseDriver = new ChromeDriver();
					baseDriver.manage().window().maximize();
				} else {
					System.out.println("Browser not available");
					System.exit(0);
				}
			}else if(os.contains("linux") || os.contains("mac")){
				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.marionette", frameworkClassPath + "/geckodriver");
					baseDriver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", frameworkClassPath + "/chromedriver");
					baseDriver = new ChromeDriver();
					baseDriver.manage().window().maximize();
				} else {
					System.out.println("Browser not available");
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String captureScreenShot(ITestResult result) {
		String imageFileName = "";

		Object currentClass = result.getInstance();
		EventFiringWebDriver driverObject = ((BaseTest) currentClass).getDriver();
		if (driverObject == null) {
			imageFileName = "No Image Captured";
		} else {
			String customeLocation = "/src/test/resources/screenshots/";
			FileStructureHelper.makeDirectory(testClassPath + customeLocation);
			imageFileName = testClassPath + customeLocation
					+ new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new GregorianCalendar().getTime()) + "-"
					+ result.getMethod().getMethodName() + ".png";
			File scrFile = ((TakesScreenshot) driverObject).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(imageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Reporter.log("File path: " + imageFileName);
			Reporter.log("<a href=\"" + imageFileName + "\"><img src=\"file:///" + imageFileName + "\" alt=\"\""
					+ "height='100' width='100'/> " + "<br />");

			return (imageFileName);
		}

		return (imageFileName);
	}

	public void autocompleteTyper(String text, WebElement element) {
		try {
			List<String> textAsArray = Arrays.asList(text.split(""));
			for (String character : textAsArray) {
				element.sendKeys(character);
				Thread.sleep(300);
			}
			element.sendKeys(Keys.ARROW_DOWN);
			element.sendKeys(Keys.TAB);
		} catch (InterruptedException e) {
		}
	}

	public void initializeReports() {
		String reportPath = testClassPath + "/testfrogreport.html";
		report = new ExtentReports(reportPath);
	}

}
