package net.project.webDriverUtils;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	FirefoxWebDriver firefoxWebDriver=null;
	ChromeWebDriver chromeWebDriver=null;
	SafariWebDriver safariWebDriver=null;

	public WebDriver getWebDriver(String browser)
	{
		WebDriver webDriver=null;
		if(browser.toLowerCase().equals("firefox"))
		{
			webDriver=new FirefoxWebDriver().getDefaultWebDriver();
		}
		if(browser.toLowerCase().equals("chrome"))
		{
			webDriver=new ChromeWebDriver().getDefaultWebDriver();
		}
		if(browser.toLowerCase().equals("safari"))
		{
			webDriver=new SafariWebDriver().getDefaultWebDriver();
		}
		
		return webDriver;
	}
}
