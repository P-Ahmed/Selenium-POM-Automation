package Pages;

import Utils.Utils;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.io.IOException;

public class Registration {
    WebDriver driver;
    @FindBy(className = "login")
    WebElement linkSignIn;
    @FindBy(id = "email_create")
    WebElement textEmail;
    @FindBy(id = "SubmitCreate")
    WebElement buttonCreateAnAccount;
    @FindBy(id = "passwd")
    WebElement textPassword;
    @FindBy(id = "customer_firstname")
    WebElement textFirstName;
    @FindBy(id = "customer_lastname")
    WebElement textLastName;
    @FindBy(id = "address1")
    WebElement textAddress;
    @FindBy(id = "city")
    WebElement textCity;
    @FindBy(id = "id_state")
    WebElement optionState;
    @FindBy(id = "postcode")
    WebElement numberPostcode;
    @FindBy(id = "phone_mobile")
    WebElement numberPhone;
    @FindBy(id = "submitAccount")
    WebElement submitRegister;
    @FindBy(xpath = "//span[contains(text(),'Test User')]")
    WebElement lblUserName;

    public Registration(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    Utils utils;
    public String doRegistration() throws InterruptedException, IOException {
        linkSignIn.click();
        utils=new Utils(driver);

        String email= utils.generateRandomEmail(100000,999999);
        String password = "P@ssword123";
        textEmail.sendKeys(email);
        buttonCreateAnAccount.click();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password",password);
        FileWriter file = new FileWriter("./src/test/resources/user.json");
        file.write(jsonObject.toJSONString());
        file.flush();

        textFirstName.sendKeys("Test");
        textLastName.sendKeys("User");
        textPassword.sendKeys(password);
        textAddress.sendKeys("Toriko Street");
        textCity.sendKeys("Tikala");
        Select state = new Select(optionState);
        state.selectByValue("2");
        numberPostcode.sendKeys("20000");
        numberPhone.sendKeys("023658975");
        submitRegister.click();Thread.sleep(4000);
        return lblUserName.getText();
    }
}
