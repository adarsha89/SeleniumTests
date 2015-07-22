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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import net.project.listeners.AppListener;
import net.project.loggers.AppLogger;   
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.Optional;

import com.google.common.base.Function;
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
	
	public   String USERNAME = "hackerrank";
	public  final String AUTOMATE_KEY = "nt6JzvpM3fqCo3c5cRMA";
	public  final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

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
	
	public EventFiringWebDriver setupTest(String browser,@Optional String browserVersion,@Optional String oS,@Optional String oSVersion,@Optional String resolution,@Optional String nameOfTest)
	{
		WebDriver webDriver=null;
		webDriverFactory=new WebDriverFactory();
		webDriver=webDriverFactory.getWebDriver(browser,browserVersion,oS,oSVersion,resolution,nameOfTest);
		
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
	static {
		AppLogger.logInfo("Setting properties for drivers");
		if(System.getProperty("os.name").startsWith("MAC"))
		{
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driverexefiles/MACFiles/chromedriver");
			System.setProperty("phantomjs.binary.path", "src/test/resources/driverexefiles/MACFiles/phantomjs");
		}
		else if(System.getProperty("os.name").startsWith("Linux"))
		{
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driverexefiles/LinuxFiles/chromedriver");
			System.setProperty("phantomjs.binary.path", "src/test/resources/driverexefiles/LinuxFiles/phantomjs");

		}
		else if(System.getProperty("os.name").startsWith("Windows"))
		{
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driverexefiles/WindowsFiles/chromedriver.exe");
			System.setProperty("phantomjs.binary.path", "src/test/resources/driverexefiles/WindowsFiles/phantomjs.exe");
			System.setProperty("webdriver.ie.driver", "src/test/resources/driverexefiles/WindowsFiles/IEDriverServer.exe");

		}
		AppLogger.logInfo("Logger has been configured successfully");
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
		AppLogger.logInfo("Going to url: "+url);
	webDriver.get(url);	
	}
	public String getAttributeOfElement(WebElement webElement, String attributeName)
	{
		
		String attribute=null;
		if(checkWhetherDisplayed(webElement, 5))
		{
			attribute=webElement.getAttribute(attributeName);
		}		
		return attribute;
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
		if(checkWhetherDisplayed(webElement, 5))			
		{
			webElement.click();
		}
	}
	
	/**
	 * Type keys.
	 *
	 * @param webElement the web element
	 * @param keys the keys
	 */
	public  void typeKeys(WebElement webElement , String keys)
	{
		if(checkWhetherDisplayed(webElement, 5))
		{
			webElement.sendKeys(keys);
		}								
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
		{
			if (webDriver instanceof JavascriptExecutor) {
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
		String text=null;
		if(checkWhetherDisplayed(webElement, 5))
		{
						text= webElement.getText();
		}				
		return text;	
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
								webDriver=new Augmenter().augment(webDriver);
								scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
								//AppLogger.logInfo("Dest path is: target\\generated-test-sources\\screenshots\\"+testContextName+"\\"+testCaseName+"\\"+failureName+ ".jpeg");
								File file1=new File("target/generated-test-sources/screenshots/"+testContextName+"/"+testCaseName+"/"+failureName+ ".jpeg");								
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
	}

	public Object runJavascript(String javaScript, WebDriver webDriver) {
		// TODO Auto-generated method stub
		Object response=null;
		if (webDriver instanceof JavascriptExecutor) {
			try
			{
				response=((JavascriptExecutor) webDriver).executeScript(javaScript);
				System.out.println("Object is: "+response);
				
			}catch(UnreachableBrowserException ex)
			{
				System.out.println("Exception encountered");
			}
		}
		return response;
	 }
	
	public Boolean checkWhetherDisplayed(WebElement webElement, Integer secondsToWait)
		{

		FluentWait<WebElement> wait = new FluentWait<WebElement>(webElement);
		wait.pollingEvery(100,  TimeUnit.MILLISECONDS);
		wait.withTimeout(secondsToWait, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class,NoSuchElementException.class);
		Boolean status=false;
		Function<WebElement, Boolean> function = new Function<WebElement, Boolean>()
				{
			Boolean status=false;
					public Boolean apply(WebElement webElement) {
						if(webElement!=null && webElement.isDisplayed())
						{
								
								status=true;						
						}						
						
						return status;
					}
				};
			try
			{
			status=wait.until(function);				
			}catch(TimeoutException ex)
			{
				
			}
			return status;
		}
	
	
	
}
	

