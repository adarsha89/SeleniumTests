package net.project.dataProviders.HackerrankForWork.CodePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.project.dataAccess.SampleCSVDataAccessInterface;

import org.testng.annotations.DataProvider;

public class CandidateAndInterviewerInfoProvider {

	
	@DataProvider(name = "candidateAndInterviewerInfoProviderMethod")
	 
	  public static Iterator<String[]> candidateAndInterviewerInfoProviderMethod() {
		  List<String[]> list1=new ArrayList<String[]>();
		  SampleCSVDataAccessInterface sampleCSVDataAccessInterface=new SampleCSVDataAccessInterface();
			for(List<String> list: sampleCSVDataAccessInterface.getDataFromCSVFile("src/test/resources/testcasedocs/CandidateAndInterviewerInfoProvider.csv"))
			{
				list1.add(list.toArray(new String[list.size()]));
			}
			return list1.iterator();
	  }
}
