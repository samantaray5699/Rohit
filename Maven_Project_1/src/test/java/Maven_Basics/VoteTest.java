package Maven_Basics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class VoteTest {
	@Test
	public void voteTestcase()
	{
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.id("pollanswers-1")).click();
		driver.findElement(By.id("vote-poll-1")).click();
		Reporter.log("Vote Test Case is completed");
		driver.quit();
	}

}
