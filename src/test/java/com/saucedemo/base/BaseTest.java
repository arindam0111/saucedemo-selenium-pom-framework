package com.saucedemo.base;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        DriverFactory.initDriver(browser);
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}