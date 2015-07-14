package net.project.pageClasses.HackerRankPageClasses;

import java.util.List;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class SubDomainsPage.
 */
public class SubDomainsPage extends LoadableComponent<SubDomainsPage> {

	/** The url. */
	public  String url="https://www.hackerrank.com/domains/*/warmup";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	/** The challenge identifier. */
	public String challengeIdentifier="a[data-attr6='contest-challenge']";
	
	/** The sub domain identifier. */
	public String subDomainIdentifier="a[data-analytics='PracticeChapterList']";
	/**
	 * Instantiates a new sub domains page.
	 *
	 * @param webDriver the web driver
	 */
	public SubDomainsPage(WebDriver webDriver) {
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
		webDriverUtilFunctions.fluentwaitForPageLoadUsingRegexOfUrl(webDriver, 20, url);
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 20);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 20);
		AppLogger.assertLogTrue(webDriverUtilFunctions.getCurrentUrl(webDriver).matches(url),"SubDomains page is not loaded");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets the first challenge.
	 *
	 * @return the first challenge
	 */
	public List<WebElement> getListOfChallenges()
	{
		
		List<WebElement> listOfChallenges=webDriverUtilFunctions.waitForWebElements(webDriver, challengeIdentifier,10 );
		
		return listOfChallenges;
	}
	
	/**
	 * Choose sub domain by name.
	 *
	 * @param subDomainName the sub domain name
	 */
	public void chooseSubDomainByName(String subDomainName)
	{
		WebElement requiredSubDomain=null;
		String identifier=challengeIdentifier+"[data-attr1="+"'"+subDomainName+"'"+"]";
		requiredSubDomain=webDriverUtilFunctions.getWebElementByCss(webDriver, identifier, 5);
		webDriverUtilFunctions.click(requiredSubDomain);
	}
	
	/**
	 * Gets the list of sub domains.
	 *
	 * @return the list of sub domains
	 */
	public List<WebElement> getListOfSubDomains()
	{
		
		List<WebElement> listOfSubDomains=webDriverUtilFunctions.waitForWebElements(webDriver,  subDomainIdentifier,5);
		
		return listOfSubDomains;
	}
	
	/**
	 * Choose challenge by name.
	 *
	 * @param challengeName the challenge name
	 * @return the challenge page
	 */
	public ChallengePage chooseChallengeByName(String challengeName)
	{
		ChallengePage challengePage=null;
		WebElement webElement=null;
		String identifier=challengeIdentifier+"[data-attr1="+"'"+challengeName+"'"+"]";
		AppLogger.logInfo("Identifier is : "+identifier);
		webElement=webDriverUtilFunctions.getWebElementByCss(webDriver, identifier, 15);
		challengePage=new ChallengePage(webDriver);
		webDriverUtilFunctions.click(webElement);
		challengePage.isLoaded();
		return challengePage;				
					
	}	
}
