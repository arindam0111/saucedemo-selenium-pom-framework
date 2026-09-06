package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        if (browser == null || browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("driver.path"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            tlDriver.set(new ChromeDriver(options));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}