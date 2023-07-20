package com;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomateWebAppTest {
	public static WebDriver driver;
	public static String subfoldername;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	
	@BeforeTest
	public void Setup(ITestContext context) 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Capabilities  capabilities = ((RemoteWebDriver) driver).getCapabilities();
		
		String device = capabilities.getBrowserName() + "<===>" + capabilities.getBrowserVersion();
		String author = "Tushar Ranjan Nayak";
		
		extentTest = extentReport.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);
	}
	
	@AfterTest
	public void CloseProcess()
	{
		driver.quit();
	}
	
	@BeforeSuite
	public void InitializeReport()
	{
		ExtentSparkReporter  sparkreport = new ExtentSparkReporter("AllTests.html");
		sparkreport.config().setReportName("All Test Report");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkreport);
		
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));	
	}
	
	@AfterSuite
	public void generateReport()
	{
		extentReport.flush();
	}
	
	@AfterMethod
	public void checkStatus(Method m, ITestResult result)
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extentTest.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.pass(m.getName() + " is Passed");
		}
		
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}
	
	public String captureScreenShot(String fileName)
	{
		if(subfoldername==null)
		{
			LocalDateTime  myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			subfoldername = myDateObj.format(myFormat);
		}
		
		TakesScreenshot  takesScreen = (TakesScreenshot) driver;
		File sourcefile = takesScreen.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShots/" +  subfoldername + "/" + fileName);
		try
		{
			FileUtils.copyFile(sourcefile, destFile);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println("Screenshot Saved Successfully...");
		return destFile.getAbsolutePath();
	}
}
