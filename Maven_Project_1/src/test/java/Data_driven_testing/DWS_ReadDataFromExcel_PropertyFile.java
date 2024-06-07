package Data_driven_testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class DWS_ReadDataFromExcel_PropertyFile {

	@Test
	public void login() throws IOException, InterruptedException {
		FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\commondata.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		String browser =pobj.getProperty("browser");
		String url= pobj.getProperty("url");
		String email=pobj.getProperty("Email");
		String password= pobj.getProperty("password");
		String time=pobj.getProperty("time");
		
		WebDriver driver=null;
		
		//read common data from properties file
		if(browser.equals("chrome"))
			driver=new ChromeDriver(); 
		else if(browser.equals("edge"))
			driver=new EdgeDriver();
		else
			driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		//read test data from excel
		FileInputStream fis1= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String search = wb.getSheet("Sheet2").getRow(1).getCell(2).getStringCellValue();
		//wb.getSheet("Sheet2").getRow(1).createCell(4).setCellValue("pass");
		//FileOutputStream fos= new FileOutputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		//wb.write(fos);
		
		wb.close();
		driver.findElement(By.id("small-searchterms")).sendKeys(search);
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		Thread.sleep(1000);
		
		
		driver.quit();

	}

}
