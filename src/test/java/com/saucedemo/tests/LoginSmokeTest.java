package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseTest {

    @Test(groups = "smoke")
    public void verifyAppTitle() {
        String actualTitle = getDriver().getTitle();
        System.out.println("SauceDemo Loaded Title: " + actualTitle);
        Assert.assertEquals(actualTitle, "Swag Labs", "Page title does not match!");
    }
}