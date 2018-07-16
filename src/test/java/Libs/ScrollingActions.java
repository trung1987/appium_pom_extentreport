package Libs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author user
 *
 */
public class ScrollingActions {

	//scrolling
	public static void scrollToEle(AppiumDriver<WebElement> driver,String id, String contentSearch)  {
        try {
        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        	String xpath="//"+id+"[contains(@text,'"+contentSearch+"')]";
        	System.out.println(xpath);
        	while (driver.findElements(By.xpath(xpath)).size()==0){

        		swipeVertical((AndroidDriver) driver,0.8,0.2,0.2,2000);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);
	}
	
	public static void scrollManytimes(AppiumDriver<WebElement> driver,int times)  {
        try {
        	//System.out.println("scrolling............."+times);
        	swipeVertical((AndroidDriver) driver,0.8,0.2,0.2,2000);
        	for(int i=1;i<times;i++){
        		swipeVertical((AndroidDriver) driver,0.8,0.2,0.2,2000);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
      
	}
	
	public static void swipeVertical(AndroidDriver<WebElement> driver, double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
		Dimension size = driver.manage().window().getSize();
	    int anchor = (int) (size.width * anchorPercentage);
	    int startPoint = (int) (size.height * startPercentage);
	    int endPoint = (int) (size.height * finalPercentage);
	    new TouchAction(driver).press(anchor, startPoint).waitAction(duration).moveTo(anchor, endPoint).release().perform();
	}

	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
}
