package net.project.webDriverUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrokenLinksFinder {

    private static int statusCode;  
  
    public  List<WebElement> findBrokenLinks(WebDriver webDriver) throws IOException {  
       // Initialize web driver     
       //Maximize browser window  
    	List<WebElement> brokenLinks=new ArrayList<WebElement>();
    	webDriver.manage().window().maximize(); 
       //Set  timeout   
    	webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
// Get all links web driver  
 List<WebElement> links = webDriver.findElements(By.tagName("a"));  
  
        for (int i = 0; i < links.size(); i++) {  
//remove null and empty links  
if (!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))) {  
   
 if (links.get(i).getAttribute("href").contains("http")) {  
 // Find HTTP Status-Code  
statusCode= getResponseCode(links.get(i).getAttribute("href").trim());  
// Check broken link  
if (statusCode== 404) {  
System.out.println("Broken of Link# "+i+" "+links.get(i).getAttribute("href"));  
brokenLinks.add(links.get(i));                
}  
                }  
  
            }  
        }  
        return brokenLinks;
    }  
  
public static int getResponseCode(String urlString) throws MalformedURLException, IOException {         
        URL u = new URL(urlString);  
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();  
        huc.setRequestMethod("GET");  
        huc.connect();  
        return huc.getResponseCode();  
  }  

public static void main(String[] args) throws Exception {
	  // TODO Auto-generated method stub
	   ProxyServer server = new ProxyServer(8105);
	         server.start();
	         server.setCaptureHeaders(true);
	        
	         server.setCaptureContent(true);
	         server.newHar("test");
	         DesiredCapabilities capabilities = new DesiredCapabilities();
	         Proxy proxy = server.seleniumProxy();
	         FirefoxProfile profile = new FirefoxProfile();
	         profile.setAcceptUntrustedCertificates(false);
	         profile.setAssumeUntrustedCertificateIssuer(false);
	         profile.setPreference("network.proxy.http", "localhost");
	         profile.setPreference("network.proxy.http_port", 8105);
	         profile.setPreference("network.proxy.ssl", "localhost");
	         profile.setPreference("network.proxy.ssl_port", 8105);
	         profile.setPreference("network.proxy.type", 4);
	         profile.setPreference("network.proxy.no_proxies_on", "");
	         
	         capabilities.setCapability(FirefoxDriver.PROFILE,profile);
	         capabilities.setCapability(CapabilityType.PROXY, proxy);
	         WebDriver driver = new FirefoxDriver(capabilities);
	         driver.get("http://localhost:4545/paper/9PEzwfv3");
	         Har har1 = server.getHar();
	         }
}
