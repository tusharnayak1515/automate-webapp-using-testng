package com;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Mytest extends AutomateWebAppTest {
	public SoftAssert soft = new SoftAssert();
	
	@Test(testName="RegisterTest")
	public void TestRegister() throws Exception
	{			
				driver.get("C:\\Users\\tusha\\OneDrive\\Desktop\\java-fsd\\Phase-5\\Cycle-2\\Practice Project(code)\\register.html");
				Thread.sleep(2000);
				driver.findElement(By.id("name")).sendKeys("Tushar Ranjan Nayak");
				Thread.sleep(2000);
				driver.findElement(By.id("email")).sendKeys("tushar@gmail.com");
				Thread.sleep(2000);
				driver.findElement(By.id("password")).sendKeys("Test123@");
				Thread.sleep(2000);
				
				String filename = "RegisterTest" + "_" + "TestRegister" + ".jpg";
				String screenshotpath = captureScreenShot(filename);
				extentTest.addScreenCaptureFromPath(screenshotpath);
				
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("form")).submit();
				Thread.sleep(2000);
				String val = driver.switchTo().alert().getText();
				soft.assertEquals("Registration successful", val, "Registration failed");
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				soft.assertAll();
				extentTest.pass("Assertion is passed for Register");
				
	}
	
	@Test(testName="ValidLoginTest")
	public void ValidLogin() throws Exception
	{			
				driver.get("C:\\Users\\tusha\\OneDrive\\Desktop\\java-fsd\\Phase-5\\Cycle-2\\Practice Project(code)\\login.html");
				Thread.sleep(2000);
				driver.findElement(By.id("email")).sendKeys("tushar@gmail.com");
				Thread.sleep(2000);
				driver.findElement(By.id("password")).sendKeys("Test123@");
				Thread.sleep(2000);
				
				String filename = "ValidLoginTest" + "_" + "ValidLogin" + ".jpg";
				String screenshotpath = captureScreenShot(filename);
				extentTest.addScreenCaptureFromPath(screenshotpath);
				
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("form")).submit();
				Thread.sleep(2000);
				String val = driver.switchTo().alert().getText();
				soft.assertEquals("Login successful", val, "Login failed");
				driver.switchTo().alert().accept();
				soft.assertAll();
				extentTest.pass("Assertion is passed for Valid Login");
	}
	
	@Test(testName="InvalidLoginTest")
	public void InvalidLogin() throws Exception
	{			
				driver.get("C:\\Users\\tusha\\OneDrive\\Desktop\\java-fsd\\Phase-5\\Cycle-2\\Practice Project(code)\\login.html");
				Thread.sleep(2000);
				driver.findElement(By.id("email")).sendKeys("tushar1@gmail.com");
				Thread.sleep(2000);
				driver.findElement(By.id("password")).sendKeys("Test123@");
				Thread.sleep(2000);

				String filename = "InvalidLoginTest" + "_" + "InvalidLogin" + ".jpg";
				String screenshotpath = captureScreenShot(filename);
				extentTest.addScreenCaptureFromPath(screenshotpath);
				
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("form")).submit();
				Thread.sleep(2000);
				String val = driver.switchTo().alert().getText();
				soft.assertEquals("Incorrect credentials", val, "Login failed");
				driver.switchTo().alert().accept();
				soft.assertAll();
				extentTest.pass("Assertion is passed for Invalid Login");
	}
	
	@Test(testName="SearchTest")
	public void search() throws Exception
	{			
				driver.get("https://www.google.com");
				Thread.sleep(2000);

				String filename1 = "SearchTest" + "_" + "search" + ".jpg";
				String screenshotpath1 = captureScreenShot(filename1);
				extentTest.addScreenCaptureFromPath(screenshotpath1);
				
				Thread.sleep(2000);
				driver.findElement(By.id("APjFqb")).sendKeys("JavaScript",Keys.ENTER);

				String filename2 = "SearchTest" + "_" + "search2" + ".jpg";
				String screenshotpath2 = captureScreenShot(filename2);
				extentTest.addScreenCaptureFromPath(screenshotpath2);
				
				Thread.sleep(2000);
				soft.assertEquals("JavaScript - Google Search", driver.getTitle());
				Thread.sleep(2000);
				soft.assertAll();
				extentTest.pass("Assertion is passed for Searching");
	}
	
	@Test(testName="AddToCartTest")
	public void addToCart() throws Exception
	{			
				driver.get("C:\\Users\\tusha\\OneDrive\\Desktop\\java-fsd\\Phase-5\\Cycle-2\\Practice Project(code)\\cart.html");
				String filename = "AddToCartTest" + "_" + "addToCart" + ".jpg";
				String screenshotpath = captureScreenShot(filename);
				extentTest.addScreenCaptureFromPath(screenshotpath);
				Thread.sleep(2000);
				driver.findElement(By.id("btn")).click();
				String val = driver.switchTo().alert().getText();
				System.out.println("Val: "+val);
				soft.assertEquals("Added to Cart", val);
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				soft.assertAll();
				extentTest.pass("Assertion is passed for Add To Cart");
	}
}
