package com.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;

    // Locators
    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By loginStatus = By.xpath("//a[contains(text(), 'Logged in as')]");
    private By deleteAccountBtn = By.xpath("//a[contains(text(), 'Delete Account')]");
    private By logoutBtn = By.xpath("//a[@href='/logout']");
    private By subscriptionEmailField = By.id("susbscribe_email");
    private By subscribeBtn = By.id("subscribe");
    private By successSubscribeMsg = By.id("success-subscribe");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Clicking signup Button")
    public void clickSignupLogin() {
        WaitUtils.waitForElementToBeClickable(driver, signupLoginBtn).click();
    }
    @Step("Checking if User is logged in")
    public boolean isUserLoggedIn(String username) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated(loginStatus, username));
        } catch (Exception e) {
            return false;
        }
    }
    @Step("Clicking Delete Button")
    public void clickDeleteAccount() {
        WaitUtils.waitForElementToBeClickable(driver, deleteAccountBtn).click();
    }
    @Step("Clicking Logout Button")
    public void clickLogout() {
        WaitUtils.waitForElementToBeClickable(driver, logoutBtn).click();
    }
    @Step("Subscribing to news Letter")
    public void subscribeToNewsletter(String email) {
        WaitUtils.scrollToBottom(driver); // Use the new util!
        WaitUtils.waitForElementToBeVisible(driver, subscriptionEmailField).sendKeys(email);
        driver.findElement(subscribeBtn).click();
    }
    @Step("Checking if Subscription Succes Message is visible")
    public boolean isSubscriptionSuccessVisible() {
        return WaitUtils.waitForElementToBeVisible(driver, successSubscribeMsg).isDisplayed();
    }
}