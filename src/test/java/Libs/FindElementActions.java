/**
 * 
 */
package Libs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author user
 *
 */
public class FindElementActions {
	
	public static WebElement FindAppiumElesByIndex(AppiumDriver<WebElement> driver, String element, int index){
		List<WebElement> lst = driver.findElementsByClassName(element);
		if(lst.size()>0 && lst.size()>index) return lst.get(index);
		return null;
	}
	
	
	public static WebElement FindAppiumEleByXpath(AppiumDriver<WebElement> driver, String element){
		return driver.findElement(By.xpath(element));
	}
	
	public static WebElement FindAppiumEleByID(AppiumDriver<WebElement> driver, String id){
		return driver.findElement(By.id(id));
	}
}
