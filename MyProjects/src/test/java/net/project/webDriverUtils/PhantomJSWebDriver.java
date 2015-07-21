package net.project.webDriverUtils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PhantomJSWebDriver implements BrowserSpecificWebDriverCapabilities {

	@Override
	public WebDriver getDefaultWebDriver() {
		
		DesiredCapabilities desiredCapabilities= DesiredCapabilities.phantomjs();
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,System.getProperty("phantomjs.binary.path"));
		desiredCapabilities.setCapability("acceptSslCerts", true);
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setCapability("nativeEvents", true);
		String osString=System.getProperty("os.name");
		if(osString.startsWith("MAC"))
		{
			desiredCapabilities.setPlatform(Platform.MAC);
		}
		WebDriver webDriver=null;
		try {
			webDriver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), desiredCapabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webDriver.manage().window().maximize();
		return webDriver;
	
	}

	@Override
	public WebDriver getRemoteWebDriver() {
		// TODO Auto-generated method stub
		return null;
	}

}
