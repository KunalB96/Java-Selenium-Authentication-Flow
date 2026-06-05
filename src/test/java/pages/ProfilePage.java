package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProfilePage {

    WebDriver driver;
    WebDriverWait wait;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    

    // Update these locators according to your application
    
    public  By profileMenu =
            By.xpath("//*[@id=\"__nuxt\"]/div[2]/header/div/div/div[3]/div[2]/span[1]");
        

    public By profileSwitcher =
            By.xpath(" //span[normalize-space()='Switch']");
    
    
    public  By qaProfile =
            By.xpath("//*[@id=\"app\"]/div[2]/aside[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div/div[2]/span");

    public  By profileDropdown =
            By.xpath("/html/body/aside/div/div[2]/div/div[2]/div/div/div");

    public  By qa2Option =
    		By.xpath("/html/body/aside/div/div[2]/div/div[2]/div/div/div/div/span"); 


//    public   By currentProfile =
//                By.xpath("//span[normalize-space()='QA2']");
    
    
    public   By currentProfile =
            By.xpath("//span[@class='truncate text-sm text-weak leading-tight']");
    
   
    
    public void openProfile() {
    	
         driver.findElement(profileMenu).click(); 
         
         try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         driver.findElement(profileSwitcher).isDisplayed();
    }
    
 
    public void openProfileMenu() {

        WebElement profile =
                wait.until(ExpectedConditions.visibilityOfElementLocated(profileMenu));
        

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", profile);

        System.out.println("Profile menu clicked");
        
    }
    
    
    public void selectQA1() {
    	
    	 WebElement dropdown =
                 driver.findElement(profileDropdown);

         dropdown.click();

         System.out.println("Dropdown opened");

         Actions act = new Actions(driver);

         act.sendKeys(Keys.ENTER)
            .perform();
  
         System.out.println("Keys sent");
             
    }
    
    

    public void selectQA() {
        driver.findElement(qaProfile).click();
    }

    
    public void selectQA2() throws InterruptedException {

        System.out.println("Inside selectQA2");

        WebElement dropdown =
                driver.findElement(profileDropdown);

        dropdown.click();

        System.out.println("Dropdown opened");

        Actions act = new Actions(driver);

        act.sendKeys(Keys.ARROW_DOWN)
           .sendKeys(Keys.ENTER)
           .perform();
        
    //    Thread.sleep(2000);

        System.out.println("Keys sent");
    }
    
    
    public void clickProfileSwitcher(){
    	
        WebElement switchBtn =
                wait.until(ExpectedConditions.elementToBeClickable(profileSwitcher));
        
        switchBtn.click();

        System.out.println("Switch button clicked");
    }
 
    
    
    
    
    // Wait for confirmation popup
    public void clickOK() {

        WebElement okBtn =
            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='OK']")));

        okBtn.click();

        System.out.println("OK Clicked");
    }
    

    public String getCurrentProfile() {

        String profile =
                driver.findElement(
                  //      By.xpath("//span[contains(@class,'select-display')]"))
                By.xpath("//span[@class='truncate text-sm text-weak leading-tight']"))
                .getText()
                .trim();

        System.out.println("Current Profile = " + profile);

        return profile;
    }
    
    
    

    public boolean isProfileSwitcherDisplayed() {
        return driver.findElement(profileSwitcher)
                .isDisplayed();
    }
}