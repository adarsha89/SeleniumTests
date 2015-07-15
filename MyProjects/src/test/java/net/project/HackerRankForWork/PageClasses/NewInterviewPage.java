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

public class NewInterviewPage extends LoadableComponent<NewInterviewPage> implements CommonPageInterface{
	WebDriver webDriver=null;
	WebDriverUtilFunctions webDriverUtilFunctions=null;
	
	public CodePairPage codePairPage=null;
	
	@FindBy(how=How.CSS , using="a[class='normal-underline mdL']")
	@CacheLookup
	public WebElement interviewLink;
	
	@FindBy(how=How.CSS , using="a[data-role='candidate']")
	@CacheLookup
	public WebElement candidateEditInfoLink;
	
	@FindBy(how=How.CSS , using="div[id='candidate-details-container']>div>table>tbody>tr>td:nth-child(1)")
	@CacheLookup
	public WebElement candidateName;
	
	@FindBy(how=How.CSS , using="div[id='candidate-details-container']>div>table>tbody>tr>td:nth-child(2)")
	@CacheLookup
	public WebElement candidateEmailId;
	
	@FindBy(how=How.CSS , using="div[id='candidate-details-container']>div>table>tbody>tr>td:nth-child(3)")
	@CacheLookup
	public WebElement candidatePhoneNumber;
	
	
	@FindBy(how=How.CSS , using="div[class='txtbox_holder']>input[name='name']")
	public WebElement editNameInput;
	
	@FindBy(how=How.CSS , using="div[class='txtbox_holder']>input[name='email']")
	public WebElement editEmailInput;
	
	@FindBy(how=How.CSS , using="div[class='txtbox_holder']>input[name='phone']")
	public WebElement editPhoneNumberInput;
	
	@FindBy(how=How.CSS , using="div[id='interviewer-details-container']>div>table>tbody>tr>td:nth-child(1)")
	@CacheLookup
	public List<WebElement> listOfInterviewerNames;
	
	@FindBy(how=How.CSS , using="button[data-loading-text='Saving...']")
	public WebElement saveChangesButton;
	
	
	
	public NewInterviewPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		codePairPage=(CodePairPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.CodePairPage");

		
	}
	String url="https://www.hackerrank.com/x/interviews/mypads/";
	public String getInterviewLink()
	{
		return webDriverUtilFunctions.getAttributeOfElement(interviewLink, "href");
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,7);
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"New Interview page is not loaded");
	
	}

	public void clickOnCandidateEditInfoLink()
	{
		webDriverUtilFunctions.click(candidateEditInfoLink);
		waitForPageRender(webDriver, 1);
	}
	
	public List<String> getInterviewerNames()
	{
		List<String> interviewers=new ArrayList<String>();
		for(WebElement webE: listOfInterviewerNames)
		{
			interviewers.add(webE.getText());
		}
		System.out.println("Interviewers are: "+interviewers.toString());
		return interviewers;
	}
	
	public void editCandidateInfo()
	{
		webDriverUtilFunctions.click(candidateEditInfoLink);
		waitForPageRender(webDriver, 5);
		editNameInput.clear();
		editNameInput.sendKeys("abrak");
		//editEmailInput.clear();
		//editEmailInput.sendKeys("abrak99@gmail.com");
		editPhoneNumberInput.clear();
		editPhoneNumberInput.sendKeys("9898989898");
		webDriverUtilFunctions.click(saveChangesButton);
	}
}
