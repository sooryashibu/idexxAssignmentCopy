package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import handlers.TestContext;
import pageobjects.LoginPage;;

public class LoginPageStepDefs {
	LoginPage loginPage;

	public LoginPageStepDefs() {

		
		loginPage = TestContext.pageObjectManager.getLoginPage();
	}
	
	@Given("^Customer is Navigated to Login Page$")
	public void UserIsNavigatedToLoginPage() {
		loginPage.navigateTo_LoginPage();
	}

	
	@Given("^Login Page is displayed$")
	public void userIsOnLoginPage() {
		loginPage.verifyLoginPageDisplayed();
	}

	@And("^Customer enters the credentials \"(.*)\" and submit$")
	public void customerEntersUserNameAndPassword(String userName) throws Exception {
		loginPage.enterCredentialsAndSubmit(userName);

	}

	@And("^Customer enters (.*) and (.*) and submit$")
	public void customerEntersUserNameAndPassword(String userName, String Password) throws Exception {
		loginPage.enterUsernameAndPassword(userName, Password);
	}



}
