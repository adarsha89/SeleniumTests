/*
 * 
 */
package net.project.webDriverUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.project.listeners.AppListener;
import net.project.loggers.AppLogger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Function;
import com.google.sitebricks.client.Web;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


// TODO: Auto-generated Javadoc
/**
 * The Class WebDriverUtilFunctions.
 */
@Configuration
public class WebDriverUtilFunctions {
	public static Map<String,ArrayList<String>> mapOfSuitesAndTestCases = new HashMap<String, ArrayList<String>>();
	EventFiringWebDriver eventFiringWebDriver;	
	AppListener projectListener=new AppListener();
	WebDriverFactory webDriverFactory=null;
	/** The scr file. */
	File scrFile=null;
	/** The base url. */
	//String baseURL="http://www.store.demoqa.com";
	public static final String USERNAME = "hackerrank";
	  public static final String AUTOMATE_KEY = "nt6JzvpM3fqCo3c5cRMA";
	  public static final String nodeURL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	
	/** The html unit driver. */
	  WebDriver ieWebDriver, chromeWebDriver , firefoxWebDriver, phantomJSDriver,safariWebDriver;
	
	/** The firefox remote web driver. */
	  WebDriver ieRemoteWebDriver, chromeRemoteWebDriver , firefoxRemoteWebDriver , phantomJSRemoteWebDriver;
	
	/**
	 * Setup test.
	 *
	 * @param browser the browser
	 * @return the web driver
	 */
	public EventFiringWebDriver setupTest(String browser)
	{
		WebDriver webDriver=null;
		webDriverFactory=new WebDriverFactory();
		webDriver=webDriverFactory.getWebDriver(browser);
		eventFiringWebDriver=new EventFiringWebDriver(webDriver);
		eventFiringWebDriver.register(projectListener);
		webDriver.manage().window().maximize();
		return eventFiringWebDriver;
	}
	
	/**
	 * Setup remote test.
	 *
	 * @param browser the browser
	 * @return the web driver
	 */
	
	public WebDriver setupRemoteTest(String browser)
	{
		WebDriver remotewebDriver=null;
		switch(browser)
		{
		case "Chrome":
			remotewebDriver= chromeRemoteWebDriver;
			break;
		case "IE":
			remotewebDriver= ieRemoteWebDriver;
			break;
		case "Firefox":
			remotewebDriver= firefoxRemoteWebDriver;
			break;
		case "PhantomJS":
			remotewebDriver=phantomJSRemoteWebDriver;
			break;
		}		
		System.out.println("Switch statement for remote");
		eventFiringWebDriver=new EventFiringWebDriver(remotewebDriver);
		eventFiringWebDriver.register(projectListener);
		remotewebDriver.manage().window().maximize();
		return remotewebDriver;
	}
	  
	static {
		//System.setProperty("webdriver.ie.driver", "src/test/resources/driverexefiles/IEDriverServer");
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		System.setProperty("phantomjs.binary.path", "/usr/bin/phantomjs");
		//System.setProperty("webdriver.firefox.port", "8082");
		//ApplicationContext applicationContext=new ClassPathXmlApplicationContext("employee-servlet.xml");
		AppLogger.logInfo("Logger has been configured successfully");
		}
	
	
	/*public  WebDriver ieWebDriver()
	{	
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability("databaseEnabled", true);
		capability.setCapability("locationContextEnabled", true);
		capability.setCapability("applicationCacheEnabled", true);
		capability.setCapability("browserConnectionEnabled", true);
		capability.setCapability("webStorageEnabled", true);
		capability.setCapability("acceptSslCerts", true);
		capability.setJavascriptEnabled(true);
		capability.setCapability("nativeEvents", true);
		capability.setPlatform(Platform.MAC);
		InternetExplorerDriver internetExplorerDriver=new InternetExplorerDriver(capability);
		internetExplorerDriver.manage().window().maximize();
		return internetExplorerDriver;	
	}
	
	public   WebDriver firefoxWebDriver()
	{
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability("databaseEnabled", true);
		capability.setCapability("locationContextEnabled", true);
		capability.setCapability("applicationCacheEnabled", true);
		capability.setCapability("browserConnectionEnabled", true);
		capability.setCapability("webStorageEnabled", true);
		capability.setCapability("acceptSslCerts", true);
		capability.setJavascriptEnabled(true);
		capability.setCapability("nativeEvents", true);
		capability.setPlatform(Platform.MAC);
		firefoxWebDriver = new FirefoxDriver(capability);
		firefoxWebDriver.manage().window().maximize();
		return firefoxWebDriver;
	}
	
	public   WebDriver safariWebDriver()
	{
		DesiredCapabilities capability = DesiredCapabilities.safari();
		capability.setCapability("databaseEnabled", true);
		capability.setCapability("locationContextEnabled", true);
		capability.setCapability("applicationCacheEnabled", true);
		capability.setCapability("browserConnectionEnabled", true);
		capability.setCapability("webStorageEnabled", true);
		capability.setCapability("acceptSslCerts", true);
		capability.setJavascriptEnabled(true);
		capability.setCapability("nativeEvents", true);
		capability.setPlatform(Platform.MAC);
		safariWebDriver = new SafariDriver();
		safariWebDriver.manage().window().maximize();
		return safariWebDriver;
	}
	public   WebDriver chromeWebDriver()
	{	
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setCapability("databaseEnabled", true);
		capability.setCapability("locationContextEnabled", true);
		capability.setCapability("applicationCacheEnabled", true);
		capability.setCapability("browserConnectionEnabled", true);
		capability.setCapability("webStorageEnabled", true);
		capability.setCapability("acceptSslCerts", true);
		capability.setJavascriptEnabled(true);
		//capability.setCapability("nativeEvents", true);
		//LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, Level.ALL);
        //prefs.enable(LogType.DRIVER, Level.ALL);
        prefs.enable(LogType.CLIENT, Level.ALL);
        //capability.setCapability(CapabilityType.LOGGING_PREFS, prefs);		
		chromeWebDriver = new ChromeDriver(capability);	
		chromeWebDriver.manage().window().maximize();
		return chromeWebDriver;		
	}
	
	public  WebDriver phantomJSDriver()
	{
		
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
		desiredCapabilities.setPlatform(Platform.WINDOWS);
		phantomJSDriver=new PhantomJSDriver(desiredCapabilities);
		phantomJSDriver.manage().window().maximize();
		return phantomJSDriver;
	}*/
	
	public  WebDriver phantomJSRemoteWebDriver()
	{
		DesiredCapabilities capability=DesiredCapabilities.phantomjs();		
		RemoteWebDriver driver=null;
		
		try {
			driver = new RemoteWebDriver(new URL(nodeURL),capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		phantomJSRemoteWebDriver=driver;
		return driver;	
	}
	
	public  WebDriver chromeRemoteWebDriver()
	{
		System.out.println("Creating chromeremotedriver");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browser", "IE");
		capabilities.setCapability("browser_version", "7.0");
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("os_version", "XP");
		capabilities.setCapability("browserstack.debug", "true");
		
		
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browser", "Chrome");
		capabilities.setCapability("browser_version", "43");
		capabilities.setCapability("os", "OS X");
		capabilities.setCapability("os_version", "Yosemite");
		capabilities.setCapability("browserstack.local", "true");
		capabilities.setCapability("browserstack.debug", "true");
		capabilities.setCapability("browserstack.selenium_version", "2.45");
		*/
		/*capabilities.setVersion("43");
		capabilities.setPlatform(Platform.MAC);*/
		RemoteWebDriver driver=null;
		try {
			driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
			System.out.println("RemoteChromeWebdriver has been initialized");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Malformed url exception");
		}		
		chromeRemoteWebDriver=driver;
		return driver;		
	}
	
	public  RemoteWebDriver ieRemoteWebDriver()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browser", "IE");
		capabilities.setCapability("browser_version", "7.0");
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("os_version", "XP");
		capabilities.setCapability("browserstack.debug", "true");
		RemoteWebDriver driver=null;
		try {
			driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ieRemoteWebDriver=driver;
		return driver;	
	}
	
	public  RemoteWebDriver firefoxRemoteWebDriver()
	{		
		DesiredCapabilities capability=DesiredCapabilities.firefox();
		RemoteWebDriver driver=null;
		try {
			driver = new RemoteWebDriver(new URL(nodeURL),capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		firefoxRemoteWebDriver=driver;
		return driver;			
	}
		
	/**
	 * Gets the attribute of element.
	 *
	 * @param webElement the web element
	 * @param attribute the attribute
	 * @return the attribute of element
	 */
	
	public void goToURL(String url, WebDriver webDriver)
	{	
	webDriver.get(url);	
	}
	public String getAttributeOfElement(WebElement webElement, String attributeName)
	{

		FluentWait<WebElement> wait = new FluentWait<WebElement>(webElement);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(5, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class);
		
		Function<WebElement, String> function = new Function<WebElement, String>()
				{
				String attribute=null;
					public String apply(WebElement webElement) {
						
						if(webElement!=null)
						{
							
								attribute=webElement.getAttribute(attributeName);
							
						}
								return attribute;
						
					}
				};
 
				
		return wait.until(function);
	}
	
	/**
	 * Implicit wait.
	 *
	 * @param webDriver the web driver
	 * @param seconds the seconds
	 */
	public  void implicitWait(WebDriver webDriver,int seconds)
	{
		webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		
	}
	
	/**
	 * Gets the element by xpath.
	 *
	 * @param webDriver the web driver
	 * @param seconds the seconds
	 * @param xPath the x path
	 * @return the element by xpath
	 */
	public  WebElement getElementByXpath(WebDriver webDriver, int seconds, String xPath )
	{

		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement webElement =null;
					public WebElement apply(WebDriver webDriver) {
						
						
						webElement= webDriver.findElement(By.xpath(xPath));
						return webElement;
					}
				};
 
		return wait.until(function);	
	
	
	
	
		
		
	}
	
	
	public  WebElement getLinkByText(WebDriver webDriver, int seconds, String textOfLink)
	{
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement webElement =null;
					public WebElement apply(WebDriver webDriver) {
						
						
						webElement= webDriver.findElement(By.linkText(textOfLink));
						return webElement;
					}
				};
 
		return wait.until(function);	
	
	
	
	}
	public  WebElement getElementByID(WebDriver webDriver, int seconds, String id)
	{	
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement webElement =null;
					public WebElement apply(WebDriver webDriver) {
						
						
						webElement= webDriver.findElement(By.id(id));
						return webElement;
					}
				};
 
		return wait.until(function);	
	
	
	}
	public  WebElement getElementByClass(WebDriver webDriver,int seconds, String className)
	{	
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement webElement =null;
					public WebElement apply(WebDriver webDriver) {
						
						
						webElement= webDriver.findElement(By.className(className));
						return webElement;
					}
				};
 
		return wait.until(function);	
	
	
	}
	
	
	/**
	 * Gets the element by name.
	 *
	 * @param webDriver the web driver
	 * @param seconds the seconds
	 * @param name the name
	 * @return the element by name
	 */
	public  WebElement getElementByName(WebDriver webDriver, int seconds, String name)
	{	
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement webElement =null;
					public WebElement apply(WebDriver webDriver) {
						
						
						webElement= webDriver.findElement(By.name(name));
						return webElement;
					}
				};
 
		return wait.until(function);	
	
	}
	
	public  WebElement getElementByTagName(WebDriver webDriver, int seconds, String tagName)
	{	
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement webElement =null;
					public WebElement apply(WebDriver webDriver) {
						
						
						webElement= webDriver.findElement(By.tagName(tagName));
						return webElement;
					}
				};
 
		return wait.until(function);	
	}
	
	
	/**
	 * Click.
	 *
	 * @param webElement the web element
	 */
	public void click(WebElement webElement)
	{
		FluentWait<WebElement> wait = new FluentWait<WebElement>(webElement);
		wait.pollingEvery(50,  TimeUnit.MILLISECONDS);
		wait.withTimeout(20, TimeUnit.SECONDS).ignoring(NoSuchElementException.class,StaleElementReferenceException.class);
		
		Function<WebElement, Boolean> function = new Function<WebElement, Boolean>()
				{
					Boolean status=false;
					public Boolean apply(WebElement webElement) {
						if(webElement!=null && webElement.isDisplayed() && webElement.isEnabled())
						{
								webElement.click();
								status=true;						
						}						
						
						return status;
					}
				};
 
		wait.until(function);				
	}
	
	/**
	 * Type keys.
	 *
	 * @param webElement the web element
	 * @param keys the keys
	 */
	public  void typeKeys(WebElement webElement , String keys)
	{
		FluentWait<WebElement> wait = new FluentWait<WebElement>(webElement);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(2, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class,NoSuchElementException.class);
		
		Function<WebElement, Boolean> function = new Function<WebElement, Boolean>()
				{
			Boolean status=false;
					public Boolean apply(WebElement webElement) {
						if(webElement!=null && webElement.isDisplayed() && webElement.isEnabled())
						{
								webElement.sendKeys(keys);
								status=true;						
						}						
						
						return status;
					}
				};
 
			wait.until(function);				
	}
	
	/**
	 * Gets the current url.
	 *
	 * @param webDriver the web driver
	 * @return the current url
	 */
	public  String getCurrentUrl(WebDriver webDriver)
	{

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, String> function = new Function<WebDriver, String>()
				{
					public String apply(WebDriver webDriver) {
						String currentUrl =null;
						
						currentUrl= webDriver.getCurrentUrl();
						
							if(currentUrl!=null)
							{
								return currentUrl;
							}
						
						
						
						return null;
					}
				};
 
		return wait.until(function);	
	}
	
	/**
	 * Wait for page to load.
	 *
	 * @param webDriver the web driver
	 * @param pageURL the page url
	 */
	public  void waitForPageToLoad(WebDriver webDriver, int maxNumberOfSeconds)
	{

		Long startTimeInmilliseconds=System.currentTimeMillis();
		
		Integer numberOfseconds=new Integer(maxNumberOfSeconds*1000);
		String completionStatus="";
			
			while((System.currentTimeMillis()-startTimeInmilliseconds)<numberOfseconds)
			{
				if (webDriver instanceof JavascriptExecutor) {
					try
					{
						completionStatus=(String)((JavascriptExecutor) webDriver).executeScript("return document.readyState");
						
					}catch(UnreachableBrowserException ex)
					{
						
					}
				if(completionStatus.equals("complete"))
				{
					break;
				}
				}
			}
			
		
	}
	
	public  void addTexttoAnElement(WebDriver webDriver, int maxNumberOfSeconds)
	{

		Long startTimeInmilliseconds=System.currentTimeMillis();
		
		Integer numberOfseconds=new Integer(maxNumberOfSeconds*1000);
		String completionStatus="";
			
			while((System.currentTimeMillis()-startTimeInmilliseconds)<numberOfseconds)
			{
				if (webDriver instanceof JavascriptExecutor) {
					try
					{
						completionStatus=(String)((JavascriptExecutor) webDriver).executeScript("return document.readyState");
						
					}catch(UnreachableBrowserException ex)
					{
						
					}
				if(completionStatus.equals("complete"))
				{
					break;
				}
				}
			}
			
		
	}
	
	
	public void moveToAndClickOnElementAndTypeKeys(WebDriver webDriver, String cssPath, String text)
	{
		WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
		Actions actions=new Actions(webDriver);
		actions.moveToElement(webDriverUtilFunctions.getWebElementByCss(webDriver, cssPath, 20)).click().build().perform();
		actions.moveToElement(webDriverUtilFunctions.getWebElementByCss(webDriver, cssPath, 20)).clickAndHold().release().click().sendKeys(text).build().perform();
	}
	public void moveToAndClickOnElement(WebDriver webDriver, String cssPath)
	{
		WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
		Actions actions=new Actions(webDriver);
		actions.moveToElement(webDriverUtilFunctions.getWebElementByCss(webDriver, cssPath, 20)).clickAndHold().release().click().build().perform();
	}
	
	
	
	
	
	public  void waitForAjaxQueryCompletion(WebDriver webDriver, int maxNumberOfSeconds)
	{

		Long startTimeInmilliseconds=System.currentTimeMillis();
		
		Integer numberOfseconds=new Integer(maxNumberOfSeconds*1000);
		Boolean completionStatus=false;
			
			while((System.currentTimeMillis()-startTimeInmilliseconds)<numberOfseconds)
				
			{if (webDriver instanceof JavascriptExecutor) {
				try
				{
					 completionStatus=(Boolean)((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0");
					
				}catch(UnreachableBrowserException ex)
				{
					
				}
				if(completionStatus==true)
				{
					break;
				}
			}
			}
			
		
	}
	
	/**
	 * Select from drop down.
	 *
	 * @param webElement the web element
	 * @param stringValue the string value
	 */
	public  void selectFromDropDown(WebElement webElement,String stringValue)
	{	
		FluentWait<WebElement> wait = new FluentWait<WebElement>(webElement);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebElement, Boolean> function = new Function<WebElement, Boolean>()
				{
					Select dropdown = new Select(webElement);
					public Boolean apply(WebElement webElement) {		
						
						dropdown.selectByValue(stringValue);
						return true;
					}
				};
 
		wait.until(function);			
	}
	
	/**
	 * Gets the from drop down.
	 *
	 * @param webElement the web element
	 * @return the from drop down
	 */
	public  String getFromDropDown(WebElement webElement)
	{		
		Select dropdown = new Select(webElement);
		return dropdown.getFirstSelectedOption().getText();
		
	}
	
	/**
	 * Drag and drop.
	 *
	 * @param webDriver the web driver
	 * @param someElement the some element
	 * @param otherElement the other element
	 */
	public  void dragAndDrop(WebDriver webDriver, WebElement someElement, WebElement otherElement){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
				{
			Actions actions= new Actions(webDriver);
					public Boolean apply(WebDriver webDriver) {
						actions.clickAndHold(someElement).release(otherElement).build();
						actions.perform();
						return true;
					}
				};
 
		 wait.until(function);
		}
	
	/**
	 * Gets the title.
	 *
	 * @param webDriver the web driver
	 * @return the title
	 */
	public String getTitle(WebDriver webDriver)
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);		
		Function<WebDriver, String> function = new Function<WebDriver, String>()
				{
					public String apply(WebDriver webDriver) {
						String title =null;
						
						title= webDriver.getTitle();
						return title;
					}
				};
 
		return wait.until(function);	
	}
	
	/**
	 * Fluent wait.
	 *
	 * @param webDriver the web driver
	 * @param webElementByID the web element by id
	 * @param secondsToWait the seconds to wait
	 * @return the web element
	 */
	public WebElement getWebElementByCss(WebDriver webDriver, String webElementByCSS, int secondsToWait) 
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(50,  TimeUnit.MILLISECONDS);
		wait.withTimeout(secondsToWait, TimeUnit.SECONDS).ignoring(Exception.class,NoSuchElementException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					WebElement	webElement=null;
					public WebElement apply(WebDriver webDriver) {
						
						webElement=webDriver.findElement(By.cssSelector(webElementByCSS));					
						
						return webElement;
					}
				};	
		
		return wait.until(function);
	}
	
	public List<WebElement> waitForWebElements(WebDriver webDriver, String webElementsByCSS, int secondsToWait) 
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(10,  TimeUnit.MILLISECONDS);
		wait.withTimeout(secondsToWait, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class);
		
		Function<WebDriver, List<WebElement>> function = new Function<WebDriver, List<WebElement>>()
				{
					List<WebElement> webElements =null;
					public List<WebElement> apply(WebDriver webDriver) {						
							webElements = webDriver.findElements(By.cssSelector(webElementsByCSS));						
							return webElements;						
					}
				};		
		return wait.until(function);
	}
	
	public WebElement explicitWait(WebDriver webDriver, String webElementByCSS, int secondsToWait)
	{
		WebDriverWait wait = new WebDriverWait(webDriver,secondsToWait);

		WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(webElementByCSS)));
		return webElement;
	}
	public Boolean fluentwaitForPageLoadUsingUrl(WebDriver webDriver, int secondsToWait , String pageURL)
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(secondsToWait, TimeUnit.SECONDS);
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
				{
					public Boolean apply(WebDriver webDriver) {
						WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
						String url=webDriverUtilFunctions.getCurrentUrl(webDriver);
						//System.out.println("The currentURL is " + url);
						if(url!=null && url.equals(pageURL))
						{
							return true;
						}
						return false;
					}
				};
 
		return wait.until(function);
	}
		
	public Boolean fluentwaitForPageLoadUsingRegexOfUrl(WebDriver webDriver, int secondsToWait , String pageURL)
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(secondsToWait, TimeUnit.SECONDS);
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
				{
					public Boolean apply(WebDriver webDriver) {
						String url=webDriver.getCurrentUrl();
						//System.out.println("The currentURL is " + url);
						if(url.matches(pageURL))
						{
							return true;
						}
						return false;
					}
				};
 
		return wait.until(function);
	}
		
	public Boolean fluentwaitForPageLoadUsingStartingPatternOfUrl(WebDriver webDriver, int secondsToWait , String pageURL)
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(secondsToWait, TimeUnit.SECONDS);
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
				{
					public Boolean apply(WebDriver webDriver) {
						String url=webDriver.getCurrentUrl();
						//System.out.println("The currentURL is " + url);
						if(url.trim().startsWith(pageURL))
						{
							return true;
						}
						return false;
					}
				};
 
	 return	wait.until(function);
	}
	
	public String getTextContent(WebElement webElement)
	{

		FluentWait<WebElement> wait = new FluentWait<WebElement>(webElement);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(Exception.class,UnreachableBrowserException.class);		
		Function<WebElement, String> function = new Function<WebElement, String>()
				{
					String text =null;
					public String apply(WebElement webElement) {
						
						
						text= webElement.getText();
						return text;
					}
				};
 
		return wait.until(function);	
	
	}
	
	/**
	 * Gets the screen shot.
	 *
	 * @param webDriver the web driver
	 * @return the screen shot
	 * @throws IOException 
	 */
	public Boolean getScreenShot(WebDriver webDriver , String testContextName, String testCaseName , String failureName) throws IOException
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(3, TimeUnit.SECONDS).ignoring(UnreachableBrowserException.class,ScreenshotException.class);
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
				{
			Boolean status=false;
					public Boolean apply(WebDriver webDriver) {
								File scrFile =null;							
								scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
								//AppLogger.logInfo("Dest path is: target\\generated-test-sources\\screenshots\\"+testContextName+"\\"+testCaseName+"\\"+failureName+ ".jpeg");
								File file1=new File("target\\generated-test-sources\\screenshots\\"+testContextName+"\\"+testCaseName+"\\"+failureName+ ".jpeg");								
								try {
								FileUtils.copyFile(scrFile, file1);
								status=true;
								//AppLogger.logger.info("Screenshot created");								
							} catch (IOException e) {
								// TODO Auto-generated catch block								
							}
				    	
				    	
						return status;
					}
				};
 
	return 	wait.until(function);		
		
		
	}
	
	public void reactToPopupMesage(String jQueryIdentifier)
	{
		
	}
	
	public static void emailTestReport()
	{
		//generatePDFReport("target/surefire-reports/emailable-report.pdf");
		//emailFile("adarsha.shetty.1989@gmail.com", "trnkuohdtefxsdqp", "adarsha.shetty.1989@gmail.com", "adarsha.shetty.1989@gmail.com","target/surefire-reports/emailable-report.pdf","emailable-report.pdf");
	}
	
	public static void emailFile(final String emailID, final String password, final String fromAddress , final String toAddress ,final  ArrayList<String> fileNames  ) throws MessagingException
	{	    
		 Properties props = new Properties();
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.auth", "true");
         props.put("mail.debug", "false");
         props.put("mail.smtp.ssl.enable", "true");
         
	     Session session = Session.getInstance(props,
	     new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(emailID, password);
	                }
	            });	    

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(fromAddress));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(toAddress));
	        message.setSubject("Test Report Mail");
	        Multipart multipart = new MimeMultipart();
	        BodyPart messageBodyPart=null;
	       	
	       	for(String fileName:fileNames)
	       	{
	       		messageBodyPart = new MimeBodyPart();
	       		DataSource source = new FileDataSource(fileName);
			    messageBodyPart.setDataHandler(new DataHandler(source));
			    messageBodyPart.setFileName(fileName);
			    
			    multipart.addBodyPart(messageBodyPart);
			    
	       	}
	       	messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setText("PFA the Test Report and files as of :" + Calendar.getInstance().getTime().toString());
		    multipart.addBodyPart(messageBodyPart);
	        message.setContent(multipart);
	        AppLogger.logger.info("Sending");

	        Transport.send(message);

	        AppLogger.logger.info("Done");

	    
	}
	
	
	public static void generatePDFReport(String htmlPath,String pdfPath) throws DocumentException, IOException {
		 
 
              FileOutputStream file = new FileOutputStream(new File(pdfPath));
              Document document = new Document();
              PdfWriter.getInstance(document, file);
             convertHTMLToPDF(htmlPath,pdfPath);
             //Now Insert Every Thing Into PDF Document
             /*ITex xmlHandler = new ITextHandler(document);
              xmlHandler.Parse("ExampleDoc.xml");  
              document.open();                  
                 document.add
                 document.close();*/
                 file.close();
 
            AppLogger.logInfo("Pdf: "+pdfPath+" created successfully..");
 
    }
	public static Image generateImageInstance(String pathOfImageToBeCreated) throws BadElementException, MalformedURLException, IOException
	{
		 Image image=null;
		
			image = Image.getInstance (pathOfImageToBeCreated);
		
         image.scaleAbsolute(120f, 60f);//image width,height   
         return image;
	}
	public static PdfPTable generatePDFTable()
	{
		 PdfPTable table=new PdfPTable(3);
		 
         PdfPCell cell = new PdfPCell (new Paragraph ("Java4s.com"));

      cell.setColspan (3);
      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
      cell.setPadding (10.0f);
      cell.setBackgroundColor (new BaseColor (140, 221, 8));                                   

      table.addCell(cell);                                       

      table.addCell("Name");
      table.addCell("Address");
      table.addCell("Country");
      table.addCell("Java4s");
      table.addCell("NC");
      table.addCell("United States");
      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS                                          
      return table;
	}
	
	/*public List generateListInPDF()
	{
		List list=new List(true,30);
        list.add(new ListItem("Java4s"));
        list.add(new ListItem("Php4s"));
        list.add(new ListItem("Some Thing..."));     
        return list;
	}
	*/
	
	
	public static void convertHTMLToPDF(String htmlPath, String pdfPath) throws FileNotFoundException, IOException, DocumentException
	{
		 Document document = new Document();
	        // step 2
	        PdfWriter writer = null;
			
				writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			
	        // step 3
	        document.open();
	        // step 4
				XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				        new FileInputStream(htmlPath));
			
				// TODO Auto-generated catch block
				
			
	        //step 5
	         document.close();
	         writer.close();
	        System.out.println( "PDF Created!" );
	}
	public static  void combinePDFFiles( ArrayList<String> pdfFilepath) throws DocumentException, IOException
	{
		System.out.println("Concatenate Two PDF");
		PdfReader reader=null;
		PdfCopyFields copy = new PdfCopyFields(new FileOutputStream("test-output/mergedPDF.pdf"));
		for (String pdfFile: pdfFilepath)
		{
			reader = new PdfReader(pdfFile);
			copy.addDocument(reader);
		}

		copy.close();

	}
	public void setWebDriver(WebDriver webDriver)
	{
		this.eventFiringWebDriver=new EventFiringWebDriver(webDriver);
	}
	
	public void closeAndQuitWebDriver(WebDriver webDriver)
	{
		webDriver.close();
		webDriver.quit();
	}
	
	 public void zipFolder(String srcFolder, String destZipFile) throws Exception {
	    ZipOutputStream zip = null;
	    FileOutputStream fileWriter = null;

	    fileWriter = new FileOutputStream(destZipFile);
	    zip = new ZipOutputStream(fileWriter);
	    addFolderToZip("", srcFolder, zip);
	    zip.flush();
	    zip.close();
	  }

	   private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
	      throws Exception {

	    File folder = new File(srcFile);
	    if (folder.isDirectory()) {
	      addFolderToZip(path, srcFile, zip);
	    } else {
	      byte[] buf = new byte[1024];
	      int len;
	      FileInputStream in = new FileInputStream(srcFile);
	      zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
	      while ((len = in.read(buf)) > 0) {
	        zip.write(buf, 0, len);
	      }
	      in.close();
	    }
	  }

	   private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
	      throws Exception {
	    File folder = new File(srcFolder);

	    for (String fileName : folder.list()) {
	      if (path.equals("")) {
	        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
	      } else {
	        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
	      }
	    }
	  }
	public  static void setMapOfSuitesAndTestCases(Map<String,ArrayList<String>> mapOfSuitesAndTestCases1)
	{
		mapOfSuitesAndTestCases=mapOfSuitesAndTestCases1;
	}
	
	
	
	public void scrollToTopOfScreen(WebDriver webDriver)
	{
		if (webDriver instanceof JavascriptExecutor) {
			
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
			javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		}
		
	}
	public void scrollToBottomOfScreen(WebDriver webDriver)
	{
		if (webDriver instanceof JavascriptExecutor) {
			
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
			javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight,document.body.scrollHeight)", "");
		}
		
	}

	public void maximizeWindow(WebDriver webDriver) {
		webDriver.manage().window().maximize();
		
	}

	public void clickAnElementUsingJavascript(WebDriver webDriver,String string) {
		// TODO Auto-generated method stub
if (webDriver instanceof JavascriptExecutor) {
			
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
			javascriptExecutor.executeScript(string, "");
		}
	}
	public void findBrokenLinks(WebDriver webDriver)
	{
		BrokenLinksFinder brokenLinksFinder=new BrokenLinksFinder();
		try {
			brokenLinksFinder.findBrokenLinks(webDriver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Object instantiatePage(WebDriver webDriver, String pageClassName)
	{
		Object object=null;
		try {
			object=PageFactory.initElements(webDriver, Class.forName(pageClassName));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	public Object instantiatePageWithUrl(WebDriver webDriver, String url, String pageClassName)
	{
		Object object=null;
		try {
			object=PageFactory.initElements(webDriver, Class.forName(pageClassName));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpyClass spyObject=new SpyClass();
		Object fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject("url", object);
		fieldObject=url;
		goToURL(url, webDriver);
		return object;
	}
	
	public void enterCodeInEditor(WebDriver webDriver, List<String> codeList)
	{
		if (webDriver instanceof JavascriptExecutor) {
			try
			{
				((JavascriptExecutor) webDriver).executeScript("window.CodePair.editor.setValue()");
			}catch(Exception e)
			{
				
			}
		}
		
		for(String lineOfCode:codeList)
		{
			if (webDriver instanceof JavascriptExecutor) {
				try
				{
					 ((JavascriptExecutor) webDriver).executeScript("window.CodePair.editor.insert('"+lineOfCode+"');window.CodePair.editor.splitLine();window.CodePair.editor.navigateDown(1);window.CodePair.editor.navigateLineStart();");
					
				}catch(Exception ex)
				{
					AppLogger.logInfo("Exception occured");
				}
			}
		}
		
	}
	
	
	public String getJSONStringFromUrl(String jsonUrl)
	{


		StringBuilder outputComplete=new StringBuilder();
		String output=null;
		  try {
	 
			URL url = new URL(jsonUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	 
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				outputComplete.append(output+"\n");
			}
	 
			conn.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		  }
		  return outputComplete.toString();
		  //output=outputComplete.toString().split(":")[2].split(",")[0];
		  //System.out.println(output);
		    
	}

	public Long runJavascript(String javaScript, WebDriver webDriver) {
		// TODO Auto-generated method stub
		Long response=null;
		if (webDriver instanceof JavascriptExecutor) {
			try
			{
				response=(Long)((JavascriptExecutor) webDriver).executeScript(javaScript);
				System.out.println("Length is: "+((JavascriptExecutor) webDriver).executeScript(javaScript));
				
			}catch(UnreachableBrowserException ex)
			{
				System.out.println("Exception encountered");
			}
		}
		return response;
	}
	
	
	
	}
	

