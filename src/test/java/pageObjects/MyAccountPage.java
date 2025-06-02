package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class MyAccountPage extends BasePage {

    WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement masHeading;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement logOut;

    public boolean accountExist() {
        try {
            return (masHeading.isDisplayed());
        }
        catch (Exception e) {
            return false;
        }
    }

    public void clickLogOut() {
        logOut.click();
    }

}
