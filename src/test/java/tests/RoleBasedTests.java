package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.ConfigReader;

public class RoleBasedTests extends BaseTest {
	
	
	@Test
	public void TC13_LoginAsAdmin() {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(

	    	    ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

	    WebElement adminWidget =
	            driver.findElement(By.xpath("//span[normalize-space()='Admin']"));

	    Assert.assertTrue(
	            adminWidget.isDisplayed(),
	            "Admin-specific widget should be visible");
	    
	    Assert.assertTrue(
	            driver.getCurrentUrl().contains("staging-hub"),
	            "Admin should be redirected to dashboard");

	    System.out.println("Admin URL = " + driver.getCurrentUrl());
	}

	
	
	@Test
	public void TC14_LoginAsOwner() {

	    LoginPage lp = new LoginPage(driver);
	    
	    lp.login(

	    	    ConfigReader.getProperty("ownerEmail"),
	            ConfigReader.getProperty("password"));


	    // Replace with actual Owner dashboard element
	    
	    try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	    
	
	    Assert.assertTrue(
	            driver.getCurrentUrl().contains("staging-hub"),
	            "Owner should be redirected to dashboard");

	    System.out.println("Owner URL = " + driver.getCurrentUrl());
	    
	    WebElement auditrep =
	            driver.findElement(By.xpath("  //*[@id=\"__nuxt\"]/div[2]/div[2]/div/div[1]/div/div[1]/div[1]/div[1]/div/h3"));
	    
	    
	    Assert.assertEquals(
	            auditrep.getText().trim(),
	            "Audit Report");
	    
	    Assert.assertTrue(
	    		auditrep.isDisplayed(),
	            "Audit report  should be visible"); 
	       
	}
	
	
	@Test
	public void TC15_LoginAsStoreManager() {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(

	    	    ConfigReader.getProperty("managerEmail"),
	            ConfigReader.getProperty("password"));

	    // Replace with actual Store Manager widget
	    
	    try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	    
	    
	    WebElement dataWidget =
	            driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div[2]/nav/div/div[1]/span"));
	    
	    dataWidget.click();
	    
	    System.out.println("Data Import = " + dataWidget.getText());
	    
	    WebElement ImageWidget =
	            driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div[2]/nav/div/div[1]/div/ul/li[2]/a"));

	    Assert.assertTrue(
	    		ImageWidget.isDisplayed(),
	    		"Image Import option should be visible");
	    
	
	    System.out.println("Image Import = " + ImageWidget.getText());
	    
	    
	    WebElement stkauditrep =
	            driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/h3"));
	    
	    
	    Assert.assertEquals(
	    		stkauditrep.getText().trim(),
	            "Stock Audit Reports");
	    
	    Assert.assertTrue(
	    		stkauditrep.isDisplayed(),
	            "Stock Audit report  should be visible"); 
	}
	
	
	@Test
	public void TC16_AdminSeesUserManagement() {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(

	    	    ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));
	    
	    try {
			Thread.sleep(4000);
		} catch (Exception e) {
		}

	    WebElement userManagement =
	            driver.findElement(By.xpath("//*[contains(text(),'User Management')]"));

	    Assert.assertTrue(
	            userManagement.isDisplayed(),
	            "User Management should be visible for Admin");
	}
	
	
	
	@Test
	public void TC17_OwnerCannotSeeUserManagement() {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(

	    	    ConfigReader.getProperty("ownerEmail"),
	            ConfigReader.getProperty("password"));

	    List<WebElement> userManagement =
	            driver.findElements(By.xpath("//*[contains(text(),'User Management')]"));

	    Assert.assertTrue(
	            userManagement.isEmpty(),
	            "User Management should NOT be visible for Owner");
	}
	
	
	
	
	// It shouldn't work but it works
	@Test
	public void TC18_StoreManagerRestrictedAccess() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(

	    	    ConfigReader.getProperty("managerEmail"),
	            ConfigReader.getProperty("password"));

	    driver.get("https://staging-hub.tiara.jewelry/users");

	    Thread.sleep(3000);

	    String currentUrl = driver.getCurrentUrl();

	    System.out.println("Current URL = " + currentUrl);

	    Assert.assertFalse(
	            currentUrl.contains("/users"),
	            "Store Manager should not access restricted page");
	}
	
	
	@Test
	public void TC19_SwitchRoles() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    // Login as Admin
	    lp.login(

	    	    ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    Assert.assertTrue(
	            driver.getCurrentUrl().contains("staging-hub"),
	            "Admin login failed");

	    DashboardPage dp = new DashboardPage(driver);

	    dp.clickProfile();
	    dp.clickLogout();

	    Thread.sleep(3000);

	    // Login as Owner
	    lp.login(

	    	    ConfigReader.getProperty("ownerEmail"),
	            ConfigReader.getProperty("password"));

	    Assert.assertTrue(
	            driver.getCurrentUrl().contains("staging-hub"),
	            "Owner login failed");

	    System.out.println("Role switch successful");
	}
	
	
}
