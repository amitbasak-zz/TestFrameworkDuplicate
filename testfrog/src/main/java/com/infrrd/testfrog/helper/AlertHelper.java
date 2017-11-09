
package com.infrrd.testfrog.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author bsingh5
 *
 */

public class AlertHelper {

	private WebDriver driver;

	public AlertHelper(WebDriver driver) {
		this.driver = driver;

	}

	public Alert getAlert() {
		return driver.switchTo().alert();
	}

	public void AcceptAlert() {
		getAlert().accept();
	}

	public void DismissAlert() {
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();

	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();

	}

	public void AcceptPrompt(String text) {

		if (!isAlertPresent())
			return;

		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
	}
}
