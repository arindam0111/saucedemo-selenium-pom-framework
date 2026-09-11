package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.models.LoginData;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(getDriver());
        Assert.assertEquals(productsPage.getPageTitle(), "Products", "Header title mismatch on products page");
        Assert.assertTrue(productsPage.isShoppingCartVisible(), "Shopping cart is not visible");
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] getNegativeLoginData() {
        String filePath = "src/test/resources/testdata/loginData.json";
        return JsonReader.getJsonData(filePath, LoginData.class);
    }

    @Test(priority = 2, dataProvider = "negativeLoginData", groups = "regression")
    public void testInvalidLoginScenarios(LoginData data) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(data.getUsername(), data.getPassword());

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error container was not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), data.getExpectedErrorMessage(), "Error message text mismatch");
    }
}