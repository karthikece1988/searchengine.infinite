/**
 * 
 */
package com.infinite.searchengine;

import com.aventstack.extentreports.Status;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.infinite.common.*;



public class SearchEngine extends BaseClass {
	
	/**  Maximum time set to wait until **/
	
	Duration WaitDuration = Duration.ofSeconds(20);
	
	ElementAction action= new ElementAction();
	
	String base64Code;
	
	/** Webelements Xpath for Google,Yahoo & Bing Search Engines **/
	
	@FindBy(xpath="//textarea[@title='Search']")
	private WebElement Element_TextBox_GoogleSearch;
	
	@FindBy(xpath="//textarea[@type='search']")
	private WebElement Element_TextBox_BingSearch;
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement Element_TextBox_YahooSearch;
			
	
	@FindBy(xpath = "//span[@class='VuuXrf']")
	private WebElement Element_Search_Result;
	
	@FindBy(xpath = "//span[@class='VuuXrf']")
	private WebElement Element_Search_GoogleResult;
	
	@FindBy(xpath = "//span[@class='d-ib p-abs t-0 l-0 fz-14 lh-20 fc-obsidian wr-bw ls-n pb-4']")
	private WebElement Element_Search_YahooResult;
	
	@FindBy(xpath = "//div[@class='tptt']")
	private WebElement Element_Search_BingResult;
	
		
	
	public SearchEngine() {
		PageFactory.initElements(getDriver(), this);
	}
	
		
	public void ValidateSearchEngine() throws Exception 
	{
		String SearchEng= System.getProperty("SearchEngine");
		String SearchTerm=System.getProperty("SearchTerm"); 
		
		if (SearchEng.equalsIgnoreCase("google"))
		{
			/**  waits until search box element visible **/
			action.explicitWait(getDriver(),Element_TextBox_GoogleSearch , WaitDuration);	
			action.type(Element_TextBox_GoogleSearch, SearchTerm);
			Element_TextBox_GoogleSearch.sendKeys(Keys.ENTER); 
			test.log(Status.INFO, "Test Script => Performing Search for : " + SearchTerm);
			
		
		System.out.println("com.infinite.searchengine : First result set for Search Term  : " + SearchTerm + " is " + Element_Search_GoogleResult.getText());
		try {
			Assert.assertEquals("Infinite Computer Solutions", Element_Search_Result.getText(),"First search result doesn't match the expected result")	;
			test.log(Status.PASS, "Test Script => Search & Assertion Completed Successful for Search term : " + SearchTerm );
		}
		catch (AssertionError e)
		{
			test.log(Status.FAIL, "Test Script => Search & Assertion Failed for Search term : " + SearchTerm  +" With Error : " + e.toString());
			throw new Exception(e.toString());
		}
		
		    base64Code = BaseClass.captureScreenshotBase64(getDriver());
		    test.log(Status.INFO, " Test Script => Webpage Snapshot captured. On this report, click the image named 'base64.img' ").addScreenCaptureFromBase64String(base64Code);
		    System.out.println("com.infinite.searchengine : Login Screenshot captured");
		
		}
		else if(SearchEng.equalsIgnoreCase("yahoo"))
		{
		
			/**  waits until search box element visible **/
			action.explicitWait(getDriver(),Element_TextBox_YahooSearch , WaitDuration);
			action.type(Element_TextBox_YahooSearch, SearchTerm);
			Element_TextBox_YahooSearch.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			test.log(Status.INFO, "Test Script => Performing Search for : " + SearchTerm);
			System.out.println("com.infinite.searchengine : First result set for Search Term  : " + SearchTerm + " is " + Element_Search_YahooResult.getText());
			try {
			Assert.assertEquals("www.infinite.com", Element_Search_YahooResult.getText(),"First search result doesn't match the expected result")	;
			test.log(Status.PASS, "Test Script => Search & Assertion Completed Successful for Search term : " + SearchTerm );
			}
			catch (AssertionError e)
			{
				test.log(Status.FAIL, "Test Script => Search & Assertion Failed for Search term : " + SearchTerm  +" With Error : " + e.toString());
				throw new Exception(e.toString());
			}
			
			base64Code = BaseClass.captureScreenshotBase64(getDriver());
		    test.log(Status.INFO, " Test Script => Webpage Snapshot captured. On this report, click the image named 'base64.img' ").addScreenCaptureFromBase64String(base64Code);
		    System.out.println("com.infinite.searchengine : Login Screenshot captured");
		}
		else if(SearchEng.equalsIgnoreCase("bing"))
		{
		    
			/**  waits until search box element visible **/
			action.explicitWait(getDriver(),Element_TextBox_BingSearch , WaitDuration);
			action.type(Element_TextBox_BingSearch, SearchTerm);
			Element_TextBox_BingSearch.sendKeys(Keys.ENTER);
			test.log(Status.INFO, "Test Script => Performing Search for : " + SearchTerm);
			Thread.sleep(3000);
			System.out.println("com.infinite.searchengine : First result set for Search Term  : " + SearchTerm + " is " + Element_Search_BingResult.getText());
			try {
			Assert.assertEquals("Infinite Computer Solutions", Element_Search_BingResult.getText(),"First search result doesn't match the expected result")	;
			test.log(Status.PASS, "Test Script => Search & Assertion Completed Successful for Search term : " + SearchTerm );
			}
			catch (AssertionError e)
			{
				test.log(Status.FAIL, "Test Script => Search & Assertion Failed for Search term : " + SearchTerm  +" With Error : " + e.toString());
				throw new Exception(e.toString());
			}
			
			base64Code = BaseClass.captureScreenshotBase64(getDriver());
		    test.log(Status.INFO, " Test Script => Webpage Snapshot captured. On this report, click the image named 'base64.img' ").addScreenCaptureFromBase64String(base64Code);
		    System.out.println("com.infinite.searchengine : Login Screenshot captured");
			
		}
		
		else
		{
			System.out.println("com.infinite.searchengine : Failed . Please provide valid Search Engine. Valid Search Engines are Google / Yahoo / Bing ");
			test.log(Status.FAIL, "Test Script => Failed for Search Engine "+SearchEng +". Please provide valid Search Engine. Valid Search Engines are Google / Yahoo / Bing");
		}
		
		 
		
	}
}
