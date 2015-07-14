/*
 *  Data provider for login
 */
package net.project.dataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;




import org.testng.annotations.DataProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class loginDataProvider.
 */
public class loginDataProvider {
	
	/**
	 * Manager login data.
	 *
	 * @return the object[][]
	 */
	@DataProvider(name="managerLoginData")
	public  Object[][] managerLoginData()
	{
		
		ExcelInteraction eI=new ExcelInteraction();
		LinkedHashMap<String, List<LinkedHashMap<String, String>>> data=null;
		try {
			data = eI.getData("D:\\TestCases.xls");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<LinkedHashMap <String, String>> sheet=data.get("managerLogin");
		LinkedHashMap <String, String> firstRow=sheet.get(0);
		List<String> columns=new ArrayList<String>();		
		int maxNumberOfColumns=0;
		int numberOfRows=0;
		for(LinkedHashMap <String, String> link1: sheet)
		{
			if(link1.values().size()>maxNumberOfColumns)
			{
				maxNumberOfColumns=link1.values().size();
			}
			numberOfRows++;
		}
		numberOfRows--;
		String[][] testData=new String[numberOfRows][maxNumberOfColumns];		
		int j=0;
		for(String columnNames: firstRow.keySet())
		{
			columns.add(firstRow.get(columnNames));
		}
		
		for(LinkedHashMap <String, String> map1: sheet)
		{
			if(!map1.get(columns.get(0)).equals(columns.get(0)))		
			{
				for(int i=0; i<columns.size(); i++)
				{
					testData[j][i]=map1.get(columns.get(i));
				}
				j++;		
			}
				
		}		
		return testData;
	}
	
	/**
	 * Employee login data.
	 *
	 * @return the object[][]
	 */
	@DataProvider(name="employeeLoginData")
	public  Object[][] employeeLoginData()
	{
		
		ExcelInteraction eI=new ExcelInteraction();
		LinkedHashMap<String, List<LinkedHashMap<String, String>>> data=null;
		try {
			data = eI.getData("D:\\TestCases.xls");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<LinkedHashMap <String, String>> sheet=data.get("employeeLogin");
		LinkedHashMap <String, String> firstRow=sheet.get(0);
		List<String> columns=new ArrayList<String>();		
		int maxNumberOfColumns=0;
		int numberOfRows=0;
		for(LinkedHashMap <String, String> link1: sheet)
		{
			if(link1.values().size()>maxNumberOfColumns)
			{
				maxNumberOfColumns=link1.values().size();
			}
			numberOfRows++;
		}
		numberOfRows--;
		String[][] testData=new String[numberOfRows][maxNumberOfColumns];		
		int j=0;
		for(String columnNames: firstRow.keySet())
		{
			columns.add(firstRow.get(columnNames));
		}
		
		for(LinkedHashMap <String, String> map1: sheet)
		{
			if(!map1.get(columns.get(0)).equals(columns.get(0)))		
			{
				for(int i=0; i<columns.size(); i++)
				{
					testData[j][i]=map1.get(columns.get(i));
				}
				j++;		
			}
				
		}		
		return testData;
	}
	
}
