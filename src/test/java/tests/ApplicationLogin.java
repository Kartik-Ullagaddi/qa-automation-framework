package tests;

import com.kartik.automation.base.Base;
import com.kartik.automation.pages.LoginPage;
import com.kartik.automation.utils.RetryAnalyzer;
import com.kartik.automation.utils.User;
import com.kartik.automation.utils.UserReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationLogin extends Base {
    // Test methods for login functionality will go here
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLoginPage() {
        //getting user details from JSON file using UserReader
        User loginUser = UserReader.getUser("standardUser");
        LoginPage loginPage = new LoginPage(driver);

        test.info("Attempting to login with username: " + loginUser.getUsername());
        loginPage.login(loginUser.getUsername(), loginUser.getPassword());
        Assert.assertTrue(driver.getPageSource().contains("Products"), "Login successful");
        test.pass("Login test passed for user: " + loginUser.getUsername());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLoginFailed() {
        //getting user details from JSON file using UserReader
        User loginUser = UserReader.getUser("lockedOutUser");
        LoginPage loginPage = new LoginPage(driver);

        test.info("Attempting to login with username: " + loginUser.getUsername());
        loginPage.login(loginUser.getUsername(), loginUser.getPassword());
        Assert.assertTrue(loginPage.getErrorMsg().contains("locked out"), "Login failed as expected");
        test.pass("Login failed test passed for user: " + loginUser.getUsername());
    }
}
