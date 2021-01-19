package com.upgrade.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import com.upgrade.utils.UtilClass;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	// Load the config file in the constructor
	public BaseClass() {
		try {
			prop = new Properties();
			//
			FileInputStream file = new FileInputStream(
					".\\src\\main\\java\\com\\upgrade\\config\\config.properties");
			System.out.println(new File(".").getCanonicalPath());
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void Intialization() {
		String browser = prop.getProperty("browser");

		// ChromeDriver Intialization. Can be extended for other browsers.
		if (browser.equals("chrome")) {
			Reporter.log("******* Browser Session Started ******", true);
			System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(UtilClass.PAGE_LOAD_TIMED_OUT, TimeUnit.SECONDS); // Page Load wait time
		driver.manage().timeouts().implicitlyWait(UtilClass.IMPLICIT_WAIT, TimeUnit.SECONDS); // wait time for find	element
		driver.get(prop.getProperty("url"));  //Load the application URL
		Reporter.log("******* Application Started ******", true);

	}

	public static void CloseApplication() {
		driver.quit();
		Reporter.log("******* Browser Session ended ******", true);
	}

}
