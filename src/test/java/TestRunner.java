import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.testng.annotations.Test;
import pages.Login;
import pages.Registration;

import java.io.FileReader;
import java.io.IOException;

public class TestRunner extends Setup {
    Login objLogin;
    Registration objRegistration;
    String fileName = "./src/test/resources/users.json";
    @Test
    public void doLogin() throws IOException, ParseException {
        driver.get("http://automationpractice.com/index.php");
        objLogin = new Login(driver);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject json = (JSONObject) jsonArray.get(0);

        String email = (String) json.get("email");
        String password = (String) json.get("password");

        String user = objLogin.doLogin(email, password);
        Assert.assertTrue(user.contains("Test User"));
    }
    @Test
    public void doLoginWithWrongEmail() throws IOException, ParseException {
        driver.get("http://automationpractice.com/index.php");
        objLogin = new Login(driver);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject json = (JSONObject) jsonArray.get(1);

        String email = (String) json.get("email");
        String password = (String) json.get("password");

        String auth = objLogin.doLoginWithWrongEmail(email, password);
        Assert.assertTrue(auth.contains("Authentication failed."));
    }
    @Test
    public void doLoginWithWrongPassword() throws IOException, ParseException {
        driver.get("http://automationpractice.com/index.php");
        objLogin = new Login(driver);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject json = (JSONObject) jsonArray.get(2);

        String email = (String) json.get("email");
        String password = (String) json.get("password");

        String auth = objLogin.doLoginWithWrongPassword(email, password);
        Assert.assertTrue(auth.contains("Authentication failed."));
    }
    @Test
    public void doRegistration() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        objRegistration = new Registration(driver);
        String user = objRegistration.doRegistration();
        Assert.assertTrue(user.contains("Amino Tester"));
    }
}
