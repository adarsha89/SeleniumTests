/*
 * 
 */
package net.project.testClasses;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestWebDriverShutdown.
 */
public class TestWebDriverShutdown {

	/** The web driver. */
	final WebDriver webDriver;
	final WebDriverUtilFunctions webDriverUtilFunctions;

	/**
	 * Instantiates a new test web driver shutdown.
	 *
	 * @param webDriver the web driver
	 */
	
	public  TestWebDriverShutdown(WebDriver webDriver) {
		this.webDriver=webDriver;
		webDriverUtilFunctions=new WebDriverUtilFunctions();
	}
	
	/**
	 * Driver shutdown.
	 */
	@Test(alwaysRun=false , priority=60000 ,description="driverShutdown: To shutdown webdriver", testName="driverShutdown")
	public void driverShutdown()
	{
		webDriverUtilFunctions.closeAndQuitWebDriver(webDriver);
		AppLogger.logInfo("Finished shutting down the web driver: " + webDriver.getClass().getName());
	}
	@AfterMethod
	public void shutDownAndQuit()
	{
		Reporter.setEscapeHtml(true);
	}
	
	/**
	 * Close and quit.
	 */
	
	
	
}
