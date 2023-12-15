package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.aspectj.util.FileUtil;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;
import com.qa.opencart.factory.OptionsManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * To initialize driver based on browser
	 * 
	 * @param prop
	 * @return
	 */
	public WebDriver initializeDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();

		String browserName = prop.getProperty("browser").trim().toLowerCase();
		System.out.println("Browser name: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on remote grid
				init_remoteDriver("chrome");
			} else {
				// run locally
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

		} else if (browserName.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on remote grid
				init_remoteDriver("firefox");
			} else {
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				
			}
			
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on remote grid
				init_remoteDriver("edge");
			} else {
				// driver = new EdgeDriver(optionsManager.getEdgeOptions());
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			
			
		} else if (browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the right browser name: " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	/**
	 * This method is called internally to initialize the driver with RemoteWebDriver
	 * @param browser
	 */
	private void init_remoteDriver(String browser) {
		System.out.println("Running tests on selenium grid machine...");

		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;
			default:
				System.out.println("Enter the correct browser name for remote execution: " + browser);
				throw new FrameworkException("NOREMOTEBRROWSEREXCEPTION");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * get local thread copy of the driver
	 * 
	 * @return
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();

	}

//	public Properties initializeProperties() {
//		prop = new Properties();
//		try {
//			FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
//			try {
//				prop.load(fis);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return prop;
//		
//	}

	public Properties initializeProperties() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					throw new FrameworkException("WRONG ENVIRONMENT IS PASSED");
				// break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
