package net.project.dataProviders.HackerrankForWork.CodePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.project.dataAccess.SampleCSVDataAccessInterface;
import org.testng.annotations.DataProvider;

public class InterviewerCredentialsProvider {


	 /**
	 * Sample data provider method.
	 *
	 * @return the iterator
	 */
	@DataProvider(name = "interviewerCredentialsDataProviderMethod")
	 
	  public static Iterator<String[]> interviewerCredentialsDataProviderMethod() {
		
		  List<String[]> list1=new ArrayList<String[]>();
		 SampleCSVDataAccessInterface sampleCSVDataAccessInterface=new SampleCSVDataAccessInterface();
			for(List<String> list: sampleCSVDataAccessInterface.getDataFromCSVFile("src/test/resources/testcasedocs/InterviewData.csv"))
			{
				list1.add(list.toArray(new String[list.size()]));
			}
			
		 
		 
		 return list1.iterator();
	     
	 
	  }
	 
	

}
