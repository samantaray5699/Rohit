package Data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadDataBasedOnCondition {
	String expectedtestcase="tc2";
    boolean flag = false;
	@Test
	public void sample() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new  FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int rowcount = sh.getLastRowNum();
		for(int i=1;i<=rowcount;i++)
		{
			try {
			String data = sh.getRow(i).getCell(0).toString();
			if(data.equals(expectedtestcase))
			{
				short cellcount = sh.getRow(i).getLastCellNum();
				for(int j=1;j<=cellcount;j++) {
				String data1=sh.getRow(i).getCell(j).getStringCellValue();
				//String data2=sh.getRow(i).getCell(2).getStringCellValue();
				//String data3=sh.getRow(i).getCell(3).getStringCellValue();
				flag=true;
			
				System.out.println(data1);
				//System.out.println(data2);
				//System.out.println(data3);
				}
				
			}
			}catch(Exception e)
			{}
		}
		if(flag==false)
		{
			System.out.println("for "+expectedtestcase+"data not available");
		}
		wb.close();


	}

}
