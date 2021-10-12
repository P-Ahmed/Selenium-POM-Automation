package TestRunner;

import org.junit.Assert;
import org.testng.annotations.Test;
import Pages.Registration;
import Setup.Setup;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
    Registration objRegistration;
    @Test(enabled = true)
    public void doRegistration() throws InterruptedException, IOException {
        driver.get("http://automationpractice.com/index.php");
        objRegistration = new Registration(driver);
        String user = objRegistration.doRegistration();
        Assert.assertTrue(user.contains("Test User"));
    }
}
