package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import models.TestCaseData;
import utils.FileReaderManager;
import utils.SeleniumUtils;

public class ChooseLocationPage {
	WebDriver driver;
	SeleniumUtils utils;
	

	@FindBy(xpath = "(//*[@class='orphaned-page-title'])")
	private WebElement chooseLocationTitle;
	
	
	public ChooseLocationPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new SeleniumUtils(driver);
		PageFactory.initElements(driver, this);

	}


	public void verifyChooseLocationPageDisplayed() {
		utils.waitForElementPresent(chooseLocationTitle);
	}
//Dynamically generating xpath of the Location Element
	public void clickOnTheGivenLocation(String TestCaseNumber) {
		
		TestCaseData testdata = FileReaderManager.getInstance().getJsonReader().getDataByTestCaseName(TestCaseNumber);
		utils.waitForElementPresent("//*[@value='"+testdata.Location+"']");
		utils.clickOnElement("//*[@value='"+testdata.Location+"']");
	}


}
