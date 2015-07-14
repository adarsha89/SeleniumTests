package net.project.loggers;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class AppLogger {

	
	
	
	public static Logger logger = Logger.getLogger(AppLogger.class);
	 static  {
		// TODO Auto-generated method stub
		//PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("log6j.properties");
        //Log in console in and log file
	}
	 public static void assertLogEquals(Object expected, Object actual , String message)
	 {
		 logger.assertLog(expected.equals(actual), message);
	 }
	 public static void assertLogNotEquals(Object expected, Object actual , String message)
	 {
		 logger.assertLog(!expected.equals(actual), message);
	 }
	 public static void assertLogTrue(boolean expected, String message)
	 {
		 logger.assertLog(expected==true, message);
	 }
	 public static void assertLogFalse(boolean expected, String message)
	 {
		 logger.assertLog(expected==false, message);
	 }
	 public static void logInfo(String info)
	 {
		 logger.info(info);
	 }
	 public static void logError(String error)
	 {
		 logger.error(error);
	 }
	 public static void logErrorWithException(String error, Throwable throwable)
	 {
		 logger.error(error, throwable);
	 }
	 public static void logDebug(String info)
	 {
		 logger.debug(info);
	 }
	 public static void logDebugWithException(String info,Throwable exception)
	 {
		 logger.debug(info,exception);
	 }
	
	


}
