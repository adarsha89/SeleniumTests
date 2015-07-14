package net.project.testClasses;

import net.project.dataProviders.SampleDataProvider;
import net.project.loggers.AppLogger;
import org.testng.annotations.Test;

public class CheckDataProvider {

	
	@Test(dataProviderClass=SampleDataProvider.class,dataProvider="sampleDataProviderMethod")
	public void testt(String listOfArray)
	{
		AppLogger.logInfo("Data is : "+listOfArray);
	}
}
