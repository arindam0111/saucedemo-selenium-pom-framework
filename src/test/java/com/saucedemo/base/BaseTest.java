package com.saucedemo.base;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	@BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");

        DriverFactory.initializeDriver(browser);
     // 1. Maximize window so responsive CSS/hamburger menus don't hide elements
        DriverFactory.getDriver().manage().window().maximize();
     // 2. Open base URL
        DriverFactory.getDriver().get(
            ConfigReader.getProperty("url")
        );
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}