package net.project.HackerRankForWork.TestClasses;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import net.project.HackerRankForWork.PageClasses.LoginPage;
import net.project.HackerRankForWork.PageClasses.MyPadsPage;
import net.project.HackerRankForWork.PageClasses.NewInterviewPage;
import net.project.HackerRankForWork.PageClasses.ScheduleInterviewPage;
import net.project.HackerRankForWork.PageClasses.TestsPage;
import net.project.HackerRankForWork.PageClasses.WelcomePage;
import net.project.dataProviders.HackerrankForWork.CodePair.CandidateAndInterviewerInfoProvider;
import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SanityTestClass {

	LoginPage loginPage=null;
	String url="";
	WebDriver webDriver;
	WebDriver interviewerWebDriver;
	WebDriver intervieweeWebDriver;
	WebDriverUtilFunctions webDriverUtilFunctions;
	@Parameters({"browser","browserVersion","oS","oSVersion","resolution","testName"})
	public SanityTestClass(String browser,@Optional String browserVersion,@Optional String oS,@Optional String oSVersion,@Optional String resolution,@Optional String testName) {
		// TODO Auto-generated constructor stub
		webDriverUtilFunctions=new WebDriverUtilFunctions();
		this.webDriver=webDriverUtilFunctions.setupTest(browser,browserVersion,oS,oSVersion,resolution,testName);
		
	}
	@BeforeMethod(alwaysRun=true)
	public void checkForHackerrankForWorkLoginPage()
	{
		//Setting up Login page before execution of test cases
		WelcomePage welcomePage=(WelcomePage)webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.WelcomePage");
		loginPage=welcomePage.goToLoginPage();
		loginPage.isLoaded();
		}
	@Test(testName="codePairSanityTest1",alwaysRun=true,priority=1)
	public void codePairSanityTest1()
	{
		TestsPage testsPage=loginPage.performDefaultLogin();
		MyPadsPage myPadsPage=testsPage.goToCodePairPage();
		Calendar now = Calendar.getInstance();
		String padName="test"+now.get(Calendar.YEAR)+now.get(Calendar.MONTH)+now.get(Calendar.DAY_OF_MONTH)+now.get(Calendar.HOUR_OF_DAY)+now.get(Calendar.MINUTE)+now.get(Calendar.SECOND)+now.get(Calendar.MILLISECOND);
		ScheduleInterviewPage scheduleInterviewPage= myPadsPage.codePairPage.createNewInterview();
		scheduleInterviewPage.isLoaded();
		NewInterviewPage newInterviewPage=scheduleInterviewPage.createQuickPad("adarsha+test@hackerrank.com", padName);
		newInterviewPage.isLoaded();
		myPadsPage=newInterviewPage.codePairPage.goToMyPads();
		myPadsPage.isLoaded();
		AppLogger.assertLogTrue(myPadsPage.verifyPresenceOfInterviewUsingTitle(padName), "This interview title is not present");
		loginPage=myPadsPage.codePairPage.logout();
		
	}
	@Test(testName="codePairSanityTest2",priority=2,alwaysRun=true , dataProviderClass=CandidateAndInterviewerInfoProvider.class, dataProvider="candidateAndInterviewerInfoProviderMethod")
	public void codePairSanityTest2(String candidateEmail, String candidateName, String candidatePhoneNumber,String interviewerNamesList)
	{
	Boolean truthValue=null;
	List<String> interviewerNames=Arrays.asList(interviewerNamesList.split(":"));
	TestsPage testsPage=loginPage.performDefaultLogin();
	MyPadsPage myPadsPage=testsPage.goToCodePairPage();
	String padTitle="Interiew with "+candidateName+" by"+interviewerNamesList;
	ScheduleInterviewPage scheduleInterviewPage= myPadsPage.codePairPage.createNewInterview();
	myPadsPage=scheduleInterviewPage.createQuickPadUsingCandidateAndInterviewerInfo(candidateEmail, candidateName,candidatePhoneNumber, interviewerNames);
	//NewInterviewPage newInterviewPage=myPadsPage.goToLatestInterviewDetails(padTitle);
	NewInterviewPage newInterviewPage=myPadsPage.goToLatestInterviewDetailsUsingLatestInterviewId(padTitle);
	List<String> fetchedInterviewerNames=newInterviewPage.getInterviewerNames();
	if(fetchedInterviewerNames.containsAll(scheduleInterviewPage.successfullyUpdatedInterviewerNames) && scheduleInterviewPage.successfullyUpdatedInterviewerNames.containsAll(fetchedInterviewerNames))
	{
		truthValue=true;
	}
	else
	{
		truthValue=false;
	}
	AppLogger.assertLogTrue(truthValue, "Required interviewer names not present in the latest interview");
	newInterviewPage.codePairPage.logout();
	}
	@Test(testName="codePairSanityTest3",priority=3, dataProviderClass=net.project.dataProviders.HackerrankForWork.CodePair.CandidateAndInterviewerInfoProvider.class, dataProvider="candidateAndInterviewerInfoProviderMethod")
	public void codePairSanityTest3(String candidateEmail, String candidateName, String candidatePhoneNumber,String interviewerNamesList)
	{
		Boolean truthValue=null;
		List<String> interviewerNames=Arrays.asList(interviewerNamesList.split(":"));
		TestsPage testsPage=loginPage.performDefaultLogin();
		MyPadsPage myPadsPage=testsPage.goToCodePairPage();
		String padTitle="Interiew with "+candidateName+" by"+interviewerNamesList;
		ScheduleInterviewPage scheduleInterviewPage= myPadsPage.codePairPage.createNewInterview();
		myPadsPage=scheduleInterviewPage.createQuickPadUsingCandidateAndInterviewerInfo(candidateEmail, candidateName,candidatePhoneNumber, interviewerNames);
		//NewInterviewPage newInterviewPage=myPadsPage.goToLatestInterviewDetails(padTitle);
	
		NewInterviewPage newInterviewPage=myPadsPage.goToLatestInterviewDetailsUsingLatestInterviewId(padTitle);
		List<String> fetchedInterviewerNames=newInterviewPage.getInterviewerNames();
		if(fetchedInterviewerNames.containsAll(scheduleInterviewPage.successfullyUpdatedInterviewerNames) && scheduleInterviewPage.successfullyUpdatedInterviewerNames.containsAll(fetchedInterviewerNames))
		{
			truthValue=true;
		}
		else
		{
			truthValue=false;
		}
		AppLogger.assertLogEquals(true,truthValue, "Required interviewer names not present in the latest interview");
		newInterviewPage.editCandidateInfo();
		AppLogger.assertLogEquals(candidateName, newInterviewPage.candidateName.getText(), "Candidate name is not as expected");
		AppLogger.assertLogEquals(candidateEmail, newInterviewPage.candidateEmailId.getText(), "Candidate emailId is not as expected");
		AppLogger.assertLogEquals(candidatePhoneNumber, newInterviewPage.candidatePhoneNumber.getText(), "Candidate phone number is not as expected");
		newInterviewPage.codePairPage.logout();
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDownInterviewTask()
	{
		
		
		webDriverUtilFunctions.closeAndQuitWebDriver(webDriver);
	}

}
