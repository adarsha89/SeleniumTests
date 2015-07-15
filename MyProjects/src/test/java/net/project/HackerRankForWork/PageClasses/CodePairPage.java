package net.project.HackerRankForWork.PageClasses;

import net.project.webDriverUtils.WebDriverUtilFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

public class CodePairPage extends LoadableComponent<CodePairPage> implements CommonPageInterface{

	/** The url. */
	
	/** The web driver. */
	public  WebDriver webDriver;
	
	/** The web driver util functions. */
	public WebDriverUtilFunctions webDriverUtilFunctions;
	
	@FindBy(how=How.CSS , using="a[href='interviews/new']")
	@CacheLookup	
	public WebElement newInterviewButton;
	
	
	
	@FindBy(how=How.CSS , using="a[data-toggle='dropdown'][class*='userbtn']:nth-child(1)")
	@CacheLookup
	public WebElement userDropDown;
	
	@FindBy(how=How.CSS , using="a[class='logout-button']")
	@CacheLookup
	public WebElement logoutLink;
	
	
	@FindBy(how=How.CSS , using="a[href='interviews/mypads']")
	@CacheLookup
	public WebElement myPadsLink;
	
	
	@FindBy(how=How.CSS , using="a[href='interviews/shared']")
	@CacheLookup
	public WebElement sharedWithMeLink;
	
	@FindBy(how=How.CSS , using="a[href='interviews/all'][class*='hre-sidebar-link']")
	@CacheLookup
	public WebElement allPadsLink;
	
	
	
	
	
	
	@FindBy(how=How.CSS , using="div[class='txtbox_holder']>input[name='name']")
	@CacheLookup
	public WebElement editNameInput;
	
	@FindBy(how=How.CSS , using="div[class='txtbox_holder']>input[name='email']")
	@CacheLookup
	public WebElement editEmailInput;
	
	@FindBy(how=How.CSS , using="div[class='txtbox_holder']>input[name='phone']")
	@CacheLookup
	public WebElement editPhoneNumberInput;
	
	@FindBy(how=How.CSS , using="input[name='quick-pad-candidate-email']")
	@CacheLookup
	public WebElement quickPadCandidateEmailInput;
	
	
	
	
	
	public CodePairPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub

		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		
	}
	
	
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,1);
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	public LoginPage logout()
	{
		userDropDown.click();
		LoginPage loginPage=(LoginPage) webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.LoginPage");
		webDriverUtilFunctions.click(logoutLink);
		loginPage.isLoaded();
		return loginPage;
	}
	public MyPadsPage goToMyPads()
	{
		MyPadsPage myPadsPage=(MyPadsPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.MyPadsPage");
		webDriverUtilFunctions.click(myPadsLink);
		myPadsPage.isLoaded();
		return myPadsPage;
	}
	public ScheduleInterviewPage createNewInterview()
	{
		ScheduleInterviewPage scheduleInterviewPage=(ScheduleInterviewPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.ScheduleInterviewPage");
		webDriverUtilFunctions.click(newInterviewButton);
		scheduleInterviewPage.isLoaded();
		return scheduleInterviewPage;		
	}
	
}
