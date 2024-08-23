package com.assignment.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashBoardPage {
	WebDriver ldriver;
	
	public DashBoardPage(WebDriver rdriver){
		ldriver = rdriver;		
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(className ="Sidebar_sidebar__2QmUO")
	WebElement sideBarMenu;
	
	@FindBy(className ="Sidebar_accordionTitle__22J-U")
	List<WebElement> sideMenuDescription;
		
	@FindBy(xpath="//div[@data-qa='accordion-body']//a")
	List<WebElement> sideSubMenus;
	
	@FindBy(xpath="//div[@data-qa='accordion-body']//a[normalize-space()='Certificates']")
	WebElement certificateOption;
	
	@FindBy(xpath="//h2[normalize-space()=\"Certificates and Documents\"]")
	WebElement certificateHeading;
	
	@FindBy(xpath="//h6[contains(normalize-space(),'Recently generated certificates')]")
	WebElement HistoryHeading;
		
 	public void clickCertificateOption() {
		Actions act = new Actions(ldriver);
		act.moveToElement(sideBarMenu).perform();
		
		List<WebElement> menuDesc = sideMenuDescription;
		
		for(WebElement ele : menuDesc)
		{
			//System.out.println(ele.getText());
			if(ele.getText().equalsIgnoreCase("Administration"))
			{
				String menuArrow = "//div[text()='{}']/following-sibling::span";
				String menuArrowPath = menuArrow.replace("{}", ele.getText());
				act.moveToElement(sideBarMenu).perform();
				String menuClassproperty = ldriver.findElement(By.xpath(menuArrowPath)).getAttribute("class").toString();
				if(menuClassproperty.contains("icon-chevronDown"))
				{
					ldriver.findElement(By.xpath(menuArrowPath)).click();
				}
				
	            certificateOption.click();
				
			  List<WebElement> subMenuDesc = sideSubMenus; 
			  for(WebElement subEle : subMenuDesc) 
			  { 
				  act.moveToElement(sideBarMenu).perform();
				  if(subEle.getText().equalsIgnoreCase("Certificates"))
				  { 
					  subEle.click();
				  } 
			  }	 
			  break;
			}
		}
	}
	
	
	public void verifyCertificatePage() {
		try {
			if(certificateHeading.isDisplayed())
			{
				System.out.println("Successully on certicate page");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void ValidateHistory(String Name, String Class, String CertificateType) {
		try {
			if(HistoryHeading.isDisplayed())
			{
				int total_historRows = ldriver.findElements(By.xpath("//table[@class='krayon__TableVirtualized-module__RFe6H']//tbody/tr")).size();
				for(int r=1;r<=total_historRows;r++)
				{
					String actualName = ldriver.findElement(By.xpath("//table[@class='krayon__TableVirtualized-module__RFe6H']/tbody//tr["+r+"]/td[1]//p[1]")).getText();
					String actualClass = ldriver.findElement(By.xpath("//table[@class='krayon__TableVirtualized-module__RFe6H']/tbody//tr["+r+"]/td[2]//p")).getText();
					String actualCertificateType = ldriver.findElement(By.xpath("//table[@class='krayon__TableVirtualized-module__RFe6H']/tbody//tr["+r+"]/td[3]//p")).getText();
					if(actualName.equalsIgnoreCase(Name) || actualClass.equalsIgnoreCase(Class) || actualCertificateType.equalsIgnoreCase(CertificateType))
					{
						System.out.println("Entery presented at top Name :- "+actualName+" Class :- "+actualClass+" Certificate Type :- "+actualCertificateType);					     
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
