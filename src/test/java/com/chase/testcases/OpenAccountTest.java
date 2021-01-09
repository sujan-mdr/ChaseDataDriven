package com.chase.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.chase.base.TestBase;

public class OpenAccountTest extends TestBase {
	
	
	@Test
	public void openAccount() {
		
		WebElement menu = driver.findElement(By.xpath("/html/body/div/div[6]/header/div[4]/div[1]/div/div"));
		menu.click();
		Actions action = new Actions(driver);
		action.moveToElement(menu).perform();
		click("opnBtn_XPATH");
		
		
	}

}
