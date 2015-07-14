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
 * The Class WelcomePage.
 */
public class WelcomePage extends LoadableComponent<WelcomePage> {

	/** The url. */
	public  String url="https://www.hackerrank.com/";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	/** The title of the page. */
	@FindBy(how=How.CSS , using="title")
	@CacheLookup
	public WebElement titleOfThePage;	
	
	/** The logo. */
	@FindBy(how=How.CSS , using="a[class='hrw-logo static-logo pull-left']>svg>image")
	@CacheLookup	
	public WebElement logo;
	
	/** The first menu item. */
	@FindBy(how=How.CSS , using="span[class='static-nav static-links']>a[class='static-link active']")
	@CacheLookup		
	public WebElement firstMenuItem;
	
	/** The second menu item. */
	@FindBy(how=How.CSS , using="span[class='static-nav static-links']>span[class='work-navgroup static-link']>a")
	@CacheLookup		
	public WebElement secondMenuItem;
	
	/** The third menu item. */
	@FindBy(how=How.CSS , using="span[class='static-nav static-links']>a[class='static-link']")
	@CacheLookup		
	public WebElement thirdMenuItem;
		
	/** The login button. */
	@FindBy(how=How.CSS , using="a[href='/login']")
	@CacheLookup		
	public WebElement loginButton;
	
	/** The signup button. */
	@FindBy(how=How.CSS , using="a[href='/signup']")
	@CacheLookup		
	public WebElement signupButton;
	
	
	/** The take challenge button. */
	@FindBy(how=How.CSS , using="a[href='/login?utm_medium=header&utm_source=hr-homepage']")
	@CacheLookup		
	public WebElement takeChallengeButton;	
	
	/**
	 * Instantiates a new welcome page.
	 *
	 * @param webDriver the web driver
	 */
	public WelcomePage(WebDriver webDriver) {
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();		
		PageFactory.initElements(webDriver, this);
		load();
		isLoaded();
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
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"Welcome page is not loaded");
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
	 * Go to login page.
	 *
	 * @return the login page
	 */
	public LoginPage goToLoginPage()
	{
		LoginPage loginPage=new LoginPage(webDriver);
		loginButton.click();
		loginPage.isLoaded();
		return loginPage;
	}

}
