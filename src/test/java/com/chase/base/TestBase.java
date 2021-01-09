package com.chase.base;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.chase.utilities.ExcelReader;
import com.chase.utilities.MonitoringMail;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static Logger log = Logger.getLogger(TestBase.class);
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\com.chase.excel\\testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	
	
	public void quit() {
		
		driver.quit();
		log.info("Test Execution has completed");
		
	}
	
	public boolean isElementPresent( String locator) {
		
		try {
		if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator)));
			
		}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator)));
			
		}else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator)));
			
		}
		log.info("Checking the element presence for: " + locator);
		  //ExtentListeners.test.info("Checking the element presence for:: " + locator);
		return true;
		
		}catch(Throwable t) {
			return false;
		}
		  
	}
  
	public void click( String locator) {
		
		if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			
		}else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
			
		}
		  log.info("Clicking on an element: " + locator);
		 // ExtentListeners.test.info("Clicking on an element: " + locator);
	}
	
	public void type(String locator, String value) {
		
		if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			
		}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			
		}else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			
		}
		
		log.info("Typing on element: " + locator + "  and entered the value as: " + value);
	//ExtentListeners.test.info("Typing on element: " + locator + "  and entered the value as: " + value);
		
	}
	
	
	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			PropertyConfigurator.configure(".\\src\\test\\resources\\com.chase.properties\\log4j.properties");
			log.info("Test Execution has been started!!");

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\com.chase.properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.info("OR properties Loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\com.chase.properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Config.load(fis);
				log.info("Config files has been loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("Chrome has launched!!");

			} else if (Config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox has launched");

			}
			
			driver.get(Config.getProperty("testsiteurl"));
			log.info("Navigated to Chase Bank!!!");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			
			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

		}

	}

	
	@AfterSuite
	public void tearDown() {
		
		quit();
		
		

	}

}
