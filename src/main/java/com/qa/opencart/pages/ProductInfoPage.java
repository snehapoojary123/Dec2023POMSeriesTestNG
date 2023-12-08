package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productDescription = By.cssSelector("#tab-description");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successMsg = By.cssSelector("div.alert.alert-success");
	
	Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String productHeader() {
		return eleUtil.doElementGetText(productHeader);

	}

	public int getProductImageCount() {
		int imgCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Image count: " + imgCount);
		return imgCount;
	}

	public int getProductDescriptionLen() {
		int textLength = eleUtil.waitForElementVisible(productDescription, AppConstants.DEFAULT_MEDIUM_TIME_OUT)
				.getText().length();
		System.out.println("Description text length: " + textLength);
		return textLength;

	}
	
	public Map<String, String> getProductInfo() {
		//productInfoMap = new HashMap<String, String>(); does not maintain insertion order
		//productInfoMap = new LinkedHashMap<String, String>(); maintains order
		productInfoMap = new TreeMap<String, String>(); // will maintain order alphabetically 
		productInfoMap.put("Product Name", productHeader());
		getProductMetaData();
		getProductPriceInfo();
		System.out.println("Product Info: "+ productInfoMap );
		return productInfoMap;
		
	}

	private void getProductMetaData() {
				
		List<WebElement> productInfo = eleUtil.getElements(productMetaData);
		for (WebElement e : productInfo) {
			String text = e.getText();
			String metaInfo[] = text.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
			
		}
		
	}

	private void getProductPriceInfo() {
		
		List<WebElement> productPrice = eleUtil.getElements(productPriceData);
		String price = productPrice.get(0).getText();
		String extTax = productPrice.get(1).getText();
		String extValue =extTax.split(":")[1].trim();
		productInfoMap.put("Product Price", price);
		productInfoMap.put("Extra tax", extValue);
		

	}
	
	public void enterQuantity(int qty) {
		System.out.println("Enter qty: "+qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addToCart() {
		eleUtil.doClick(addToCart);
		String successMessg = eleUtil.waitForElementVisible(successMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		
		StringBuilder sb = new StringBuilder(successMessg);
		String mesg = sb.substring(0, successMessg.length()-1).replace("\n", "").toString();
		System.out.println("Success Message: "+mesg);
		return mesg;
	}

}
