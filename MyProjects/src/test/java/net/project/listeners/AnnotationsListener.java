package net.project.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.project.loggers.AppLogger;

import org.testng.IAnnotationTransformer2;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;

public class AnnotationsListener implements IAnnotationTransformer2{
	
	  
    public static Map<String,Map<String,Object>> mapOfTestContextParameters= new LinkedHashMap<String, Map<String,Object>>();
    public static Map<String,Map<String,List<Map<String,String>>>> mapOfPreTestCaseSteps=new LinkedHashMap<String, Map<String,List<Map<String,String>>>>();
    public static Map<String,Map<String,List<Map<String,String>>>> mapOfTestSteps=new LinkedHashMap<String, Map<String,List<Map<String,String>>>>();
    public static Map<String,String> testNameSetting=new HashMap<String, String>();
    public static Map<String,Map<String,List<String>>> mapOfTestsAndTestCases=new LinkedHashMap<String, Map<String,List<String>>>();

	public static Map<String,Map<String,List<Map<String,String>>>>  mapOfParametersFromDataProviders=new LinkedHashMap<String,Map<String,List<Map<String,String>>>>();
    
    List<String> onetestContext=null;
    List<String> testCases=null;
    


	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass,Constructor testConstructor, Method testMethod) {
		AppLogger.logInfo("--------In Transformation method-----");
		if(annotation.getTestName().equals("driverShutdown"))
		{
			annotation.setEnabled(true);
			return;
		}
		
		AppLogger.logInfo("Map of testcases in AnnotationsListener: "+AnnotationsListener.mapOfTestsAndTestCases.toString());
		AppLogger.logInfo("Map of Pretestcasesteps in AnnotationsListener: "+AnnotationsListener.mapOfPreTestCaseSteps.toString());
		
		String testContext=(String)AnnotationsListener.mapOfTestsAndTestCases.keySet().toArray()[0];
		AppLogger.logInfo("CurrentTestContext from AnnotationsListener is: "+testContext);
		String testCase=(String)AnnotationsListener.mapOfTestsAndTestCases.get(testContext).keySet().toArray()[0];
		AppLogger.logInfo("CurrentTestCase from AnnotationsListener is: "+testCase);
		List<String> testCaseConfigs=AnnotationsListener.mapOfTestsAndTestCases.get(testContext).remove(testCase);
		AppLogger.logInfo("Map of testcase configs is: "+testCaseConfigs.toString());
		AppLogger.logInfo("Map of testsandtestcases after current deletion: "+AnnotationsListener.mapOfTestsAndTestCases.get(testContext).toString());
		if(AnnotationsListener.mapOfTestsAndTestCases.get(testContext).isEmpty())
			AnnotationsListener.mapOfTestsAndTestCases.remove(testContext);
		Integer priorityInt=new Integer(testCaseConfigs.get(0));
		annotation.setPriority(priorityInt);
		Integer runFlag=new Integer(testCaseConfigs.get(1));
		annotation.setTestName(testCase);
		annotation.setDescription(testCaseConfigs.get(2));
		try {
			annotation.setDataProviderClass(Class.forName("net.project.dataProviders."+testCaseConfigs.get(3)));
		} catch (ClassNotFoundException e) {
			AppLogger.logInfo("Not able to set data provider class: "+"net.project.dataProviders"+testCaseConfigs.get(3));
		}
		if(testCaseConfigs.get(4)!=null || !testCaseConfigs.get(4).equals("") )
		{
			annotation.setDataProvider(testCaseConfigs.get(4));
		}
		
		annotation.setGroups(testCaseConfigs.get(5).split(","));
		if(testCaseConfigs.get(6)!=null || !testCaseConfigs.get(6).equals("") )
		{
			annotation.setSuiteName(testCaseConfigs.get(6));
		}
		
		AppLogger.logInfo("dataProviderclass is: "+annotation.getDataProviderClass().getName());
		AppLogger.logInfo("dataProvider method is: "+annotation.getDataProvider());
		AppLogger.logInfo("groups is: "+annotation.getGroups().toString());
		AppLogger.logInfo("suite name is: "+annotation.getSuiteName());
		AppLogger.logInfo("runFlag is: "+runFlag);
		AppLogger.logInfo("testcasename and isEnabled is: "+annotation.getTestName()+annotation.getEnabled());
		if(!runFlag.equals(0))
		{
			AppLogger.logInfo("Enabling testcase: " + annotation.getTestName());
			annotation.setEnabled(true);
			
		}
			
		else
			annotation.setEnabled(false);
		
		
	}


	@SuppressWarnings("rawtypes")
	@Override
	public void transform(IConfigurationAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
		
		
	}


	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
	
	}


}
