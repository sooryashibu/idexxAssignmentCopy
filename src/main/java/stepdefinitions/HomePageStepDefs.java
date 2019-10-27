package stepdefinitions;

import cucumber.api.java.en.And;
import handlers.TestContext;
import pageobjects.HomePage;
import pageobjects.SearchResultPage;;

public class HomePageStepDefs {
	HomePage homePage;
	SearchResultPage searchResultPage;

	public HomePageStepDefs() {
		homePage = TestContext.pageObjectManager.getHomePage();
		searchResultPage = TestContext.pageObjectManager.getSearchResultPage();
	}

	@And("^Verify customer is navigated to Home Page$")
	public void verifyCustomerIsNavigatedToHomePage() {
		homePage.verifyHomePageDisplayed();
	}

	@And("^Verify New Contact icon on Home Page$")
	public void VerifyNewContacticononHomePage() {
		homePage.verifyNewContactIconDisplayed();
	}

	@And("^verify search functionality for \"(.*)\"$")
	public void verifySearchFunctionality(String testCaseNumber) {
		homePage.enterTheGivenSearchKeyword(testCaseNumber);
		homePage.selectTheGivenSearchType(testCaseNumber);
		searchResultPage.verifySearchResutPageDisplayed();
		searchResultPage.verifySearchResultDisplayedIsForTheGivenName(testCaseNumber);
	}

}
