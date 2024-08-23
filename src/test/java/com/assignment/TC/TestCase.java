package com.assignment.TC;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.testng.annotations.Test;

import com.assignment.pageObject.CertificatePage;
import com.assignment.pageObject.DashBoardPage;
import com.assignment.pageObject.LoginPage;
import com.assignment.pageObject.StudentDetailsPage;

public class TestCase extends BaseClass{
	
	@Test
	public void LoginTest() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("URL is opened successfully");
		
		
		
		LoginPage lp = new LoginPage(driver);
		String LoginType = "Password";
		if(LoginType.equals("Password"))
		{
			lp.setUserName(adminCredentials[0]);
			logger.info("userName is set");
			lp.setUpPassword(pass);
			logger.info("OTP is set");
		}
		else
		{
			lp.setUserName(adminCredentials[0]);
			logger.info("userName is set");
			lp.setOtp(adminCredentials[1]);
			logger.info("OTP is set");
		}
		
		lp.verifySuccessfullLogin(accountName);
		logger.info("Logged in successfully");
		
		DashBoardPage dp = new DashBoardPage(driver);
		dp.clickCertificateOption();
		logger.info("Successfully on Certificate Page");
		
		CertificatePage cp = new CertificatePage(driver);
		String certificatType="School leaving certificate";
		cp.selectCertificate(certificatType);
		logger.info("Certificate Selected is :"+certificatType);
		cp.clickGenerateCertificate();
		logger.info("Generate btn is clicked ");
		cp.SearchAndSelectGuest("SAM");
		logger.info("Search and select the student and Click on generate");
		
		StudentDetailsPage sp = new StudentDetailsPage(driver);
		sp.verifyPage();  
		logger.info("Successfully on Enetr Student Details page");
		String todaysDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		sp.enterRemarks("SAM", "", "TESTFA", "Test Father", "Test Mother","UR", "12/12/2001","12", todaysDate,"INDIAN","PASS", "Good Student");
		logger.info("Remarks added successfully");
		sp.clickGenerateBtn();
		logger.info("Generate Btn is clicked");
		sp.downloadCertificate();
		logger.info("certicate Downloaded");
		sp.getbackToParentWindow();
		logger.info("Back to parent Window");
		
		dp.clickCertificateOption();
		logger.info("Back to certificate page.");
		dp.ValidateHistory("SAM", "12", "School leaving certificate");
		logger.info("history Validated");
		
	}

}
