



package net.project.testClasses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.PrioritizedParameterNameDiscoverer;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;

import net.project.listeners.AnnotationsListener;
import net.project.listeners.AppListener;
import net.project.loggers.AppLogger;
import net.project.pageClasses.BasicLayoutPage;
import net.project.pageClasses.ToolsQAHomePage;
import net.project.webDriverUtils.WebDriverUtilFunctions;
import net.project.webDriverUtils.SpyClass;

import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MySampleTestClass {
	PrioritizedParameterNameDiscoverer prioritizedParameterNameDiscoverer= new PrioritizedParameterNameDiscoverer();
	SpyClass spyObject=new SpyClass();
	Object fieldObject=null;
	Object currentPage=null;
	WebDriver webDriver=null;
	WebDriverUtilFunctions webDriverUtilFunctions=null;
	public MySampleTestClass() {
	}
	
private Map<String, Object> getMapOfTestContextConfigs(String testContextName) {
	// TODO Auto-generated method stub
	return AnnotationsListener.mapOfTestContextParameters.get(testContextName);
}

private String getTestCaseName(String testCaseDescription) {
	// TODO Auto-generated method stub
	return testCaseDescription.split(":")[0];
}

private String getTestContextName() {
	// TODO Auto-generated method stub
	return Reporter.getCurrentTestResult().getTestContext().getName();
}

private String getTestCaseDescription() {
	// TODO Auto-generated method stub
	return Reporter.getCurrentTestResult().getMethod().getDescription();    
}


private void setActualStartTimeOfTestCase() {
	// TODO Auto-generated method stub
	Reporter.getCurrentTestResult().setAttribute("startTime", new Long(System.currentTimeMillis()));
	
}
private List<Map<String, String>> getListOfPreTestCaseMethods(String testContextName, String testCaseName)
{
	return AnnotationsListener.mapOfPreTestCaseSteps.get(testContextName).get(testCaseName);
}
private List<Map<String, String>> getListOfTestMethods(String testContextName,String testCaseName)
{
	return AnnotationsListener.mapOfTestSteps.get(testContextName).get(testCaseName);
}

private List<Map<String, String>> getListOfDataProviderIndex(String testContextName,String testCaseName)
{
	return AnnotationsListener.mapOfParametersFromDataProviders.get(testContextName).get(testCaseName);
}

public  ToolsQAHomePage  goToStartPage(WebDriver webDriver)
{
		ToolsQAHomePage toolsQAHomePage=new ToolsQAHomePage(webDriver);
		toolsQAHomePage.load();
		toolsQAHomePage.isLoaded();
		return toolsQAHomePage;	
}
@Test(enabled=false, testName="sample1",description=":Just a sample testCase ")
public void sample1(String data)

	{
		AppLogger.logInfo("The data obtained is: "+data);		
		String testContextName=getTestContextName();
    	String testCaseDescription=getTestCaseDescription();
    	String testCaseName=getTestCaseName(testCaseDescription);
    	AppLogger.logInfo("Executing beforetest method: "+testCaseName +testContextName);
    	List<Map<String,String>> listOfPreTestCaseMethods=	getListOfPreTestCaseMethods(testContextName, testCaseName);
      	Map<String,Object> testContextConfigs= getMapOfTestContextConfigs(testContextName);
    	webDriver=(WebDriver)testContextConfigs.get("webDriver");
      	webDriverUtilFunctions=new WebDriverUtilFunctions();
    	AppLogger.logInfo("List of pretest methods"+listOfPreTestCaseMethods);
    	if(listOfPreTestCaseMethods==null)
    	{
    		
    	}
    	else
    	{
    	for(Map<String,String> mapOfMethods:  listOfPreTestCaseMethods)
    			{
    		List<String> listOfMethods=new ArrayList<String>();
			for(String methodName: mapOfMethods.keySet())
			{
				listOfMethods.add(methodName);
			}
    		
    				for(String methodName: listOfMethods)
    				{
    					
    					if(currentPage==null)
    					{
    						currentPage=goToStartPage((WebDriver)testContextConfigs.get("webDriver"));
    					}
    					
								
								
   						 Class<?> class1=currentPage.getClass();   	
   						
							AppLogger.logInfo("Fields are: "+spyObject.getAllFieldNames(currentPage));
   						 
							for(Method methodAtPresent: spyObject.getMethodsFromClassObject(class1))
							{
								Integer parameterCount=null;
								String methodParameter=null;
								
								if(spyObject.getNameOfAMethod(methodAtPresent).equals(methodName))
								{
									parameterCount=spyObject.getParameterCountOfMethod(methodAtPresent);
									if(( spyObject.getReturnTypeOfMethod(methodAtPresent).equals("void"))==true)
								{
										
									if(parameterCount ==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										spyObject.invokeAMethodwithNoParameterAndNoReturnValue(methodAtPresent, currentPage);
									}
									else if(parameterCount==1)
									{ 
																			
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
										
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											spyObject.invokeAMethodwithOneParameterAndNoReturnValue(methodAtPresent, currentPage, fieldObject);
											AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +fieldObject);											
										}
										else
										{
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												spyObject.invokeAMethodwithOneParameterAndNoReturnValue(methodAtPresent, currentPage, methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: "+methodParameter);	
												
											}
											else
											{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
											}
											}
										
										
										
									}
									else if (parameterCount==2)
									{
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());		
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												spyObject.invokeAMethodwithTwoParametersAndNoReturnValue(methodAtPresent, currentPage, fieldObject,methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameters: " +fieldObject+" and: "+methodParameter);		
											}
											else{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
												}
																				
										}
										else
										{
											
											AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
											Reporter.getCurrentTestResult().setStatus(3);
										
										}
									}
									
									
										
								}									
								else
								{
									Object currentTemporaryPage=null;
									if(parameterCount ==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										currentPage=spyObject.invokeAMethodwithNoParameterAndReturnValue(methodAtPresent, currentPage);
										
									}
									else if(parameterCount==1)
									{
										
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
										
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											
											AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +fieldObject);											
											
											currentTemporaryPage=spyObject.invokeAMethodwithOneParameterAndReturnValue(methodAtPresent, currentPage, fieldObject);
											if(currentTemporaryPage!=null)
											{
												currentPage=currentTemporaryPage;								
											}	
											
										}
										else
										{
											
											
											
											
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												currentTemporaryPage=spyObject.invokeAMethodwithOneParameterAndReturnValue(methodAtPresent, currentPage, methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: "+methodParameter);	
												
											}
											else
											{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
											
											}
											if(currentTemporaryPage!=null)
											{
												currentPage=currentTemporaryPage;								
											}	
											
										}
										
										
										
									}
									
									else if (parameterCount==2)
									{
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());		
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												currentTemporaryPage=spyObject.invokeAMethodwithTwoParametersAndReturnValue(methodAtPresent, currentPage, fieldObject,methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameters: " +fieldObject+" and: "+methodParameter);		
											}
											else{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
											}
																				
										}
										else
										{
											AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
											Reporter.getCurrentTestResult().setStatus(3);
										
										}
									}
									if(currentTemporaryPage!=null)
									{
										currentPage=currentTemporaryPage;								
									}	
									
									
								}
																		
							}
							}
						
						
					}
    			}
			}	
    	
    			
    	AppLogger.logInfo("Executing  testMethod:"+testCaseName+testContextName);
    	List<Map<String,String>> listOfTestMethods=	getListOfTestMethods(testContextName,testCaseName);
    	List<Map<String,String>> listOfDataProviderIndex=	getListOfDataProviderIndex(testContextName,testCaseName);
    	AppLogger.logInfo("List of data providerIndexes is: "+listOfDataProviderIndex.toString());
    	
    	setActualStartTimeOfTestCase();
    	Integer countOftestStep=-1;
    	Method methodAtPresent=null;
    	for(Map<String,String> mapOfMethods:  listOfTestMethods)
		{
    		List<String> listOfMethods=new ArrayList<String>();
			for(String methodName: mapOfMethods.keySet())
			{
				listOfMethods.add(methodName);
			}
			for(String methodName: listOfMethods)
			{				
				Integer parameterCount=null;					
					 	Class<?> class1=currentPage.getClass();							
					countOftestStep++;
					AppLogger.logInfo("Fields are: "+spyObject.getAllFieldNames(currentPage));
									 
					for(Method methodRefferedTo: spyObject.getMethodsFromClassObject(class1))
					{
						
						if(spyObject.getNameOfAMethod(methodRefferedTo).equals(methodName))
						{
							parameterCount=spyObject.getParameterCountOfMethod(methodRefferedTo);
							methodAtPresent=methodRefferedTo;
						
						}
					}
							if(( spyObject.getReturnTypeOfMethod(methodAtPresent).equals("void"))==true)
						{
								
							if(parameterCount ==0)
							{
								AppLogger.logInfo("Selected method for execution: "+methodName);
								AppLogger.logInfo("current page is: "+currentPage);
								spyObject.invokeAMethodwithNoParameterAndNoReturnValue(methodAtPresent, currentPage);
							
							}
							else 
							{ 
																	
								AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
								
								fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
								List<String> parametersList=null;
								List<String> listOfDataProviderParameters=null;
								List<String> listOfIndex=null;
								if(mapOfMethods.get(methodName)!=null)
								{
									 parametersList=new ArrayList<String>(Arrays.asList(mapOfMethods.get(methodName).split(",")));
									
								}
								if(data!=null)
								{
									listOfDataProviderParameters=new ArrayList<String>( Arrays.asList(data.split(",")));
									AppLogger.logInfo("List of Data provider parameters are: "+ listOfDataProviderParameters.toString());
								}
								if(listOfDataProviderIndex.get(countOftestStep).get(methodName)!=null)
								{
									listOfIndex=Arrays.asList(listOfDataProviderIndex.get(countOftestStep).get(methodName).split(","));
									AppLogger.logInfo("List of Indexes are: "+ listOfIndex.toString());
								}
								Map<Integer,Object> mapOfData=new HashMap<Integer,Object>();
								
								if(fieldObject!=null)
								{
									mapOfData.put(0, fieldObject);
									
									for(Integer i=1;i<parameterCount;i++)
									{
										if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
										{
											mapOfData.put(i, listOfDataProviderParameters.remove(0));
											
										}
										else
										{
											mapOfData.put(i, parametersList.remove(0));
											
											
										}
									}
								}
								else
								{
									for(Integer i=0;i<parameterCount;i++)
									{
										if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
										{
											mapOfData.put(i, listOfDataProviderParameters.remove(0));
											
										}
										else
										{
											mapOfData.put(i, parametersList.remove(0));
										}
									}
									AppLogger.logInfo("Map Of Data is : "+mapOfData.toString());
								}
								if(parameterCount==5)
								{
									spyObject.invokeAMethodwithFiveParametersAndNoReturnValue(methodAtPresent, currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3),mapOfData.get(4));
								}
								if(parameterCount==4)
								{
									spyObject.invokeAMethodwithFourParametersAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3));
								}
								if(parameterCount==3)
								{
									spyObject.invokeAMethodwithThreeParametersAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2));
								}
								if(parameterCount==2)
								{
									spyObject.invokeAMethodwithTwoParametersAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1));
								}
								if(parameterCount==1)
								{
									spyObject.invokeAMethodwithOneParameterAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0));
								}
								
								}
							}
							else
								
							{
							
								

								Object currentTemporaryPage=null;
								if(parameterCount ==0)
								{
									AppLogger.logInfo("Selected method for execution: "+methodName);
									AppLogger.logInfo("current page is: "+currentPage);
									currentTemporaryPage=spyObject.invokeAMethodwithNoParameterAndReturnValue(methodAtPresent, currentPage);
									
								}
								else
								{
									 
									AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
									
									fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
									List<String> parametersList=null;
									List<String> listOfDataProviderParameters=null;
									List<String> listOfIndex=null;
									if(mapOfMethods.get(methodName)!=null)
									{
										 parametersList=new ArrayList<String>(Arrays.asList(mapOfMethods.get(methodName).split(",")));
										
									}
									if(data!=null)
									{
										listOfDataProviderParameters=new ArrayList<String>( Arrays.asList(data.split(",")));
										AppLogger.logInfo("List of Data provider parameters are: "+ listOfDataProviderParameters.toString());
									}
									if(listOfDataProviderIndex.get(countOftestStep).get(methodName)!=null)
									{
										listOfIndex=Arrays.asList(listOfDataProviderIndex.get(countOftestStep).get(methodName).split(","));
										AppLogger.logInfo("List of Indexes are: "+ listOfIndex.toString());
									}
									Map<Integer,Object> mapOfData=new HashMap<Integer,Object>();
									
									if(fieldObject!=null)
									{
										mapOfData.put(0, fieldObject);
										
										for(Integer i=1;i<parameterCount;i++)
										{
											if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
											{
												mapOfData.put(i, listOfDataProviderParameters.remove(0));
												
											}
											else
											{
												mapOfData.put(i, parametersList.remove(0));
												
												
											}
										}
									}
									else
									{
										for(Integer i=0;i<parameterCount;i++)
										{
											if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
											{
												mapOfData.put(i, listOfDataProviderParameters.remove(0));
												
											}
											else
											{
												mapOfData.put(i, parametersList.remove(0));
											}
										}
										AppLogger.logInfo("Map Of Data is : "+mapOfData.toString());
									}
									if(parameterCount==5)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithFiveParametersAndReturnValue(methodAtPresent,currentPage, mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3),mapOfData.get(4));
									}
									if(parameterCount==4)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithFourParametersAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3));
									}
									if(parameterCount==3)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithThreeParametersAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2));
									}
									if(parameterCount==2)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithTwoParametersAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1));
									}
									if(parameterCount==1)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithOneParameterAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0));
									}
									
									
								}	
								if(currentTemporaryPage!=null)
								{
									currentPage=currentTemporaryPage;								
								}
								
							}
							
						}
					
			}
		
	
	try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
	}
@Test(enabled=false, testName="sample2",description=":Just a sample testCase ")
public void sample2(String data)

	{
		AppLogger.logInfo("The data obtained is: "+data);		
		String testContextName=getTestContextName();
    	String testCaseDescription=getTestCaseDescription();
    	String testCaseName=getTestCaseName(testCaseDescription);
    	AppLogger.logInfo("Executing beforetest method: "+testCaseName +testContextName);
    	List<Map<String,String>> listOfPreTestCaseMethods=	getListOfPreTestCaseMethods(testContextName, testCaseName);
      	Map<String,Object> testContextConfigs= getMapOfTestContextConfigs(testContextName);
    	webDriver=(WebDriver)testContextConfigs.get("webDriver");
      	webDriverUtilFunctions=new WebDriverUtilFunctions();
    	AppLogger.logInfo("List of pretest methods"+listOfPreTestCaseMethods);
    	if(listOfPreTestCaseMethods==null)
    	{
    		
    	}
    	else
    	{
    	for(Map<String,String> mapOfMethods:  listOfPreTestCaseMethods)
    			{
    		List<String> listOfMethods=new ArrayList<String>();
			for(String methodName: mapOfMethods.keySet())
			{
				listOfMethods.add(methodName);
			}
    		
    				for(String methodName: listOfMethods)
    				{
    					
    					if(currentPage==null)
    					{
    						currentPage=goToStartPage((WebDriver)testContextConfigs.get("webDriver"));
    					}
    					
								
								
   						 Class<?> class1=currentPage.getClass();   	
   						
							AppLogger.logInfo("Fields are: "+spyObject.getAllFieldNames(currentPage));
   						 
							for(Method methodAtPresent: spyObject.getMethodsFromClassObject(class1))
							{
								Integer parameterCount=null;
								String methodParameter=null;
								
								if(spyObject.getNameOfAMethod(methodAtPresent).equals(methodName))
								{
									parameterCount=spyObject.getParameterCountOfMethod(methodAtPresent);
									if(( spyObject.getReturnTypeOfMethod(methodAtPresent).equals("void"))==true)
								{
										
									if(parameterCount ==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										spyObject.invokeAMethodwithNoParameterAndNoReturnValue(methodAtPresent, currentPage);
									}
									else if(parameterCount==1)
									{ 
																			
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
										
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											spyObject.invokeAMethodwithOneParameterAndNoReturnValue(methodAtPresent, currentPage, fieldObject);
											AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +fieldObject);											
										}
										else
										{
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												spyObject.invokeAMethodwithOneParameterAndNoReturnValue(methodAtPresent, currentPage, methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: "+methodParameter);	
												
											}
											else
											{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
											}
											}
										
										
										
									}
									else if (parameterCount==2)
									{
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());		
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												spyObject.invokeAMethodwithTwoParametersAndNoReturnValue(methodAtPresent, currentPage, fieldObject,methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameters: " +fieldObject+" and: "+methodParameter);		
											}
											else{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
												}
																				
										}
										else
										{
											
											AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
											Reporter.getCurrentTestResult().setStatus(3);
										
										}
									}
									
									
										
								}									
								else
								{
									Object currentTemporaryPage=null;
									if(parameterCount ==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										currentPage=spyObject.invokeAMethodwithNoParameterAndReturnValue(methodAtPresent, currentPage);
										
									}
									else if(parameterCount==1)
									{
										
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
										
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											
											AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +fieldObject);											
											
											currentTemporaryPage=spyObject.invokeAMethodwithOneParameterAndReturnValue(methodAtPresent, currentPage, fieldObject);
											if(currentTemporaryPage!=null)
											{
												currentPage=currentTemporaryPage;								
											}	
											
										}
										else
										{
											
											
											
											
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												currentTemporaryPage=spyObject.invokeAMethodwithOneParameterAndReturnValue(methodAtPresent, currentPage, methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: "+methodParameter);	
												
											}
											else
											{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
											
											}
											if(currentTemporaryPage!=null)
											{
												currentPage=currentTemporaryPage;								
											}	
											
										}
										
										
										
									}
									
									else if (parameterCount==2)
									{
										AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());		
										fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
										
										if(fieldObject!=null)
										{
											methodParameter=mapOfMethods.get(methodName);
											if(!(methodParameter==null ||  methodParameter.equals("")))
											{
												currentTemporaryPage=spyObject.invokeAMethodwithTwoParametersAndReturnValue(methodAtPresent, currentPage, fieldObject,methodParameter);
												AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameters: " +fieldObject+" and: "+methodParameter);		
											}
											else{
												AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
												Reporter.getCurrentTestResult().setStatus(3);
											}
																				
										}
										else
										{
											AppLogger.logInfo("Pretestmethod failed . Hence skipping this testcase: "+"TestContext: "+ testContextName+"testcase: "+testCaseName);	
											Reporter.getCurrentTestResult().setStatus(3);
										
										}
									}
									if(currentTemporaryPage!=null)
									{
										currentPage=currentTemporaryPage;								
									}	
									
									
								}
																		
							}
							}
						
						
					}
    			}
			}	
    	
    			
    	AppLogger.logInfo("Executing  testMethod:"+testCaseName+testContextName);
    	List<Map<String,String>> listOfTestMethods=	getListOfTestMethods(testContextName,testCaseName);
    	List<Map<String,String>> listOfDataProviderIndex=	getListOfDataProviderIndex(testContextName,testCaseName);
    	AppLogger.logInfo("List of data providerIndexes is: "+listOfDataProviderIndex.toString());
    	
    	setActualStartTimeOfTestCase();
    	Integer countOftestStep=-1;
    	Method methodAtPresent=null;
    	for(Map<String,String> mapOfMethods:  listOfTestMethods)
		{
    		List<String> listOfMethods=new ArrayList<String>();
			for(String methodName: mapOfMethods.keySet())
			{
				listOfMethods.add(methodName);
			}
			for(String methodName: listOfMethods)
			{				
				Integer parameterCount=null;					
					 	Class<?> class1=currentPage.getClass();							
					countOftestStep++;
					AppLogger.logInfo("Fields are: "+spyObject.getAllFieldNames(currentPage));
									 
					for(Method methodRefferedTo: spyObject.getMethodsFromClassObject(class1))
					{
						
						if(spyObject.getNameOfAMethod(methodRefferedTo).equals(methodName))
						{
							parameterCount=spyObject.getParameterCountOfMethod(methodRefferedTo);
							methodAtPresent=methodRefferedTo;
						
						}
					}
							if(( spyObject.getReturnTypeOfMethod(methodAtPresent).equals("void"))==true)
						{
								
							if(parameterCount ==0)
							{
								AppLogger.logInfo("Selected method for execution: "+methodName);
								AppLogger.logInfo("current page is: "+currentPage);
								spyObject.invokeAMethodwithNoParameterAndNoReturnValue(methodAtPresent, currentPage);
							
							}
							else 
							{ 
																	
								AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
								
								fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
								List<String> parametersList=null;
								List<String> listOfDataProviderParameters=null;
								List<String> listOfIndex=null;
								if(mapOfMethods.get(methodName)!=null)
								{
									 parametersList=new ArrayList<String>(Arrays.asList(mapOfMethods.get(methodName).split(",")));
									
								}
								if(data!=null)
								{
									listOfDataProviderParameters=new ArrayList<String>( Arrays.asList(data.split(",")));
									AppLogger.logInfo("List of Data provider parameters are: "+ listOfDataProviderParameters.toString());
								}
								if(listOfDataProviderIndex.get(countOftestStep).get(methodName)!=null)
								{
									listOfIndex=Arrays.asList(listOfDataProviderIndex.get(countOftestStep).get(methodName).split(","));
									AppLogger.logInfo("List of Indexes are: "+ listOfIndex.toString());
								}
								Map<Integer,Object> mapOfData=new HashMap<Integer,Object>();
								
								if(fieldObject!=null)
								{
									mapOfData.put(0, fieldObject);
									
									for(Integer i=1;i<parameterCount;i++)
									{
										if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
										{
											mapOfData.put(i, listOfDataProviderParameters.remove(0));
											
										}
										else
										{
											mapOfData.put(i, parametersList.remove(0));
											
											
										}
									}
								}
								else
								{
									for(Integer i=0;i<parameterCount;i++)
									{
										if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
										{
											mapOfData.put(i, listOfDataProviderParameters.remove(0));
											
										}
										else
										{
											mapOfData.put(i, parametersList.remove(0));
										}
									}
									AppLogger.logInfo("Map Of Data is : "+mapOfData.toString());
								}
								if(parameterCount==5)
								{
									spyObject.invokeAMethodwithFiveParametersAndNoReturnValue(methodAtPresent, currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3),mapOfData.get(4));
								}
								if(parameterCount==4)
								{
									spyObject.invokeAMethodwithFourParametersAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3));
								}
								if(parameterCount==3)
								{
									spyObject.invokeAMethodwithThreeParametersAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2));
								}
								if(parameterCount==2)
								{
									spyObject.invokeAMethodwithTwoParametersAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1));
								}
								if(parameterCount==1)
								{
									spyObject.invokeAMethodwithOneParameterAndNoReturnValue(methodAtPresent,currentPage,mapOfData.get(0));
								}
								
								}
							}
							else
								
							{
							
								

								Object currentTemporaryPage=null;
								if(parameterCount ==0)
								{
									AppLogger.logInfo("Selected method for execution: "+methodName);
									AppLogger.logInfo("current page is: "+currentPage);
									currentTemporaryPage=spyObject.invokeAMethodwithNoParameterAndReturnValue(methodAtPresent, currentPage);
									
								}
								else
								{
									 
									AppLogger.logInfo("Following are the parameters:"+spyObject.getParameterNamesFromMethodObject(methodAtPresent).toString());																	
									
									fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject(spyObject.getParameterNamesFromMethodObject(methodAtPresent).get(0), currentPage);
									List<String> parametersList=null;
									List<String> listOfDataProviderParameters=null;
									List<String> listOfIndex=null;
									if(mapOfMethods.get(methodName)!=null)
									{
										 parametersList=new ArrayList<String>(Arrays.asList(mapOfMethods.get(methodName).split(",")));
										
									}
									if(data!=null)
									{
										listOfDataProviderParameters=new ArrayList<String>( Arrays.asList(data.split(",")));
										AppLogger.logInfo("List of Data provider parameters are: "+ listOfDataProviderParameters.toString());
									}
									if(listOfDataProviderIndex.get(countOftestStep).get(methodName)!=null)
									{
										listOfIndex=Arrays.asList(listOfDataProviderIndex.get(countOftestStep).get(methodName).split(","));
										AppLogger.logInfo("List of Indexes are: "+ listOfIndex.toString());
									}
									Map<Integer,Object> mapOfData=new HashMap<Integer,Object>();
									
									if(fieldObject!=null)
									{
										mapOfData.put(0, fieldObject);
										
										for(Integer i=1;i<parameterCount;i++)
										{
											if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
											{
												mapOfData.put(i, listOfDataProviderParameters.remove(0));
												
											}
											else
											{
												mapOfData.put(i, parametersList.remove(0));
												
												
											}
										}
									}
									else
									{
										for(Integer i=0;i<parameterCount;i++)
										{
											if(listOfIndex!=null && listOfIndex.contains(Integer.toString(i)))
											{
												mapOfData.put(i, listOfDataProviderParameters.remove(0));
												
											}
											else
											{
												mapOfData.put(i, parametersList.remove(0));
											}
										}
										AppLogger.logInfo("Map Of Data is : "+mapOfData.toString());
									}
									if(parameterCount==5)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithFiveParametersAndReturnValue(methodAtPresent,currentPage, mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3),mapOfData.get(4));
									}
									if(parameterCount==4)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithFourParametersAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2),mapOfData.get(3));
									}
									if(parameterCount==3)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithThreeParametersAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1), mapOfData.get(2));
									}
									if(parameterCount==2)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithTwoParametersAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0), mapOfData.get(1));
									}
									if(parameterCount==1)
									{
										currentTemporaryPage=spyObject.invokeAMethodwithOneParameterAndReturnValue(methodAtPresent,currentPage,mapOfData.get(0));
									}
									
									
								}	
								if(currentTemporaryPage!=null)
								{
									currentPage=currentTemporaryPage;								
								}
								
							}
							
						}
					
			}
		
	
	try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
	}
}