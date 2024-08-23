package com.assignment.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver){
		ldriver = rdriver;		
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//input[@type='text']")
	WebElement userName;
	
	@FindBy(id="send-otp-btn-id")
	WebElement BtnNext;
	
	@FindBy(id="submit-otp-btn-id")
	WebElement BtnSubmitOtp;
	
	@FindBy(xpath="//span[@onclick='onPasswordLoginClick()']")
	WebElement loginWithPassword;
	
	@FindBy(id="login-password")
	WebElement inputPass;
	
	public void setUserName(String uName)
	{
		userName.sendKeys(uName);
        //Thread.sleep(1000);
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("entered user phone number " + uName);
        BtnNext.click();

        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));
        ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void setOtp(String OTP_Val)
	{
		
		String inputOtpField = "//input[@data-group-idx='{}']";
        for (int i = 0; i < OTP_Val.length(); i++) {
            String otpField = inputOtpField.replace("{}", String.valueOf(i));
            ldriver.findElement(By.xpath(otpField)).sendKeys(String.valueOf(OTP_Val.charAt(i)));
        }
        System.out.println("entered otp " + OTP_Val);

        ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ldriver.findElement(By.id("submit-otp-btn-id")).click();
        ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));

        ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void setUpPassword(String pass)
	{
		loginWithPassword.click();
		inputPass.sendKeys(pass);
		BtnSubmitOtp.click();
	}
	
	public void verifySuccessfullLogin(String accountName) {
		String userName = "//div[@class='profile-user-name']/..//div[text()='" + accountName + "']";
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(userName)));
        ldriver.findElement(By.xpath(userName)).click();

        String dashboardXpath = "//a[text()='Dashboard']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dashboardXpath)));

        ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ldriver.navigate().refresh();
       
       }
	
}
