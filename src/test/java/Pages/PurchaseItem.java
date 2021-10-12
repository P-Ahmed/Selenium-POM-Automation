package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PurchaseItem {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "search_query_top")
    WebElement txtSearch;
    @FindBy(name = "submit_search")
    WebElement btnSearch;
    @FindBy(xpath = "//span[@class='heading-counter']")
    WebElement lblProductFound;
    @FindBy(className = "product-container")

    List<WebElement> imgItem;
    @FindBy(name = "Submit")
    WebElement btnSubmit;
    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    WebElement btnSubmit2;
    @FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
    WebElement btnSubmit3;
    @FindBy(xpath = "//button[@type='submit']//span[contains(text(),'Proceed to checkout')]")
    WebElement btnSubmit4;
    @FindBy(id = "cgv")
    WebElement chkBox1;
    @FindBy(xpath = "//button[@type='submit']//span[contains(text(),'Proceed to checkout')]")
    WebElement btnSubmit5;
    @FindBy(className = "bankwire")
    WebElement btnWireCard;
    @FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
    WebElement btnConfirm;
    @FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
    WebElement lblConfirmation;
    @FindBy(xpath = "//span[contains(text(),'Order history and details')]")
    WebElement btnOrderHistory;

    public PurchaseItem(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkHasCart() {
        wait = new WebDriverWait(driver, 30);
        boolean status = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > div:nth-child(3) > div > div > div:nth-child(3) > div"))).isDisplayed();
        return status;
    }

    public String orderHistory() {
        btnOrderHistory.click();
        wait = new WebDriverWait(driver, 30);
        String headerText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Order history')]"))).getText();
        return headerText;
    }

    public String checkSearch() throws InterruptedException {
        txtSearch.sendKeys("Dress");
        Thread.sleep(2000);
        btnSearch.click();
        Thread.sleep(1000);
        return lblProductFound.getText();
    }

    public String purchaseItem() throws InterruptedException {
        txtSearch.sendKeys("Dress");
        Thread.sleep(2000);
        btnSearch.click();
        Thread.sleep(1000);

        imgItem.get(1).click();
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(2000);
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Proceed to checkout')]")));
        btnSubmit2.click();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //get the height of the webpage and scroll to the end
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        btnSubmit3.click();
        Thread.sleep(3000);
        btnSubmit4.click();
        Thread.sleep(1000);
        chkBox1.click();
        btnSubmit5.click();
        Thread.sleep(1000);
        btnWireCard.click();
        Thread.sleep(1000);
        btnConfirm.click();

        Thread.sleep(2000);
        String successMessage = lblConfirmation.getText();
        return successMessage;
    }
}
