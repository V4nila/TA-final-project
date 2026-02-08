package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.Method;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

@Listeners({
        AllureTestNg.class,
        listeners.TestListener.class
})

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected String baseUrl = "https://automationexercise.com";

    @BeforeSuite
    public void globalSetup() {
        RestAssured.baseURI = "https://automationexercise.com/api";
        RestAssured.registerParser("text/html", Parser.JSON);

        RestAssured.filters(new AllureRestAssured());
    }

    @BeforeMethod
    public void setUp(Method method) {
        if (method.getDeclaringClass().getName().contains("ui_tests")) {
            driver.set(new ChromeDriver());

            getDriver().manage().window().maximize();
            getDriver().get(baseUrl);
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}