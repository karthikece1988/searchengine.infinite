package com.infinite.common;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.ietf.jgss.Oid;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.beust.jcommander.Parameter;
import com.infinite.common.ElementAction;
import com.infinite.common.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	/**  Base Class component with common functionalities **/
	
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
		
	
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	
	@BeforeSuite
	public void InitializeExtentReport() 	
	{

		
		String Path = 	System.getProperty("user.dir")+"\\Reports\\extentreport.html";	
		ExtentSparkReporter reporter = new ExtentSparkReporter(Path);		
		reporter.config().setReportName("com.infinite.searchengine - Test Automation");
		reporter.config().setDocumentTitle("com.infinite.searchengine - Test Automation Report");
		
		extent = new ExtentReports();	
		extent.attachReporter(reporter);
		extent.setSystemInfo("Karthik Muniraman"," SDET Team");
		 
	}
	
	@AfterSuite
	public void GenerateExtentReport() 	
	{
		extent.flush();
		
				
	}
	
	
	/* Get driver thread */ 	
	public static WebDriver getDriver() 
	{
				return driver.get();
				
	}
	
	//@Parameters("browser")	
	@BeforeTest
	@SuppressWarnings("deprecation")
	public void LaunchBrowser(ITestContext context) throws InterruptedException {

		String browserName =  System.getProperty("Browser"); 		
		if (browserName.equalsIgnoreCase("Chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");  
		    System.setProperty("webdriver.http.factory", "jdk-http-client");
			WebDriverManager.chromedriver().setup();			
			driver.set(new ChromeDriver());
			
			
			
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}	
		
		else
		{
			test.log(Status.FAIL, "Test Script => Failed. Please Check Browser Parameter, Valid Browser Parameters are Chrome/Edge/Firefox ");
		}
		
		
		getDriver().manage().window().maximize();	
		System.out.println("com.infinite.searchengine : Launching Browser : " + browserName.toLowerCase());

//				
		test = extent.createTest(context.getName()).assignAuthor("Test Automation Script").assignCategory("Search Engine Test");
		test.log(Status.INFO, "Test Script => Launched Browser : " + browserName.toLowerCase());
		Thread.sleep(1000);
	
				
	}

	public static String captureScreenshot(WebDriver driver, String ScreenshotName)
	{
	    try 
	    {
	        TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest="./Screenshots/"+ScreenshotName+".png";
	        File snapshotDest =new File(dest);
	        FileUtils.copyFile(source, snapshotDest);
	        System.out.println("Screenshot Taken at "+System.currentTimeMillis());
	        return dest;
	    } 
	    catch (Exception e) 
	    {
	    System.out.println("Exception while taking screenshot "+e.getMessage());
	    return e.getMessage();
	        }
	    } 


		public static String captureScreenshotBase64(WebDriver driver)
	{
	    try 
	    {
	        TakesScreenshot ts = (TakesScreenshot)driver;	        
	        String base64Code = ts.getScreenshotAs(OutputType.BASE64);      
	        System.out.println("Screenshot Taken at "+System.currentTimeMillis());
	        return base64Code;
	    } 
	    catch (Exception e) 
	    {
	    System.out.println("Exception while taking screenshot "+e.getMessage());
	    return e.getMessage();
	        }
	    }
	
    public static void reportScreenshot(String ExtentScreenshotName) {
		
	      test.pass(MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+ "\\Screenshots\\"+ExtentScreenshotName+".png").build());
	}


	public void LaunchWebsite (String WebsiteUrl)
	{
		
			getDriver().get(WebsiteUrl);
		
			
			
	}

	public void explicitWait(WebDriver driver, WebElement element, Duration timeOut) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
