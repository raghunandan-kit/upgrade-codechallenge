package com.upgrade.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.upgrade.base.BaseClass;
import com.upgrade.pages.*;

public class NewBorrowerFlowWeb_Test extends BaseClass {

	SignupUpgradePage signupPage;
	BorrowerInformationPage borrowerInformationPage;
	OfferPage offerpage;
	LoginPage loginpage;
	List<String> offerDetailsOffered = new ArrayList<String>();
	List<String> offerDetailsActual = new ArrayList<String>();
	
	// call the parent class constructor
	public NewBorrowerFlowWeb_Test() {
		super();
	}

	// Call Initialization for browser
	@BeforeMethod
	public void setup() {
		Intialization();
		signupPage = new SignupUpgradePage();
		borrowerInformationPage = new BorrowerInformationPage();
		offerpage = new OfferPage();
		loginpage = new LoginPage();
		
	}

	// New Borrower Sign-up Test
	@Test(dataProvider="signupdata")
	public void signupFlowNewBorrowerUI(String signupData) throws InterruptedException {
		
		String inputdata[]= signupData.split(",");	
		
		// Validate the title of the page
		 Assert.assertEquals("Upgrade - Personal Loans and Cards", signupPage.getSignupPageTitle());

		// Sign-up Page check your rate.
		borrowerInformationPage = signupPage.checkYourRate(inputdata[0], inputdata[1]);
		borrowerInformationPage.enterBorrowersInformation(inputdata[2],inputdata[3],inputdata[4],inputdata[5]);
		borrowerInformationPage.enterBorrowerIncomeDetails(inputdata[6],inputdata[7]);
		borrowerInformationPage.enterAccountDetails(inputdata[8],inputdata[9]);
		
		// Capture the offer details for the Borrower
		offerDetailsOffered= offerpage.captureBorrowerOfferDetails();
		
		//Sign-out from offer page
		offerpage.signOutFromOfferPage();
		
		//Access new URL and login with previously created credentials
		loginpage.navigateToLogin(inputdata[8], inputdata[9]);
		
		//Capture the new offer details 
		offerDetailsActual = offerpage.captureBorrowerOfferDetails();
				
		//Compare both offer values 
		Assert.assertTrue(offerDetailsOffered.equals(offerDetailsActual));
		Reporter.log("Validation of Offer displayed during signup and after login is success.", true);
		
	}

	@AfterMethod
	public void teardown() {
		CloseApplication();

	}

	// Data Provider to read the Input Test data
	@DataProvider(name = "signupdata")
	public String[] readjson() throws IOException, FileNotFoundException, ParseException {
		JSONParser jsonparser = new JSONParser();
		System.out.println("Before file");
		FileReader filereader = new FileReader(
				".\\src\\main\\java\\com\\upgrade\\datasource\\testdata.json");
		Object obj = jsonparser.parse(filereader);
		JSONObject signupdataJsonObj = (JSONObject) obj;
		JSONArray signupdataArray = (JSONArray) signupdataJsonObj.get("TestData");

		String arr[] = new String[signupdataArray.size()];

		for (int i = 0; i < signupdataArray.size(); i++) {

			JSONObject signupdataObject = (JSONObject) signupdataArray.get(i);
			String amount = (String) signupdataObject.get("amount");
			String purpose = (String) signupdataObject.get("purpose");
			String firstName = (String) signupdataObject.get("firstName");
			String lastName = (String) signupdataObject.get("lastName");
			String homeAddress = (String) signupdataObject.get("homeAddress");
			String DOB = (String) signupdataObject.get("DOB");
			String annualIncome = (String) signupdataObject.get("annualIncome");
			String additonalIncome = (String) signupdataObject.get("additonalIncome");
			String email = (String) signupdataObject.get("email");
			String password = (String) signupdataObject.get("password");

			arr[i] = amount + "," + purpose + "," + firstName + "," + lastName + "," + homeAddress + "," + DOB + ","
					+ annualIncome + "," + additonalIncome + "," + email + "," + password;
		}

		return arr;

	}

}
