package com.upgrade.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.upgrade.base.BaseClass;

public class SignupUpgradePage extends BaseClass {

	// Page Factory

	@FindBy(xpath = "//h2[contains(text(),'Get Started Here')]")
	WebElement hdrGetStartedHere;

	@FindBy(name = "desiredAmount")
	WebElement txtLoanAmt;

	@FindBy(xpath = "//select[@aria-label='Loan Purpose']")
	WebElement ddLoanPurpose;

	@FindBy(xpath = "//button[contains(text(),'Check your rate')]")
	WebElement btnCheckYourRate;

	// Intializing the page objects
	public SignupUpgradePage() {
		PageFactory.initElements(driver, this);
	}

	// Return the title of the page
	public String getSignupPageTitle() {

		return driver.getTitle();

	}

	// Check your rate action after entering amount and purpose
	public BorrowerInformationPage checkYourRate(String amt, String purpose) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Get Started Here')]")));
		
		//Enter amount and purpose and proceed
		txtLoanAmt.sendKeys(amt);
		ddLoanPurpose.click();
		Select loanPurpose = new Select(ddLoanPurpose);
		loanPurpose.selectByValue(purpose);
		btnCheckYourRate.click();

		return new BorrowerInformationPage();
	}

}
