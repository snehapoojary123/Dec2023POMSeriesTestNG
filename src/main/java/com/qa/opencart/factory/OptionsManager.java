package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	private SafariOptions so;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setBrowserVersion(prop.getProperty("browserversion"));
			co.setCapability("selenoid:options", new HashMap<String, Object>() {{
				put("browsername", "chrome");
				put("enableVNC", true);
		       
		    }});
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim().toLowerCase())) {
			System.out.println("Running chrome in headless..");
			co.addArguments("--headless");
			
		}
			

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim().toLowerCase())) {
			System.out.println("Running chrome in incognito..");
			co.addArguments("--incognito");
		}
			

		return co;

	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setBrowserVersion(prop.getProperty("browserversion"));						
			fo.setCapability("selenoid:options", new HashMap<String, Object>() {{
			put("browsername", "firefox");
		    put("enableVNC", true);
	       
	    }});
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim().toLowerCase())) {
			System.out.println("Running firefox in headless..");
			fo.addArguments("--headless");
		}
			

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim().toLowerCase())) {
			System.out.println("Running firefox in incognito..");
			fo.addArguments("--incognito");
		}
			

		return fo;

	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim().toLowerCase())) {
			System.out.println("Running edge in headless..");
			eo.addArguments("--headless");
		}
			

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim().toLowerCase())) {
			System.out.println("Running edge in incognito..");
			eo.addArguments("--incognito");
		}
			

		return eo;

	}
	
//	public SafariOptions getSafariOptions() {
//		so = new SafariOptions();
//		if (Boolean.parseBoolean(prop.getProperty("headless").trim().toLowerCase()))
//			so.addArguments("--headless");
//
//		if (Boolean.parseBoolean(prop.getProperty("incognito").trim().toLowerCase()))
//			so.addArguments("--incognito");
//
//		return so;
//
//	}

}
