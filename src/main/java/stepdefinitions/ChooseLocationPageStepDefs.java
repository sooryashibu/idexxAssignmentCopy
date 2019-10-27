package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import handlers.TestContext;
import pageobjects.ChooseLocationPage;;

public class ChooseLocationPageStepDefs {
	ChooseLocationPage chooseLocationPage;

	public ChooseLocationPageStepDefs() {
		chooseLocationPage = TestContext.pageObjectManager.getChooseLocationPage();
	}

	@Given("^Verify customer is navigated to Choose Location Page$")
	public void verifyUserIsNavigatedToChooseLocationPage() {
		chooseLocationPage.verifyChooseLocationPageDisplayed();
	}

	@And("^Choose the Location for \"(.*)\"$")
	public void ChooseTheLocation(String TestCaseNumber) {
		chooseLocationPage.clickOnTheGivenLocation(TestCaseNumber);
	}
}
