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
 * The Class DomainsPage.
 */
public class DomainsPage extends LoadableComponent<DomainsPage>{

	/** The url. */
	public  String url="https://www.hackerrank.com/domains";
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	/** The domain button identifier. */
	public String domainButtonIdentifier="a[data-analytics='DomainsPageSelectDomain']";
	
	/**
	 * Instantiates a new domains page.
	 *
	 * @param webDriver the web driver
	 */
	public DomainsPage(WebDriver webDriver) {
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
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"Domains page is not loaded");
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
	 * Choose domain by domain name.
	 *
	 * @param domainName the domain name
	 * @return the sub domains page
	 */
	public SubDomainsPage chooseDomainByDomainName(String domainName)
	{
		WebElement requiredDomain=null;
		
		String identifier=domainButtonIdentifier+"[data-attr1="+"'"+domainName+"'"+"]";
		requiredDomain=webDriverUtilFunctions.getWebElementByCss(webDriver, identifier, 5);
		SubDomainsPage subDomainsPage=new SubDomainsPage(webDriver);
		requiredDomain.click();
		return subDomainsPage;
	}
	public List<WebElement> getListOfDomains()
	{
		return webDriverUtilFunctions.waitForWebElements(webDriver, domainButtonIdentifier, 15);
	}
}
