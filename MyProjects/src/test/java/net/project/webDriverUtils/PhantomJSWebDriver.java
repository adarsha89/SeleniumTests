package net.project.webDriverUtils;

import java.net.MalformedURLException;
import java.net.URL;

import net.project.loggers.AppLogger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PhantomJSWebDriver implements BrowserSpecificWebDriverCapabilities {

	@Override
	public WebDriver getDefaultWebDriver() {
		
		DesiredCapabilities capability= DesiredCapabilities.phantomjs();
		capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,System.getProperty("phantomjs.binary.path"));
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
		PhantomJSDriver phantomJSDriver = new PhantomJSDriver(capability);	
		phantomJSDriver.manage().window().maximize();
		return phantomJSDriver;
	
	}

	@Override
	public WebDriver getRemoteWebDriver() {
		// TODO Auto-generated method stub
		DesiredCapabilities caps=new DesiredCapabilities();
		WebDriver webDriver=null;
		caps.setBrowserName("phantomjs");
		try {
			webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			AppLogger.logError("Not able to start webdriver remotely");
		}
		return webDriver;
	}

}
