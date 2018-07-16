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
public class LoginPage extends BasePage {

	//Page Variable
	int usernameIndex = 0;
	int passwordIndex = 1;
	String inputClass = "android.widget.EditText";
	String btnLogin = "//android.widget.TextView[contains(@text,'次へ')]";
	String alertError = "//android.widget.TextView[@resource-id='android:id/message']";
	String alertErrorContent = "Invalid username or password";
	String alertOKButtonID = "android:id/button1";
	
	 //Constructor
	public LoginPage(AppiumDriver<WebElement> driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public String getAlertError() {
		return this.alertErrorContent;
	}
	
	
	//Page Methods
	public void inputUserName(String username) {
		writeTextByIndex(inputClass, usernameIndex, username);
	}
	
	public void inputPassWord(String password) {
		writeTextByIndex(inputClass, passwordIndex, password);
	}
	
	public void clickSumbit() {
		click(btnLogin);
	}
	
	public boolean isUsreNameDisplayed() {
		return isElementDisplayedByClassIndex(inputClass, usernameIndex);
	}
	
	public boolean isPassDisplayed() {
		return isElementDisplayedByClassIndex(inputClass, passwordIndex);
	}
	
	public boolean isLoginButtonDisplayed() {
		return isElementDisplayed(btnLogin);

	}
	
	public boolean isLoginButtonAnabled() {
		return isElementEnableByXpath(btnLogin);
	}
	
	public boolean isAlertFailDisplayed() {
		return isElementDisplayed(alertError);
	}
	
	public void acceptAlertError() {
		clickOnEleByID(alertOKButtonID);
	}
}
