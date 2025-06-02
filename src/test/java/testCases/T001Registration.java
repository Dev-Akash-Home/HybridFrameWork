package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class T001Registration extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verifyRegistration() {

        // SilverWind001@gmail.com
        // yQF3nFizj@D#VX
        // 3164461313

        logger.info("************ Starting T001Registration **************");

        try {
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("************ click My Account **************");

            hp.clickRegister();
            logger.info("************ click on Register **************");

            RegistrationPage rp = new RegistrationPage(driver);

            logger.info("************ customer details **************");
            rp.setFirstName(randomString().toUpperCase());
            rp.setLastName(randomString().toUpperCase());

            rp.setEmail(randomString() + "@gmail.com");
            rp.setTelePhone(randomNumber());

            String password = randomAlphanumeric();

            rp.setPassword(password);
            rp.setPasswordConfirm(password);

            rp.chkPolicy();
            rp.btnConfirm();

            logger.info("************ confirmation massage **************");
            String confmsg = rp.getConfirmation();
            if (confmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
                System.out.println(confmsg);
            } else {
                logger.error("Test is failed.....");
                logger.debug("Debug logs.........");
            }
        } catch (Exception e) {

            Assert.fail();
        }
        logger.info("************ Finish T001Registration **************");
    }
}
