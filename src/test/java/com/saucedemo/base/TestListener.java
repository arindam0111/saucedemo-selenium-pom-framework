package com.saucedemo.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucedemo.utils.DriverFactory;
import com.saucedemo.utils.ExtentReportManager;
import com.saucedemo.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final ExtentReports extent = ExtentReportManager.getReporter();

    @Override
    public void onStart(ITestContext context) {
        // Report engine initialized via getReporter()
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        ExtentReportManager.setTest(test);
        ExtentReportManager.getTest().log(Status.INFO, "Test execution started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test PASSED: " + result.getMethod().getMethodName());
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = ExtentReportManager.getTest();

        test.log(Status.FAIL, "Test FAILED: " + testName);
        test.log(Status.FAIL, result.getThrowable());

        String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), testName);
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        }

        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.log(Status.SKIP, "Test SKIPPED: " + result.getMethod().getMethodName());
            if (result.getThrowable() != null) {
                test.log(Status.SKIP, result.getThrowable());
            }
            ExtentReportManager.removeTest();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}