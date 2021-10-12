package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;
    @FindBy(className = "login")
    WebElement linkSignIn;
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "passwd")
    WebElement txtPassword;
    @FindBy(id = "SubmitLogin")
    WebElement btnSignIn;
    @FindBy(xpath = "//span[contains(text(),'Test User')]")
    WebElement lblUserName;
    @FindBy(xpath = "//li[contains(text(),'Authentication failed.')]")
    WebElement lblAuthentication;
    @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
    WebElement lblInvalidEmail;

    public Login(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String doLogin(String email, String password){
        linkSignIn.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSignIn.click();
        return lblUserName.getText();
    }
    public void doLoginForPurchaseItem(String email, String password){
        linkSignIn.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSignIn.click();
    }
    public String doLoginWithInvalidEmail(String email, String password){
        linkSignIn.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSignIn.click();
        return lblInvalidEmail.getText();
    }
    public String doLoginWithWrongPassword(String email, String password){
        linkSignIn.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSignIn.click();
        return lblAuthentication.getText();
    }
}
