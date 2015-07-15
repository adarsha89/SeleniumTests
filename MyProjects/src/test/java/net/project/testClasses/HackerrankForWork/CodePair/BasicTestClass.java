package net.project.testClasses.HackerrankForWork.CodePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.project.HackerRankForWork.PageClasses.InterviewLoginPage;
import net.project.HackerRankForWork.PageClasses.LiveInterviewPage;
import net.project.HackerRankForWork.PageClasses.LoginPage;
import net.project.HackerRankForWork.PageClasses.MyPadsPage;
import net.project.HackerRankForWork.PageClasses.NewInterviewPage;
import net.project.HackerRankForWork.PageClasses.ScheduleInterviewPage;
import net.project.HackerRankForWork.PageClasses.TestsPage;
import net.project.HackerRankForWork.PageClasses.WelcomePage;
import net.project.dataAccess.SampleTextFileDataAccessInterface;
import net.project.loggers.AppLogger;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BasicTestClass {
	
public String interviewLink=null;
WebDriver webDriver;
WebDriver interviewerWebDriver;
WebDriver intervieweeWebDriver;
WebDriverUtilFunctions webDriverUtilFunctions;
@Parameters({"browser","browser1","browser2"})
public BasicTestClass(String browser, String browser1, String browser2) {
	webDriverUtilFunctions=new WebDriverUtilFunctions();
	this.webDriver=webDriverUtilFunctions.setupTest(browser);
	this.interviewerWebDriver=webDriverUtilFunctions.setupTest(browser1);
	this.intervieweeWebDriver=webDriverUtilFunctions.setupTest(browser2);
}

	@BeforeClass(alwaysRun=true)
	public void setupInterviewTask()
	{
		WelcomePage welcomePage=(WelcomePage) webDriverUtilFunctions.instantiatePage(webDriver, "net.project.HackerRankForWork.PageClasses.WelcomePage");
		
		LoginPage loginPage=welcomePage.goToLoginPage();
		TestsPage testsPage=loginPage.performDefaultLogin();
		MyPadsPage myPadsPage=testsPage.goToCodePairPage();
		ScheduleInterviewPage scheduleInterviewPage= myPadsPage.codePairPage.createNewInterview();
		NewInterviewPage newInterviewPage=scheduleInterviewPage.createQuickPad("adarsha+dev@hackerrank.com", "test1");
		newInterviewPage.isLoaded();
		interviewLink=newInterviewPage.getInterviewLink();
		newInterviewPage.codePairPage.logout();
		
		
	}
	

	@Test(testName="interviewerTest",priority=1, dataProviderClass=net.project.dataProviders.HackerrankForWork.CodePair.InterviewerCredentialsProvider.class, dataProvider="interviewerCredentialsDataProviderMethod")
	public void testFromInterviewerSide(String email, String password)
	{
		InterviewLoginPage interviewLoginPage=(InterviewLoginPage) webDriverUtilFunctions.instantiatePageWithUrl(interviewerWebDriver, interviewLink, "net.project.HackerRankForWork.PageClasses.InterviewLoginPage");		
		interviewLoginPage.isLoaded();
		LiveInterviewPage liveInterviewPage=interviewLoginPage.startInterviewAsInterviewer(email, password);
		
	}
	@Test(testName="intervieweeTest",priority=2 , dataProviderClass=net.project.dataProviders.HackerrankForWork.CodePair.IntervieweeDataProvider.class, dataProvider="intervieweeDataProviderMethod")
	public void testFromIntervieweeSide(String intervieweeName,String programmingLanguage, String codePath, String inputPath){
		InterviewLoginPage interviewLoginPage=(InterviewLoginPage) webDriverUtilFunctions.instantiatePageWithUrl(intervieweeWebDriver, interviewLink, "net.project.HackerRankForWork.PageClasses.InterviewLoginPage");		
		interviewLoginPage.isLoaded();
		LiveInterviewPage liveInterviewPage=interviewLoginPage.startInterviewAsCandidate(intervieweeName);
		liveInterviewPage.endTour();
		liveInterviewPage.selectLanguageUsingLanguageList(programmingLanguage);
		liveInterviewPage.discardMyCode();
		//The entire page view should load completely
		
		
		SampleTextFileDataAccessInterface sampleTextFileDataAccessInterface=new SampleTextFileDataAccessInterface();
		List<String> codeList=sampleTextFileDataAccessInterface.getDataFromTextFile(codePath);
		StringBuilder stringBuilder = new StringBuilder();
		liveInterviewPage.enterCodeInEditor(codeList);
		codeList=sampleTextFileDataAccessInterface.getDataFromTextFile(inputPath);
		for (String s : codeList)
		{
		    stringBuilder.append(s);
		}
		liveInterviewPage.enterInput(stringBuilder.toString());
		liveInterviewPage.runCode();
		
		liveInterviewPage.continueWithRunningCode();
		AppLogger.assertLogTrue(webDriverUtilFunctions.getWebElementByCss(intervieweeWebDriver, "span[class='success']", 10).isDisplayed(),"Compilation failed");
	}
	
	public static void main(String[] args) {

		StringBuilder outputComplete=new StringBuilder();
		String output=null;
		  try {
	 
			URL url = new URL("https://www.interviewstreet.com/api/interviews?api_key=5b68eb247ab6f3d8e25066794649d75e4e08d9a351d128ff42f3445a6addb42e&_&limit=5&order_by=id&order_dir=desc");
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
		  System.out.println(outputComplete);
		  output=outputComplete.toString().split(":")[2].split(",")[0];
		  System.out.println(output);
		    }
		  
	 
		
	 
	
	@AfterClass(alwaysRun=true)
	public void tearDownInterviewTask()
	{
		
		
		//webDriverUtilFunctions.closeAndQuitWebDriver(webDriver);
		//webDriverUtilFunctions.closeAndQuitWebDriver(interviewerWebDriver);
		//webDriverUtilFunctions.closeAndQuitWebDriver(intervieweeWebDriver);
	}

}
