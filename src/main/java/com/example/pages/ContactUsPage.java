package com.example.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.utils.WaitUtils;

public class ContactUsPage {
    WebDriver driver;

    // Locators
    private By contactHeader = By.xpath("//h2[text()='Get In Touch']");
    private By nameField = By.xpath("//input[@data-qa='name']");
    private By emailField = By.xpath("//input[@data-qa='email']");
    private By subjectField = By.xpath("//input[@data-qa='subject']");
    private By messageField = By.xpath("//textarea[@data-qa='message']");
    private By submitBtn = By.xpath("//input[@data-qa='submit-button']");
    private By successMsg = By.xpath("//div[contains(@class, 'status alert alert-success')]");
    private By homeBtn = By.xpath("//a[span[text()=' Home']]");

    public ContactUsPage(WebDriver driver) { this.driver = driver; }

    @Step("Filling out the contact form for: {0}")
    public void fillContactForm(String name, String email, String subject, String message) {
        WaitUtils.waitForElementToBeVisible(driver, contactHeader);
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(messageField).sendKeys(message);
    }

    @Step("Clicking Submit on Contact Form")
    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    @Step("Handling the browser confirmation alert")
    public void handleAlert() {
        driver.switchTo().alert().accept();
    }

    @Step("Retrieving the success message")
    public String getSuccessMessage() {
        return WaitUtils.waitForElementToBeVisible(driver, successMsg).getText();
    }

    @Step("Clicking Home button")
    public void clickHome() {
        driver.findElement(homeBtn).click();
    }
}