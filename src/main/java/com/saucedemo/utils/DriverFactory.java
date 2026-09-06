package com.saucedemo.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void initializeDriver(String browser) {

        if (browser == null || browser.equalsIgnoreCase("chrome")) {

            String driverPath = ConfigReader.getProperty("driver.path");

            File driverFile = new File(driverPath);

            System.setProperty("webdriver.chrome.driver",
                    driverFile.getAbsolutePath());

            driver.set(new ChromeDriver());

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}