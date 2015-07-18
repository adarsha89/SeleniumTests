package net.project.webDriverUtils;

import java.net.MalformedURLException;
import java.net.URL;

import net.project.loggers.AppLogger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;

public class WebDriverFactory {
	FirefoxWebDriver firefoxWebDriver=null;
	ChromeWebDriver chromeWebDriver=null;
	SafariWebDriver safariWebDriver=null;

	/*public WebDriver getWebDriver(String browser)
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
		}if(browser.toLowerCase().equals("phantomjs"))
		{
			webDriver=new PhantomJSWebDriver().getDefaultWebDriver();
		}
		
		return webDriver;
	}*/
	public WebDriver getWebDriver(String browser,@Optional String browserVersion,@Optional String oS,@Optional String oSVersion,@Optional String resolution)
	{
		if(browserVersion==null || oS==null || oSVersion==null || resolution==null)
		{
			if(browser.startsWith("Remote"))
			{
				WebDriver webDriver = null;
				DesiredCapabilities caps = new DesiredCapabilities();
			    String brow=browser.split("Remote")[1];
			    System.out.println(brow);
			    if(brow.toLowerCase().equals("firefox"))
				{
					caps.setBrowserName("firefox");
				}
				if(brow.toLowerCase().equals("chrome"))
				{
					caps.setBrowserName("chrome");
				}
				if(brow.toLowerCase().equals("safari"))
				{
					caps.setBrowserName("safari");
				}if(brow.toLowerCase().equals("phantomjs"))
				{
					caps.setBrowserName("phantomjs");
				}
			    	caps.setPlatform(Platform.fromString(oS));
			try {
				webDriver = new RemoteWebDriver(new URL("http://localhost:5556/wd/hub"), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				AppLogger.logError("Not able to start webdriver remotely");
			}
		    return webDriver;
			}
			
			
			
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
			}if(browser.toLowerCase().equals("phantomjs"))
			{
				webDriver=new PhantomJSWebDriver().getDefaultWebDriver();
			}
			
			return webDriver;
		}
		 
		else{
			WebDriver webDriver = null;
			DesiredCapabilities caps = new DesiredCapabilities();
		    
		    caps.setCapability("browser", browser);
		    if(browserVersion!=null)
		    	{
		    	caps.setCapability("browser_version", browserVersion);
		    	}
		    	caps.setCapability("os", oS);
		    if(oSVersion!=null)
		    	{
		    		caps.setCapability("os_version", oSVersion);
		    	}
		    if(resolution!=null)
	    	{
	    		caps.setCapability("resolution", resolution);
	    	}
		    
		    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
		    caps.setCapability("browserstack.debug", "true");
		    //caps.setCapability("browserstack.local", "true");
		    caps.setCapability("acceptSslCerts", "true");
		    caps.setCapability("project","SeleniumTests");
		    caps.setCapability("build","1.0");
		    caps.setCapability("name","Trial");
		    try {
				webDriver = new RemoteWebDriver(new URL(new WebDriverUtilFunctions().URL), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				AppLogger.logError("Not able to start webdriver remotely");
			}
		    return webDriver;
		}
		
	}
}
