package net.project.pageClasses.HackerRankPageClasses;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class ChallengePage.
 */
public class ChallengePage extends LoadableComponent<ChallengePage>{

	/** The url. */
	public  String url="https://www.hackerrank.com/challenges/";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	
	/**
	 * Instantiates a new challenge page.
	 *
	 * @param webDriver the web driver
	 */
	public ChallengePage(WebDriver webDriver) {
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
		webDriverUtilFunctions.fluentwaitForPageLoadUsingStartingPatternOfUrl(webDriver, 20, url);
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 20);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 20);
		AppLogger.assertLogTrue(webDriverUtilFunctions.getCurrentUrl(webDriver).startsWith(url),"Challenges page is not loaded");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

}
