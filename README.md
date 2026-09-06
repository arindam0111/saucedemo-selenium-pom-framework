# SauceDemo Selenium Test Automation Framework

A scalable and maintainable UI test automation framework for the [SauceDemo](https://www.saucedemo.com/) e-commerce application, built using **Selenium WebDriver, Java, TestNG, and Maven**.

The framework follows the **Page Object Model (POM)** design pattern and demonstrates practical automation framework concepts including reusable page components, configurable explicit waits, centralized configuration, thread-safe WebDriver management, data-driven testing, TestNG listeners, and automatic screenshots on test failure.

---

## 🚀 Key Features

* **Page Object Model (POM)** for maintainable and reusable page objects
* **Selenium WebDriver** for browser automation
* **TestNG** for test execution, assertions, and test organization
* **Maven** for dependency and build management
* **Configurable explicit waits** through `config.properties`
* **Centralized configuration management** using `ConfigReader`
* **ThreadLocal WebDriver** management for thread-safe driver handling
* **Data-driven testing** using TestNG `@DataProvider`
* **Reusable Selenium actions** through `BasePage`
* **Automatic screenshots on test failure**
* **TestNG listener** for test execution and failure handling
* Separate **smoke, negative, and end-to-end test scenarios**
* **TestNG XML suite** for organized regression execution
* Clean separation between test classes, page objects, framework utilities, and configuration

---

## 🛠️ Tech Stack

| Technology         | Version / Usage               |
| ------------------ | ----------------------------- |
| Java               | 8                             |
| Selenium WebDriver | 4.3.0                         |
| TestNG             | 6.9.10                        |
| Maven              | Build & dependency management |
| Chrome             | Primary browser               |
| Git                | Version control               |
| GitHub             | Source code repository        |

---

## 📁 Project Structure

```text
saucedemo-selenium-pom-framework/
│
├── drivers/
│   └── chromedriver.exe
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── saucedemo/
│   │               ├── pages/
│   │               │   ├── BasePage.java
│   │               │   ├── LoginPage.java
│   │               │   ├── ProductsPage.java
│   │               │   ├── CartPage.java
│   │               │   └── CheckoutPage.java
│   │               │
│   │               └── utils/
│   │                   ├── ConfigReader.java
│   │                   ├── DriverFactory.java
│   │                   └── ScreenshotUtils.java
│   │
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── saucedemo/
│   │   │           ├── base/
│   │   │           │   ├── BaseTest.java
│   │   │           │   └── TestListener.java
│   │   │           │
│   │   │           └── tests/
│   │   │               ├── LoginSmokeTest.java
│   │   │               ├── LoginTest.java
│   │   │               └── EndToEndPurchaseTest.java
│   │   │
│   │   └── resources/
│   │       └── config.properties
│   │
│   └── ...
│
├── testng.xml
├── pom.xml
├── .gitignore
└── README.md
```

> **Note:** The `drivers/` directory is intended for local driver files and should remain excluded from version control when configured in `.gitignore`.

---

# 🏗️ Framework Architecture

The framework follows a layered structure to keep test scenarios separate from page interactions and framework utilities.

```text
                    TestNG Test Classes
                           │
                           ▼
                       BaseTest
                           │
                           ▼
                     DriverFactory
                           │
                           ▼
                    Selenium WebDriver
                           │
                           ▼
                      Page Objects
                           │
                           ▼
                       BasePage
                           │
                           ▼
                  Reusable Web Actions
```

## Main Components

### `BaseTest`

Provides common test setup and teardown functionality.

Responsibilities include:

* Reading browser and application URL from configuration
* Initializing the WebDriver
* Opening the application
* Providing access to the current WebDriver instance
* Closing the browser after each test

---

### `DriverFactory`

Responsible for WebDriver lifecycle management.

The framework uses:

```java
ThreadLocal<WebDriver>
```

to maintain a separate WebDriver instance per execution thread.

This provides a foundation for safe parallel execution when parallel execution is introduced into the TestNG configuration.

---

### `BasePage`

Provides reusable Selenium operations used by page objects.

Examples include:

* Waiting for element visibility
* Waiting for element clickability
* Clicking elements
* Entering text
* Retrieving element text
* Checking element visibility

Explicit waits are configured through:

```text
src/test/resources/config.properties
```

Example:

```properties
explicit.wait=10
```

The timeout is read through `ConfigReader`, allowing the value to be changed without modifying page-object code.

---

### `ConfigReader`

Centralizes application and framework configuration.

Example:

```properties
browser=chrome
url=https://www.saucedemo.com/
explicit.wait=10
```

The framework provides typed configuration access for integer-based properties such as the explicit wait timeout.

---

### `ScreenshotUtils`

Provides screenshot capture functionality used for failure diagnostics.

Screenshots help with:

* Failure investigation
* Debugging
* Test execution analysis

---

### `TestListener`

A TestNG listener used to monitor test execution and trigger screenshot capture when tests fail.

---

# 🧪 Test Coverage

The framework currently contains the following test categories.

## Login Smoke Testing

`LoginSmokeTest.java`

Covers the basic login functionality to quickly verify that the application is available and the primary login flow is working.

## Login Testing

`LoginTest.java`

Contains login-related scenarios, including negative test cases and data-driven test execution where applicable.

## End-to-End Purchase Testing

`EndToEndPurchaseTest.java`

Validates the complete purchase flow:

```text
Login
  ↓
Products
  ↓
Add Product
  ↓
Cart
  ↓
Checkout
  ↓
Complete Order
```

---

# ⚙️ Configuration

Framework configuration is maintained in:

```text
src/test/resources/config.properties
```

Example:

```properties
browser=chrome
url=https://www.saucedemo.com/
explicit.wait=10
```

## Configurable Explicit Wait

The explicit wait timeout is externalized from the Java implementation.

For example:

```properties
explicit.wait=10
```

can be changed to:

```properties
explicit.wait=20
```

without modifying `BasePage.java`.

The framework reads the configured value at runtime through `ConfigReader`.

---

# ▶️ Running the Tests

## Option 1 — Run an Individual Test in Eclipse

To run a specific test:

1. Open the required test class.
2. Right-click the test method or test class.
3. Select:

```text
Run As → TestNG Test
```

This is useful during development when working on a specific test.

---

## Option 2 — Run the Complete TestNG Suite

The framework contains:

```text
testng.xml
```

The suite includes:

```xml
<class name="com.saucedemo.tests.LoginSmokeTest" />
<class name="com.saucedemo.tests.LoginTest" />
<class name="com.saucedemo.tests.EndToEndPurchaseTest" />
```

To execute the complete suite in Eclipse:

```text
Right-click testng.xml
        ↓
Run As
        ↓
TestNG Suite
```

The suite has been locally verified with:

```text
Total tests run: 8
Failures: 0
Skips: 0
```

---

## Option 3 — Run Using Maven

From the project root:

```bash
mvn clean test
```

> Maven execution depends on the project's `pom.xml` configuration. The TestNG suite can be executed directly from Eclipse using `testng.xml`.

---

# 🔍 Design Principles

The framework is designed around the following principles:

### 1. Separation of Concerns

Test classes focus on test scenarios while page classes handle UI interactions.

### 2. Reusability

Common Selenium operations are centralized in `BasePage`.

### 3. Maintainability

Locators and page-specific interactions are maintained inside dedicated page classes.

### 4. Centralized Configuration

Framework configuration is maintained outside Java source code.

### 5. Thread-Safe Driver Management

`ThreadLocal<WebDriver>` provides a foundation for managing independent browser instances across execution threads.

### 6. Failure Diagnostics

Automatic screenshots are captured when tests fail.

---

# 📌 Current Framework Capabilities

| Capability                       | Status            |
| -------------------------------- | ----------------- |
| Selenium WebDriver               | ✅ Implemented     |
| Java                             | ✅ Implemented     |
| TestNG                           | ✅ Implemented     |
| Maven                            | ✅ Implemented     |
| Page Object Model                | ✅ Implemented     |
| Base Page                        | ✅ Implemented     |
| Explicit Waits                   | ✅ Implemented     |
| Configurable Explicit Wait       | ✅ Implemented     |
| Centralized Configuration        | ✅ Implemented     |
| ThreadLocal WebDriver            | ✅ Implemented     |
| Data-Driven Testing              | ✅ Implemented     |
| Failure Screenshots              | ✅ Implemented     |
| TestNG Listener                  | ✅ Implemented     |
| TestNG Suite                     | ✅ Implemented     |
| Smoke Testing                    | ✅ Implemented     |
| Negative Testing                 | ✅ Implemented     |
| End-to-End Testing               | ✅ Implemented     |
| Headless Execution               | ❌ Not implemented |
| CI/CD Pipeline                   | ❌ Not implemented |
| Advanced Reporting               | ❌ Not implemented |
| Parallel Execution Configuration | ❌ Not implemented |

---

# 🗺️ Future Enhancements

The framework can be further enhanced with:

* Configurable **headless browser execution**
* Browser selection for multiple browsers
* Improved generic product and cart methods
* Parallel test execution
* GitHub Actions CI/CD integration
* Advanced reporting using Extent Reports or Allure
* Improved test data management
* Additional API test automation
* Cross-browser execution
* Environment-specific configuration

These features will be added incrementally as the framework evolves.

---

# 🧹 Git & Version Control

The project uses Git for version control.

Typical workflow:

```bash
git status
git add .
git commit -m "Make explicit wait timeout configurable"
git push origin main
```

---

# 👨‍💻 Author

**Arindam Chowdhury**

QA Automation Engineer / SDET

### Areas of Focus

* UI Test Automation
* Selenium WebDriver
* Playwright
* Java
* TestNG
* API Testing
* Performance Testing
* CI/CD
* Software Quality Engineering

---

# ⭐ Project Goals

This project is continuously evolving to demonstrate practical test automation framework design, maintainability, reusability, and modern QA engineering practices.

The framework is intentionally improved incrementally, with each enhancement tracked through Git commits.

The goal is to demonstrate not only the ability to write automated tests, but also the ability to **design, refactor, maintain, and continuously improve an automation framework**.
