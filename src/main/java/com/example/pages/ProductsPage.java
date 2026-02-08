package com.example.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.example.utils.WaitUtils;
import java.util.List;

public class ProductsPage {
    WebDriver driver;

    private By allProductsHeader = By.xpath("//h2[text()='All Products']");
    private By searchField = By.id("search_product");
    private By searchBtn = By.id("submit_search");
    private By searchedProductsHeader = By.xpath("//h2[text()='Searched Products']");
    private By viewProductFirst = By.xpath("(//a[text()='View Product'])[1]");
    private By productName = By.xpath("//div[@class='product-information']/h2");
    private By productPrice = By.xpath("//div[@class='product-information']/span/span");


    public ProductsPage(WebDriver driver) { this.driver = driver; }

    @Step("Checking if 'All Products' header is visible")
    public boolean isAllProductsVisible() {
        return WaitUtils.waitForElementToBeVisible(driver, allProductsHeader).isDisplayed();
    }

    @Step("Searching for product: {0}")
    public void searchProduct(String name) {
        driver.findElement(searchField).sendKeys(name);
        driver.findElement(searchBtn).click();
    }

    @Step("Viewing the first product details")
    public void clickViewProduct() {
        driver.findElement(viewProductFirst).click();
    }

    @Step("Verifying product details are visible")
    public boolean isProductDetailVisible() {
        return driver.findElement(productName).isDisplayed() &&
                driver.findElement(productPrice).isDisplayed();
    }
    @Step("Verifyiing if searched Product is Visible")
    public boolean isSearchedProductsVisible() {
        return WaitUtils.waitForElementToBeVisible(driver, searchedProductsHeader).isDisplayed();
    }
}