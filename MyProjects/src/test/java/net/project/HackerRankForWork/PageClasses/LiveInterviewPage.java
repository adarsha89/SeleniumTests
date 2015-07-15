package net.project.HackerRankForWork.PageClasses;

import java.util.List;
import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

public class LiveInterviewPage extends LoadableComponent<LiveInterviewPage> implements CommonPageInterface{

	
	public WebDriver webDriver;
	public WebDriverUtilFunctions webDriverUtilFunctions;
	String urlPattern="https://codepair.hackerrank.com/paper/";
	
	@FindBy(how=How.CSS , using="span[class='select2-arrow']")
	@CacheLookup
	public WebElement languagesDropdown;
	
	@FindBy(how=How.CSS , using="div[class='select2-result-label']")
	public List<WebElement> listOfAvailableLanguages;
	
	@FindBy(how=How.CSS , using="input[class='select2-input']")
	public WebElement languageSearchBox;
	
	@FindBy(how=How.CSS , using="span[class='select2-chosen']")
	@CacheLookup
	public WebElement languageSelected;
	
	@FindBy(how=How.CSS , using="div[class='cp_chatHeader']")
	public WebElement chatHeaderBox;
	
	@FindBy(how=How.CSS , using="textarea[id='chat_message_text']")
	public WebElement chatTextArea;
	
	@FindBy(how=How.CSS , using="a[data-tab='input']")
	public WebElement inputDataTab;
	
	@FindBy(how=How.CSS , using="a[data-tab='output']")
	public WebElement outputDataTab;
	
	@FindBy(how=How.CSS , using="textarea[class*='input-program']")
	public WebElement inputTextArea;
	
	@FindBy(how=How.CSS , using="pre[class*='cp_codeText']")
	public WebElement outputTextArea;
	
	
	@FindBy(how=How.CSS , using="div[class='willrun']")
	public WebElement runCodeButton;
	
	@FindBy(how=How.CSS , using="div[id='floatingCirclesG']")
	public WebElement loadingCircle;
	
	@FindBy(how=How.CSS , using="span[class='error']")
	public WebElement errorMessageBox;
	
	@FindBy(how=How.CSS , using="span[class='error']")
	public WebElement successMessageBox;
	
	@FindBy(how=How.CSS , using="button[data-role='end']")
	public WebElement endTourButton;
	
	@FindBy(how=How.CSS , using="button[class*='js-no']")
	public WebElement discardCodeButton;
	
	@FindBy(how=How.CSS , using="button[class*='js-yes']:nth-child(2)")
	public WebElement continueRunningCodeButton;
	
	public LiveInterviewPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,1);
		AppLogger.assertLogTrue(webDriverUtilFunctions.getCurrentUrl(webDriver).startsWith(urlPattern),"Live interview Page page is not loaded");
		
	}
	
	public void selectLanguageUsingLanguageList(String language)
	{
		webDriverUtilFunctions.click(languagesDropdown);
		waitForPageRender(webDriver, 1);
		webDriverUtilFunctions.waitForWebElements(webDriver, "div[class='select2-result-label']", 5);
		for(WebElement webE: listOfAvailableLanguages)
		{
			if(webE.getText().equals(language))
			{
				webDriverUtilFunctions.click(webE);
			}
		}
		
	}
	public String checkSelectedLanguage()
	{
		return languageSelected.getText();
	}
	public void selectLanguageUsingSearchBox(String language)
	{
		webDriverUtilFunctions.click(languagesDropdown);
		languageSearchBox.clear();
		webDriverUtilFunctions.typeKeys(languageSearchBox, language+Keys.ENTER);
		
	}
	public void enterCodeInEditor(List<String> codeList)
	{
		webDriverUtilFunctions.enterCodeInEditor(webDriver, codeList);
	}
	public void runCode()
	{
		webDriverUtilFunctions.click(runCodeButton);
		waitForCompilationToComplete(webDriver, 10);
	}
	public String getErrorMessage()
	{
		return errorMessageBox.getText();
	}
	public  void waitForCompilationToComplete(WebDriver webDriver, int maxNumberOfSeconds)
	{

		Long startTimeInmilliseconds=System.currentTimeMillis();
		
		Integer numberOfseconds=new Integer(maxNumberOfSeconds*1000);
			
			while((System.currentTimeMillis()-startTimeInmilliseconds)<numberOfseconds)
			{
				
				if(loadingCircle!=null && loadingCircle.isDisplayed())
				{
					
				}
				else
				{
					break;
				}	
			}	
			
	}
	public void endTour()
	{
		if(endTourButton!=null)
		{
			if(endTourButton.isDisplayed())
			{
				webDriverUtilFunctions.click(endTourButton);
			}
		}
	}

	public void discardMyCode() {
		// TODO Auto-generated method stub
		webDriverUtilFunctions.click(discardCodeButton);
	}
	public void enterInput(String inputString)
	{
		webDriverUtilFunctions.click(inputDataTab);
		webDriverUtilFunctions.typeKeys(inputTextArea, inputString);
		
	}

	public void continueWithRunningCode() {
		// TODO Auto-generated method stub
		webDriverUtilFunctions.click(continueRunningCodeButton);
	}
	
}
