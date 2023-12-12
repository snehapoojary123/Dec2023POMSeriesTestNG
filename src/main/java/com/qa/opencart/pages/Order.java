package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class Order {
	
	By locator =By.id("order");
	By price =By.id("price");
	
	public void order() {
		System.out.println("order..");
	}

	public void price() {
		System.out.println("price..");
	}

}
