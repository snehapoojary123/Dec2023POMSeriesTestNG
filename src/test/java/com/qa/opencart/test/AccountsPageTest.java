package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import net.bytebuddy.description.annotation.AnnotationList.Empty;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}

	@Test
	public void accountsPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		String expTitle = AppConstants.ACCOUNT_PAGE_TITLE;
		Assert.assertEquals(actTitle, expTitle);

	}

	@Test
	public void accountsPageURLTest() {
		String actURL = accountsPage.getAccountPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTON_VALUE));

	}

	@Test
	public void logoutLinkExistsTest() {
		boolean flag = accountsPage.isLogoutLinkExists();
		Assert.assertTrue(flag);

	}

	@Test
	public void searchTextExistsTest() {
		boolean flag = accountsPage.isSearchExists();
		Assert.assertTrue(flag);

	}

	@Test
	public void accountHeadersCountTest() {
		List<String> accHeaderList = accountsPage.getAccountHearderList();
		Assert.assertEquals(accHeaderList.size(), AppConstants.ACCOUNT_PAGE_HEADER_COUNT);

	}
	
	@Test
	public void accountHeadersValueTest() {
		List<String> accHeaderList = accountsPage.getAccountHearderList();
		System.out.println("actual account pahe header list: "+ accHeaderList);
		System.out.println("expected account pahe header list: "+ AppConstants.EXP_ACCOUNTS_PAGE_HEADER_LIST);
		Assert.assertEquals(accHeaderList, AppConstants.EXP_ACCOUNTS_PAGE_HEADER_LIST);

	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
			
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accountsPage.doSearch(searchKey);
		Assert.assertTrue(searchPage.searchPageProductCount()>0);
		
	}
	
	@DataProvider
	public Object[][] getProductSeriesData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"},
			
		};
	}
	
	@Test(dataProvider = "getProductSeriesData")
	public void selectProductTest(String product,String productSeries) {
		searchPage = accountsPage.doSearch(product);
		if(searchPage.searchPageProductCount()> 0) {
			productInfoPage = searchPage.selectProduct(productSeries);
			String actHeaderValue = productInfoPage.productHeader();
			Assert.assertEquals(actHeaderValue, productSeries);
		}
		else {
			System.out.println("No search result found...");
		}
		
		
		
	}
	
	

}
