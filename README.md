# SauceDemo Test Automation Framework

An enterprise-grade UI test automation framework built using **Selenium WebDriver**, **Java**, and **TestNG**, implementing the **Page Object Model (POM)** design pattern with thread-safe driver management, explicit synchronization, and data-driven testing.

---

## 🛠️ Tech Stack & Dependencies

* **Language**: Java 8
* **Automation Engine**: Selenium WebDriver 4.3.0
* **Test Runner**: TestNG 6.9.10
* **Design Pattern**: Page Object Model (POM)
* **Build Tool**: Apache Maven
* **Driver Management**: ThreadLocal `DriverFactory` (Parallel-ready)

---

## 🏛️ Framework Architecture

* **Thread-Safe Sessions**: Uses `ThreadLocal<WebDriver>` within `DriverFactory` to prevent session crossover during concurrent executions.
* **Smart Synchronization**: Encapsulates dynamic elements within `BasePage` using `WebDriverWait` and `ExpectedConditions` rather than thread sleeps.
* **Data-Driven Capabilities**: Leverages TestNG `@DataProvider` matrices for multi-scenario boundary and negative testing.
* **Failure Hook & Reporting**: Implements TestNG `ITestListener` to capture timestamped screenshots automatically upon test failure.
* **Externalized Configurations**: Centralized `config.properties` decoupling environment configurations (URLs, timeouts, browser types) from code.

---

## 📂 Project Structure

```text
Selenium/
├── src/
│   ├── main/java/
│   │   └── com/saucedemo/
│   │       ├── pages/          # Page Objects (BasePage, LoginPage, ProductsPage, CartPage, CheckoutPage)
│   │       └── utils/          # ConfigReader, DriverFactory, ScreenshotUtils
│   └── test/java/
│       └── com/saucedemo/
│           ├── base/           # BaseTest, TestListener
│           └── tests/          # Functional, Negative, and E2E Tests
│   └── test/resources/
│       └── config.properties   # Environment configurations
├── testng.xml                  # Suite runner & listener hooks
├── pom.xml                     # Maven dependencies & build lifecycle
└── README.md