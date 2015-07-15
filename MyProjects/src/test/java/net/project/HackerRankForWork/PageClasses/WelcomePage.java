package net.project.HackerRankForWork.PageClasses;

import net.project.loggers.AppLogger;
import net.project.HackerRankForWork.PageClasses.LoginPage;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class WelcomePage.
 */
public class WelcomePage extends LoadableComponent<WelcomePage> implements CommonPageInterface{


	/** The url. */
	public  String url="https://www.hackerrank.com/work";
	
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
	@FindBy(how=How.CSS , using="a[href='/work/login']")
	@CacheLookup		
	public WebElement loginButton;
	
	/** The signup button. */
	@FindBy(how=How.CSS , using="a[href='/signup']")
	@CacheLookup		
	public WebElement signupButton;
	
	/** The signup button. */
	@FindBy(how=How.CSS , using="a[href='http://www.capterra.com/recruiting-software/reviews/143549/HackerRank/HackerRank/new']")
	@CacheLookup		
	public WebElement reviewLink;
	
	/** The take challenge button. */
	@FindBy(how=How.CSS , using="a[href='/login?utm_medium=header&utm_source=hr-homepage']")
	@CacheLookup		
	public WebElement takeChallengeButton;	
	
	@FindBy(how=How.CSS , using="div[class='hero-content']>a")
	@CacheLookup		
	public WebElement freeTrialLink;	
	
	@FindBy(how=How.CSS , using="section[class='static-section fill-dark']>div[class='container']>a")
	@CacheLookup		
	public WebElement videoLink;
	
	
	@FindBy(how=How.CSS , using="div[id='habla_panel_div']")
	@CacheLookup		
	public WebElement liveHelpSection;
	
	@FindBy(how=How.CSS , using="div[id='habla_offline_message_div']>div>textarea[id='habla_name_input']")
	@CacheLookup		
	public WebElement offlineNameInput;
	
	@FindBy(how=How.CSS , using="div[id='habla_offline_message_div']>div>textarea[id='habla_offline_email_input']")
	@CacheLookup		
	public WebElement offlineEmailInput;
	
	@FindBy(how=How.CSS , using="div[id='habla_offline_message_div']>div>textarea[id='habla_offline_body_input']")
	@CacheLookup		
	public WebElement offlineBodyInput;
	
	
	@FindBy(how=How.CSS , using="div[id='habla_pre_chat_div']>div>textarea[id='habla_pre_chat_name_input']")
	@CacheLookup		
	public WebElement preChatNameInput;
	
	
	@FindBy(how=How.CSS , using="div[id='habla_pre_chat_div']>div>textarea[id='habla_pre_chat_email_input']")
	@CacheLookup		
	public WebElement preChatEmailInput;
	
	@FindBy(how=How.CSS , using="div[id='habla_pre_chat_div']>input[id='habla_pre_chat_submit_input']")
	@CacheLookup		
	public WebElement preChatSubmitInput;
	
	
	
	/**
	 * Instantiates a new welcome page.
	 *
	 * @param webDriver the web driver
	 */
	public WelcomePage(WebDriver webDriver) {
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();	
		load();
		isLoaded();
	}
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,1);
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
		LoginPage loginPage=(LoginPage) webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.LoginPage");
		webDriverUtilFunctions.click(loginButton);		
		loginPage.isLoaded();
		return loginPage;
	}


}
