package handlers;

import org.openqa.selenium.WebDriver;

public class TestContext {
	public static WebDriverManager webDriverManager;
	public static PageObjectManager pageObjectManager;
	public static WebDriver driver;

	public TestContext() {
		
	
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		pageObjectManager = new PageObjectManager(driver);
	}
}