package com.kartik.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    //constructor to initialize the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Object Model (POM) methods for interacting with the login page will go here
    //Locators for username, password, and login button can be defined here
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginFailedErrorMsg = By.cssSelector("h3[data-test='error']");

    // Method to perform login action
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Method to perform the complete login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    //Login failed error msg
    public String getErrorMsg() {
        return driver.findElement(loginFailedErrorMsg).getText();
    }
}
