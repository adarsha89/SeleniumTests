/*
 * Excel Interaction
 */
package net.project.dataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


	// TODO: Auto-generated Javadoc
/**
	 * The Class ExcelInteraction.
	 */
	public class ExcelInteraction {
		
		/** The column index. */
		int rowIndex = 0, columnIndex = 0;
		
		/** The wb. */
		Workbook workBook;
		
		/** The num of sheets. */
		int numOfSheets;
		
		/**  The worSheets. */
		List<Sheet> workSheet =new ArrayList<Sheet>();
		
		/** The wr. */
		Row row;
		
		/** The final data. */
		LinkedHashMap<String,List<LinkedHashMap <String, String>>> finalData;	
		
		/**
		 * Open.
		 *
		 * @param fileName the file name
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public  void open(String fileName) throws IOException  {
						
				workBook = new HSSFWorkbook(new FileInputStream(new File(fileName)));			
						
		}
		
		/**
		 * Gets the sheets.
		 *
		 * @return the sheets
		 */
		public void getSheets()
		{
			numOfSheets = workBook.getNumberOfSheets();
			for(int i=0; i<numOfSheets; i++ )
			{
				workSheet.add(workBook.getSheetAt(i));
			}
		}
		//@SuppressWarnings("unchecked")
		/**
		 * Gets the data.
		 *
		 * @param fileName the file name
		 * @return the data
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public LinkedHashMap<String,List<LinkedHashMap <String, String>>> getData(String fileName) throws IOException  {	
			open(fileName);
			getSheets();
			finalData = new LinkedHashMap<String,List<LinkedHashMap <String, String>>>();   
			for(Sheet sheet:workSheet)
			{
				List<LinkedHashMap <String, String>> sheetData= getSheetData(sheet);
				if(sheetData.equals(null))
				{
					sheetData=null;
				}
				
				finalData.put(sheet.getSheetName(), sheetData);
			}
			
			
			return finalData;
			
		}
		
		/**
		 * Gets the sheet data.
		 *
		 * @param sheet the sheet
		 * @return the sheet data
		 */
		public List<LinkedHashMap <String, String>> getSheetData(Sheet sheet)
		{
			List<LinkedHashMap <String, String>> data = new ArrayList<LinkedHashMap <String, String>>();                           				
			row = sheet.getRow(0);	
			
			for(rowIndex=0; rowIndex< sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				data.add(new LinkedHashMap <String, String>());
				for (columnIndex = 0; columnIndex < sheet.getRow(0).getPhysicalNumberOfCells(); columnIndex++) {
				//	AppLogger.logInfo("numberofavailablecolumns in row no: "+rowIndex+"is: "+ sheet.getRow(rowIndex).getPhysicalNumberOfCells());
				try
				{
					sheet.getRow(rowIndex).getCell(columnIndex).setCellType(Cell.CELL_TYPE_STRING);
					data.get(rowIndex).put(row.getCell(columnIndex).getStringCellValue(),sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue() );
					
				}catch(NullPointerException  | IllegalStateException e)
				{
					//AppLogger.logInfo("row: "+ rowIndex+ " column: "+columnIndex);
					data.get(rowIndex).put(row.getCell(columnIndex).getStringCellValue(),"");
				}
				
				}	
			}	
			return data;	
		}
	
	
	       /**
       	 * Close.
       	 *
       	 * @throws IOException Signals that an I/O exception has occurred.
       	 */
       	public  void close() throws IOException  {
	            workBook = null;
	            workSheet = null;
	            row = null;
	            finalData = null;
		}
	       
	       /**
       	 * Creates the sheets.
       	 */
       	public void createSheets()
	   	{
	   		numOfSheets = finalData.size();
	   		for(String sheet:finalData.keySet())
	   		{   			
	   			workBook.createSheet(sheet);
	   			workSheet.add(workBook.getSheet(sheet));
	   		}
	   		
	   	}
	       
       	/**
       	 * Creates the file.
       	 */
       	public void createFile()
	       {
	   		
				workBook = new HSSFWorkbook();		
				
	       }
		
		/**
		 * Put data.
		 *
		 * @param finalData the final data
		 * @param fileName the file name
		 * @return true, if successful
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public boolean putData(LinkedHashMap<String,List<LinkedHashMap <String, String>>> finalData, String fileName) throws IOException
		{
			
			this.finalData=finalData;
			createFile();
			FileOutputStream fileOut = new FileOutputStream(fileName);
			createSheets();
					
			for(Sheet sheet:workSheet)
			{
				putSheetData(sheet, finalData.get(sheet.getSheetName()));
			}
			workBook.write(fileOut);
			fileOut.close();
			close();		
			return true;   
			}
		
		/**
		 * Put sheet data.
		 *
		 * @param sheet the sheet
		 * @param sheetData the sheet data
		 * @return true, if successful
		 */
		public boolean putSheetData(Sheet sheet,List<LinkedHashMap <String, String>> sheetData)
		{
			
			for(rowIndex = 0; rowIndex < sheetData.size(); rowIndex++) {
				sheet.createRow(rowIndex);
				row = sheet.getRow(rowIndex);	
				Set<String> cellKeys= sheetData.get(rowIndex).keySet();
				ArrayList<String> al1=new ArrayList<String>();
				for(String cellKey: cellKeys)
				{
					al1.add(sheetData.get(rowIndex).get(cellKey));
				}
				columnIndex=0;	
				for(String str: al1 )
					{
						if(columnIndex < sheetData.get(rowIndex).size())					
					
						{
							row.createCell(columnIndex);
							row.getCell(columnIndex++).setCellValue(str);
						}			
						
				}
			}
			return true;
		}
		
	
}
