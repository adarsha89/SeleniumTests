package net.project.webDriverUtils;

import java.net.MalformedURLException;
import java.net.URL;

import net.project.loggers.AppLogger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeWebDriver implements BrowserSpecificWebDriverCapabilities{

	@Override
	public WebDriver getDefaultWebDriver() {
				// TODO Auto-generated method stub
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setCapability("databaseEnabled", true);
		capability.setCapability("locationContextEnabled", true);
		capability.setCapability("applicationCacheEnabled", true);
		capability.setCapability("browserConnectionEnabled", true);
		capability.setCapability("webStorageEnabled", true);
		capability.setCapability("acceptSslCerts", true);
		capability.setJavascriptEnabled(true);
		capability.setCapability("nativeEvents", true);
		String osString=System.getProperty("os.name");
		if(System.getProperty("os.name").startsWith("MAC"))
		{
			capability.setPlatform(Platform.MAC);
		}
		else if(System.getProperty("os.name").startsWith("Linux"))
		{
			capability.setPlatform(Platform.LINUX);
		}
		else if(System.getProperty("os.name").startsWith("Windows"))
		{
			capability.setPlatform(Platform.WINDOWS);
		}
		ChromeDriver chromeDriver = new ChromeDriver(capability);	
		chromeDriver.manage().window().maximize();
		return chromeDriver;
	}


	@Override
	public WebDriver getRemoteWebDriver() {
		// TODO Auto-generated method stub
		DesiredCapabilities caps=new DesiredCapabilities();
		WebDriver webDriver=null;
		caps.setBrowserName("chrome");
		try {
			webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			AppLogger.logError("Not able to start webdriver remotely");
		}
		return webDriver;
	}

}
