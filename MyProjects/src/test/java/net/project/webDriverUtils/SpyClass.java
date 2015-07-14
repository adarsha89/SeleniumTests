package net.project.webDriverUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.project.loggers.AppLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class SpyClass.
 */
public class SpyClass {

	/**
	 * Gets the class object from class name.
	 *
	 * @param className the class name
	 * @return the class object from class name
	 */
	@SuppressWarnings("rawtypes")
	public Class getClassObjectFromClassName(String className)
	{
		Class classObject=null;
	 try {
		  classObject=	Class.forName(className);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		AppLogger.logInfo("Not able to get class object from classname: "+className);
	} 
	 return classObject;
	}
	
	/**
	 * Gets the field object using field name and object.
	 *
	 * @param fieldname the fieldname
	 * @param obj the obj
	 * @return the field object using field name and object
	 */
	public Object getFieldObjectUsingFieldNameAndObject(String fieldname, Object obj)
	{
		Field field=null;
		
			try {
				field = obj.getClass().getField(fieldname);
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				AppLogger.logError("Field with field name: "+fieldname+" : was not obtained" +e.getMessage());
			}
		
		Object fieldObject=null;
		try {
			fieldObject = field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException  | NullPointerException e ) {
			// TODO Auto-generated catch block
			AppLogger.logError("Field object with field name: "+fieldname+" : was not obtained for the object"+e.getMessage());
		}
		return fieldObject;
	}
	
	/**
	 * Gets the all field names.
	 *
	 * @param obj the obj
	 * @return the all field names
	 */
	public List<String> getAllFieldNames(Object obj)
	{
		
		Field[] fields=null;
		
		try
		{

			fields = obj.getClass().getFields();
		}catch(NullPointerException exc)
		{
			AppLogger.logInfo("No accessible field");
		}
			
			
		List<String> listOfFields=new ArrayList<String>();
		if(fields!=null)
		{
			for(Field field: fields)
			{
				listOfFields.add(field.getName());
			}
		}
		
		return listOfFields;
	}
	
	
	
	/**
	 * Gets the methods from class object.
	 *
	 * @param classObject the class object
	 * @return the methods from class object
	 */
	@SuppressWarnings("rawtypes")
	public List<Method> getMethodsFromClassObject(Class classObject)
	{
		return Arrays.asList(classObject.getMethods());
	}
	
	/**
	 * Gets the return type of method.
	 *
	 * @param methodObject the method object
	 * @return the return type of method
	 */
	public String getReturnTypeOfMethod(Method methodObject)
	{
		return methodObject.getReturnType().getName();
	}
	
	/**
	 * Gets the parameter count of method.
	 *
	 * @param methodObject the method object
	 * @return the parameter count of method
	 */
	public Integer getParameterCountOfMethod(Method methodObject)
	{
		return methodObject.getParameterCount();
	}
	
	/**
	 * Invoke a methodwith no parameter and no return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 */
	public void invokeAMethodwithNoParameterAndNoReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked)
	{
		try {
			methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getStackTrace());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getStackTrace());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getStackTrace());
		}
	}
	
	/**
	 * Gets the parameter names from method object.
	 *
	 * @param methodObjectFromWhichParamIsObtained the method object from which param is obtained
	 * @return the parameter names from method object
	 */
	public  List<String> getParameterNamesFromMethodObject(Method methodObjectFromWhichParamIsObtained)
	{
		Parameter[] params = methodObjectFromWhichParamIsObtained.getParameters();
        List<String> paramNames=new ArrayList<String>();
        for(Parameter param: params)
        {
        	paramNames.add(param.getName());
        }
        return paramNames;
	}
	
	/**
	 * Invoke a methodwith one parameter and no return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument the argument
	 */
	public void invokeAMethodwithOneParameterAndNoReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument)
	{
		try {
			methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
	}
	
	/**
	 * Invoke a methodwith no parameter and return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @return the object
	 */
	public Object invokeAMethodwithNoParameterAndReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked)
	{
		Object objectToBeReturned=null;
		try {
			objectToBeReturned=methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
		return objectToBeReturned;
	}
	
	/**
	 * Invoke a methodwith one parameter and return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument the argument
	 * @return the object
	 */
	public Object invokeAMethodwithOneParameterAndReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked , Object argument)
	{
		Object objectToBeReturned=null;
		try {
			objectToBeReturned=methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getStackTrace());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getStackTrace());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getStackTrace());
		}
		return objectToBeReturned;
	}
	
	/**
	 * Invoke a methodwith two parameters and no return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 */
	public void invokeAMethodwithTwoParametersAndNoReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument1, Object argument2)
	{
		try {
			methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
	}
	
	/**
	 * Invoke a methodwith three parameters and no return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @param argument3 the argument3
	 */
	public void invokeAMethodwithThreeParametersAndNoReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument1, Object argument2 , Object argument3)
	{
		try {
			methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2,argument3);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
	}
	
	/**
	 * Invoke a methodwith two parameters and return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @return the object
	 */
	public Object invokeAMethodwithTwoParametersAndReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked , Object argument1,Object argument2)
	{
		Object objectToBeReturned=null;
		try {
			objectToBeReturned=methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
		return objectToBeReturned;
	}
	
	/**
	 * Invoke a methodwith three parameters and return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @param argument3 the argument3
	 * @return the object
	 */
	public Object invokeAMethodwithThreeParametersAndReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked , Object argument1,Object argument2,Object argument3)
	{
		Object objectToBeReturned=null;
		try {
			objectToBeReturned=methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2,argument3);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
		return objectToBeReturned;
	}
	
	/**
	 * Gets the class name from object.
	 *
	 * @param objectForWhichClassNameIsObtained the object for which class name is obtained
	 * @return the class name from object
	 */
	public String getClassNameFromObject(Object objectForWhichClassNameIsObtained)
	{
		return objectForWhichClassNameIsObtained.getClass().getName();
	}
	
	/**
	 * Gets the name of a method.
	 *
	 * @param methodForWhichNameIsObtained the method for which name is obtained
	 * @return the name of a method
	 */
	public String getNameOfAMethod(Method methodForWhichNameIsObtained)
	{
		return methodForWhichNameIsObtained.getName();
	}
	
	/**
	 * Invoke a methodwith four parameters and no return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @param argument3 the argument3
	 * @param argument4 the argument4
	 */
	public void invokeAMethodwithFourParametersAndNoReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument1, Object argument2 , Object argument3,Object argument4)
	{
		try {
			methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2,argument3,argument4);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
	}
	
	/**
	 * Invoke a methodwith four parameters and return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @param argument3 the argument3
	 * @param argument4 the argument4
	 * @return the object
	 */
	public Object invokeAMethodwithFourParametersAndReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument1, Object argument2 , Object argument3,Object argument4)
	{
		Object objectToBeReturned=null;
		try {
			objectToBeReturned=methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2,argument3,argument4);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
		return objectToBeReturned;
	}
	
	/**
	 * Invoke a methodwith five parameters and no return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @param argument3 the argument3
	 * @param argument4 the argument4
	 * @param argument5 the argument5
	 */
	public void invokeAMethodwithFiveParametersAndNoReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument1, Object argument2 , Object argument3,Object argument4,Object argument5)
	{
		try {
			methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2,argument3,argument4,argument5);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
	}
	
	/**
	 * Invoke a methodwith five parameters and return value.
	 *
	 * @param methodToBeInvoked the method to be invoked
	 * @param objectOnWhichMethodIsInvoked the object on which method is invoked
	 * @param argument1 the argument1
	 * @param argument2 the argument2
	 * @param argument3 the argument3
	 * @param argument4 the argument4
	 * @param argument5 the argument5
	 * @return the object
	 */
	public Object invokeAMethodwithFiveParametersAndReturnValue(Method methodToBeInvoked, Object objectOnWhichMethodIsInvoked, Object argument1, Object argument2 , Object argument3,Object argument4,Object argument5)
	{
		Object objectToBeReturned=null;
		try {
			objectToBeReturned=methodToBeInvoked.invoke(objectOnWhichMethodIsInvoked,argument1,argument2,argument3,argument4,argument5);
		} catch (IllegalAccessException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+" due to exception: " +e.getMessage());
		} catch (IllegalArgumentException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		} catch (InvocationTargetException e) {
			AppLogger.logError("Not able to invoke method"+methodToBeInvoked.getName()+"  due to exception: " +e.getMessage());
		}
		return objectToBeReturned;
	}
	
}
