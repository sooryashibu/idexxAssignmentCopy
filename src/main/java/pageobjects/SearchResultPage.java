package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import models.TestCaseData;

import utils.FileReaderManager;
import utils.SeleniumUtils;

public class SearchResultPage {
	WebDriver driver;
	SeleniumUtils utils;
	

	@FindBy(xpath = "//*[@id='findResult-searchResult-table']")
	private WebElement searchResultHeading;
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new SeleniumUtils(driver);
		PageFactory.initElements(driver, this);
	
	}


	public void verifySearchResutPageDisplayed() {
		utils.waitForElementPresent(searchResultHeading);
	}
	
	public void verifySearchResultDisplayedIsForTheGivenName(String testCaseNumber) {
		utils.waitForElementPresent(searchResultHeading);
		TestCaseData testdata = FileReaderManager.getInstance().getJsonReader().getDataByTestCaseName(testCaseNumber);
		//Dynamcally genarating webelement for the Name in search Table
		utils.waitForElementPresent("//td[contains(text(), '"+testdata.searchKeyword+"')]");
		
	}



}
