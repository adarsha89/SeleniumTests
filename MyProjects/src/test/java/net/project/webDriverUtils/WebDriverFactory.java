package net.project.webDriverUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

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

	public WebDriver getWebDriver(String browser,@Optional String browserVersion,@Optional String oS,@Optional String oSVersion,@Optional String resolution,@Optional String nameOfTest)
	{
		
		WebDriver webDriver=null;
		if(browserVersion==null)
		{
			
			if(browser.startsWith("Remote"))
			{
			    String brow=browser.split("Remote")[1];
			    if(brow.toLowerCase().equals("firefox"))
				{
					webDriver=new FirefoxWebDriver().getRemoteWebDriver();
				}
				if(brow.toLowerCase().equals("chrome"))
				{
					webDriver=new ChromeWebDriver().getRemoteWebDriver();
				}
				if(brow.toLowerCase().equals("safari"))
				{
					webDriver=new SafariWebDriver().getRemoteWebDriver();
				}if(brow.toLowerCase().equals("phantomjs"))
				{
					webDriver=new PhantomJSWebDriver().getRemoteWebDriver();
				}
			
			}
			
			
			else
			{

				
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
				
				
			}
		}
		 
		else{
			 webDriver = null;
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
		    Calendar now = Calendar.getInstance();
		    caps.setCapability("project", "SeleniumTests");
		    caps.setCapability("build",""+now.get(Calendar.YEAR)+now.get(Calendar.MONTH)+now.get(Calendar.DAY_OF_MONTH)+now.get(Calendar.HOUR_OF_DAY));
		    caps.setCapability("name",""+now.get(Calendar.YEAR)+now.get(Calendar.MONTH)+now.get(Calendar.DAY_OF_MONTH)+now.get(Calendar.HOUR_OF_DAY)+now.get(Calendar.MINUTE)+now.get(Calendar.SECOND)+now.get(Calendar.MILLISECOND));
		    try {
				webDriver = new RemoteWebDriver(new URL(new WebDriverUtilFunctions().URL), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				AppLogger.logError("Not able to start webdriver remotely");
			}
		}
		return webDriver;
	}
}
