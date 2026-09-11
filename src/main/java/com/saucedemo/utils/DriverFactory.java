package com.saucedemo.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

   public static void initializeDriver(String browser) {

        if (browser == null || browser.equalsIgnoreCase("chrome")) {

            String driverPath = ConfigReader.getProperty("driver.path");

            File driverFile = new File(driverPath);

            System.setProperty("webdriver.chrome.driver",
                    driverFile.getAbsolutePath());

            ChromeOptions options = new ChromeOptions();

            if (ConfigReader.getBooleanProperty("headless")) {
                options.addArguments("--headless");
            }

            driver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (ConfigReader.getBooleanProperty("headless")) {
                options.addArguments("--headless");
            }

            driver.set(new FirefoxDriver(options));

        } else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            if (ConfigReader.getBooleanProperty("headless")) {
                options.addArguments("--headless");
            }

            driver.set(new EdgeDriver(options));

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