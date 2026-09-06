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
## 🌐 Browser Driver Setup

This framework currently uses **Google Chrome** with a project-relative ChromeDriver configuration.

### Prerequisites

Before running the tests, ensure the following are installed:

* **Java 8**
* **Apache Maven**
* **Google Chrome**
* **Eclipse IDE** or any Java IDE

### ChromeDriver Setup

1. Download the ChromeDriver version compatible with your installed Google Chrome version.
2. Place the `chromedriver.exe` file inside the following project directory:

```text
drivers/
└── chromedriver.exe

---

## ▶️ How to Run Tests

### Option 1: Run from Eclipse

1. Import the project as a Maven project.
2. Ensure the required dependencies are downloaded successfully.
3. Verify the ChromeDriver configuration in `config.properties`.
4. Right-click the required TestNG test class.
5. Select:

```text
Run As → TestNG Test

## 🏛️ Framework Architecture

* **Thread-Safe Sessions**: Uses `ThreadLocal<WebDriver>` within `DriverFactory` to prevent session crossover during concurrent executions.
* **Smart Synchronization**: Encapsulates dynamic elements within `BasePage` using `WebDriverWait` and `ExpectedConditions` rather than thread sleeps.
* **Data-Driven Capabilities**: Leverages TestNG `@DataProvider` matrices for multi-scenario boundary and negative testing.
* **Failure Hook & Reporting**: Implements TestNG `ITestListener` to capture timestamped screenshots automatically upon test failure.
* **Externalized Configurations**: Centralized `config.properties` decoupling environment configurations (URLs, timeouts, browser types) from code.

---

## 📂 Project Structure

## 📂 Project Structure

```text
Selenium/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/saucedemo/
│   │           ├── pages/
│   │           │   ├── BasePage.java
│   │           │   ├── LoginPage.java
│   │           │   ├── ProductsPage.java
│   │           │   ├── CartPage.java
│   │           │   └── CheckoutPage.java
│   │           └── utils/
│   │               ├── ConfigReader.java
│   │               ├── DriverFactory.java
│   │               └── ScreenshotUtils.java
│   │
│   └── test/
│       ├── java/
│       │   └── com/saucedemo/
│       │       ├── base/
│       │       │   ├── BaseTest.java
│       │       │   └── TestListener.java
│       │       └── tests/
│       │           ├── LoginTest.java
│       │           ├── LoginSmokeTest.java
│       │           └── EndToEndPurchaseTest.java
│       │
│       └── resources/
│           └── config.properties
│
├── drivers/
│   └── chromedriver.exe        # Local only; excluded from Git
├── testng.xml
├── pom.xml
├── .gitignore
└── README.md