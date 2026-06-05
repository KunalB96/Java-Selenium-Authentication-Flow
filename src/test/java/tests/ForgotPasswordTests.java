package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import utilities.ConfigReader;

public class ForgotPasswordTests extends BaseTest {

	
	@Test
	public void TC08_ForgotPasswordLink() {

	    ForgotPasswordPage fp = new ForgotPasswordPage(driver);

	    fp.clickForgotPassword();
	    
	    try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

	    Assert.assertTrue(
	            fp.isEmailFieldDisplayed(),
	            "Forgot Password form should appear");
	}
	
	
	
	@Test
	public void TC09_EmptyEmailForgotPassword() {

	    ForgotPasswordPage fp = new ForgotPasswordPage(driver);

	    fp.clickForgotPassword();

	    fp.clickSubmit();

	    try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	    
	    WebElement email =
	            driver.findElement(By.xpath("//*[@id=\"v-0-3\"]"));
	    email.getText();

	    String validationMessage =
	            email.getAttribute("validationMessage");
	    
	    System.out.println("Pleas fill out this field");

	    Assert.assertFalse(
	            validationMessage.isEmpty(),
	            "Validation message should appear");
	}
	
	
	
	@Test
	public void TC10_ValidEmailForgotPassword() {

	    ForgotPasswordPage fp = new ForgotPasswordPage(driver);

	    fp.clickForgotPassword();
	    fp.enterEmail("cypress.admin@irysgroup.com");
	    fp.clickSubmit();

	    try {
	        Thread.sleep(3000);
	    } catch (Exception e) {
	    }
	    
	    System.out.println(driver.getPageSource());

//	    WebElement message = driver.findElement(
//	            By.xpath("//p[contains(text(),'An email has been sent')]"));
//	  
//	    Assert.assertTrue(
//	            message.isDisplayed(),
//	            "Confirmation message should be displayed after submitting valid email");
//
//	    Assert.assertTrue(
//	            message.getText().contains("An email has been sent"),
//	            "Expected confirmation message is not displayed");
	    
	    
	    String pageText = driver.findElement(By.tagName("body")).getText();

	    Assert.assertTrue(
	        pageText.contains("An email has been sent")
	        || pageText.contains("Please wait before requesting another password reset"),
	        "Expected password reset message not displayed");

	//    System.out.println("Confirmation Message = " + message.getText());
	}
	
	
	
	@Test
	public void TC11_PasswordMismatch() throws InterruptedException {
		
		
		  LoginPage lp = new LoginPage(driver);

		    lp.login(
		            ConfigReader.getProperty("adminEmail"),
		            ConfigReader.getProperty("password"));

	    ForgotPasswordPage fp = new ForgotPasswordPage(driver);
	    
	    fp.openProfileMenu();
	    
	    fp.clickProfileOpt();
	    
	    Thread.sleep(2000);
	    
	    fp.clickPasswOpt();

	    fp.enterNewPassword("Password123");

	    fp.enterConfirmPassword("Password456");

	  //  fp.clickSubmit();
	    
	    fp.clickUpdatePass();

	    WebElement error =
	            driver.findElement(
	                    By.xpath("//*[contains(text(),'match')]"));
	
	    Assert.assertTrue(
	            error.isDisplayed(),
	            "Password mismatch error should appear");
	}
	
	
	@Test
	public void TC12_WeakPassword() throws InterruptedException {
		
		  LoginPage lp = new LoginPage(driver);

		    lp.login(
		            ConfigReader.getProperty("adminEmail"),
		            ConfigReader.getProperty("password"));

	    ForgotPasswordPage fp = new ForgotPasswordPage(driver);
	    
	    fp.openProfileMenu();
	    
        fp.clickProfileOpt();
	    
	    Thread.sleep(2000);
	    
	    fp.clickPasswOpt();

	    fp.enterNewPassword("123");

	    fp.enterConfirmPassword("123");

	//    fp.clickSubmit();
	    
	    fp.clickUpdatePass();

	    WebElement error =
	            driver.findElement(
	                    By.xpath("//*[@id=\"app\"]/div/main/div/div/div[3]/form/div[3]/div/div/div/div[2]/div/div/div"));
	        
	    

	    Assert.assertTrue(
	            error.isDisplayed(),
	            "Weak password validation should be displayed");
	}
	
	    
		
}
