package com.kartik.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    //constructor to initialize the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Page Object Model (POM) methods for interacting with the login page will go here
    //Locators for username, password, and login button can be defined here
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginFailedErrorMsg = By.cssSelector("h3[data-test='error']");

    // Method to perform login action
    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        element.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }

    // Method to perform the complete login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    //Login failed error msg
    public String getErrorMsg() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(loginFailedErrorMsg));
        return element.getText();
    }
}
