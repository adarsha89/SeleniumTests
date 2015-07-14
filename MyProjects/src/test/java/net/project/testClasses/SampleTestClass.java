package net.project.testClasses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.project.listeners.AnnotationsListener;
import net.project.loggers.AppLogger;
import net.project.pageClasses.BasicLayoutPage;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SampleTestClass {
	Object currentPage=null;
	String currentPageClass="";
	WebDriver webDriver=null;
	WebDriverUtilFunctions webDriverUtilFunctions=null;
	public SampleTestClass() {
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
@SuppressWarnings("rawtypes")
@Test(enabled=false, testName="sample1",description=":Just a sample testCase ")
public void sample1()

	{
	
	
	
		
		String testContextName=getTestContextName();
    	String testCaseDescription=getTestCaseDescription();
    	String testCaseName=getTestCaseName(testCaseDescription);
    	AppLogger.logInfo("Executing beforetest method: "+testCaseName +testContextName);
    	List<Map<String,String>> listOfPreTestCaseMethods=	getListOfPreTestCaseMethods(testContextName, testCaseName);
      	Map<String,Object> testContextConfigs= getMapOfTestContextConfigs(testContextName);
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
    					
						
						try {
							if(currentPageClass.equals(""))
							{
								currentPageClass="net.project.pageClasses.BasicLayoutPage";								
								currentPage=new BasicLayoutPage((WebDriver)testContextConfigs.get("webDriver"));
								}
								
								
   						 Class class1=Class.forName(currentPageClass);    						 
							for(Method methodAtPresent:   class1.getMethods())
							{
								String methodParameter=null;
								if(methodAtPresent.getName().equals(methodName))
								{
									
									if((methodAtPresent.getReturnType().getName().equals("void"))==true)
								{
									if(methodAtPresent.getParameterCount()==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										methodAtPresent.invoke(currentPage);
									}
									else
									{ 
										methodParameter=mapOfMethods.get(methodName);
										AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +methodParameter);
										AppLogger.logInfo("current page is: "+currentPage);
										if(methodParameter.equals(null) || methodParameter.trim().equals(""))
										{
											
											
											methodAtPresent.invoke(currentPage,(WebDriver)testContextConfigs.get("webDriver"));
										}
										else
										{
											methodAtPresent.invoke(currentPage,methodParameter);
										}
										
									}
									
										
								}
								else
								{
									currentPageClass=methodAtPresent.getReturnType().getName();
									if(methodAtPresent.getParameterCount()==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										currentPage=methodAtPresent.invoke(currentPage);
									}
									else
									{
										methodParameter=mapOfMethods.get(methodName);
										AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +methodParameter);
										AppLogger.logInfo("current page is: "+currentPage);
										if(methodParameter.equals(null) || methodParameter.trim().equals(""))
										{
											Object currentTemporaryPage=null;
											
											
												currentTemporaryPage=methodAtPresent.invoke(currentPage,(WebDriver)testContextConfigs.get("webDriver"));
												if(currentTemporaryPage!=null)
												{
													currentPage=currentTemporaryPage;
													currentPageClass=currentPage.getClass().getName();
												}									
												
											
											
										}
										else
										{
											currentPage=methodAtPresent.invoke(currentPage,methodParameter);
										}
										
									}
									
									
								}
																		
							}
							}
							
						} catch (SecurityException| ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
    			}
			}	
    	
    			
    	AppLogger.logInfo("Executing  testMethod:"+testCaseName+testContextName);
    	List<Map<String,String>> listOfTestMethods=	getListOfTestMethods(testContextName,testCaseName);
    	setActualStartTimeOfTestCase();
    	for(Map<String,String> mapOfMethods:  listOfTestMethods)
    			{
    		List<String> listOfMethods=new ArrayList<String>();
			for(String methodName: mapOfMethods.keySet())
			{
				listOfMethods.add(methodName);
			}
    		
    				for(String methodName: listOfMethods)
    				{
						
						try {
						
							AppLogger.logInfo("Current page class is:"+currentPageClass);
   						 	Class class1=Class.forName(currentPageClass);    						 
							for(Method methodAtPresent:   class1.getMethods())
							{
								String methodParameter=null;
								if(methodAtPresent.getName().equals(methodName))
								{
									
									if((methodAtPresent.getReturnType().getName().equals("void"))==true)
								{
									if(methodAtPresent.getParameterCount()==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										methodAtPresent.invoke(currentPage);
									}
									else
									{
										methodParameter=mapOfMethods.get(methodName);
										AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +methodParameter);
										methodAtPresent.invoke(currentPage,methodParameter);
									}
									
										
								}
								else
								{
									currentPageClass=methodAtPresent.getReturnType().getName();
									if(methodAtPresent.getParameterCount()==0)
									{
										AppLogger.logInfo("Selected method for execution: "+methodName);
										AppLogger.logInfo("current page is: "+currentPage);
										currentPage=methodAtPresent.invoke(currentPage);
									}
									else
									{
										methodParameter=mapOfMethods.get(methodName);
										AppLogger.logInfo("Selected method for execution: "+methodName+ "  with parameter: " +methodParameter);
										AppLogger.logInfo("current page is: "+currentPage);
										if(methodParameter.equals(null) || methodParameter.trim().equals(""))
										{
										
											Object currentTemporaryPage=null;
											currentTemporaryPage=methodAtPresent.invoke(currentPage,(WebDriver)testContextConfigs.get("webDriver"));
											if(currentTemporaryPage!=null)
											{
												currentPage=currentTemporaryPage;
												currentPageClass=currentPage.getClass().getName();
											}	
										}
										else
										{
											currentPage=methodAtPresent.invoke(currentPage,methodParameter);
											
										}
										
										
									
									}
									
									
								}
																		
							}
							}
							
						} catch (SecurityException| ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
    			}
    
		
	
	
	
	
	
	
	
	
	}

}
