package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.models.CheckoutData;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EndToEndPurchaseTest extends BaseTest {

    @DataProvider(name = "checkoutDataProvider")
    public Object[][] getCheckoutData() {
        String filePath = "src/test/resources/testdata/checkoutData.json";
        return JsonReader.getJsonData(filePath, CheckoutData.class);
    }

    @Test(dataProvider = "checkoutDataProvider", groups = "regression")
    public void testCompleteOrderFlow(CheckoutData checkoutData) {
        // 1. Log in with standard user credentials
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        // 2. Select product, add to cart, and open cart
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.clickCart();

        // 3. Navigate to checkout from cart
        cartPage.clickCheckout();

        // 4. Fill shipping information and proceed
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.fillInformation(
                checkoutData.getFirstName(),
                checkoutData.getLastName(),
                checkoutData.getPostalCode()
        );

        // 5. Complete order and verify confirmation message
        checkoutPage.clickFinish();
        Assert.assertEquals(
                checkoutPage.getSuccessOrderMessage(),
                checkoutData.getExpectedConfirmationMessage(),
                "Confirmation message mismatch upon order completion"
        );
    }
}