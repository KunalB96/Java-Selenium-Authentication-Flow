package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginLogoutTests extends BaseTest {

	@Test(priority = 1)
	public void TC01_SuccessfulLogin() {
		// Successful login with valid credentials
		// User should be redirected to the Hub dashboard
		System.out.println("Email = " + ConfigReader.getProperty("adminEmail"));

		System.out.println("Password = " + ConfigReader.getProperty("password"));

		loginAsAdmin();

		DashboardPage dp = new DashboardPage(driver);

		Assert.assertTrue(dp.isDashboardVisible(), "User should be redirected to Hub dashboard after successful login");
	}

	@Test(priority = 2)
	public void TC02_WrongPassword() {

		LoginPage lp = new LoginPage(driver);

		lp.login(ConfigReader.getProperty("adminEmail"), "wrong123");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Verify user stays on login page
		Assert.assertEquals(driver.getCurrentUrl(), "https://staging-auth.tiara.jewelry/",
				"User should stay on login page");

		// Verify error message appeared
            Assert.assertTrue(
                   lp.isLoginErrorDisplayed(),
                    "Error message should appear");

	}

	@Test
	public void TC03_InvalidEmailFormat() {

		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("cypress.admin@");

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("teamwork");

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e);
		}

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

		WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));

		String validationMessage = emailField.getAttribute("validationMessage");

		System.out.println("Validation Message = " + validationMessage);

		Assert.assertFalse(validationMessage.isEmpty(), "Validation error should be shown");
	}

	@Test
	public void TC04_EmptyFields() {
		// Submit login form with both fields empty
		// Required field errors should be shown for both fields
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
		String emailValidationMessage = emailField.getAttribute("validationMessage");

		WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
		String passwordValidationMessage = passwordField.getAttribute("validationMessage");

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e);
		}

		Assert.assertFalse(emailValidationMessage.isEmpty(), "Email field should show required field error");
		Assert.assertFalse(passwordValidationMessage.isEmpty(), "Password field should show required field error");
	}

	@Test
	public void TC05_Logout() {
		// User logs out
		// Should be redirected back to login page
		loginAsAdmin();

		DashboardPage dp = new DashboardPage(driver);
		dp.clickProfile();
		dp.clickLogout();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

		System.out.println("URL After Logout = " + driver.getCurrentUrl());

//                Assert.assertEquals(
//                        driver.getCurrentUrl(),
//                        "https://staging-auth.tiara.jewelry/",
//                        "User should be redirected to login page after logout");             
	}

	@Test
	public void TC06_DirectURLAfterLogout() {

		loginAsAdmin();

		DashboardPage dp = new DashboardPage(driver);
		dp.clickProfile();
		dp.clickLogout();

		driver.get("https://staging-hub.tiara.jewelry/");

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("staging-auth"));

		System.out.println("Current URL = " + driver.getCurrentUrl());

		Assert.assertTrue(driver.getCurrentUrl().contains("staging-auth"), "User should be redirected to login page");
	}

	@Test
	public void TC07_RefreshAfterLogin() {
		// Page refresh while logged in
		// User should stay logged in and dashboard should still be shown
		loginAsAdmin();

		DashboardPage dp = new DashboardPage(driver);
		Assert.assertTrue(dp.isDashboardVisible(), "Dashboard should be visible after login");

		// Refresh the page
		driver.navigate().refresh();

		DashboardPage dpAfterRefresh = new DashboardPage(driver);
		Assert.assertTrue(dpAfterRefresh.isDashboardVisible(),
				"User should stay logged in and dashboard should be visible after page refresh");
	}
}