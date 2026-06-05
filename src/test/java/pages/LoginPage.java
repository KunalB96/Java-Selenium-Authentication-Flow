package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Use more flexible selectors that don't rely on dynamic IDs
    By emailField = By.xpath("//input[@type='email']");
    By passwordField = By.xpath("//input[@type='password']");
    By loginBtn = By.xpath("//button[@type='submit']");
    By proceedToLogin =  By.xpath("//span[normalize-space()='Proceed to Login']");
    By loginError = By.xpath("//div[contains(@class,'text-error')]");

    public void login(String user, String pass) {
        // Find email field by type rather than ID
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(user);

        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(pass);

        driver.findElement(loginBtn).click();
        
   
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(proceedToLogin))
                    .click();
        } catch (Exception e) {
            System.out.println(e);
        }
        

        // Wait for navigation after login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Current URL = "
                + driver.getCurrentUrl());
    }
    
    
//    public void adminWidget() {
//    	driver.findElement(By.xpath("//span[normalize-space()='Admin']")).isDisplayed();
//    }
    
    public boolean isLoginErrorDisplayed() {

        try {
            return driver.findElements(loginError).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public boolean isLoginPageDisplayed() {
        return driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
    }
   
}