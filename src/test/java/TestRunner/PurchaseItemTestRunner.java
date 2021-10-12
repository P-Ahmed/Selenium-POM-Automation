package TestRunner;

import Pages.Login;
import Pages.PurchaseItem;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseItemTestRunner extends Setup {
    PurchaseItem objPurchase;
    Login objLogin;
    Utils utils;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        driver.get("http://automationpractice.com");
        utils = new Utils(driver);
        utils.readJSONFile();
        String email = utils.getEmail();
        String password = utils.getPassword();
        objLogin = new Login(driver);
        objLogin.doLoginForPurchaseItem(email, password);
    }

    @Test
    public void checkHasCart() {
        objPurchase = new PurchaseItem(driver);
        boolean status = objPurchase.checkHasCart();
        Assert.assertEquals(status, true);
    }

    @Test
    public void checkOrderHistory() {
        objPurchase = new PurchaseItem(driver);
        String headerText = objPurchase.orderHistory();
        Assert.assertEquals(headerText, "ORDER HISTORY");

    }

    @Test
    public void checkSearchTextBox() throws InterruptedException {
        objPurchase = new PurchaseItem(driver);
        String result = objPurchase.checkSearch();
        Assert.assertTrue(result.contains("results have been found"));
    }

    @Test
    public void doPurchase() throws Exception {
        driver.get("http://automationpractice.com");
        objPurchase = new PurchaseItem(driver);
        String successMessage = objPurchase.purchaseItem();
        Assert.assertEquals(successMessage, "Your order on My Store is complete.");
        utils.addDescription("User can purchase a product successfully");

    }
    @AfterTest
    public void AfterTest(){
        driver.quit();
    }
}

