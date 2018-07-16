/**
 * 
 */
package CommonPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;

/**
 * @author user
 *
 */
public class SettingsPage extends BasePage {

	String imgClass = "android.widget.ImageView";
	String settingScreen = "//android.widget.TextView[contains(@text,'予約システムの設定')]";
	String alertLogoutID ="android:id/alertTitle";
	String alertConfirmLogOutID = "android:id/button1";
	
	int imgLogOutButton = 1;
	
	public SettingsPage(AppiumDriver<WebElement> driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	//Page Methods
	
	public boolean isSettingScreenDisplayed() {
		return isElementDisplayed(settingScreen);
	} 
	
	public void clickLogOutButton() {
		clickOnEleByClassIndex(imgClass, imgLogOutButton);
		if(isElementDisplayedByID(alertLogoutID)) {
			clickOnEleByID(alertConfirmLogOutID);
		} else {
			Assert.assertTrue(false, "Can not log out");
		}
	}
}
