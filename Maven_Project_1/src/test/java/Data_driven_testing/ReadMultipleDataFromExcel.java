package Data_driven_testing;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadMultipleDataFromExcel {
	@Test
	public void sample() throws Throwable
	{
		FileInputStream fis= new  FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\SampleData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("SalesOrders");
		int rowcount = sh.getLastRowNum();
		
		for(int i=1;i<=rowcount;i++)
		{
			String data1 = sh.getRow(i).getCell(1).toString();
			String data2 = sh.getRow(i).getCell(2).toString();
			System.out.println(data1+"\t"+data2);
			
		}
		wb.close();


	}

}
