package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(getDriver());
        Assert.assertEquals(productsPage.getPageTitle(), "Products", "Header title mismatch on products page");
        Assert.assertTrue(productsPage.isShoppingCartVisible(), "Shopping cart is not visible");
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] getNegativeLoginData() {
        return new Object[][] {
            { "locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out." },
            { "invalid_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service" },
            { "standard_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service" },
            { "", "secret_sauce", "Epic sadface: Username is required" },
            { "standard_user", "", "Epic sadface: Password is required" }
        };
    }

    @Test(priority = 2, dataProvider = "negativeLoginData")
    public void testInvalidLoginScenarios(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error container was not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message text mismatch");
    }
}