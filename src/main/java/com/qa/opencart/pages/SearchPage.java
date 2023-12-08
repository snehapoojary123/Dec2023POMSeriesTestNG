package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By searchProductResult = By.cssSelector("#content div.product-layout");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public int searchPageProductCount() {
		 int productCount = eleUtil.waitForElementsVisible(searchProductResult, AppConstants.DEFAULT_LONG_TIME_OUT).size();
		 System.out.println("Product count: "+productCount);
		 return productCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();;
		return new ProductInfoPage(driver);
		
	}

}
