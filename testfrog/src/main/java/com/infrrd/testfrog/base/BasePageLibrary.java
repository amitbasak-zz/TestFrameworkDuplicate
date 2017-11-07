package com.infrrd.testfrog.base;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BasePageLibrary<ObjectRepository extends BasePageObjectRepository> {
	
	EventFiringWebDriver driver;
	public ObjectRepository ObjectRepository;
	
	public BasePageLibrary(EventFiringWebDriver driver, ObjectRepository repository){
		this.driver=driver;
		ObjectRepository = repository;
	}

}
