package org.projectName.dataprovider;

import java.io.File;
import java.lang.reflect.Method;

import org.framework.utils.ExcelLibrary;
import org.testng.annotations.DataProvider;

public class DatadrivenTestingFromExcel {

	@DataProvider(name="excelSheetDataRead")
	public static Object[][] excelSheetDataRead(Method methodName) throws Exception{
		File file = new File("src/test/resources/TestData/"+methodName.getName()+".xlsx");
		ExcelLibrary excel =  new ExcelLibrary(file.getAbsolutePath());
		 Object data[][] =	excel.readFromExcelDataForTestNGDataProvider(file.getAbsolutePath());
		return data;
	}
	
	@DataProvider(name="singleExcelMultipleSheets")
	public static Object[][] singleExcelMultipleSheets(Method methodName) throws Exception{
		File file = new File("src/test/resources/TestData/APITestData.xlsx");
		ExcelLibrary excel =  new ExcelLibrary(file.getAbsolutePath(),methodName.getName());
		 Object data[][] =	excel.readFromExcelDataForTestNGDataProvider(file.getAbsolutePath(),methodName.getName());
		return data;
	}
}
