package net.project.pageClasses;

import net.project.loggers.AppLogger;

import org.openqa.selenium.WebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommonPageInterface.
 */
public interface CommonPageInterface {

	/**
	 * Verify or set up of tools qa page.
	 *
	 * @param webDriver the web driver
	 * @return the tools qa home page
	 */
	public default ToolsQAHomePage  verifyOrSetUpOfToolsQAPage(WebDriver webDriver)
	{
		if(webDriver.getTitle().equals("ONLINE STORE | Toolsqa Dummy Test site"))
		{
			return null;
		}
		else
		{
			ToolsQAHomePage toolsQAHomePage=new ToolsQAHomePage(webDriver);
			toolsQAHomePage.load();
			toolsQAHomePage.isLoaded();
			return toolsQAHomePage;
		}
			
		
			
	}
	
	/**
	 * Go to my account.
	 *
	 * @param basicLayoutPage the basic layout page
	 * @return the my account page
	 */
	public default MyAccountPage goToMyAccount(BasicLayoutPage basicLayoutPage)
	{
		MyAccountPage myAccountPage= basicLayoutPage.goToMyAccount();
		if(myAccountPage==null)
		{
			AppLogger.logInfo("Not able to initialize my Account page");
			
		}
		return myAccountPage;
	}
}
