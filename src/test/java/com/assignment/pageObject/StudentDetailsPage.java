package com.assignment.pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StudentDetailsPage {

	WebDriver ldriver;
	
	public StudentDetailsPage(WebDriver rdriver){
		ldriver = rdriver;		
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//h2[contains(normalize-space(),'Enter details to complete document')]")
	WebElement pageHeading;
	
	@FindBy(xpath="//input[@placeholder='First Name']") 
	WebElement inputFirstName;
	
	@FindBy(xpath="//input[@placeholder='Middle Name']") 
	WebElement inputMidName;
	
	@FindBy(xpath="//input[@placeholder='Last Name']") 
	WebElement inputLastName;
	
	@FindBy(xpath="//input[@placeholder='Father']") 
	WebElement inputFatherName;
	
	@FindBy(xpath="//input[@placeholder='Mother']") 
	WebElement inputMotherName;
	
	@FindBy(xpath="//input[@placeholder='Category']") 
	WebElement inputCategory;
	
	@FindBy(xpath="//input[@placeholder='Class']") 
	WebElement inputClass;
	
	@FindBy(xpath="//input[@placeholder='Date of Birth']") 
	WebElement inputDOB;
	
	@FindBy(xpath="//input[@placeholder='Date']") 
	WebElement inputDate;
	
	@FindBy(xpath="//input[@placeholder='Nationality']") 
	WebElement inputNationality;
	
	@FindBy(xpath="//input[@placeholder='Last result']") 
	WebElement inputLastResult;
	
	@FindBy(xpath="//input[@placeholder='Remarks']") 
	WebElement inputRemark;
	
	@FindBy(xpath="//button//span[contains(normalize-space(),'Show changes in preview')]") 
	WebElement BtnShowPreview;
	
	@FindBy(xpath="//button/div[normalize-space()='Generate']")
	WebElement BtnGenerate;
	
	@FindBy(xpath="//div[@data-qa='popup']")
	WebElement DownloadPopUp;
	
	@FindBy(id="download")
	WebElement BtnDownload;
	 
	
    
	public void verifyPage() {
		try {
			if(pageHeading.isDisplayed())
			{
				System.out.println("Enter complete details to generate certicate !!!");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Details page is not opened");
		}
	}

	public String[] enterRemarks(String Fname, String Mname, String Lname, String Fathrname,String MotherName, String Category, String DOB, String Class, String Date,String Nationality, String result, String remark) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));
		String Actual_Fname="", Actual_Class="";
		if(inputFirstName.getAttribute("value").isEmpty())
		{
			inputFirstName.sendKeys(Fname);
		}
		else
		{
			Actual_Fname = inputFirstName.getAttribute("value");
		}
		if(inputMidName.getAttribute("value").isEmpty())
		{
			inputMidName.sendKeys(Mname);
		}
		if(inputLastName.getAttribute("value").isEmpty())
		{
			inputLastName.sendKeys(Lname);
		}
		if(inputFatherName.getAttribute("value").isEmpty())
		{
			inputFatherName.sendKeys(Fathrname);
		}
		if(inputMotherName.getAttribute("value").isEmpty())
		{
			inputMotherName.sendKeys(MotherName);
		}
		if(inputCategory.getAttribute("value").isEmpty())
		{
			inputCategory.sendKeys(Category);
		}
		if(inputNationality.getAttribute("value").isEmpty())
		{
			inputNationality.sendKeys(Nationality);
		}
		if(inputDOB.getAttribute("value").isEmpty())
		{
			inputDOB.sendKeys(DOB);
		}
		if(inputClass.getAttribute("value").isEmpty())
		{
			inputFirstName.sendKeys(Class);
		}
		else
		{
			Actual_Class = inputClass.getAttribute("value");
		}
		if(inputDate.getAttribute("value").isEmpty())
		{
			inputDate.sendKeys(Date);
		}
		if(inputLastResult.getAttribute("value").isEmpty())
		{
			inputLastResult.sendKeys(result);
		}
		if(inputRemark.getAttribute("value").isEmpty())
		{
			inputRemark.sendKeys(remark);
		}
		try {
			if(BtnShowPreview.isDisplayed())
			{
				BtnShowPreview.click();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		String values_returned[] = {Actual_Fname, Actual_Class};
		return values_returned;
		
	}

	public void clickGenerateBtn() {		
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));
		try {
			if(BtnGenerate.isDisplayed())
			{
				BtnGenerate.click();
				System.out.println("Generate Btn is clicked");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	public void downloadCertificate() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));
		try {
			if(DownloadPopUp.isDisplayed())
			{
				BtnDownload.click();
				System.out.println("Download btn is clicked");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void getbackToParentWindow() {
		try {
			Set<String> windowIds = ldriver.getWindowHandles();//captures window ids in set data type
			
			List<String> WId = new ArrayList<String>(windowIds); //converting Set to List data type in order to fetch the details
			String Id1 = WId.get(0);  
			if(ldriver.switchTo().window(Id1).getTitle().contains("Teachmint"))
			{
				ldriver.switchTo().window(Id1);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
