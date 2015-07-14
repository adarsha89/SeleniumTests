/*
 * 
 */
package net.project.testFactories;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.project.dataAccess.ExcelInteraction;
import net.project.testClasses.TestWebDriverShutdown;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating CompleteTest objects.
 */
public class CompleteTestFactory {
	
	/** The web driver. */
	WebDriver webDriver;
	
	/** The web driver util functions. */
	WebDriverUtilFunctions webDriverUtilFunctions;
	
	/**
	 * Creates a new CompleteTest object.
	 *
	 * @param browser the browser
	 * @return the object[]
	 */
	@Parameters("browser")
	@Factory     
	public Object[] createnewInstances(String browser ) {  
		webDriverUtilFunctions=new WebDriverUtilFunctions();
		webDriver=webDriverUtilFunctions.setupTest(browser);
		ExcelInteraction eI=new ExcelInteraction();
		LinkedHashMap<String, List<LinkedHashMap<String, String>>> data=null;
		try {
			data = eI.getData("src/test/resources/testcasedocs/TestCases.xls");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		List<LinkedHashMap <String, String>> sheet=data.get("TestCases");
		List<Object> listOfObjects=new ArrayList<Object>();
		LinkedHashMap <String, String> firstRow=sheet.get(0);
		List<String> columns=new ArrayList<String>();
		for(String columnNames: firstRow.keySet())
		{
			columns.add(firstRow.get(columnNames));
		}
		int columnCount=columns.size();
		LinkedHashMap<String, String> map2=new LinkedHashMap <String, String>();
		for(LinkedHashMap <String, String> map1: sheet)
		{
			for(int i=0;i<columnCount;i++)
			{
				if(map1.get(columns.get(i)).equals(columns.get(i)))
				{
					continue;
				}				
				map2.put(columns.get(i), map1.get(columns.get(i)));
				
			}
			if(!map2.isEmpty())
			{
				listOfObjects.add(createTestClasses(map1.get(columns.get(0)),map2));
				map2.clear();
			}
			
		}
		listOfObjects.add(new TestWebDriverShutdown(webDriver));
		return listOfObjects.toArray();  		    
}
	
	/**
	 * Creates a new CompleteTest object.
	 *
	 * @param testClassToBeCreated the test class to be created
	 * @param map the map
	 * @return the object
	 */
	@SuppressWarnings("rawtypes")
	public Object createTestClasses(String testClassToBeCreated,LinkedHashMap<String,String> map) 
	{
		Constructor constructor=null;
		try {
			constructor = Class.forName(testClassToBeCreated).getConstructors()[0];
		} catch (SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object obj=null;
		try {
			obj = constructor.newInstance(webDriver);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
}
