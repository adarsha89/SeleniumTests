package net.project.dataProviders;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class SampleDataProvider.
 */
public class SampleDataProvider {

	 /**
 	 * Sample data provider method.
 	 *
 	 * @return the iterator
 	 */
 	@DataProvider(name = "sampleDataProviderMethod")
	 
	  public  static Iterator<String[]> sampleDataProviderMethod() {
		 List<String[]> list1=new ArrayList<String[]>();
		 List<String> listOfString=new ArrayList<String>();
		 StringBuilder stringBuilder=new StringBuilder();
		
		 stringBuilder.append("affa"+",");
		 stringBuilder.append("abbs");
		 listOfString.add(stringBuilder.toString());
		 
		 list1.add(listOfString.toArray(new String[listOfString.size()]));
		 return list1.iterator();
	     
	 
	  }
	 
	 /**
 	 * The main method.
 	 *
 	 * @param args the arguments
 	 */
 	public static void main(String[] args)
	 {
		 List<String> listOfString=new ArrayList<String>();
		 StringBuilder stringBuilder=new StringBuilder();
		
		 stringBuilder.append("affa");
		 stringBuilder.append("abbs");
		 listOfString.add(stringBuilder.toString());
		 stringBuilder.delete(0,stringBuilder.length()-1);
		 listOfString.add(stringBuilder.toString());
		 System.out.println(listOfString.toArray());
	 }
}
