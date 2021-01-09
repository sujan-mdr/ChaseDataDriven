package com.chase.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.chase.base.TestBase;
import com.chase.utilities.TestUtil;

public class ChaseSignInTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")
	public void chaseSignin(String username, String password) throws InterruptedException {
		
		WebElement webElement = driver.findElement(By.cssSelector("iframe[id^='logonbox']"));
		if (webElement.isDisplayed()) {
			driver.switchTo().frame("logonbox");
			type("username_CSS", username);
			type("password_CSS", password);
			click("signinBtn_CSS");
		}
		else {
			driver.findElement(By.xpath("//*[@id=\"signin-module\"]/div[2]/div/div/div/div[2]/a")).click();
			type("username_CSS", username);
			type("password_CSS", password);
			click("signinBtn_CSS");
		}
		
		

	
		
		}

}
