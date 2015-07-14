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
 * The Class ToolsQAHomePage.
 */
public class ToolsQAHomePage extends LoadableComponent<ToolsQAHomePage> implements CommonPageInterface{
	
	/** The basic layout page. */
	public BasicLayoutPage basicLayoutPage;	
	
	/** The web driver. */
	public WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;	
	
	/** The url. */
	public String url="http://store.demoqa.com/";	
	
	/** The checkout link. */
	@FindBy(how=How.CSS , using="a[title='Checkout']")
	@CacheLookup
	public WebElement checkoutLink;
	
	/**
	 * Instantiates a new tools qa home page.
	 *
	 * @param webDriver the web driver
	 */
	public ToolsQAHomePage(WebDriver webDriver)
	{	
		this.webDriver=webDriver;
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();	
		basicLayoutPage=new BasicLayoutPage(webDriver);		
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
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 5);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 10);
		AppLogger.assertLogEquals(webDriver.getCurrentUrl(),url,"ToolsQA page is not loaded");
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub
		basicLayoutPage.load();
	}

	/**
	 * Check my account link.
	 *
	 * @return true, if successful
	 */
	public boolean checkMyAccountLink() {
		// TODO Auto-generated method stub
		return this.basicLayoutPage.myAccountLink.isDisplayed();
	}


	
	

}
