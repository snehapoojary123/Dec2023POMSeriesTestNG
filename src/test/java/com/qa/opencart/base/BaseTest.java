package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchPage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest {
	
	DriverFactory df;
	protected Properties prop;
	WebDriver driver;
	String browser = "chrome";
	protected LoginPage loginPage; 
	protected AccountsPage accountsPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initializeProperties();
		driver = df.initializeDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
