package com.upgrade.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.upgrade.base.BaseClass;

public class OfferPage extends BaseClass {

	// Page Factory
	@FindBy(xpath = "//div[contains(text(),'Your Loan Amount')]/parent::div//span[@data-auto='userLoanAmount']")
	WebElement txtLoanAmt;

	@FindBy(xpath = "//p[contains(text(),'Monthly Payment')]/preceding-sibling::span")
	WebElement txtMonthlyPayment;

	@FindBy(xpath = "//div[@data-auto='defaultLoanTerm']")
	WebElement txtTerm;

	@FindBy(xpath = "//div[@data-auto='defaultLoanInterestRate']")
	WebElement txtInterestRate;

	@FindBy(xpath = "//div[@data-auto='defaultMoreInfoAPR']/div")
	WebElement txtAPR;

	@FindBy(xpath = "//div/label[@class='header-nav__toggle']")
	WebElement hdrMenu;

	@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	WebElement btnSignOut;

	public OfferPage() {
		PageFactory.initElements(driver, this);
	}

	// To store the offer details
	List<String> offerDetails = new ArrayList<String>();

	public List<String> captureBorrowerOfferDetails() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/label[contains(text(),'Menu')]")));
		offerDetails.add(txtLoanAmt.getText().trim());
		offerDetails.add(txtMonthlyPayment.getText().trim());
		offerDetails.add(txtTerm.getText().trim());
		offerDetails.add(txtInterestRate.getText().trim());
		offerDetails.add(txtAPR.getText().trim());

		return offerDetails;
	}

	public void signOutFromOfferPage() throws InterruptedException {

		Thread.sleep(3000);
		// action class for Menu > sign-out click
		Actions builder = new Actions(driver);
		builder.moveToElement(hdrMenu).build().perform();
		builder.moveToElement(hdrMenu).click().build().perform();
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign Out')]"))).click();
	}

}
