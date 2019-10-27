package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import models.TestCaseData;

import utils.FileReaderManager;
import utils.SeleniumUtils;

public class HomePage {
	WebDriver driver;
	SeleniumUtils utils;

	@FindBy(xpath = "(//*[@class='welcome-text'])")
	private WebElement welcomeInfo;

	@FindBy(xpath = "(//*[@id='topMenuBar-addNewClient-button'])")
	private WebElement newContactIcon;

	@FindBy(xpath = "(//*[@id='topMenuBar-searchDropdown-button'])")
	private WebElement searchTypeDropdown;

	@FindBy(xpath = "(//*[@id='headerSearchInput'])")
	private WebElement searchField;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.utils = new SeleniumUtils(driver);
		driver.switchTo().defaultContent(); // you are now outside both frames
		driver.switchTo().frame("mana");
		PageFactory.initElements(driver, this);

	}

	public void verifyHomePageDisplayed() {
		utils.waitForElementPresent(welcomeInfo);
	}

	public void verifyNewContactIconDisplayed() {
		utils.waitForElementPresent(newContactIcon);
	}

	public void selectTheGivenSearchType(String testCaseNumber) {
		utils.waitForElementPresent(searchTypeDropdown);
		TestCaseData testdata = FileReaderManager.getInstance().getJsonReader().getDataByTestCaseName(testCaseNumber);
		searchTypeDropdown.click();
		// Dynamically generating xpath of the search type
		utils.waitForElementPresent("//*[@beanname='" + testdata.searchType + "']");
		utils.clickOnElement("//*[@beanname='" + testdata.searchType + "']");

	}

	public void enterTheGivenSearchKeyword(String testCaseNumber) {
		utils.waitForElementPresent(searchField);
		TestCaseData testdata = FileReaderManager.getInstance().getJsonReader().getDataByTestCaseName(testCaseNumber);

		searchField.sendKeys(testdata.searchKeyword);

	}

}
