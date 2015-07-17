/*
 * 
 */
package net.project.testFactories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.project.dataAccess.*;
import net.project.listeners.AnnotationsListener;
import net.project.loggers.AppLogger;
import net.project.webDriverUtils.InlineCompiler;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
// TODO: Auto-generated Javadoc
/**
 * A factory for creating CompleteTest objects.
 */
public class GlobalTestFactory {
	
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
	@Parameters({"browser","testCaseDocument","testCaseSheet","testName"})
	@Factory
	public Object[] createnewInstances(String browser, String testCaseDocument , String testCaseSheet , String testName ) {  
		webDriverUtilFunctions=new WebDriverUtilFunctions();
		//webDriver=webDriverUtilFunctions.setupTest(browser);
		ExcelInteraction eI=new ExcelInteraction();
		Map<String, List<LinkedHashMap<String, String>>> data=null;
		try {
			data = eI.getData("src/test/resources/testcasedocs/"+testCaseDocument+".xls");

		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		List<LinkedHashMap<String, String>> sheet=data.get(testCaseSheet);
		AppLogger.logInfo("---------Content of testcases sheet is-------------:"+ sheet.toString());
		List<Object> listOfObjects=new ArrayList<Object>();
		Map<String, String> firstRow=sheet.get(0);
		List<String> columns=new ArrayList<String>();
		for(String columnNames: firstRow.keySet())
		{
			columns.add(firstRow.get(columnNames));
		}
		AppLogger.logInfo("Columns in the current testcases sheet is: "+columns.toString());
		Map<String,Object> mapOfTestContextParameters =new HashMap<String, Object>();
		mapOfTestContextParameters.put("testCaseSheet", testCaseSheet);
		mapOfTestContextParameters.put("testCaseDocument", testCaseDocument);
		mapOfTestContextParameters.put("webDriver", webDriver);
		Map<String,List<Map<String,String>>> map2=new LinkedHashMap<String,List<Map<String,String>>>();
		Map<String,List<Map<String,String>>> map3=new LinkedHashMap<String,List<Map<String,String>>>();
		Map<String,List<Map<String,String>>> map4=new LinkedHashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> listOfSteps=null;
		List<Map<String,String>> listOfParametersFromDataProvider=null;
		List<Map<String,String>> listOfPreTestSteps=null;
		Map <String, String> stepsMap=null;
		Map <String, String> parameterFromDataProviderMap=null;
		Map <String, String> preTestStepsMap=null;
		List<String> parametersOfTestCases=null;
		Map<String,List<String>> mapOfTestCaseParameters=new LinkedHashMap<String, List<String>>();
		System.out.println("The sheet: "+sheet.toString());
		
		for(Map<String, String> map1: sheet)
		{
			
				if(map1.get(columns.get(0)).equals(columns.get(0)))
				{
					continue;
				}				
				

				
				if(map2.get(map1.get(columns.get(0)))!=null || map1.get(columns.get(0)).equals(""))
						{
							stepsMap=new LinkedHashMap<String, String>();	
							parameterFromDataProviderMap=new LinkedHashMap<String, String>();	
							preTestStepsMap=new LinkedHashMap<String, String>();
							listOfSteps.add(stepsMap);	
							listOfParametersFromDataProvider.add(parameterFromDataProviderMap);
							listOfPreTestSteps.add(preTestStepsMap);
							stepsMap.put(map1.get(columns.get(1)), map1.get(columns.get(2)));
							parameterFromDataProviderMap.put(map1.get(columns.get(1)), map1.get(columns.get(10)));
							preTestStepsMap.put(map1.get(columns.get(6)), map1.get(columns.get(7)));				
							
						}
				else
				{
					listOfSteps=new ArrayList<Map<String,String>>();
					listOfParametersFromDataProvider=new ArrayList<Map<String,String>>();
					listOfPreTestSteps=new ArrayList<Map<String,String>>();
					stepsMap=new LinkedHashMap<String, String>();	
					parameterFromDataProviderMap=new LinkedHashMap<String, String>();	
					preTestStepsMap=new LinkedHashMap<String, String>();
					parametersOfTestCases=new ArrayList<String>();
					listOfSteps.add(stepsMap);	
					listOfParametersFromDataProvider.add(parameterFromDataProviderMap);
					listOfPreTestSteps.add(preTestStepsMap);						
					stepsMap.put(map1.get(columns.get(1)), map1.get(columns.get(2)));		
					parameterFromDataProviderMap.put(map1.get(columns.get(1)), map1.get(columns.get(10)));
					preTestStepsMap.put(map1.get(columns.get(6)), map1.get(columns.get(7)));				
					parametersOfTestCases.add(map1.get(columns.get(3)));
					parametersOfTestCases.add(map1.get(columns.get(4)));
					parametersOfTestCases.add(map1.get(columns.get(0))+":"+map1.get(columns.get(5)));
					parametersOfTestCases.add(map1.get(columns.get(8)));
					parametersOfTestCases.add(map1.get(columns.get(9)));
					parametersOfTestCases.add(map1.get(columns.get(11)));
					parametersOfTestCases.add(map1.get(columns.get(12)));
					mapOfTestCaseParameters.put(map1.get(columns.get(0)), parametersOfTestCases);
					map2.put(map1.get(columns.get(0)), listOfSteps);
					map3.put(map1.get(columns.get(0)), listOfPreTestSteps);
					map4.put(map1.get(columns.get(0)), listOfParametersFromDataProvider);
					
				}		
				
		}
		AppLogger.logInfo("Required map of testcase steps is: "+map2.toString());
		AppLogger.logInfo("Required map of pretestcase steps is : "+map3.toString());
		AppLogger.logInfo("Required map of parameterindexesfromDataProvider is: "+map4.toString());
		AnnotationsListener.mapOfTestSteps.put(testName, map2);
		AnnotationsListener.mapOfPreTestCaseSteps.put(testName, map3);	
		AnnotationsListener.mapOfParametersFromDataProviders.put(testName, map4);
		AppLogger.logInfo("mapOfTestCaseParameters is : "+mapOfTestCaseParameters.toString());
		AnnotationsListener.mapOfTestsAndTestCases.put(testName, mapOfTestCaseParameters);
		AnnotationsListener.mapOfTestContextParameters.put(testName, mapOfTestContextParameters);
		BufferedReader bufferedReader=null;
		try {
			bufferedReader= new BufferedReader(new FileReader("src/test/resources/testcasedocs/SampleTestClass.txt"));
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			AppLogger.logError("Not able to create testClass file"+e.getCause());
		}
		String line=null;
		StringBuilder stringBuilder1=new StringBuilder();
		try {
			while ((line = bufferedReader.readLine()) != null) {
			   stringBuilder1.append("\n"+line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			AppLogger.logError(e.getMessage());
		}
		try {
			bufferedReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		StringBuilder stringBuilder2=null;
		
		stringBuilder2=new StringBuilder();
		int countOfTestCases=1;
		for(@SuppressWarnings("unused") String testcase: mapOfTestCaseParameters.keySet())
		{
			try {
				
				bufferedReader= new BufferedReader(new FileReader("src/test/resources/testcasedocs/sampleTestCase.txt"));
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				AppLogger.logError("Not able to create testClass file"+e.getCause());
			}
			
			try {
				while ((line = bufferedReader.readLine()) != null) {
					
				   stringBuilder2.append("\n"+line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				AppLogger.logError(e.getMessage());
			}
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stringBuilder1.append("\n@Test(enabled=false, testName="+"\""+"sample"+countOfTestCases+"\""+",description="+"\""+":Just a sample testCase "+"\""+")"+"\npublic void sample"+countOfTestCases+"(String data)");
			stringBuilder1.append(stringBuilder2);
			stringBuilder2=new StringBuilder();
			countOfTestCases++;
			
		}
		stringBuilder1.append("\n}");
		BufferedWriter bufferedWriter=null;
		try {		
		
		bufferedWriter=new BufferedWriter(new FileWriter("src/test/java/net/project/testClasses/MySampleTestClass.java"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bufferedWriter.flush();
			bufferedWriter.write(stringBuilder1.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InlineCompiler inlineCompiler=new InlineCompiler();
		AppLogger.logInfo("Now compiling testClass");
		inlineCompiler.createJavaClass("net/project/testClasses/MySampleTestClass","net.project.testClasses.MySampleTestClass");
		try {
			Class.forName("net.project.testClasses.MySampleTestClass");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listOfObjects.add(createSampleTestClasses("net.project.testClasses.MySampleTestClass"));
		listOfObjects.add(createPlainClasses("net.project.testClasses.TestWebDriverShutdown"));
		AppLogger.logInfo("list of test classes added to the current Test factory"+listOfObjects.toString());
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
	public Object createTestClasses(String testClassToBeCreated,List<Map<String,String>> listOfTestMethods) 
	{
		AppLogger.logInfo("Creating testclass: "+testClassToBeCreated);
		Constructor constructor=null;
		try {
			constructor = Class.forName(testClassToBeCreated).getConstructors()[0];
		} catch (SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object obj=null;
		try {
			obj = constructor.newInstance(webDriver, listOfTestMethods);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return obj;
	}
	@SuppressWarnings("rawtypes")
	public Object createPlainClasses(String testClassToBeCreated) 
	{
		AppLogger.logInfo("Creating testclass: "+testClassToBeCreated);
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
	@SuppressWarnings("rawtypes")
	public Object createDatabaseTestClasses(String testClassToBeCreated,List<Map<String,String>> listOfTestMethods) 
	{
		AppLogger.logInfo("Creating testclass: "+testClassToBeCreated);
		Constructor constructor=null;
		try {
			constructor = Class.forName(testClassToBeCreated).getConstructors()[0];
		} catch (SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object obj=null;
		try {
			obj = constructor.newInstance(listOfTestMethods);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return obj;
	}
	@SuppressWarnings("rawtypes")
	public Object createSampleTestClasses(String testClassToBeCreated) 
	{
		AppLogger.logInfo("Creating testclass: "+testClassToBeCreated);
		Constructor constructor=null;
		try {
			constructor = Class.forName(testClassToBeCreated).getConstructors()[0];
		} catch (SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object obj=null;
		try {
			obj = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return obj;
	}
}
