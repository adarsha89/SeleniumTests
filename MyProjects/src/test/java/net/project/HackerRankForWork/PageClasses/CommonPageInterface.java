package net.project.HackerRankForWork.PageClasses;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import net.project.webDriverUtils.SpyClass;
import net.project.webDriverUtils.WebDriverUtilFunctions;

public interface CommonPageInterface {
	SpyClass spyObject=new SpyClass();
	WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
default void waitForPageRender(WebDriver webDriver, Integer seconds)
{
	
	webDriverUtilFunctions.waitForPageToLoad(webDriver, 30);
	webDriverUtilFunctions.waitForAjaxQueryCompletion(webDriver, 30);
	seconds=seconds*1000;
	try {
		Thread.sleep(seconds.longValue());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
default void setUrlOfPage(Object page, String url )
{
	Class class1=null;
	try {
		class1= Class.forName(spyObject.getClassNameFromObject(page));
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Object fieldObject=spyObject.getFieldObjectUsingFieldNameAndObject("url", page);
	fieldObject=url;
}
default void waitForReInitialization(Object page, WebDriver webDriver )
{
	PageFactory.initElements(webDriver, page);
	
}
}

