package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import models.TestCaseData;
import utils.FileReaderManager;
import utils.SeleniumUtils;

public class LoginPage {
	WebDriver driver;
	SeleniumUtils utils;

	@FindBy(xpath = "(//*[@id='username'])")
	private WebElement userNameField;
	
	@FindBy(xpath = "(//*[@id='password'])")
	private WebElement passwordField;
	
	@FindBy(xpath = "(//*[@id='logon-login-button'])")
	private WebElement loginButton;
	
	@FindBy(xpath = "//*[@class='alert alert-danger']")
	private WebElement InvalidCredentalsAlet;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new SeleniumUtils(driver);
		PageFactory.initElements(driver, this);

	}
	public void navigateTo_LoginPage() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getFrontendUrl());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void enterCredentialsAndSubmit(String testCaseNumber) {
		TestCaseData testdata = FileReaderManager.getInstance().getJsonReader().getDataByTestCaseName(testCaseNumber);
		userNameField.sendKeys(testdata.userName);
		passwordField.sendKeys(testdata.password);
		loginButton.click();
	}

	public void enterUsernameAndPassword(String userName, String Password) {
		userNameField.sendKeys(userName);
		passwordField.sendKeys(Password);
		loginButton.click();
	}

	public void verifyLoginPage() {
		utils.waitForElementPresent(loginButton);
		utils.waitForElementPresent(userNameField);
		utils.waitForElementPresent(passwordField);
	}

	public void verifyLoginPageDisplayed() {
		utils.waitForElementPresent(loginButton);
	}


}
