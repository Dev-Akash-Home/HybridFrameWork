package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class T002LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void login() {

        logger.info("***************** Starting T002LoginTest ***********************");

        try {
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("************ click My Account **************");

            hp.clickLogin();
            logger.info("************ click on Login **************");

            LoginPage lp = new LoginPage(driver);

            logger.info("************ customer details **************");
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));

            lp.logIn();

            MyAccountPage mp = new MyAccountPage(driver);

            logger.info("************ account details **************");
            boolean existingacc = mp.accountExist();
            Assert.assertEquals(existingacc, true, "login fail....");
        }
        catch (Exception e) {
           Assert.fail();
        }
        logger.info("************ Finish T002LoginTest **************");
    }
}
