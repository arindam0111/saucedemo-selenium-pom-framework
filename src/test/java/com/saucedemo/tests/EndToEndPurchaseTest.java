package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndPurchaseTest extends BaseTest {

    @Test
    public void testCompleteOrderFlow() {
        // 1. Log in
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        // 2. Select products and verify cart badge counter
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", "Cart count badge does not match 1");

        // 3. Navigate to Cart and verify item
        CartPage cartPage = productsPage.clickCart();
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack", "Product name in cart mismatch");

        // 4. Proceed to Checkout and input details
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillInformation("John", "Doe", "12345");

        // 5. Complete order and verify confirmation header
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getSuccessOrderMessage(), "Thank you for your order!", "Order confirmation header failed");
    }
}