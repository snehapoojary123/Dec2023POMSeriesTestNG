package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: Design login for open cart application")
@Story("US - 01: design login page features for open cart application")

public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("To get the title of the page..")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		String expectedTitle = AppConstants.LOGIN_PAGE_TITLE;
		Assert.assertEquals(actTitle, expectedTitle);

	}
	@Severity(SeverityLevel.NORMAL)
	@Description("To get the url of the page..")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTON_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("To verify forgot password link..")
	@Test(priority = 3)
	public void ForgotPasswordLinkExistsTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
	}
	@Severity(SeverityLevel.BLOCKER)
	@Description("To verify login to open cart application with valid credentials..")
	@Test(priority = 4)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		String actTitle = accountsPage.getAccountPageTitle();
		String expTitle = AppConstants.ACCOUNT_PAGE_TITLE;
		Assert.assertEquals(actTitle, expTitle);

	}

}
