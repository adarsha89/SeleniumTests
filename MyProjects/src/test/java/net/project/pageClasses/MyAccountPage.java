/*
 * 
 */
package net.project.pageClasses;

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
 * The Class MyAccountPage.
 */
public class MyAccountPage extends LoadableComponent<MyAccountPage> implements CommonPageInterface {

	/** The basic layout page. */
	public BasicLayoutPage basicLayoutPage=null;
	
	/** The web driver. */
	public WebDriver webDriver=null;;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions=null;
	
	/** The url. */
	public String url="http://store.demoqa.com/products-page/your-account/";
	
	/** The username field. */
	@FindBy(how=How.CSS , using="input[id='log']")
	@CacheLookup
	public WebElement usernameField;
	
	/** The password field. */
	@FindBy(how=How.CSS , using="input[id='pwd']")
	@CacheLookup
	public WebElement passwordField;
	
	/** The submit button. */
	@FindBy(how=How.CSS , using="input[id='login']")
	@CacheLookup
	public WebElement submitButton;
	
	/**
	 * Instantiates a new my account page.
	 *
	 * @param webDriver the web driver
	 */
	public MyAccountPage(WebDriver webDriver)
	{		
		this.webDriver=webDriver;
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		basicLayoutPage=new BasicLayoutPage(webDriver);	
		PageFactory.initElements(webDriver,this);	
	}
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub	
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 5);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 10);
		AppLogger.assertLogEquals(webDriver.getCurrentUrl(),url,"MyAccount page is not loaded");
		}
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub
		basicLayoutPage.goToMyAccount();
		
	}
	
	/**
	 * Enter username.
	 *
	 * @param username the username
	 */
	public void enterUsername(String username)
	{
		
		usernameField.sendKeys(username);
	}
	
	/**
	 * Enter password.
	 *
	 * @param password the password
	 */
	public void enterPassword(String password)
	{
		passwordField.click();
		passwordField.sendKeys(password);
		
	}
	
	/**
	 * Submit.
	 */
	public void submit()
	{
		submitButton.click();
	}
	
	/**
	 * Go to my account.	 *
	 *
	 * @return true, if successful
	 */
	
	
	/**
	 * Check my account link.
	 *
	 * @param webDriver the web driver
	 * @return true, if successful
	 */
	public boolean checkMyAccountLink()
	{
		return basicLayoutPage.checkMyAccountLink();
	}

	/**
	 * Check whether its loaded.
	 */
	public void checkWhetherItsLoaded() {
		// TODO Auto-generated method stub
		
		AppLogger.assertLogTrue(webDriver.getCurrentUrl().startsWith("http://store.demoqa.com/products-page/your-account"), "My account page is not loaded");
		//AssertJUnit.assertTrue(webDriver.getCurrentUrl().startsWith("http://store.demoqa.com/products-page/your-account"));	
	}
	
	
}
