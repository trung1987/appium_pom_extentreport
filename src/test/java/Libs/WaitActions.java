/**
 * 
 */
package Libs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * @author user
 *
 */
public class WaitActions {

	//sleep
	public static void sleep(int timeInSeconds){
		try {
			Thread.sleep(timeInSeconds * 1000);
	//					System.out.println("SLEEP IN " +timeInSeconds+ " SECONDS");
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	public static WebElement WaitPresentbyId(WebDriver driver, String id ) {
		WebDriverWait wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		return driver.findElement(By.id(id));
	}
	
	public static WebElement WaitPresentbyXpath(WebDriver driver, String xpath ) {
		WebDriverWait wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return driver.findElement(By.xpath(xpath));
	}
	
	//Wait for visible of element(FindElementActions By,Element)
	public static WebElement presenceOfTheElementID(WebDriver driver, final String id) {
		//final WebElement ele;	
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return  driver.findElement(By.id(id));	
			}
		});
	}
	
	
}
