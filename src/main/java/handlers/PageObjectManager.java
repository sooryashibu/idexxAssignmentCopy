package handlers;

import org.openqa.selenium.WebDriver;

import pageobjects.ChooseLocationPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.SearchResultPage;;

public class PageObjectManager {

	private WebDriver driver;
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ChooseLocationPage chooseLocationPage;
	private  SearchResultPage searchResultPage;
	public PageObjectManager(WebDriver driver) {
		
		this.driver = driver;
	}



	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
	public ChooseLocationPage getChooseLocationPage() {
		return (chooseLocationPage == null) ? chooseLocationPage = new ChooseLocationPage(driver) : chooseLocationPage;
	}
	public SearchResultPage getSearchResultPage() {
		return (searchResultPage == null) ? searchResultPage = new SearchResultPage(driver) : searchResultPage;
	}
}