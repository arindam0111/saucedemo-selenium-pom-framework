# SauceDemo Selenium Test Automation Framework

A maintainable **UI test automation framework** for the [SauceDemo](https://www.saucedemo.com/) e-commerce application, built using **Selenium WebDriver, Java, TestNG, and Maven**.

The framework follows the **Page Object Model (POM)** design pattern and demonstrates practical automation framework concepts including reusable page objects, explicit waits, centralized configuration, thread-safe WebDriver management, data-driven testing, failure screenshots, TestNG listeners, and headless execution.

---

## 🚀 Key Features

* Page Object Model (POM)
* Selenium WebDriver
* Java 8 compatible implementation
* TestNG test execution
* Maven build and dependency management
* Configurable explicit waits
* Centralized framework configuration
* ThreadLocal WebDriver management
* Data-driven testing using TestNG `@DataProvider`
* Reusable Selenium actions through `BasePage`
* Automatic screenshots on test failure
* TestNG listener for failure handling
* Smoke testing
* Negative testing
* End-to-end purchase flow
* Headless browser execution
* TestNG XML suites
* Maven command-line execution
* Clean separation between tests, page objects, utilities, and configuration

---

## 🛠️ Tech Stack

| Technology         | Usage                         |
| ------------------ | ----------------------------- |
| Java               | 8                             |
| Selenium WebDriver | 4.3.0                         |
| TestNG             | 6.9.10                        |
| Maven              | Build & dependency management |
| Chrome             | Primary browser               |
| Git                | Version control               |
| GitHub             | Source code repository        |

> **Compatibility note:** The project uses Java 8 and Selenium 4.3.0 to maintain compatibility with the development environment used for this project.

---

## 📁 Project Structure

```text
saucedemo-selenium-pom-framework/
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
├── reports/
├── testng.xml
├── testng-smoke.xml
├── testng-regression.xml
├── pom.xml
├── .gitignore
└── README.md
```

> Browser driver binaries are intended for local execution and should remain excluded from version control where appropriate.

---

# 🏗️ Framework Architecture

The framework follows a layered structure that separates test scenarios, browser management, page interactions, and reusable utilities.

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

### BaseTest

Provides common test setup and teardown functionality.

Responsibilities include:

* Reading framework configuration
* Initializing WebDriver
* Opening the application
* Providing access to the current driver
* Closing the browser after test execution

### DriverFactory

Manages WebDriver lifecycle.

The framework uses:

```java
ThreadLocal<WebDriver>
```

to maintain an independent WebDriver instance for each execution thread.

This provides a foundation for future parallel execution.

### BasePage

Contains reusable Selenium operations used by page objects.

Examples include:

* Waiting for element visibility
* Waiting for element clickability
* Clicking elements
* Entering text
* Retrieving element text
* Checking element visibility

### ConfigReader

Centralizes framework configuration such as:

```properties
browser=chrome
url=https://www.saucedemo.com/
explicit.wait=10
```

Configuration values can be changed without modifying the page-object implementation.

### ScreenshotUtils

Provides screenshot capture functionality for failure diagnostics.

### TestListener

A TestNG listener that monitors test execution and triggers screenshot capture when a test fails.

---

# 🧪 Test Coverage

## Login Smoke Test

`LoginSmokeTest.java`

Validates the basic login flow and verifies that the application is available and the primary login functionality is working.

## Login Tests

`LoginTest.java`

Covers login-related scenarios including:

* Valid login
* Invalid login
* Negative test scenarios
* Data-driven execution where applicable

## End-to-End Purchase Test

`EndToEndPurchaseTest.java`

Validates the complete purchase workflow:

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

### Configurable Explicit Wait

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

---

# ▶️ Running the Tests

## 1. Run from Eclipse

Open the required test class and select:

```text
Run As → TestNG Test
```

To execute the complete suite:

```text
Right-click testng.xml
        ↓
Run As
        ↓
TestNG Suite
```

---

## 2. Run with Maven

From the project root:

```bash
mvn clean test
```

---

## 3. Run Smoke Tests

```bash
mvn test -Dgroups=smoke
```

---

## 4. Run Regression Tests

```bash
mvn test -Dgroups=regression
```

---

## 5. Run with Chrome

```bash
mvn test -Dbrowser=chrome
```

---

## 6. Run in Headless Mode

```bash
mvn test -Dbrowser=chrome -Dheadless=true
```

Headless execution allows the test suite to run without opening the browser UI.

---

# 📊 Test Execution

The complete TestNG suite has been locally verified with:

```text
Total tests run: 8
Failures: 0
Skips: 0
```

The result above represents a local execution of the current test suite and may change as additional tests are added.

---

# 🧩 Design Principles

### 1. Separation of Concerns

Test classes focus on test scenarios while page classes handle UI interactions.

### 2. Reusability

Common Selenium operations are centralized in `BasePage`.

### 3. Maintainability

Page-specific locators and interactions are maintained inside dedicated page classes.

### 4. Centralized Configuration

Application and framework settings are maintained outside the Java implementation.

### 5. Thread-Safe Driver Management

`ThreadLocal<WebDriver>` provides isolated driver instances for execution threads.

### 6. Failure Diagnostics

Screenshots are automatically captured when tests fail.

---

# ✅ Current Framework Capabilities

| Capability                       | Status    |
| -------------------------------- | --------- |
| Selenium WebDriver               | ✅         |
| Java                             | ✅         |
| TestNG                           | ✅         |
| Maven                            | ✅         |
| Page Object Model                | ✅         |
| Base Page                        | ✅         |
| Explicit Waits                   | ✅         |
| Centralized Configuration        | ✅         |
| ThreadLocal WebDriver            | ✅         |
| Data-Driven Testing              | ✅         |
| Failure Screenshots              | ✅         |
| TestNG Listener                  | ✅         |
| TestNG Suites                    | ✅         |
| Smoke Testing                    | ✅         |
| Negative Testing                 | ✅         |
| End-to-End Testing               | ✅         |
| Headless Execution               | ✅         |
| Maven CLI Execution              | ✅         |
| GitHub Actions CI/CD             | ⏳ Planned |
| Advanced Reporting               | ⏳ Planned |
| Parallel Execution Configuration | ⏳ Planned |
| Cross-Browser Execution          | ⏳ Planned |

---

# 🗺️ Planned Enhancements

The framework will be enhanced incrementally with:

* GitHub Actions CI/CD integration
* Improved cross-browser configuration
* Parallel test execution
* Advanced reporting
* Additional end-to-end scenarios
* Improved test-data management
* Environment-specific configuration
* API test integration where appropriate

---

# 🌱 Learning & Development Focus

This project is continuously improved to demonstrate practical skills in:

* UI test automation
* Selenium framework design
* Page Object Model
* TestNG
* Maven
* Configuration management
* Failure diagnostics
* Git and GitHub
* CI/CD
* Maintainable test automation

The project is intentionally developed incrementally, with framework improvements tracked through Git commits.

---

# 👨‍💻 Author

**Arindam Chowdhury**

QA Automation Engineer / SDET

### Areas of Focus

* UI Test Automation
* Selenium WebDriver
* Playwright
* Java
* JavaScript / TypeScript
* TestNG
* API Testing
* k6 Performance Testing
* CI/CD
* Software Quality Engineering

---

## ⭐ Project Goal

The goal of this project is to demonstrate the ability to **design, build, maintain, refactor, and continuously improve a UI automation framework**, rather than simply writing individual Selenium tests.

---
