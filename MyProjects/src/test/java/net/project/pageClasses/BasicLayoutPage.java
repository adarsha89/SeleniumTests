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
 * The Class BasicLayoutPage.
 */
public class BasicLayoutPage extends LoadableComponent<BasicLayoutPage> implements CommonPageInterface {
	
	
	
	
	
	
	/** The url. */
	public  String url="http://store.demoqa.com/";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	
	
	/** The my account link. */
	@FindBy(how=How.CSS , using="a[title='My Account']")
	@CacheLookup
	public WebElement myAccountLink;	
	
	/** The checkout link. */
	@FindBy(how=How.CSS , using="a[title='Checkout']")
	@CacheLookup
	public WebElement checkoutLink;
	
	/** The tools qa logo. */
	@FindBy(how=How.CSS , using="img[alt='home']")
	@CacheLookup
	public WebElement toolsQALogo;
	
	/** The home link. */
	@FindBy(how=How.CSS , using="a[href='http://store.demoqa.com/']")
	@CacheLookup
	public WebElement homeLink;
	
	/** The products category link. */
	@FindBy(how=How.CSS , using="li[id='menu-item-33a']>a[href='http://store.demoqa.com/products-page/product-category/']")
	@CacheLookup
	public WebElement productsCategoryLink;
	
	/** The all product link. */
	@FindBy(how=How.CSS , using="li[id='menu-item-16']>a[href='http://store.demoqa.com/products-page/product-category/']")
	@CacheLookup
	public WebElement allProductLink;

	/** The search box. */
	@FindBy(how=How.CSS , using="input[class='search']")
	@CacheLookup
	public WebElement searchBox;

	//private WebDriver webDriver;
	
	/**
	 * Instantiates a new basic layout page.
	 *
	 * @param webDriver the web driver
	 */
	public BasicLayoutPage(WebDriver webDriver)
	{		
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
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 5);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 10);
		AppLogger.assertLogEquals(webDriver.getCurrentUrl(),url,"Basic Page is not loaded");
		}


	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	public  void load() {
		// TODO Auto-generated method stub	
		webDriver.get(url);
	}
	
	/**
	 * Go to my account.
	 *
	 * @return the my account page
	 */
	public MyAccountPage goToMyAccount()
	{
		MyAccountPage myAccountPage=new MyAccountPage(webDriver);
		try
		{
			myAccountLink.click();	
		}catch(NullPointerException ex)
		{
			AppLogger.logError("Not able to find myaccount link");
		}
		myAccountPage.isLoaded();
		return myAccountPage;
	}
	
	
	/**
	 * Check my account link.
	 *
	 * @return true, if successful
	 */
	public boolean checkMyAccountLink()
	{
		return myAccountLink.isDisplayed();
	}
	
	/**
	 * Load tools qa home page.
	 *
	 * @return the tools qa home page
	 */
	public ToolsQAHomePage loadToolsQAHomePage()
	{
		return new ToolsQAHomePage(webDriver);
	}
	
}
