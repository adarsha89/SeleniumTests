/*
 * Excel Functions Demo
 */
package net.project.dataAccess;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelDemo.
 */
public class ExcelDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExcelInteraction eI=new ExcelInteraction();
		LinkedHashMap<String,List<LinkedHashMap <String, String>>> data=eI.getData("D:\\sample.xls");
		System.out.println(data.toString());
		ExcelInteraction eI1=new ExcelInteraction();
		eI1.putData(data, "D:\\sample1.xls");
	}
}
