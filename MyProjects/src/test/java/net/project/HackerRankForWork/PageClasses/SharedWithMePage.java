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

public class SharedWithMePage extends LoadableComponent<SharedWithMePage> implements CommonPageInterface{

	WebDriver webDriver=null;
	WebDriverUtilFunctions webDriverUtilFunctions=null;
	CodePairPage codePairPage=null;
	
	String url="https://www.hackerrank.com/x/interviews/shared";
	
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
	
	@FindBy(how=How.CSS , using="div[id='interviewer-details-container']>div>table>tbody>tr>td:nth-child(1)")
	@CacheLookup
	public List<WebElement> listOfInterviewerNames;
	
	public SharedWithMePage(WebDriver webDriver) {
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
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageRender(webDriver,1);
		AppLogger.assertLogEquals(webDriverUtilFunctions.getCurrentUrl(webDriver),url,"Shared with me page is not loaded");
		
	}
	public void goToLatestInterviewDetails(String title )
	{
		webDriverUtilFunctions.click(listOfInterviewTitles.get(0));

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
}
