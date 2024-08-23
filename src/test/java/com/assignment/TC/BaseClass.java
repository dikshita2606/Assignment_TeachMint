package com.assignment.TC;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public String baseURL = "https://accounts.teachmint.com/";
	public String[] adminCredentials = new String[]{"0000020232", "120992", "@Automation-2"};
	public String pass = "test123@";
	public String accountName = "@Automation-2";
	
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	public void browserSetup() {		
		logger = Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("log4j2.properties");	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseURL); 
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
		logger.info("Test Case ended and Chrome is closed");
	}
}
