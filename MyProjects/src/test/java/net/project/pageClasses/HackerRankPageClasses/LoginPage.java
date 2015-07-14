package net.project.pageClasses.HackerRankPageClasses;



import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginPage.
 */
public class LoginPage extends LoadableComponent<LoginPage>{

	
	/** The url. */
	public  String url="https://www.hackerrank.com/login";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	/** The title of the page. */
	@FindBy(how=How.CSS , using="title")
	@CacheLookup
	public WebElement titleOfThePage;	
	
	/** The login button. */
	@FindBy(how=How.CSS , using="a[href='/login']")
	@CacheLookup	
	public WebElement loginButton;
	
	/** The sign up button. */
	@FindBy(how=How.CSS , using="a[href='/signup']")
	@CacheLookup	
	public WebElement signUpButton;
	
	/** The first name input. */
	@FindBy(how=How.CSS , using="input[id='first_name']")
	@CacheLookup	
	public WebElement firstNameInput;
	
	/** The last name input. */
	@FindBy(how=How.CSS , using="input[id='last_name']")
	@CacheLookup	
	public WebElement lastNameInput;
	
	/** The email input. */
	@FindBy(how=How.CSS , using="input[id='email']")
	@CacheLookup	
	public WebElement emailInput;
	
	/** The username input. */
	@FindBy(how=How.CSS , using="input[id='username']")
	@CacheLookup	
	public WebElement usernameInput;
	
	/** The password input. */
	@FindBy(how=How.CSS , using="input[id='password']")
	@CacheLookup	
	public WebElement passwordInput;
	
	
	/** The create an account button. */
	@FindBy(how=How.CSS , using="div[class='homepage_signupgroup--legacy']>p>button[data-analytics='SignupPassword']")
	@CacheLookup	
	public WebElement createAnAccountButton;
	
	/** The login input. */
	@FindBy(how=How.CSS , using="input[id='login']")
	@CacheLookup
	public WebElement loginInput;
	
	
	/** The create an account using facebook button. */
	@FindBy(how=How.CSS , using="div[id='social-signup']>div>a[data-analytics='SignupFacebook']")
	@CacheLookup	
	public WebElement createAnAccountUsingFacebookButton;
	
	/** The create an account using google button. */
	@FindBy(how=How.CSS , using="div[id='social-signup']>div>a[data-analytics='SignupGoogle']")
	@CacheLookup	
	public WebElement createAnAccountUsingGoogleButton;
	
	/** The create an account using github button. */
	@FindBy(how=How.CSS , using="div[id='social-signup']>div>a[data-analytics='SignupGithub']")
	@CacheLookup	
	public WebElement createAnAccountUsingGithubButton;
	
	/** The log in button. */
	@FindBy(how=How.CSS , using="div[class='homepage_signupgroup--legacy']>p>button[data-analytics='LoginPassword']")
	@CacheLookup	
	public WebElement logInButton;
	
	/**
	 * Instantiates a new login page.
	 *
	 * @param webDriver the web driver
	 */
	public LoginPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();		
		PageFactory.initElements(webDriver, this);
	}
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub
		webDriverUtilFunctions.fluentwaitForPageLoadUsingUrl(webDriver, 20, url);
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 20);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 20);
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
		loginInput.sendKeys("qaanalyst1989");
	}
	
	/**
	 * Enter password.
	 */
	public void enterPassword()
	{
		passwordInput.sendKeys("QAADAshe89");
	}
	
	/**
	 * Perform default login.
	 *
	 * @return the domains page
	 */
	public DomainsPage performDefaultLogin()
	{
		
		enterUsername();
		enterPassword();
		DomainsPage domainsPage=new DomainsPage(webDriver);
		logInButton.click();
		domainsPage.isLoaded();
		return domainsPage;
	}
}
