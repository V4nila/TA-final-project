package ui_tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.pages.HomePage;
import com.example.pages.SignupPage;

public class UserAuthTests extends BaseTest {

    private String email1 = "test1_" + System.currentTimeMillis() + "@test.com";
    private String email2 = "test2_" + System.currentTimeMillis() + "@test.com";
    private String email4 = "test4_" + System.currentTimeMillis() + "@test.com";
    private String email5 = "test5_" + System.currentTimeMillis() + "@test.com";

    @Test(priority = 1, description = "Test Case 1: Register User and Delete")
    public void testRegisterUser() {
        HomePage homePage = new HomePage(getDriver());
        SignupPage signupPage = new SignupPage(getDriver());

        homePage.clickSignupLogin();
        signupPage.enterSignupDetails("User1", email1);
        signupPage.fillFullDetails(
                "Mr",
                "Pass123",
                "John",
                "Doe",
                "12",
                "June",
                "1990",
                "My Company Inc",
                "123 Street",
                "United States",
                "California",
                "Los Angeles",
                "90001",
                "555-1234"
        );

        Assert.assertTrue(signupPage.isAccountCreated(), "Verification Failed: 'ACCOUNT CREATED!' message was not visible.");

        signupPage.clickContinue();
        Assert.assertTrue(homePage.isUserLoggedIn("User1"), "Verification Failed: User1 is not shown as logged in.");

        homePage.clickDeleteAccount();
        Assert.assertTrue(signupPage.isAccountDeletedVisible(), "ACCOUNT DELETED! message not found");
        signupPage.clickContinue();
    }

    @Test(priority = 2, description = "Test Case 2: Login with Correct Credentials")
    public void testLoginCorrect() {
        HomePage homePage = new HomePage(getDriver());
        SignupPage signupPage = new SignupPage(getDriver());

        homePage.clickSignupLogin();
        signupPage.enterSignupDetails("User2", email2);
        signupPage.fillFullDetails(
                "Mr",
                "Pass123",
                "John",
                "Doe",
                "12",
                "June",
                "1990",
                "My Company Inc",
                "123 Street",
                "United States",
                "California",
                "Los Angeles",
                "90001",
                "555-1234"
        );
        signupPage.clickContinue();
        homePage.clickLogout();

        homePage.clickSignupLogin();
        signupPage.login(email2, "Pass123");
        Assert.assertTrue(homePage.isUserLoggedIn("User2"), "Verification Failed: User2 is not shown as logged in after correct login.");

        homePage.clickDeleteAccount();
        Assert.assertTrue(signupPage.isAccountDeletedVisible(), "ACCOUNT DELETED! message not found");
        signupPage.clickContinue();
    }

    @Test(priority = 3, description = "Test Case 3: Login User with incorrect email")
    public void testLoginIncorrect() {
        HomePage homePage = new HomePage(getDriver());
        SignupPage signupPage = new SignupPage(getDriver());

        homePage.clickSignupLogin();
        signupPage.login("wrong_" + System.currentTimeMillis() + "@email.com", "WrongPass123");

        Assert.assertEquals(signupPage.getLoginErrorMessage(), "Your email or password is incorrect!",
                "Verification Failed: Wrong error message shown for incorrect login.");
    }

    @Test(priority = 4, description = "Test Case 4: Logout User")
    public void testLogout() {
        HomePage homePage = new HomePage(getDriver());
        SignupPage signupPage = new SignupPage(getDriver());

        homePage.clickSignupLogin();
        signupPage.enterSignupDetails("User4", email4);
        signupPage.fillFullDetails(
                "Mr",
                "Pass123",
                "John",
                "Doe",
                "12",
                "June",
                "1990",
                "My Company Inc",
                "123 Street",
                "United States",
                "California",
                "Los Angeles",
                "90001",
                "555-1234"
        );
        signupPage.clickContinue();

        Assert.assertTrue(homePage.isUserLoggedIn("User4"), "Verification Failed: User4 not logged in before logout.");

        homePage.clickLogout();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/login"), "Verification Failed: URL does not contain '/login' after logout.");
    }

    @Test(priority = 5, description = "Test Case 5: Register with Existing Email")
    public void testRegisterWithExistingEmail() {
        HomePage homePage = new HomePage(getDriver());
        SignupPage signupPage = new SignupPage(getDriver());

        homePage.clickSignupLogin();
        signupPage.enterSignupDetails("User5", email5);
        signupPage.fillFullDetails(
                "Mr",
                "Pass123",
                "John",
                "Doe",
                "12",
                "June",
                "1990",
                "My Company Inc",
                "123 Street",
                "United States",
                "California",
                "Los Angeles",
                "90001",
                "555-1234"
        );
        signupPage.clickContinue();
        homePage.clickLogout();

        homePage.clickSignupLogin();
        signupPage.enterSignupDetails("User5", email5);

        Assert.assertEquals(signupPage.getSignupErrorMessage(), "Email Address already exist!",
                "Verification Failed: Did not see 'Email Address already exist!' error.");
    }
}