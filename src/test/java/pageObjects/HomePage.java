package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class HomePage extends BasePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='caret']")
    WebElement linkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement linkRegister;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement logIn;

    public void clickMyAccount() {
        linkMyAccount.click();
    }

    public void clickRegister() {
        linkRegister.click();
    }

    public void clickLogin() { logIn.click(); }

}
