package com.kartik.automation.utils;

import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "reports/screenshots/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");

    static {
        // Create screenshots directory if it doesn't exist
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("Screenshots directory created: " + SCREENSHOT_DIR);
            }
        }
    }

    /**
     * Captures screenshot and saves it to the reports/screenshots directory
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return Relative path to the screenshot file for HTML report, or absolute path if needed
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            if (driver == null) {
                System.err.println("WebDriver is null. Cannot capture screenshot.");
                return null;
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(formatter);
            String filename = testName + "_" + timestamp + ".png";
            String path = SCREENSHOT_DIR + filename;

            File destination = new File(path);
            FileUtils.copyFile(src, destination);
            
            String absolutePath = destination.getAbsolutePath();
            System.out.println("Screenshot captured: " + absolutePath);
            
            // Return path relative to reports directory for HTML embedding
            return "./screenshots/" + filename;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error during screenshot capture: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}