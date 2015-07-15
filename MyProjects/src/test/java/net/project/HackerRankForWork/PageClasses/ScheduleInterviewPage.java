package net.project.HackerRankForWork.PageClasses;

import java.util.ArrayList;
import java.util.List;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

public class ScheduleInterviewPage extends LoadableComponent<ScheduleInterviewPage> implements CommonPageInterface{

	WebDriver webDriver=null;
	WebDriverUtilFunctions webDriverUtilFunctions=null;
	CodePairPage codePairPage=null;
	public List<String> successfullyUpdatedInterviewerNames=null;
	
	@FindBy(how=How.CSS , using="input[name='quick-pad-candidate-email']")
	@CacheLookup	
	public WebElement candidateEmailInput;
	
	@FindBy(how=How.CSS , using="input[name='email']")
	@CacheLookup	
	public WebElement candidateEmailAddressInfoInput;
	
	@FindBy(how=How.CSS , using="input[name='name']")
	@CacheLookup	
	public WebElement candidateNameInfoInput;
	
	@FindBy(how=How.CSS , using="input[name='phone_number']")
	@CacheLookup	
	public WebElement candidatephoneNumberInfoInput;
	
	@FindBy(how=How.CSS , using="ul[class='select2-results']>li>div")
	public List<WebElement> interviewerInfoInput;
	
	@FindBy(how=How.CSS , using="ul[class='select2-choices']")
	public WebElement interviewerInputBox;
	
	@FindBy(how=How.CSS , using="input[name='quick-pad-title']")
	@CacheLookup	
	public WebElement padTitleInput;
	
	@FindBy(how=How.CSS , using="button[name='quick-pad-button']")
	@CacheLookup	
	public WebElement createQuickPadButton;

	@FindBy(how=How.CSS , using="button[name='schedule-interview-button']")
	public WebElement createInterviewButton;
	
	@FindBy(how=How.CSS , using="input[name='resume']")
	@CacheLookup
	public WebElement resumeInputButton;
	
	String url="https://www.hackerrank.com/x/interviews/new";
	
	public ScheduleInterviewPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		codePairPage=(CodePairPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.CodePairPage");
		successfullyUpdatedInterviewerNames=new ArrayList<String>();
		
	}
	
	
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isLoaded() throws Error {
		//webDriverUtilFunctions.fluentwaitForPageLoadUsingUrl(webDriver, 30, url);
		waitForPageRender(webDriver,1);
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"Schedule page is not loaded");		
	}
	public NewInterviewPage createQuickPad(String email, String padTitle)
	{
		webDriverUtilFunctions.typeKeys(candidateEmailInput,email);
		webDriverUtilFunctions.typeKeys(padTitleInput,padTitle);
		NewInterviewPage newInterviewPage=(NewInterviewPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.NewInterviewPage");
		webDriverUtilFunctions.click(createQuickPadButton);
		newInterviewPage.isLoaded();
		return newInterviewPage;
	}
	
	public MyPadsPage createQuickPadUsingCandidateAndInterviewerInfo(String candidateEmail, String candidateName, String candidatePhoneNumber, List<String> interviewerNames)
	{
		webDriverUtilFunctions.typeKeys(candidateEmailAddressInfoInput, candidateEmail);
		webDriverUtilFunctions.typeKeys(candidateNameInfoInput, candidateName);
		webDriverUtilFunctions.typeKeys(candidatephoneNumberInfoInput, candidatePhoneNumber);
		webDriverUtilFunctions.click(interviewerInputBox);
		waitForPageRender(webDriver, 1);
		
			for(String interviewer: interviewerNames)
			{
				for(WebElement webE:interviewerInfoInput)
				{
				  if(webE.getText().trim().equals(interviewer))
					{
					  System.out.println("text in the web element is:"+webE.getText());
						successfullyUpdatedInterviewerNames.add(webE.getText().trim());
					  	webDriverUtilFunctions.click(webE);
						webDriverUtilFunctions.click(interviewerInputBox);
						break;
					}		
				}
			}
		MyPadsPage myPadsPage=(MyPadsPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.MyPadsPage");
		webDriverUtilFunctions.runJavascript("$(\"button[name='schedule-interview-button']\").click()",webDriver);	
		myPadsPage.isLoaded();
		return myPadsPage;
	}
}
