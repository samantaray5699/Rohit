package Data_driven_testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class DWS_ReadDataFromCMDLineTest {
	@Test
	public void tc()
	{
		
		//data from cmd line
		WebDriver driver=null;
		String browser=System.getProperty("browser");
		String url=System.getProperty("url");
		String email=System.getProperty("email");
		String password=System.getProperty("password");
		
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
	}

}
