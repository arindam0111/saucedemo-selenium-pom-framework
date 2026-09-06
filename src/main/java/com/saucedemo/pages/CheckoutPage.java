package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    // Step One: Customer Information
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    // Step Two: Overview & Finish
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String fName, String lName, String zip) {
        enterText(firstNameField, fName);
        enterText(lastNameField, lName);
        enterText(postalCodeField, zip);
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }

    public String getSuccessOrderMessage() {
        return getText(completeHeader);
    }
}