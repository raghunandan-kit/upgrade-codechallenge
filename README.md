# upgrade-codechallenge
Automation code challenge repo for Upgrade

Project: Maven Project

Framework Components: Selenium Webdriver,Maven, TestNG, Rest-Assured

Tests: automationcode-challenge\src\test\java\com\upgrade\tests

Test scenarios automated:  
UI -  Happy Path - Sign up for a new borrower with all the details. Capture and validate the offer details during signup process and after login post account creation.
API -  Happy Path - Test Login REST API end-point with valid credentials and validate the response and status code. 
API  - Negative case - which includes validation of failure case with invalid credentials with corresponding status code.

Run :  mvn clean install
