package com.upgrade.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.upgrade.base.BaseClass;

public class BorrowerInformationPage extends BaseClass {

	// Page Factory
	@FindBy(name = "borrowerFirstName")
	WebElement txtFirstName;

	@FindBy(name = "borrowerLastName")
	WebElement txtLastName;

	@FindBy(name = "borrowerStreet")
	WebElement txtStreet;

	@FindBy(name = "borrowerCity")
	WebElement txtCity;

	@FindBy(name = "borrowerState")
	WebElement txtState;

	@FindBy(name = "borrowerZipCode")
	WebElement txtZipCode;

	@FindBy(name = "borrowerDateOfBirth")
	WebElement txtDOB;

	@FindBy(xpath = "//h1[contains(text(),'How much money do you make in a year?')]")
	WebElement hdrIncomePage;

	@FindBy(name = "borrowerIncome")
	WebElement txtIncome;

	@FindBy(name = "borrowerAdditionalIncome")
	WebElement txtAdditionalIncome;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement btnContinue;

	@FindBy(xpath = "//input[@name='username']")
	WebElement txtemailAddress;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtpassword;

	@FindBy(xpath = "(//input[@type='checkbox']/following-sibling::div)[1]")
	WebElement cbTermsOfUse;

	@FindBy(xpath = "//button[contains(text(),'Check Your Rate')]")
	WebElement btnChkYourRate;

	// Intializing the page objects
	public BorrowerInformationPage() {
		PageFactory.initElements(driver, this);
	}

	// Enter Borrowser's Information
	public void enterBorrowersInformation(String fname, String lname, String homeaddress, String DOB)
			throws InterruptedException {

		// Enter Borrower's name
		String[] address = homeaddress.split("-");
		System.out.println(address);
		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);

		// Enter Borrower's address details
		txtStreet.clear();
		txtStreet.sendKeys(address[0]);
		Thread.sleep(2000);
		txtStreet.sendKeys(Keys.ENTER);
		txtCity.sendKeys(address[1]);
		txtState.sendKeys(address[2]);
		txtZipCode.sendKeys(address[3]);

		txtDOB.sendKeys(DOB);
		btnContinue.click();

	}

	public void enterBorrowerIncomeDetails(String income, String addIncome) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h1[contains(text(),'How much money do you make in a year?')]")));
		txtIncome.sendKeys(income);
		txtAdditionalIncome.sendKeys(addIncome);
		txtAdditionalIncome.sendKeys(Keys.ENTER);

	}

	public OfferPage enterAccountDetails(String username, String pwd) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div//h2[contains(text(),'Create an Account')]")));
		if(txtemailAddress.isDisplayed()) {
			txtemailAddress.click();
			txtemailAddress.sendKeys(username);
			txtpassword.sendKeys(pwd);
			cbTermsOfUse.click();
			btnChkYourRate.click();
		}
		
		return new OfferPage();
	}

}
