package com.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.example.utils.WaitUtils;

public class SignupPage {
    WebDriver driver;

    // Locators
    private By newUserText = By.xpath("//h2[text()='New User Signup!']");
    private By nameInput = By.xpath("//input[@data-qa='signup-name']");
    private By emailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[@data-qa='signup-button']");
    private By accountInfoText = By.xpath("//b[text()='Enter Account Information']");
    private By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By loginHeader = By.xpath("//h2[text()='Login to your account']");
    private By loginError = By.xpath("//p[text()='Your email or password is incorrect!']");
    private By signupError = By.xpath("//p[text()='Email Address already exist!']");
    private By accountDeletedHeader = By.xpath("//b[text()='Account Deleted!']");
    private By mrRadio = By.id("id_gender1");
    private By mrsRadio = By.id("id_gender2");
    private By daysSelect = By.id("days");
    private By monthsSelect = By.id("months");
    private By yearsSelect = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By optinCheckbox = By.id("optin");
    private By companyInput = By.id("company");
    private By countrySelect = By.id("country");

    // Detailed Info Locators
    private By passwordInput = By.id("password");
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By addressInput = By.id("address1");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileInput = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");
    private By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Entering signup details - Name: {0}, Email: {1}")
    public void enterSignupDetails(String name, String email) {
        WaitUtils.waitForElementToBeVisible(driver, newUserText);
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(signupBtn).click();
    }

    @Step("Filling full account information for: {2} {3}")
    public void fillFullDetails(String title, String password, String fName, String lName,
                                String day, String month, String year, String company,
                                String address, String country, String state, String city,
                                String zip, String mobile) {

        WaitUtils.waitForElementToBeVisible(driver, accountInfoText);

        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(mrRadio).click();
        } else {
            driver.findElement(mrsRadio).click();
        }

        driver.findElement(passwordInput).sendKeys(password);

        new org.openqa.selenium.support.ui.Select(driver.findElement(daysSelect)).selectByVisibleText(day);
        new org.openqa.selenium.support.ui.Select(driver.findElement(monthsSelect)).selectByVisibleText(month);
        new org.openqa.selenium.support.ui.Select(driver.findElement(yearsSelect)).selectByVisibleText(year);

        driver.findElement(newsletterCheckbox).click();
        driver.findElement(optinCheckbox).click();

        driver.findElement(firstNameInput).sendKeys(fName);
        driver.findElement(lastNameInput).sendKeys(lName);
        driver.findElement(companyInput).sendKeys(company);
        driver.findElement(addressInput).sendKeys(address);

        new org.openqa.selenium.support.ui.Select(driver.findElement(countrySelect)).selectByVisibleText(country);

        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipcodeInput).sendKeys(zip);
        driver.findElement(mobileInput).sendKeys(mobile);

        driver.findElement(createAccountBtn).click();
    }

    @Step("Verifying if 'Account Created' message is visible")
    public boolean isAccountCreated() {
        try {
            WebElement successMsg = WaitUtils.waitForElementToBeVisible(driver, accountCreatedText);
            return successMsg.getText().equalsIgnoreCase("ACCOUNT CREATED!");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicking 'Continue' button")
    public void clickContinue() {
        WaitUtils.waitForElementToBeClickable(driver, continueBtn).click();
    }

    @Step("Attempting login with Email: {0}")
    public void login(String email, String password) {
        WaitUtils.waitForElementToBeVisible(driver, loginHeader);
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    @Step("Retrieving login error message")
    public String getLoginErrorMessage() {
        return WaitUtils.waitForElementToBeVisible(driver, loginError).getText();
    }

    @Step("Retrieving signup error message")
    public String getSignupErrorMessage() {
        return WaitUtils.waitForElementToBeVisible(driver, signupError).getText();
    }

    @Step("Verifying if 'Account Deleted' confirmation is visible")
    public boolean isAccountDeletedVisible() {
        return WaitUtils.waitForElementToBeVisible(driver, accountDeletedHeader).isDisplayed();
    }
}