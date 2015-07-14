package net.project.testClasses.HackerrankTestCases;

import java.io.IOException;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TrialTest {

	@Test
	public void phantomJSTask()
	{
		WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
		WebDriver webDriver=webDriverUtilFunctions.setupTest("PhantomJS");
		webDriver.get("https://www.hackerrank.com/");
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 10);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 10);
		try {
			webDriverUtilFunctions.getScreenShot(webDriver, "Hi", "Hi", "just");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppLogger.logInfo(webDriverUtilFunctions.getWebElementByCss(webDriver, "a[href='/login']", 5).getText());
		webDriverUtilFunctions.fluentwaitForPageLoadUsingUrl(webDriver, 5, "https://www.hackerrank.com/");
		webDriverUtilFunctions.getWebElementByCss(webDriver, "a[href='/login']", 5).click();
		webDriverUtilFunctions.fluentwaitForPageLoadUsingUrl(webDriver, 5, "https://www.hackerrank.com/login");
		webDriverUtilFunctions.waitForPageToLoad(webDriver, 10);
		webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 10);
		try {
			webDriverUtilFunctions.getScreenShot(webDriver, "Hi", "Hi", "just1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
