package com.assignment.pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CertificatePage {
	WebDriver ldriver;
	
	public CertificatePage(WebDriver rdriver){
		ldriver = rdriver;		
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(xpath="//div[@class='Cards_cardDetails__WsZ-E']/h6")
	List<WebElement> certificatesNames;	
	
	@FindBy(xpath="//button[@body='Generate']")
	WebElement BtnGenerate;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement searchStudentfield;
	
	
	public void selectCertificate(String certificateType) {
		
		for(WebElement ele : certificatesNames)
		{
			if(ele.getText().equalsIgnoreCase(certificateType))
			{
				ele.click();
			}
		}
	}
	
	public void clickGenerateCertificate() {
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		BtnGenerate.click();
	}
	
	public void SearchAndSelectGuest(String name) {
		try
		{
			if(searchStudentfield.isDisplayed())
			{
				searchStudentfield.sendKeys(name,Keys.ENTER);
				//getting rows count as per student list
				int total_rows = ldriver.findElements(By.xpath("//table[contains(@class,'UserList')]/tbody//tr")).size();
				for(int r=1;r<=total_rows;r++)
				{
					String actualName = ldriver.findElement(By.xpath("//table[contains(@class,'UserList')]/tbody//tr["+r+"]/td[2]//p[1]")).getText();
					if(actualName.equalsIgnoreCase(name))
					{
						ldriver.findElement(By.xpath("//table[contains(@class,'UserList')]/tbody//tr["+r+"]//input[@type='checkbox']")).click();
						ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						ldriver.findElement(By.xpath("//table[contains(@class,'UserList')]/tbody//tr["+r+"]//td//button")).click();
						
						 WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
					     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));
					     
					     
					     break;
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
