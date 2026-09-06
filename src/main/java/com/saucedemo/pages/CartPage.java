package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By checkoutButton = By.id("checkout");
    private By cartItemName = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstItemName() {
        return getText(cartItemName);
    }

    public CheckoutPage clickCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}