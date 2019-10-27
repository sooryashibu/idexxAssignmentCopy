package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumUtils {

	private WebDriver driver;

	public SeleniumUtils(WebDriver driver) {
		
		this.driver = driver;
	}

	public void scrollTo(WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
	}

	public void waitForTheGivenTimeForElementPresent(WebElement element, long timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	public void waitForElementPresent(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement webElement=driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf( webElement));

	}
	public void clickOnElement(String xpath) {
		driver.findElement(By.xpath(xpath)).click();

	}
	public Boolean isElementPresent(String elementPresent) {
		boolean element;
		try {
			driver.findElement(By.xpath(elementPresent)).isDisplayed();
			element = true;
		} catch (Exception e) {
			element = false;
		}

		return element;
	}

	public void clearElement(String xpath) {
		driver.findElement(By.xpath(xpath)).clear();
	}

	public int getNumberOfElements(String xpath) {
		return driver.findElements(By.xpath(xpath)).size();
	}

}
