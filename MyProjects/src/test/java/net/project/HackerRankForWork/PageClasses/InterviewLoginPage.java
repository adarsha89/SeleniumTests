package net.project.HackerRankForWork.PageClasses;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

public class InterviewLoginPage extends LoadableComponent<InterviewLoginPage> implements CommonPageInterface{

	public WebDriver webDriver=null;
	public WebDriverUtilFunctions webDriverUtilFunctions=null;;
	String url=null;
	
	@FindBy(how=How.CSS , using="p[class='mjT mdB']>button")
	@CacheLookup	
	public WebElement signInWithHackerRankForWorkButton;
	
	@FindBy(how=How.CSS , using="input[id='candidateName']")
	@CacheLookup	
	public WebElement candidateNameInput;
	
	@FindBy(how=How.CSS , using="p[class='msT mdB']>button")
	public WebElement joinAsCandidateButton;
	
	@FindBy(how=How.CSS , using="input[id='email']")	
	public WebElement interviewerEmailInput;
	
	@FindBy(how=How.CSS , using="input[id='password']")	
	public WebElement interviewerPasswordInput;
	
	@FindBy(how=How.CSS , using="input[id='hidename']")	
	public WebElement hideCandidateNameCheckbox;
	
	@FindBy(how=How.CSS , using="p[class='mmT mdB']>button")	
	public WebElement startInterviewButton;
	
	
	
	public InterviewLoginPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,1);
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"Interview login page is not loaded");
		
	}
	
	public LiveInterviewPage startInterviewAsInterviewer(String emailIdOfInterviewer, String passwordOfInterviewer)
	{
		webDriverUtilFunctions.click(signInWithHackerRankForWorkButton);
		waitForPageRender(webDriver, 2);
		interviewerEmailInput.clear();
		webDriverUtilFunctions.typeKeys(interviewerEmailInput, emailIdOfInterviewer);
		interviewerPasswordInput.clear();
		webDriverUtilFunctions.typeKeys(interviewerPasswordInput, passwordOfInterviewer);
		webDriverUtilFunctions.click(hideCandidateNameCheckbox);
		LiveInterviewPage liveInterviewPage=(LiveInterviewPage) webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.LiveInterviewPage");
		webDriverUtilFunctions.click(startInterviewButton);
		liveInterviewPage.isLoaded();
		return liveInterviewPage;
		
	}
	public LiveInterviewPage startInterviewAsCandidate(String nameOfCandidate)
	{
		candidateNameInput.clear();
		webDriverUtilFunctions.typeKeys(candidateNameInput, nameOfCandidate+Keys.TAB);
		LiveInterviewPage liveInterviewPage=(LiveInterviewPage) webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.LiveInterviewPage");
		webDriverUtilFunctions.click(joinAsCandidateButton);
		liveInterviewPage.isLoaded();
		return liveInterviewPage;
		
	}
	

}
