package com.infinite.common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	
	/**  Action Interface to perform web element actions. Reusable across different Tests **/
	
	public void click(WebDriver ldriver, WebElement ele);
	
	public boolean type(WebElement ele, String text);
	
	public void explicitWait(WebDriver driver, WebElement element, Duration timeOut );
	
	


}
