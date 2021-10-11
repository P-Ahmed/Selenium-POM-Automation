package TestRunner;

import Setup.Setup;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import Pages.Login;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    Login objLogin;
    Utils utils;

    //@Test(enabled =true, priority = 1, description = "Login with valid email and password", groups = "login_positive")
    @Test(enabled =true, priority = 1, description = "Login with valid email and password")
    public void doLogin() throws IOException, ParseException {
        driver.get("http://automationpractice.com/index.php");
        objLogin = new Login(driver);

        utils = new Utils(driver);
        utils.readJSONArray(0);

        String user = objLogin.doLogin(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(user.contains("Test User"));

        driver.findElement(By.xpath("//a[@class='logout']")).click();
    }
    //@Test(enabled = true, priority = 3, description = "Login with valid email and invalid password", groups = "login_negative")
    @Test(enabled = true, priority = 3, description = "Login with valid email and invalid password")
    public void doLoginWithInvalidPassword() throws IOException, ParseException {
        driver.get("http://automationpractice.com/index.php");
        objLogin = new Login(driver);

        utils = new Utils(driver);
        utils.readJSONArray(1);

        String auth = objLogin.doLoginWithWrongPassword(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(auth.contains("Authentication failed."));
    }
    //@Test(enabled = true, priority = 2, description = "Login with invalid email and valid password", groups = "login_negative")
    @Test(enabled = true, priority = 2, description = "Login with invalid email and valid password")
    public void doLoginWithWrongEmail() throws IOException, ParseException {
        driver.get("http://automationpractice.com/index.php");
        objLogin = new Login(driver);

        utils = new Utils(driver);
        utils.readJSONArray(2);

        String auth = objLogin.doLoginWithInvalidEmail(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(auth.contains("Invalid email address."));
    }
}
