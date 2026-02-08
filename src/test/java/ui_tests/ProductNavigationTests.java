package ui_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.pages.HomePage;
import com.example.pages.ProductsPage;

public class ProductNavigationTests extends BaseTest {

    @Test(priority = 1, description = "Test Case 7: Verify Test Cases Page")
    public void testVerifyTestCasesPage() {
        getDriver().findElement(By.xpath("//a[contains(text(),'Test Cases')]")).click();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("test_cases"));
    }

    @Test(priority = 2, description = "Test Case 8: Verify All Products and detail page")
    public void testProductDetails() {
        ProductsPage productPage = new ProductsPage(getDriver());

        getDriver().findElement(By.xpath("//a[@href='/products']")).click();

        Assert.assertTrue(productPage.isAllProductsVisible());
        productPage.clickViewProduct();
        Assert.assertTrue(productPage.isProductDetailVisible());
    }

    @Test(priority = 3, description = "Test Case 9: Search Product")
    public void testSearchProduct() {
        ProductsPage productPage = new ProductsPage(getDriver());

        getDriver().findElement(By.xpath("//a[@href='/products']")).click();

        productPage.searchProduct("dress");
        Assert.assertTrue(productPage.isSearchedProductsVisible());
    }
}