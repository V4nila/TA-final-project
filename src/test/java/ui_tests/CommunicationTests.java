package ui_tests;

import base.BaseTest;
import com.example.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.pages.ContactUsPage;

public class CommunicationTests extends BaseTest {

    @Test(priority = 1, description = "Test Case 6: Contact Us Form")
    public void testContactUs() {
        ContactUsPage contactPage = new ContactUsPage(getDriver());

        getDriver().findElement(By.xpath("//a[contains(text(),'Contact us')]")).click();

        contactPage.fillContactForm("Tester", "test@test.com", "Bug Report", "This is a test message.");

        getDriver().findElement(By.name("upload_file")).sendKeys("/Users/giorgivanishvili/Desktop/myfile.txt");

        contactPage.clickSubmit();
        contactPage.handleAlert();

        Assert.assertEquals(contactPage.getSuccessMessage(), "Success! Your details have been submitted successfully.");

        contactPage.clickHome();

        Assert.assertEquals(getDriver().getTitle(), "Automation Exercise");
    }

    @Test(priority = 2, description = "Test Case 10: Verify Subscription")
    public void testSubscription() {
        HomePage homePage = new HomePage(getDriver());

        homePage.subscribeToNewsletter("test" + System.currentTimeMillis() + "@test.com");
        Assert.assertTrue(homePage.isSubscriptionSuccessVisible());
    }
}