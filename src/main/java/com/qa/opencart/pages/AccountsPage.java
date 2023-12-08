package com.qa.opencart.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By searchText = By.xpath("//input[@name='search']");
	private By btnSearch = By.cssSelector("#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		return eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, "Account");
	}

	public String getAccountPageURL() {
		return eleUtil.getURL();
	}

	public boolean isLogoutLinkExists() {
		return eleUtil.doElementIsDisplayed(logoutLink);
	}

	public boolean isSearchExists() {
		return eleUtil.doElementIsDisplayed(searchText);
	}

	public List<String> getAccountHearderList() {
		return eleUtil.getElementsTextList(accHeaders);

	}
	
	public SearchPage doSearch(String searchKey) {
		if(isSearchExists()){
		eleUtil.waitForElementPresence(searchText, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		eleUtil.doSendKeys(searchText, searchKey);
		eleUtil.doClick(btnSearch);
		return new SearchPage(driver);
		}
		else {
			System.out.println("Search field is not present on the page...");
			return null;
		}
		
		
		
	}

}
