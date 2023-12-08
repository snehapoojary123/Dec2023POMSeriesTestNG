package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// 1. private By locators
	// so that the xpath is not changed, do not give access to page locatora
	// restrict access via public method - example of encapsulation

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By txtEmailID = By.id("input-email");
	private By txtPassword = By.id("input-password");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By btnLogon = By.xpath("//input[@value='Login']");
	private By welcomeText = By.xpath("//h2[text()='My Account']");
	private By registerLink = By.linkText("Register");

	// 2.page const..
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3.page actions/method
	@Step("To get the login page title")
	public String getLoginPageTitle() {
		return  eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, "Login");
		}
	@Step("To get the login page url")
	public String getLoginPageURL() {
		return eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, "login");
	}
	@Step("To verify the forgot password link exists")
	public boolean isForgotPasswordLinkExists() {
		return eleUtil.waitForElementVisible(forgotPassword, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	@Step("To login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String password) {
		eleUtil.waitForElementVisible(txtEmailID, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(txtPassword, password);
		eleUtil.doClick(btnLogon);
		return new AccountsPage(driver);

	}
	@Step("To get the account page welcome text")
	public String getWelcomeText() {
		return driver.findElement(welcomeText).getText();

	}
	@Step("To navigate to registration page")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
