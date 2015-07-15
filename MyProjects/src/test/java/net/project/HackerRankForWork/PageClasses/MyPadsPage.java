package net.project.HackerRankForWork.PageClasses;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;
import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

public class MyPadsPage extends LoadableComponent<MyPadsPage> implements CommonPageInterface{
	WebDriver webDriver=null;
	WebDriverUtilFunctions webDriverUtilFunctions=null;
	public CodePairPage codePairPage=null;
	
	String url="https://www.hackerrank.com/x/interviews/mypads";
	@FindBy(how=How.CSS , using="input[placeholder='Search by email / title']")
	@CacheLookup
	public WebElement searchByEmailOrTitleInput;
	
	@FindBy(how=How.CSS , using="input[placeholder='When']")
	@CacheLookup
	public WebElement filterByWhenInput;
	
	@FindBy(how=How.CSS , using="input[placeholder='Created At']")
	@CacheLookup
	public WebElement filterByCreatedAtInput;
	
	@FindBy(how=How.CSS , using="table[id='datatable-mypads']>tbody>tr>td:nth-child(2)")
	@CacheLookup	
	public List<WebElement> listOfInterviewTitles;
	
	@FindBy(how=How.CSS , using="table[id='datatable-mypads']>tbody>tr>td:nth-child(3)")
	@CacheLookup	
	public List<WebElement> listOfWhenDateOfInterviews;
	
	@FindBy(how=How.CSS , using="table[id='table[id='datatable-mypads']>tbody>tr>td:nth-child(4)")
	@CacheLookup	
	public List<WebElement> listOfCreatedOnDateOfInterviews;
	
	@FindBy(how=How.CSS , using="table[id='datatable-mypads']>tbody>tr>td:nth-child(5)")
	@CacheLookup	
	public List<WebElement> listOfStatusesOfInterviews;
	
	@FindBy(how=How.CSS , using="tbody[role='alert']>tr>td>a:nth-child(1)")
	@CacheLookup	
	public List<WebElement> listOfLinksInFirstColumn;
	
	@FindBy(how=How.CSS , using="tbody[role='alert']>tr>td>a:nth-child(2)")
	@CacheLookup	
	public List<WebElement> listOfLinksInSecondColumn;
	
	@FindBy(how=How.CSS , using="tbody[role='alert']>tr>td>a:nth-child(2)")
	@CacheLookup	
	public List<WebElement> listOfLinksInThirdColumn;
	
	@FindBy(how=How.CSS , using="select[name='datatable-mypads_length']")
	@CacheLookup	
	public WebElement recordsPerPageDropdown;
	
	@FindBy(how=How.CSS , using="div[class='dataTables_paginate paging_bootstrap pagination']>ul>li[class*='prev']>a")
	@CacheLookup	
	public WebElement previousPageButton;
	
	@FindBy(how=How.CSS , using="div[class='dataTables_paginate paging_bootstrap pagination']>ul>li[class*='next']>a")
	@CacheLookup	
	public WebElement nextPageButton;
	
	@FindBy(how=How.CSS , using="div[class='dataTables_paginate paging_bootstrap pagination']>ul>li[class*='active']")
	@CacheLookup	
	public List<WebElement> individualPageButtons;
	
	@FindBy(how=How.CSS , using="a[data-original-title='Download Excel']")
	@CacheLookup	
	public List<WebElement> downloadExcelLink;
	
	@FindBy(how=How.CSS , using="tbody[role='alert']>tr>td:nth-child(2)")
	@CacheLookup
	public List<WebElement> listOfInterviewerNames;
	
	public MyPadsPage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;	
		this.webDriverUtilFunctions=new WebDriverUtilFunctions();
		codePairPage=(CodePairPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.CodePairPage");

	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void isLoaded() throws Error {
		// TODO Auto-generated method stub
		//webDriverUtilFunctions.fluentwaitForPageLoadUsingUrl(webDriver, 30, url);
		waitForPageRender(webDriver,5);
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"MyPads page is not loaded");
		
	}
	public Boolean verifyPresenceOfInterviewUsingTitle(String title)
	{
		for(WebElement webE : listOfInterviewTitles)
		{
			if(webE.getText().equals(title))
			{
				return true;
			}
		}
		return false;
	}
	
	public List<String> getInterviewerNames()
	{
		List<String> interviewers=new ArrayList<String>();
		for(WebElement webE: listOfInterviewerNames)
		{
			interviewers.add(webE.getText());
		}
		return interviewers;
	}
	public NewInterviewPage goToLatestInterviewDetails(String title )
	{

		NewInterviewPage newInterviewPage=(NewInterviewPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.NewInterviewPage");
		webDriverUtilFunctions.click(listOfInterviewTitles.get(0));
		newInterviewPage.isLoaded();
		return newInterviewPage;
	}
	public NewInterviewPage goToLatestInterviewDetailsUsingLatestInterviewId(String title )
	{

		String jsonString=webDriverUtilFunctions.getJSONStringFromUrl("https://www.interviewstreet.com/api/interviews?api_key=5b68eb247ab6f3d8e25066794649d75e4e08d9a351d128ff42f3445a6addb42e&_&limit=5&order_by=id&order_dir=desc");
		String urlToBeLoaded="https://www.hackerrank.com/x/interviews/mypads/"+jsonString.split(":")[2].split(",")[0];
		NewInterviewPage newInterviewPage=(NewInterviewPage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.NewInterviewPage");
		webDriverUtilFunctions.goToURL(urlToBeLoaded, webDriver);
		newInterviewPage.isLoaded();
		return newInterviewPage;
	}
}
