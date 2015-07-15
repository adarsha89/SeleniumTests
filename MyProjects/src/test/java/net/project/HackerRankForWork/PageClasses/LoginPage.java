package net.project.HackerRankForWork.PageClasses;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginPage.
 */
public class LoginPage extends LoadableComponent<LoginPage> implements CommonPageInterface{

	
	/** The url. */
	public  String url="https://www.hackerrank.com/work/login";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	/** The title of the page. */
	@FindBy(how=How.CSS , using="title")
	@CacheLookup
	public WebElement titleOfThePage;
	
	/** The username input. */
	@FindBy(how=How.CSS , using="input[id='email']")
	@CacheLookup	
	public WebElement usernameInput;
	
	/** The password input. */
	@FindBy(how=How.CSS , using="input[id='password']")
	@CacheLookup	
	public WebElement passwordInput;
	
	
	/** The log in button. */
	@FindBy(how=How.CSS , using="button[class='btn btn-primary signupBtn'][type='submit']")
	@CacheLookup	
	public WebElement logInButton;
	
	
	@FindBy(how=How.CSS , using="a[href='/work/signup']")
	@CacheLookup	
	public WebElement signUpLink;
	
	@FindBy(how=How.CSS , using="input[id='remember_me']")
	@CacheLookup	
	public WebElement rememberMeCheckBox;
	
	@FindBy(how=How.CSS , using="a[class*='password-retrieve']")
	@CacheLookup	
	public WebElement forgotPasswordLink;
	
	
	/**
	 * Instantiates a new login page.
	 *
	 * @param webDriver the web driver
	 */
	public LoginPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();		

		
	}
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,2);
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"Login page is not loaded");
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		webDriver.get(url);
	}
	
	/**
	 * Enter username.
	 */
	public void enterUsername()
	{
		usernameInput.clear();
		usernameInput.sendKeys("adarsha+dev@hackerrank.com");
		
	}
	
	/**
	 * Enter password.
	 */
	public void enterPassword()
	{
		passwordInput.clear();
		passwordInput.sendKeys("ADAshe89");
	}
	public void enterUsername(String username)
	{
		usernameInput.clear();
		usernameInput.sendKeys(username);
		
	}
	public void enterPassword(String password)
	{
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}
	
	/**
	 * 
	 * Perform default login.
	 *
	 * @return the domains page
	 */
	public TestsPage performDefaultLogin()
	{
		enterUsername();
		enterPassword();
		webDriverUtilFunctions.click(rememberMeCheckBox);
		TestsPage testsPage=(TestsPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.TestsPage");
		logInButton.click();
		testsPage.isLoaded();
		return testsPage;
	}
	public TestsPage loginWithCredentials(String username, String password) {

		enterUsername(username);
		enterPassword(password);
		webDriverUtilFunctions.click(rememberMeCheckBox);
		TestsPage testsPage=(TestsPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.TestsPage");
		logInButton.click();
		testsPage.isLoaded();
		return testsPage;
	
	}
}
