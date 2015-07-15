package net.project.dataAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.project.loggers.AppLogger;

public class SampleCSVDataAccessInterface {
	 List<List<String>> listOfListOfData=new ArrayList<List<String>>();
	public List<List<String>> getDataFromCSVFile(String csvFile)
	{
		BufferedReader inputStream = null;
		 
		  List<String> listOfString=null;
		  try {
	          try {
				inputStream = new BufferedReader(new FileReader(csvFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				AppLogger.logInfo("Not able to find the file"+csvFile);
			}
	          
	          String l;
	          try {
				while ((l = inputStream.readLine()) != null) {
					  listOfString=new ArrayList<String>();
				      listOfString=Arrays.asList(l.split(","));
				      listOfListOfData.add(listOfString);
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
		  return listOfListOfData;
	}
	  
	
public static void main(String[] args)
{
	SampleCSVDataAccessInterface sampleCSVDataAccessInterface=new SampleCSVDataAccessInterface();
	System.out.println(sampleCSVDataAccessInterface.getDataFromCSVFile("src/test/resources/testcasedocs/InterviewData.csv"));
}
}
