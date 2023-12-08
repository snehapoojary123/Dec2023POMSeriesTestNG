package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Samsung","Samsung Galaxy Tab 10.1",7},
			
		};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String productKey, String ProductName, int imgCount) {
		searchPage = accountsPage.doSearch(productKey);
		productInfoPage = searchPage.selectProduct(ProductName);
		Assert.assertEquals(productInfoPage.getProductImageCount(), imgCount);
		
		
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
	public void productDescPresentTest(String productKey, String ProductName) {
		searchPage = accountsPage.doSearch(productKey);
		productInfoPage = searchPage.selectProduct(ProductName);
		Assert.assertTrue(productInfoPage.getProductDescriptionLen()>0);
		
		
	}
	
	@Test()
	public void productInfoTest() {
		searchPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Name"),"MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Product Price"),"$2,000.00");
		softAssert.assertAll();
		
		
	}
	@Test(dataProvider = "getProductSeriesData")
	public void addToCartTest(String productKey, String ProductName) {
		searchPage = accountsPage.doSearch(productKey);
		productInfoPage = searchPage.selectProduct(ProductName);
		productInfoPage.enterQuantity(2);
		String actSuccMsg = productInfoPage.addToCart();
		softAssert.assertTrue(actSuccMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actSuccMsg.indexOf(ProductName)>0);
		softAssert.assertEquals(actSuccMsg, "Success: You have added "+ProductName + " to your shopping cart!");
		softAssert.assertAll();
		
	}
	
	
}
