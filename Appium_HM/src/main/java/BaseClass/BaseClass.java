package BaseClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import AndroidDriverutiliy.AndroidDriverUtility;
import AndroidDriverutiliy.GestureUtility;
import Fileutility.Fileutility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * @author aditya
 * Base class
 */
public class BaseClass {
	public AndroidDriver driver;
	public static AndroidDriver sdriver;
	AppiumDriverLocalService as;
	Fileutility fu= new Fileutility();
	public  AndroidDriverUtility adu= new AndroidDriverUtility();
	

	@BeforeSuite
	public void configBs()
	{
		System.out.println("connect to Appium server");
		File f= new File("C:\\Users\\adity\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
		as=new AppiumServiceBuilder().withAppiumJS(f).
				withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();//withtimeout is optional

		as.start();

	}

	@BeforeClass
	public void configBc() throws Throwable
	{
		System.out.println("capability");
		
		DesiredCapabilities dc= new DesiredCapabilities();
		dc.setCapability("platformName", fu.getdataFromPropertyFile("platformName"));
		dc.setCapability("automationName",fu.getdataFromPropertyFile("automationName"));
		dc.setCapability("deviceName",fu.getdataFromPropertyFile("deviceName"));
		dc.setCapability("UDID", fu.getdataFromPropertyFile("UDID"));
		dc.setCapability("noReset",false);
		dc.setCapability("fullReset", false);
		dc.setCapability("autoGrantPermission",true);
		dc.setCapability("ignorHiddenApiPolicyError", true);
		dc.setCapability("appPackage",fu.getdataFromPropertyFile("appPackage"));
		dc.setCapability("appActivity",fu.getdataFromPropertyFile("appActivity"));
		URL u= new URL("http://localhost:4723");
		driver= new AndroidDriver(u,dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		DesiredCapabilities dc= new DesiredCapabilities();
//		dc.setCapability("platformName", "android");
//		dc.setCapability("automationName", "uiAutomator2");
//		dc.setCapability("deviceName","iQOO Z5");
//		dc.setCapability("UDID", "1367872601000BB");;
//		dc.setCapability("autoGrantPermission",true);
//		dc.setCapability("noReset","false");
//		dc.setCapability("fullReset", "false");
//		dc.setCapability("ignorHiddenApiPolicyError", true);
//		dc.setCapability("appPackage","com.hm.goe");
//		dc.setCapability("appActivity",".startup.StartupActivity");
//		URL u= new URL("http://localhost:4723");
//		driver= new AndroidDriver(u,dc);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		sdriver= driver;

	}

	@AfterClass
	public void configAc() throws IOException
	{
		System.out.println("Close app");
		adu.closeApp(driver, fu.getdataFromPropertyFile("appPackage"));


	}
	@AfterSuite
	public void configAs()
	{
		System.out.println("close  appium serverconnection");
		as.stop();


	}
}
