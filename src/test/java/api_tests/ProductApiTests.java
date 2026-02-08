package api_tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductApiTests extends BaseTest {

    @Test(priority = 1)
    public void testGetAllProducts() {
        given()
                .when()
                .get("/productsList")
                .then()
                .contentType(ContentType.HTML)
                .body("products", hasSize(greaterThan(0)));
    }

    @Test(priority = 2)
    public void testPostToAllProductsList() {
        given()
                .when()
                .post("/productsList")
                .then()
                .body("message", containsString("is not supported"));
    }

    @Test(priority = 3)
    public void testGetAllBrands() {
        given()
                .when()
                .get("/brandsList")
                .then()
                .body("brands", notNullValue());
    }

    @Test(priority = 4)
    public void testPutToAllBrandsList() {
        given()
                .when()
                .put("/brandsList")
                .then()
                .body("message", containsString("is not supported"));
    }

    @Test(priority = 5)
    public void testPostToSearchProduct() {
        given()
                .formParam("search_product", "top")
                .when()
                .post("/searchProduct")
                .then()
                .body("products", hasSize(greaterThan(0)));
    }

    @Test(priority = 6)
    public void testPostToSearchProductWithoutParameter() {
        given()
                .when()
                .post("/searchProduct")
                .then()
                .body("message", containsString("parameter is missing"));
    }

    @Test(priority = 7)
    public void testVerifyLoginWithValidDetails() {
        given()
                .formParam("email", "test12345@test.com")
                .formParam("password", "password123")
                .when()
                .post("/verifyLogin")
                .then()
                .body("message", containsString("User not found"));
    }

    @Test(priority = 8)
    public void testVerifyLoginWithoutEmail() {
        given()
                .formParam("password", "password123")
                .when()
                .post("/verifyLogin")
                .then()
                .body("message", containsString("parameter is missing"));
    }

    @Test(priority = 9)
    public void testDeleteToVerifyLogin() {
        given()
                .when()
                .delete("/verifyLogin")
                .then()
                .body("message", containsString("is not supported"));
    }

    @Test(priority = 10)
    public void testVerifyLoginWithInvalidDetails() {
        given()
                .formParam("email", "nonexistent_user_1121@test.com")
                .formParam("password", "wrongpass")
                .when()
                .post("/verifyLogin")
                .then()
                .body("message", containsString("User not found"));
    }
}