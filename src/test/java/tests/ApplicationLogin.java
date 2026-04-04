package tests;

import com.kartik.automation.base.Base;
import com.kartik.automation.pages.LoginPage;
import com.kartik.automation.utils.User;
import com.kartik.automation.utils.UserReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationLogin extends Base {
    // Test methods for login functionality will go here
    @Test
    public void testLoginPageTitleTest() {

        //getting user details from JSON file using UserReader
        User CurrentUser = UserReader.getUser("standardUser");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(CurrentUser.getUsername(), CurrentUser.getPassword());
        Assert.assertTrue(driver.getPageSource().contains("Products"), "Login successful");
    }

    @Test
    public void testLoginFailed() {
        //getting user details from JSON file using UserReader
        User CurrentUser = UserReader.getUser("lockedOutUser");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(CurrentUser.getUsername(), CurrentUser.getPassword());
        Assert.assertTrue(loginPage.getErrorMsg().contains("locked out"), "Login failed as expected");
    }
}
