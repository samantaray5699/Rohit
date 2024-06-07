package PracticeTestOrg;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateOrganizationWithIndustryTest {
	@Test
	public void TC02() throws Throwable
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\commondataCrm.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		String browser=pobj.getProperty("browser");
		String url=pobj.getProperty("url");
		String username=pobj.getProperty("username");
		String password=pobj.getProperty("password");

		Random ran= new Random();	
		int num=ran.nextInt(1000);

		FileInputStream fis1= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");

		Workbook wb = WorkbookFactory.create(fis1);
		String data = wb.getSheet("Sheet1").getRow(1).getCell(2).toString()+num;
		Row row = wb.getSheet("Sheet1").getRow(4);
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();

		wb.close();

		WebDriver driver=null;
		if(browser.equals("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(browser.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		else
			driver= new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(data);
		WebElement ind_dropdown = driver.findElement(By.name("industry"));
		Select select1= new Select(ind_dropdown);
		select1.selectByVisibleText(industry);

		WebElement typedropdown = driver.findElement(By.name("accounttype"));
		Select select2= new Select(typedropdown);
		select2.selectByVisibleText(type);
		// save		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify header msg
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(data))
		{
			Reporter.log(data+ "is displayed===pass");
		}
		else
		{
			Reporter.log(data+ "is not displayed===fail");
		}
		//verify org name , industry,type info
		String actualorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		String actualindustry = driver.findElement(By.id("dtlview_Industry")).getText();
		String actualtype = driver.findElement(By.id("dtlview_Type")).getText();

		if(actualorgname.equals(data) && actualindustry.equals(industry)&& actualtype.equals(type))
		{
			Reporter.log(data+industry+type+ "is displayed in info===pass");
		}
		else
		{
			Reporter.log(data+industry+type+ "is not displayed in info===fail");
		}

		//signout
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
