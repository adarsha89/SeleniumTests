import java.net.MalformedURLException;
import java.net.URL;

import net.project.webDriverUtils.BrowserSpecificWebDriverCapabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class RemoteSample {

	public static void main(String[] args) {

	    
	    DesiredCapabilities caps = DesiredCapabilities.chrome();
	    caps.setPlatform(Platform.WINDOWS);  
	    //caps.setVersion("11.0");
	    
	    //caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
	    WebDriver driver=null;
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:5556/wd/hub"), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    driver.get("http://www.google.com/");
	    driver.manage().window().maximize();
	    System.out.println(driver.getTitle());
	    driver.quit();

	  
	}
}
