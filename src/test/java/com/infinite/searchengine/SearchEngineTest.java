package com.infinite.searchengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.infinite.common.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.infinite.searchengine.*;


public class SearchEngineTest extends BaseClass
{
	SearchEngine SearchEngine;
	String SearchEng ="";
	String Url="";
		
	
	@Test (priority = 1)	
	public void openWebsite() throws IOException 
		{
		try
		{
		/** Below Properties file **/
		String configFilePath = System.getProperty("user.dir")+"\\Config\\Properties.config";
		FileInputStream propsInput = new FileInputStream(configFilePath);		
		Properties prop = new Properties();
		prop.load(propsInput);
		SearchEng= System.getProperty("SearchEngine");
		Url = prop.getProperty(SearchEng.toLowerCase());
	 	LaunchWebsite(Url); 
	 	System.out.println("com.infinite.searchengine : Launching Search Engine : " + SearchEng);
		test.log(Status.INFO, "Test Script => Navigated to Website : " + Url);
				
		}
		
		catch(Exception e) 
		{
			System.out.println("com.infinite.searchengine : Failure : " + e.toString());
			//messageAndScreenshot(Status.INFO, "Exception caught in getting properties : ", false, "");
		}
		
	}
	
	@Test (priority = 2)
	public void verifyTitle() throws Exception 
	{
		
		String actTitle=getDriver().getTitle();		 
		System.out.println("com.infinite.searchengine : Web Page Title :  " + actTitle);	
		//Assert.assertAll();
	 	
	}
	
	@Test (priority = 3)
	public void TestSearchEngine() throws Throwable 
	{   
			
	
		SearchEngine= new SearchEngine();		
		SearchEngine.ValidateSearchEngine();
		
	}
	
	
	
		
	@AfterTest (enabled = true)
	public void CloseBrowser() throws InterruptedException 
	{   
		
		test.log(Status.INFO, " Test Script => End of Test Suite : com.infinite.searchengine");	
		Thread.sleep(2000);
		getDriver().quit();
		//extent.flush();
	
	}
	
	
}
