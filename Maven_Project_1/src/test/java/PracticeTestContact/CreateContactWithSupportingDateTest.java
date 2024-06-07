package PracticeTestContact;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateContactWithSupportingDateTest {
	@Test
	public void TC02() throws Throwable
	{
	//Fetching date and after date
		Date dateobj= new Date();
		
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(dateobj);
			
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String dynamicdate = sim.format(cal.getTime());
		


	FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\commondataCrm.properties");
	Properties pobj= new Properties();
	pobj.load(fis);
	String browser=pobj.getProperty("browser");
	String url=pobj.getProperty("url");
	String username=pobj.getProperty("username");
	String password=pobj.getProperty("password");


	FileInputStream fis1= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");

	Workbook wb = WorkbookFactory.create(fis1);
	String lastname = wb.getSheet("Sheet4").getRow(1).getCell(2).toString();
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
	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	driver.findElement(By.name("lastname")).sendKeys(lastname);
	
	WebElement startdate = driver.findElement(By.name("support_start_date"));
	startdate.clear();
	startdate.sendKeys(currentdate);
	
	WebElement enddate = driver.findElement(By.name("support_end_date"));
	enddate.clear();
	enddate.sendKeys(dynamicdate);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	String actuallastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	String actualstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
	String actualenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
	
	if(actuallastname.contains(lastname) && actualstartdate.equals(currentdate) && actualenddate.equals(dynamicdate))
	{
		Reporter.log(lastname+currentdate+dynamicdate+"is displayed ====pass");
	}
	else
	{
		Reporter.log(lastname+lastname+currentdate+dynamicdate+"is displayed ====fail");
	}
	//signout
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();
	}

}
