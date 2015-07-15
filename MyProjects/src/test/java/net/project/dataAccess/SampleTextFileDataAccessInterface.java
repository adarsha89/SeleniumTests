package net.project.dataAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.project.loggers.AppLogger;

public class SampleTextFileDataAccessInterface {

	 
	public List<String> getDataFromTextFile(String textFile)
	{
		List<String> listOfData=new ArrayList<String>();
		BufferedReader inputStream = null;
		  try {
	          try {
				inputStream = new BufferedReader(new FileReader(textFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				AppLogger.logInfo("Not able to find the file"+textFile);
			}
	          
	          String l;
	          try {
				while ((l = inputStream.readLine()) != null) {
					  listOfData.add(l);
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				AppLogger.logInfo("IOException");
			}
	      } finally {
	          if (inputStream != null) {
	              try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					AppLogger.logInfo("IOException");
				}
	          }
	      }
		  return listOfData;
	}
	  
	
public static void main(String[] args)
{
	SampleTextFileDataAccessInterface sampleTextFileDataAccessInterface=new SampleTextFileDataAccessInterface();
	System.out.println(sampleTextFileDataAccessInterface.getDataFromTextFile("src/test/resources/testcasedocs/InterviewData.csv"));
}

}
