package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utilities.ConfigReader;

public class BaseTest {

	public static WebDriver driver;

    @BeforeMethod
    public void setup() {

   //     WebDriverManager.chromedriver().setup();
       

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(
                ConfigReader.getProperty("url"));
    }

    public void loginAsAdmin() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                ConfigReader.getProperty("adminEmail"),
                ConfigReader.getProperty("password"));
    }

    public void loginAsOwner() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                ConfigReader.getProperty("ownerEmail"),
                ConfigReader.getProperty("password"));
    }

    public void loginAsStoreManager() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                ConfigReader.getProperty("managerEmail"),
                ConfigReader.getProperty("password"));
    }

    @AfterMethod
    public void tearDown() {

        if(driver!=null)
            driver.quit();
    }
}