package Data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RandomNumberGeneration {
	@Test(invocationCount = 5)
	public void sample() throws EncryptedDocumentException, IOException
	{
		Random random= new Random();
		int num=random.nextInt(1000);
	
		FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String search = wb.getSheet("Sheet2").getRow(1).getCell(2).getStringCellValue()+num;
		
		

		WebDriver driver= new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.id("small-searchterms")).sendKeys(search);
		


	}

}
