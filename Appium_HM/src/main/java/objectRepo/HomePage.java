package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AndroidDriverutiliy.AndroidDriverUtility;
import AndroidDriverutiliy.GestureUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
/**
 * @author aditya
 * Contains Web elements and Business method for Home Page
 */
public class HomePage extends GestureUtility{
 
	AndroidDriver driver;
	public HomePage(AndroidDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id = "com.hm.goe:id/btn_accept_cookies")
	private WebElement acceptCookiesBtn;
	@FindBy(id = "android:id/button2")
	private WebElement noThanksBtn;
	@FindBy(id = "com.hm.goe:id/action_search_button")
	private WebElement serchicon;

	@FindBy(xpath  ="//android.widget.TextView[@text='India/English']")
	private WebElement country;
	@FindBy(xpath  ="//android.widget.EditText")
	private WebElement serchEdt;
	@FindBy(id = "com.hm.goe:id/action_hub")
	private WebElement profileicon;
	@FindBy(xpath  ="//android.view.ViewGroup[@resource-id=\"com.hm.goe:id/hm_toolbar\"]/android.widget.ImageButton")
	private WebElement hamBergerIcon;
	
	
	
	///
	public WebElement getSerchicon() {
		return serchicon;
	}
	
	public WebElement getSerchEdt() {
		return serchEdt;
	}
	
	public WebElement getHamBergerIcon() {
		return hamBergerIcon;
	}

	/**
	 * @author aditya
	 * bussiness method for countryselection and accept cookis
	 */
	public void countrySelection()
	{
		scrollByText(driver, "India/English").click();
		acceptCookiesBtn.click();
		noThanksBtn.click();	
	}
}
