package com.upgrade.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.upgrade.base.BaseClass;

public class LoginPage extends BaseClass {

	// Page Factory
	@FindBy(name = "username")
	WebElement txtUserName;

	@FindBy(name = "password")
	WebElement txtPassword;

	@FindBy(xpath = "//button[contains(text(),'Sign in to your account')]")
	WebElement btnSignIn;
	
	String loginurl = "https://www.credify.tech/portal/login";

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public OfferPage navigateToLogin(String username, String password) throws InterruptedException {
		
		driver.navigate().to(loginurl);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h1[contains(text(),'Welcome Back!')]")));
	
		txtUserName.sendKeys(username);
		if(txtPassword.isEnabled()){
			txtPassword.sendKeys(password);
		}
		btnSignIn.click();
		return new OfferPage();

	}

}
