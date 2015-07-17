import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class JavaSample {

  public static final String USERNAME = "hackerrank";
  public static final String AUTOMATE_KEY = "nt6JzvpM3fqCo3c5cRMA";
  public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {
    
    DesiredCapabilities caps = new DesiredCapabilities();
    
    caps.setCapability("browser", "Firefox");
    caps.setCapability("browser_version", "39.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "7");
    caps.setCapability("resolution", "1024x768");
    
    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
    caps.setCapability("browserstack.debug", "true");
    caps.setCapability("browserstack.local", "true");
    caps.setCapability("acceptSslCerts", "true");
    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("https://staging.vpc.hackerrank.com/");
    driver.manage().window().maximize();
    System.out.println(driver.getTitle());
    driver.quit();

  }
}