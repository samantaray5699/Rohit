package Data_driven_testing;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class ReadDataFromJsonFile {

	@Test
	public void test() throws ParseException, IOException
	{
		//step1-parse Json file into java object using jsonparse class

		JSONParser parser= new JSONParser();
		Object obj=parser.parse(new FileReader("C:\\Users\\\\adity\\OneDrive\\Desktop\\datadriven\\commondatajson.json"));
		//step2-type caste obj to jsonobject
		JSONObject jobj= (JSONObject)obj;
		//step3-get the value
		 Object browser = jobj.get("browser").toString();
		
		
		 String url = (String) jobj.get("url");
		long timeout= (long) jobj.get("timeout");
		String email=(String) jobj.get("email");
		String password=(String) jobj.get("password");

		WebDriver driver=null;

		//read common data from json file
		if(browser.equals("chrome"))
			driver=new ChromeDriver(); 
		else if(browser.equals("edge"))
			driver=new EdgeDriver();
		else
			driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		driver.quit();
	}

}
