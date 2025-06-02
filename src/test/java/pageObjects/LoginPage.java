package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class LoginPage extends BasePage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    public void setEmail(String Email){
        email.sendKeys(Email);
    }

    public void setPassword(String pass){
        password.sendKeys(pass);
    }

    public void logIn(){
        btnLogin.click();
    }

}
