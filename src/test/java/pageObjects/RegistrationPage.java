package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class RegistrationPage extends BasePage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telePhone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement passwordConfirm;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnConfirm;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirm;

    public void setFirstName(String fname) {
        firstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        lastName.sendKeys(lname);
    }

    public void setEmail(String Email) {
        email.sendKeys(Email);
    }

    public void setTelePhone(String tphone) {
        telePhone.sendKeys(tphone);
    }

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }

    public void setPasswordConfirm(String confirm) {
        passwordConfirm.sendKeys(confirm);
    }

    public void chkPolicy() {
        chkPolicy.click();
    }

    public void btnConfirm() {
        btnConfirm.click();
    }

    public String getConfirmation() {
        try {
            return (msgConfirm.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

}
