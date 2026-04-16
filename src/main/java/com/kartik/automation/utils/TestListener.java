package com.kartik.automation.utils;

import com.aventstack.extentreports.Status;
import com.kartik.automation.base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener class implements ITestListener to listen to test events and log them in ExtentReports.
 * Gets driver directly from test instance to ensure it's available during screenshot capture.
 */
public class TestListener extends Base implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        if (extent != null) {
            test = extent.createTest(result.getMethod().getMethodName());
            test.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            String testName = result.getMethod().getMethodName();
            test.log(Status.PASS, "Test Passed: " + testName);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
            test.log(Status.FAIL, result.getThrowable());
            
            // Capture screenshot on failure
            captureScreenshotOnFailure(result);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test != null) {
            test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        }
    }

    /**
     * Helper method to capture screenshot on test failure
     * Gets driver directly from test instance (not Base class)
     */
    private void captureScreenshotOnFailure(ITestResult result) {
        try {
            // Get the test instance (the actual test class, e.g., ApplicationLogin)
            Object testInstance = result.getInstance();
            
            if (testInstance instanceof Base) {
                // Cast to Base to access the protected driver
                Base testBase = (Base) testInstance;
                WebDriver testDriver = testBase.driver;
                
                System.out.println("Attempting screenshot capture. Driver status: " + 
                    (testDriver != null ? "AVAILABLE" : "NULL"));
                
                if (testDriver != null) {
                    try {
                        String screenshotPath = ScreenshotUtil.captureScreenshot(
                            testDriver, 
                            result.getMethod().getMethodName()
                        );
                        
                        if (screenshotPath != null && !screenshotPath.isEmpty()) {
                            test.addScreenCaptureFromPath(screenshotPath);
                            test.log(Status.INFO, "Screenshot captured and attached: " + screenshotPath);
                            System.out.println("✓ Screenshot successfully attached: " + screenshotPath);
                        } else {
                            test.log(Status.WARNING, "Screenshot path was null or empty");
                            System.out.println("⚠ Screenshot path is empty");
                        }
                    } catch (Exception e) {
                        test.log(Status.WARNING, "Screenshot capture exception: " + e.getMessage());
                        System.err.println("✗ Screenshot capture failed: " + e.getMessage());
                    }
                } else {
                    test.log(Status.WARNING, "Driver is null - cannot capture screenshot");
                    System.err.println("✗ Driver is NULL at time of failure");
                }
            } else {
                test.log(Status.WARNING, "Test instance is not a Base subclass");
                System.err.println("✗ Test instance doesn't extend Base");
            }
        } catch (Exception e) {
            System.err.println("✗ Error in captureScreenshotOnFailure: " + e.getMessage());
            e.printStackTrace();
            try {
                test.log(Status.WARNING, "Error capturing screenshot: " + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Failed to log error: " + ex.getMessage());
            }
        }
    }
}