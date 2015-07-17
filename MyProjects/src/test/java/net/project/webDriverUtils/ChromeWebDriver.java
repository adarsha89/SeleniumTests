package net.project.webDriverUtils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeWebDriver implements BrowserSpecificWebDriverCapabilities{

	@Override
	public WebDriver getDefaultWebDriver() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		// TODO Auto-generated method stub
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability("databaseEnabled", true);
		capability.setCapability("locationContextEnabled", true);
		capability.setCapability("applicationCacheEnabled", true);
		capability.setCapability("browserConnectionEnabled", true);
		capability.setCapability("webStorageEnabled", true);
		capability.setCapability("acceptSslCerts", true);
		capability.setJavascriptEnabled(true);
		capability.setCapability("nativeEvents", true);
		String osString=System.getProperty("os.name");
		if(osString.startsWith("MAC"))
		{
			capability.setPlatform(Platform.MAC);
		}
		ChromeDriver chromeWebDriver = new ChromeDriver(capability);	
		chromeWebDriver.manage().window().maximize();
		return chromeWebDriver;
	}


	@Override
	public WebDriver getRemoteWebDriver() {
		// TODO Auto-generated method stub
		return null;
	}

}
