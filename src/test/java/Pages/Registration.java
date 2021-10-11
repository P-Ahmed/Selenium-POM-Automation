package Pages;

import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
    @FindBy(xpath = "//span[contains(text(),'Amino Tester')]")
    WebElement lblUserName;

    public Registration(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    Utils utils;
    public String doRegistration() throws InterruptedException {
        linkSignIn.click();
        utils=new Utils(driver);
        String email= utils.generateRandomEmail(100000,999999);
        textEmail.sendKeys(email);
        buttonCreateAnAccount.click();
        textFirstName.sendKeys("Amino");
        textLastName.sendKeys("Tester");
        textPassword.sendKeys("P@ssword123");
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
