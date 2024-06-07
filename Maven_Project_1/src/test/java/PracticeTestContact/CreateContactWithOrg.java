package PracticeTestContact;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateContactWithOrg {
	@Test
	public void TC03() throws EncryptedDocumentException, IOException
	{//integration
		FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\commondataCrm.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		String browser=pobj.getProperty("browser");
		String url=pobj.getProperty("url");
		String username=pobj.getProperty("username");
		String password=pobj.getProperty("password");
		
		Random ran= new Random();	
		int num=ran.nextInt(2000);
		
		FileInputStream fis1= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis1);
		String org = wb.getSheet("Sheet4").getRow(4).getCell(3).toString()+num;
		String lastname = wb.getSheet("Sheet4").getRow(4).getCell(2).toString();
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
		driver.findElement(By.name("accountname")).sendKeys(org);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(org))
		{
			Reporter.log(org+ "is displayed===pass");
		}
		else
		{
			Reporter.log(org+ "is not displayed===fail");
		}
		
		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		String win = driver.getWindowHandle();
		Set<String> allwin = driver.getWindowHandles();
		allwin.remove(win);
		for(String child:allwin)
		{
			driver.switchTo().window(child);
			String actualurl = driver.getCurrentUrl();
			if(actualurl.contains("module=Accounts&action")) {
			   break;
			}
			//here we have one child window but in general we have to do like this
		}
		
		
		driver.findElement(By.name("search_text")).sendKeys(org);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+org+"']")).click();//making dynamic xpath
		driver.switchTo().window(win);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verifyheader
		String actuallastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String actualorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	

		if(actuallastname.contains(lastname)&& actualorgname.trim().equals(org))//trim to remove space
		{
			Reporter.log(lastname+org+"is displayed ====pass");
		}
		else
		{
			Reporter.log(lastname+org+"is displayed ====fail");
		}
		
		//signout
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
