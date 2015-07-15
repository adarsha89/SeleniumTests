package net.project.webDriverUtils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJSWebDriver implements BrowserSpecificWebDriverCapabilities {

	@Override
	public WebDriver getDefaultWebDriver() {
		
		DesiredCapabilities desiredCapabilities= DesiredCapabilities.phantomjs();
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("phantomjs.binary.path"));
		desiredCapabilities.setCapability("phantomjs.binary.path", System.getProperty("phantomjs.binary.path"));
		desiredCapabilities.setCapability("databaseEnabled", true);
		desiredCapabilities.setCapability("locationContextEnabled", true);
		desiredCapabilities.setCapability("applicationCacheEnabled", true);
		desiredCapabilities.setCapability("browserConnectionEnabled", true);
		desiredCapabilities.setCapability("webStorageEnabled", true);
		desiredCapabilities.setCapability("acceptSslCerts", true);
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setCapability("nativeEvents", true);
		String osString=System.getProperty("os.name");
		if(osString.startsWith("MAC"))
		{
			desiredCapabilities.setPlatform(Platform.MAC);
		}
		PhantomJSDriver phantomJSDriver=new PhantomJSDriver(desiredCapabilities);
		phantomJSDriver.manage().window().maximize();
		return phantomJSDriver;
	
	}

}
