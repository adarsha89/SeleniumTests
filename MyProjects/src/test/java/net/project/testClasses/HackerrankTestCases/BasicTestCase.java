package net.project.testClasses.HackerrankTestCases;

import java.io.IOException;
import java.util.List;

import net.project.loggers.AppLogger;
import net.project.pageClasses.HackerRankPageClasses.ChallengePage;
import net.project.pageClasses.HackerRankPageClasses.DomainsPage;
import net.project.pageClasses.HackerRankPageClasses.LoginPage;
import net.project.pageClasses.HackerRankPageClasses.SubDomainsPage;
import net.project.pageClasses.HackerRankPageClasses.WelcomePage;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BasicTestCase {
WebDriver webDriver;
WebDriverUtilFunctions webDriverUtilFunctions;
@Parameters("browser")
public BasicTestCase(String browser) {
	webDriverUtilFunctions=new WebDriverUtilFunctions();
	this.webDriver=webDriverUtilFunctions.setupTest(browser);
}

	@Test(testName="smokeTest", priority=1)
	public void firstTask()
	{
		WebElement reference=null;
		WelcomePage welcomePage=new WelcomePage(webDriver);
		LoginPage loginPage=welcomePage.goToLoginPage();
		DomainsPage domainsPage=loginPage.performDefaultLogin();
		SubDomainsPage  subDomainsPage=domainsPage.chooseDomainByDomainName("algorithms");
		ChallengePage challengePage= subDomainsPage.chooseChallengeByName("solve-me-first");
		
		List<WebElement> listOfWebElement= webDriverUtilFunctions.waitForWebElements(webDriver, "span[class='cm-variable']", 5);
		Actions action =null;
		action = new Actions(webDriver);
		for(WebElement webElement:listOfWebElement)
		{
			if(webElement.getText().equals("b"))
			{
				reference=webElement;
				action.moveToElement(reference).click().perform();
				action.sendKeys(Keys.ARROW_RIGHT).perform();
				action.sendKeys("+2-").perform();	
			}
			
		}
		
			
		reference=webDriverUtilFunctions.getWebElementByCss(webDriver,"button[data-analytics='Compile and Test']",15);
		action.moveToElement(reference).click().perform();
		reference=webDriverUtilFunctions.getWebElementByCss(webDriver, "p[class='status']>span", 20);
		action.moveToElement(reference).click().perform();
		AppLogger.logInfo("Class of webelement is: "+reference.getAttribute("class"));
		webDriver.manage().deleteAllCookies();
		webDriverUtilFunctions.click(webDriverUtilFunctions.getWebElementByCss(webDriver,"span[class='mmR']",15));
		webDriverUtilFunctions.click(webDriverUtilFunctions.getWebElementByCss(webDriver,"a[class='logout-button']",15));
	}

	@Test(testName="smokeTest2" , priority=2)
	public void secondTask()
	{
		WebElement reference=null;
		WelcomePage welcomePage=new WelcomePage(webDriver);
		LoginPage loginPage=welcomePage.goToLoginPage();
		DomainsPage domainsPage=loginPage.performDefaultLogin();
		SubDomainsPage  subDomainsPage=domainsPage.chooseDomainByDomainName("algorithms");
		ChallengePage challengePage= subDomainsPage.chooseChallengeByName("solve-me-first");
		
		List<WebElement> listOfWebElement= webDriverUtilFunctions.waitForWebElements(webDriver, "span[class='cm-operator']", 5);
		Actions action =null;
		action = new Actions(webDriver);
		for(WebElement webElement:listOfWebElement)
		{
			if(webElement.getText().equals("+"))
			{
				reference=webElement;
				action.moveToElement(reference).click().perform();
				action.sendKeys(Keys.ARROW_RIGHT).perform();
				action.sendKeys("+2-").perform();	
			}
			
		}
		
			
		reference=webDriverUtilFunctions.getWebElementByCss(webDriver,"button[data-analytics='Compile and Test']",15);
		action.moveToElement(reference).click().perform();
		reference=webDriverUtilFunctions.getWebElementByCss(webDriver, "p[class='status']>span", 20);
		action.moveToElement(reference).click().perform();
		AppLogger.logInfo("Class of webelement is: "+reference.getAttribute("class"));
		webDriverUtilFunctions.closeAndQuitWebDriver(webDriver);
	}
	

}
