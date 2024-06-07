package Data_driven_testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class WriteDatabackToExcel {

	@Test
	public void sample() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Row row = wb.getSheet("Sheet1").getRow(4);
		Cell cel = row.createCell(4);
		cel.setCellType(CellType.STRING);//optional
		cel.setCellValue("pass");
		FileOutputStream fos= new FileOutputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
