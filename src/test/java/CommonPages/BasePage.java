package CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libs.FindElementActions;
import Libs.WaitActions;
import io.appium.java_client.AppiumDriver;

public class BasePage {
	protected  AppiumDriver<WebElement> driver;
	protected WebDriverWait wait;

    //Constructor
	protected BasePage ( AppiumDriver<WebElement> driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    //===============Click Method
	protected void click (String elementLocation) {
       FindElementActions.FindAppiumEleByXpath(driver, elementLocation).click();
    }

	protected void clickOnEleByClassIndex (String elementLocation, int index) {
       FindElementActions.FindAppiumElesByIndex(driver, elementLocation, index).click();
    }
    
	protected void clickOnEleByID (String id) {
       FindElementActions.FindAppiumEleByID(driver, id).click();
    }
	//===============Check element is clickable

	protected boolean isElementEnableByXpath(String locator) {
    	WebElement ele = WaitActions.WaitPresentbyXpath(driver, locator);
    	return ele.isEnabled();
    }
    //===============Write Text
	protected void writeText (By elementLocation, String text) {
        driver.findElement(elementLocation).sendKeys(text);
    }

	protected void writeTextByIndex(String elementLocation,int index, String text) {
        WebElement ele = Libs.FindElementActions.FindAppiumElesByIndex(driver, elementLocation, index);
    	ele.clear();
    	ele.sendKeys(text);
    	WaitActions.sleep(5);
    }
    
    //===============Read Text
	protected String readText (By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }

    //===============Check element is displayed
	protected boolean isElementDisplayed(String locator) {
    	WebElement ele = WaitActions.WaitPresentbyXpath(driver, locator);
    	return ele.isDisplayed();
    }
    
	protected boolean isElementDisplayedByID(String id) {
    	WebElement ele = WaitActions.WaitPresentbyId(driver, id);
    	return ele.isDisplayed();
    }
	
	protected boolean isElementDisplayedByClassIndex(String locator, int index) {
    	WebElement ele = FindElementActions.FindAppiumElesByIndex(driver, locator, index);
    	return ele.isDisplayed();
    }
	
	
}
