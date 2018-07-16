/**
 * 
 */
package CommonPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

/**
 * @author user
 *
 */
public class HomePage extends BasePage{

	//Page Variable
	String homeStatus = "//android.widget.TextView[contains(@text,'本日の')]";
	String temp = "//android.widget.TextView[contains(@text,'abc')]";
	String imgClass = "android.widget.ImageView";
	int imgSetting = 1;
	//Constructor
	public HomePage(AppiumDriver<WebElement> driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public String getHomeStatus() {
		return temp;
	}

	//Page Methods
	public void clickSettingButton() {
		clickOnEleByClassIndex(imgClass, imgSetting);
	}
	
	public boolean isHomeScreenDisplayed() {
		return isElementDisplayed(homeStatus);
	}
	
	public boolean isHomeScreenDisplayedFail() {
		return isElementDisplayed(temp);
	}
}
