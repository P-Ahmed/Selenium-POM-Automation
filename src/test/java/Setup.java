import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Setup {
    public WebDriver driver;
    @BeforeTest
    public void setUp() throws IOException {
        FirefoxOptions ops = new FirefoxOptions();
        ops.addArguments("--headed");
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils util = new Utils(driver);
                util.takeScreenShot();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }

    @AfterTest
    public void AfterTest(){
        driver.close();
    }
}
