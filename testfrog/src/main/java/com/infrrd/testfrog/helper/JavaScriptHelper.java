
package com.infrrd.testfrog.helper;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;




public class JavaScriptHelper {

	private WebDriver driver;
	

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
	}

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel(int args) {
		executeScript("window.scrollBy(0,args)",args);
	}

	public void scrollUpByPixel(int args) {
		executeScript("window.scrollBy(0,-args)",args);
	}

	public void ZoomInBypercentage(int args) {
		executeScript("document.body.style.zoom='args%'",args);
	}

	public void ZoomBy100percentage(int args) {
		executeScript("document.body.style.zoom='args%'",args);
	}
}
