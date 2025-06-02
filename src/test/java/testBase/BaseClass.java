package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master", "DataDriven"}) // Grouping Together
    @Parameters({"OS", "Browser"})
    public void setUp(String os, String br) throws IOException {

        // Loading config.properties File
        FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());


            // For Remote Execution in Grid
            if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

                DesiredCapabilities dc = new DesiredCapabilities();

                // Operating System
                if (os.equalsIgnoreCase("windows10")) {
                    dc.setPlatform(Platform.WIN10);
                } else if (os.equalsIgnoreCase("linux")) {
                    dc.setPlatform(Platform.LINUX);
                } else if (os.equalsIgnoreCase("mac")) {
                    dc.setPlatform(Platform.MAC);
                } else {
                    System.out.println("No Matching OS.......");
                    return;
                }

                // Browser
                switch (br.toLowerCase()) {
                    case "chrome":
                        dc.setBrowserName("chrome");
                        break;

                    case "firefox":
                        dc.setBrowserName("firefox");
                        break;

                    default:
                        System.out.println("Invalid Browser....");
                        return;
                }

                // Creating Remote Browser
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
            }

        // For Local Execution
        if(p.getProperty("execution_env").equalsIgnoreCase("local")) {

            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    System.out.println("Invalid Browser....");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Reading data from config.properties file
        driver.get(p.getProperty("appURL"));
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master", "DataDriven"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber() {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphanumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString + "@" + generatedNumber);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot tk = (TakesScreenshot) driver;
        File sourceFile = tk.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+"tname"+"_"+"timeStamp"+".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
