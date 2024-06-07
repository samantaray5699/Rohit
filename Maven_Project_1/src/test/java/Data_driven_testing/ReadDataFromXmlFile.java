package Data_driven_testing;

import java.io.FileInputStream;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromXmlFile {
		
	@Test
	public void sample(XmlTest test)  {
		String browser=test.getParameter("browser");
		String url=test.getParameter("url");
		String email=test.getParameter("email");
		String password=test.getParameter("password");
		
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
		driver.quit();
	}
}
