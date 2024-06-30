package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AndroidDriverutiliy.GestureUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
/**
 * @author aditya
 * Contains Web elements and Business method for All Product page
 */
public class AllProductpage extends GestureUtility {

	AndroidDriver driver;
	public AllProductpage(AndroidDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}               
	@FindBy(xpath = "(//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.hm.goe:id/horizontal_images\"])[1]/android.view.View/android.view.View/android.widget.ImageView")
	private WebElement firstproduct;
	@FindBy(id  = "com.hm.goe:id/action_feed_favourite")
	private WebElement favoritesIcon;
	
	
	public WebElement getFirstproduct() {
		return firstproduct;
	}

	public WebElement getFavoritesIcon() {
		return favoritesIcon;
	}
	
	
}
