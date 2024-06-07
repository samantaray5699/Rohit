package Data_driven_testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadDataFromExcel {
	@Test
	public void sample() throws Throwable
	{
		//Step1-Get the excel path and java object of physical file
		FileInputStream fis= new  FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\SampleData.xlsx");
		//Step2-OpenWorkBook in read mode
		Workbook wb = WorkbookFactory.create(fis);
		//Step3-Get the control  specific sheet
		Sheet sheet = wb.getSheet("SalesOrders");
		//Step4-Get the control of row
		Row row = sheet.getRow(1);
		//Step5-Get the control of cell and read the data
		Cell cell = row.getCell(4);
		String data = cell.getStringCellValue();
		System.out.println(data);

		//close workbook
		wb.close();
		
		//Use method chaining instead of storing it and using it

	}


}
