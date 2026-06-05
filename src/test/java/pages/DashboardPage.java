package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

	WebDriver driver;
	WebDriverWait wait;

	public DashboardPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	By dashboardMenu = By.xpath("//a[contains(.,'Dashboard')]");

	// Alternative selectors for dashboard detection
	By dashboardAlt1 = By.xpath("//h1[contains(text(), 'Dashboard')]");
	By dashboardAlt2 = By.xpath("//*[contains(text(), 'dashboard')]");
	By mainContent = By.xpath("//*[@class='main-content']");

	By profileName = By.xpath("//span[@class='truncate text-base text-strong leading-tight']");

	By logout = By.xpath("//span[text()='Logout']");

	public boolean isDashboardVisible() {
		try {
			// Try primary selector
			return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardMenu)).isDisplayed();
		} catch (Exception e1) {
			// Try alternative selectors
			try {
				return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardAlt1)).isDisplayed();
			} catch (Exception e2) {
				// Check if we're on dashboard URL
				String currentUrl = driver.getCurrentUrl();
				System.out.println("Current URL: " + currentUrl);
				return currentUrl.contains("dashboard") && !currentUrl.contains("login");
			}
		}
	}

	public void clickProfile() {

		wait.until(ExpectedConditions.elementToBeClickable(profileName)).click();
		
	    System.out.println("Profile clicked");
	}

	public void clickLogout() {

		wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
		
		  System.out.println("Logout clicked");
	}
}