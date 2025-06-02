package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class T003DDTLogin extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
    public void loginDDT(String email, String password, String expRes) {

        logger.info("***************** Starting T002LoginTest ***********************");

        try {
            // Home Page
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("************ click My Account **************");

            hp.clickLogin();
            logger.info("************ click on Login **************");

            // Login Page
            LoginPage lp = new LoginPage(driver);

            logger.info("************ customer details **************");
            lp.setEmail(email);
            lp.setPassword(password);

            lp.logIn();

            // My Account
            MyAccountPage mp = new MyAccountPage(driver);

            logger.info("************ account validation **************");
            boolean existingacc = mp.accountExist();

            // validation conditions
            // valid data -- login -- test pass -- logout // valid data -- login -- test fail
            // Invalid data -- login -- test pass -- logout // Invalid data -- login fail -- test pass

            // valid condition
            if (expRes.equalsIgnoreCase("Valid")) {
                if (existingacc == true) {
                    mp.clickLogOut();
                    Assert.assertTrue(true, "condition successful");
                } else {
                    Assert.assertTrue(false, "condition unsuccessful");
                }
            }

            // Invalid condition
            if (expRes.equalsIgnoreCase("Invalid")) {
                if (existingacc == true) {
                    mp.clickLogOut();
                    Assert.assertTrue(false, "condition unsuccessful");
                } else {
                    Assert.assertTrue(true, "condition successful");
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***************** Finish T002LoginTest ***********************");
    }
}
