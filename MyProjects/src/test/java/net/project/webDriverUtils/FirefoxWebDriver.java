package net.project.webDriverUtils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxWebDriver implements BrowserSpecificWebDriverCapabilities {

	@Override
	public WebDriver getDefaultWebDriver() {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
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
		FirefoxDriver firefoxWebDriver = new FirefoxDriver(capability);
		firefoxWebDriver.manage().window().maximize();
		return firefoxWebDriver;
	}

}
