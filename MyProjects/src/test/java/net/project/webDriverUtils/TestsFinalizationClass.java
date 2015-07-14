package net.project.webDriverUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.project.loggers.AppLogger;

import org.apache.commons.io.FileDeleteStrategy;

public class TestsFinalizationClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
		Map<String,ArrayList<String>> mapOfSuitesAndTestCases=WebDriverUtilFunctions.mapOfSuitesAndTestCases;
		
				@SuppressWarnings("unused")
				List<String> tests=null;
				AppLogger.logInfo(mapOfSuitesAndTestCases.toString());
				for (String suiteName: mapOfSuitesAndTestCases.keySet())
				{
					tests= mapOfSuitesAndTestCases.get(suiteName);
					
					
				}
				
			  File	folderToBeCleaned=new File("target/surefire-reports/old/");
				try {
					System.gc();
			        Thread.sleep(1000);
					FileDeleteStrategy.FORCE.delete(folderToBeCleaned);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					AppLogger.logError("Folder: "+"target/surefire-reports/old/"+ " not cleaned successfully"+ e.getCause());
				}
				
				String DATE_FORMAT = "yyyyMMddHHmmss";
			    SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
			    Calendar c1 = Calendar.getInstance();
				String zipFileTimeStamp=sdf.format(c1.getTime());
				new File("D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp).mkdir();
				try {
					webDriverUtilFunctions.zipFolder("target/surefire-reports", "D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp+"/reports.zip");
					webDriverUtilFunctions.zipFolder("target/generated-test-sources", "D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp+"/generated-test-sources.zip");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					AppLogger.logError("Not able to create zip files: "+e.getCause());
				}
				ArrayList<String> fileNames =new ArrayList<String>();
				fileNames.add("D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp+"/reports.zip");
				fileNames.add("D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp+"/generated-test-sources.zip");
				fileNames.add("target/surefire-reports/emailable-report.html");
				fileNames.add("target/surefire-reports/customized-emailable-report.html");
				folderToBeCleaned.mkdir();
				/*try {
					WebDriverUtilFunctions.emailFile("adarsha.shetty.1989@gmail.com", "xiivvvaxucyuhqyr", "adarsha.shetty.1989@gmail.com", "adarsha.shetty.1989@gmail.com",fileNames);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					AppLogger.logErrorWithException(e.getMessage(), e);
				}*/
				try {
					WebDriverUtilFunctions.emailFile("qaanalyst1989@gmail.com", "ltmxwhcfdkflujhs", "qaanalyst1989@gmail.com", "qaanalyst1989@gmail.com",fileNames);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					AppLogger.logErrorWithException(e.getMessage(), e);
				}
	}

}
