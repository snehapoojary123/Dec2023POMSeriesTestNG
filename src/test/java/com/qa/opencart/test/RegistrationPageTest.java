package com.qa.opencart.test;

import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest{
	
	//private final Logger logger = Logger.getLogger(RegistrationPageTest.class);
	
	public String getRandomEmail() {
		Random random = new Random();
		//String email = "automation"+random.nextInt(1000)+"@gmail.com";
		String email = "automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	@BeforeClass
	public void registerPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object[][] registerData = ExcelUtils.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return registerData;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void userRegistrationTest(String fname,String lname,String telephone,String password,String subscribe) {
		Assert.assertTrue(registrationPage.registerUser(fname,lname,getRandomEmail(),telephone,password,subscribe));
		//MDC.put("testClassName", this.getClass().getSimpleName());
		//logger.info("This is a logger from RegistrationPageTest...");
	}

}
