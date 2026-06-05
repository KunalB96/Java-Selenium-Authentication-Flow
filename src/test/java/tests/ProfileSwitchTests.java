package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import base.BaseTest;
import pages.LoginPage;
import pages.ProfilePage;
import utilities.ConfigReader;

public class ProfileSwitchTests extends BaseTest {
	
	@Test
	public void TC20_ProfileSwitcherVisible() {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    ProfilePage pp = new ProfilePage(driver);
	    
	    pp.openProfile();
	    
	    if (pp.isProfileSwitcherDisplayed()) {
	        System.out.println("PASS - Profile switcher is visible");
	    }
	    Assert.assertTrue(
	            pp.isProfileSwitcherDisplayed(),
	            "Profile switcher should be visible");
	}
	
	
	
	@Test
	public void TC21_CurrentProfileNameDisplayed() {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    ProfilePage pp = new ProfilePage(driver);

	    System.out.println(
	            "Current Profile = "
	                    + pp.getCurrentProfile());
	    
	    try {
	    	Thread.sleep(4000);
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }

	    Assert.assertEquals(
	            pp.getCurrentProfile(),
	            "QA");
	}
	
	
	
	
	@Test
	public void TC22_SwitchProfileQAtoQA2() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    ProfilePage pp = new ProfilePage(driver);

	    pp.openProfileMenu();

	   Thread.sleep(2000);
	    
	    pp.selectQA2();
	   
	    pp.clickProfileSwitcher();
	  
	    Thread.sleep(1000);
	    
	    pp.clickOK(); 

	    Thread.sleep(3000); 
	     
	     driver.manage().timeouts()
	      .implicitlyWait(Duration.ofSeconds(3));

	    
	   String currentProfile = pp.getCurrentProfile();

	    System.out.println("Current Profile = " + currentProfile);

	    Assert.assertEquals(currentProfile, "QA2");
	}

	
	
	@Test
	public void TC23_ProfileNameUpdatesAfterSwitch() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    ProfilePage pp = new ProfilePage(driver);

	    pp.openProfileMenu();

	    pp.selectQA2();

	    pp.clickProfileSwitcher();

	    pp.clickOK();

	    Thread.sleep(3000);

	    String currentProfile = pp.getCurrentProfile();

	    System.out.println("Current Profile = " + currentProfile);

	    Assert.assertEquals(currentProfile, "QA2");
	}
	
	
	
	@Test
	public void TC24_DashboardDataChangesAfterProfileSwitch() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    

	    ProfilePage pp = new ProfilePage(driver);
	    
	    
	 // Capture data for QA profile
	    String dashboardDataBefore =
	            driver.findElement(
	                    By.xpath("//*[@id=\"__nuxt\"]/div[2]/div[2]/div/div[1]/div[10]/div[2]/div[1]/div/div[2]/p[2]"))
	                    .getText();
 
	    System.out.println("Before Switch = " + dashboardDataBefore);
	    
	    

	    pp.openProfileMenu();

	    pp.selectQA2();

	    pp.clickProfileSwitcher();

	    pp.clickOK();

	    Thread.sleep(5000);

	    // Capture data for QA2 profile
	    String dashboardDataAfter =
	            driver.findElement(
	                      By.xpath("//*[@id=\"__nuxt\"]/div[2]/div[2]/div/div[1]/div[10]/div[2]/div[1]/div/div[2]/p[2]"))
	                   .getText();

	    System.out.println("After Switch = " + dashboardDataAfter);
	    
	    
	    if(!dashboardDataBefore.equals(dashboardDataAfter)) {
	        System.out.println("PASS - Dashboard reflects different data after switch");
	    }

	    Assert.assertNotEquals(
	            dashboardDataBefore,
	            dashboardDataAfter,  
	            "Dashboard data did not change after switching profile");
	}
	
	
	
	@Test
	public void TC25_SwitchBackQA2ToQA() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    ProfilePage pp = new ProfilePage(driver);

	    // Switch to QA2
	    pp.openProfileMenu();
	    pp.selectQA2();
	    pp.clickProfileSwitcher();
	    pp.clickOK();

	    Thread.sleep(3000);

	    Assert.assertEquals(
	            pp.getCurrentProfile(),
	            "QA2");

	    // Switch back to QA
	    pp.openProfileMenu();
	        
	    pp.selectQA1();

	    pp.clickProfileSwitcher();

	    pp.clickOK();

	    Thread.sleep(3000);

	    Assert.assertEquals(
	            pp.getCurrentProfile(),
	            "QA");
	}
	
	
	@Test
	public void TC26_ProfilePersistsAfterRefresh() throws Exception {

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	            ConfigReader.getProperty("adminEmail"),
	            ConfigReader.getProperty("password"));

	    ProfilePage pp = new ProfilePage(driver);

	    pp.openProfileMenu();

	    pp.selectQA2();

	    pp.clickProfileSwitcher();

	    pp.clickOK();

	    Thread.sleep(5000);

	    Assert.assertEquals(
	            pp.getCurrentProfile(),
	            "QA2");

	    driver.navigate().refresh();

	    Thread.sleep(5000);

	    String profileAfterRefresh =
	            pp.getCurrentProfile();

	    System.out.println(
	            "Profile after refresh = "
	            + profileAfterRefresh);

	    Assert.assertEquals(
	            profileAfterRefresh,
	            "QA2");
	}
	
	
}
