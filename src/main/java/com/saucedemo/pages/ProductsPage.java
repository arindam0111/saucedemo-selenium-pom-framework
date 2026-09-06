package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private By pageTitle = By.className("title");
    private By shoppingCartBadge = By.className("shopping_cart_link");
    private By addToCartBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartBikeLightBtn = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartItemCountBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isShoppingCartVisible() {
        return isDisplayed(shoppingCartBadge);
    }

    public void addBackpackToCart() {
        click(addToCartBackpackBtn);
    }

    public void addBikeLightToCart() {
        click(addToCartBikeLightBtn);
    }

    public String getCartBadgeCount() {
        return getText(cartItemCountBadge);
    }

    public CartPage clickCart() {
        click(shoppingCartBadge);
        return new CartPage(driver);
    }
}