package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

    WebDriver driver;
    WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
    }

    By forgotPasswordLink =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div/div/form/div[3]/button/span");

    By emailField =
            By.xpath("//*[@id=\"v-0-3\"]");

    By submitBtn =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div/div/div/form/button/span");
    
    
    By continuelogin = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div/div/div/div[2]/button");
    
    
    
     By profileMenu =
            By.xpath("//*[@id=\"__nuxt\"]/div[2]/header/div/div/div[3]/div[2]/span[1]");
     
     
     By ProfileOpt = By.xpath("//span[normalize-space()='Profile']");
     
     
     By PasswordOpt = By.xpath("//a[normalize-space()='Password']");
     
     
    

    By newPassword =
            By.xpath("//input[@placeholder='Enter new password']");

    By confirmPassword = By.xpath("//input[@placeholder='Enter confirm new password']");
          
  
    By Update_PasswordBtn = By.xpath(" //*[@id=\"app\"]/div/main/div/div/div[3]/form/div[5]/button");
    


    public void clickForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }
    
    
    public void openProfileMenu() {

        WebElement profile =
                wait.until(ExpectedConditions.visibilityOfElementLocated(profileMenu));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", profile);

        System.out.println("Profile menu clicked");
        
      
    }
    
    public void clickProfileOpt() {
    	//click on profile option it is available inside profile menu below switch button
          driver.findElement(ProfileOpt).click();;
    }
    
    public void clickPasswOpt() {
    	
    	//Click on Password Option
    	driver.findElement(PasswordOpt).click();
    
    }
    
    

    public void enterNewPassword(String pwd) {
    	
    	
    	
    	//Current password
    	driver.findElement(By.xpath("//input[@id='input-153']")).sendKeys("teamwork");
    	
        driver.findElement(newPassword).sendKeys(pwd);
    }

    public void enterConfirmPassword(String pwd) {
    //    driver.findElement(confirmPassword).sendKeys(pwd);

        WebElement confirmPwd =
                driver.findElement(confirmPassword);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                confirmPwd);

        confirmPwd.sendKeys(pwd);
    }
    
    
    public void clickUpdatePass() {
        driver.findElement(Update_PasswordBtn).click();
    }
    

    public boolean isEmailFieldDisplayed() {
        return driver.findElement(emailField).isDisplayed();
    }
}