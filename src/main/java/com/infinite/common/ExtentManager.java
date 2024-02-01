package com.infinite.common;



import java.io.File;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//import com.mdirect.testcases.Extent;

public class ExtentManager {
	
	//public static ExtentReports extent;
	//public ExtentSparkReporter sparkreporter;
	//public ExtentTest extentlogger;
	
	
	public static ExtentReports getReportObject() {
		
	String Path = 	System.getProperty("user.dir")+"\\Reports\\extentreport.html";	
	ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
	
	
	
	reporter.config().setReportName("Microchip Direct Test Automation");
	reporter.config().setDocumentTitle("Microchip Direct Test Automation Report");
	
	ExtentReports extent = new ExtentReports();
	//extent = new ExtentReports(path, false);
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester","Mircochip Direct QA team");
	
	return extent;	
	}
	
	
	
	
}
