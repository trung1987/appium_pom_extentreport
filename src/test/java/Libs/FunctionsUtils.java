package Libs;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;


public class FunctionsUtils {

	//Execute adb commands
	public static String executeCommand(String cmd)
	{
	    String commandresponse="";
	    try
	    {
	        Runtime run = Runtime.getRuntime();
	        Process proc=run.exec(cmd);
	        BufferedReader stdInput = new BufferedReader(new 
	                InputStreamReader(proc.getInputStream()));

	        BufferedReader stdError = new BufferedReader(new 
	                InputStreamReader(proc.getErrorStream()));

	        String response=null;
	        while ((response = stdInput.readLine()) != null) 
	        {
	            if(response.length()>0)
	            {
	                commandresponse=commandresponse+response;
	            }
	        }

	        while ((response = stdError.readLine()) != null) 
	        {
	            commandresponse=commandresponse+response;

	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    //System.out.println(commandresponse);
	    return commandresponse;
	}
	
/*	//sleep
	public static void sleep(int timeInSeconds){
		try {
			Thread.sleep(timeInSeconds * 1000);
//				System.out.println("SLEEP IN " +timeInSeconds+ " SECONDS");
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}*/
	
/*	public static WebElement WaitPresentbyId(WebDriver driver, String id ) {
		WebDriverWait wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		
		return driver.findElement(By.id(id));
		
	}*/
	
/*	public static WebElement WaitPresentbyXpath(WebDriver driver, String xpath ) {
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
	
	//Verify two string
	public static boolean verifyTwoString(String ActualStr, String ExpectStr) {
		return ActualStr.equalsIgnoreCase(ExpectStr);
	}*/
	
	
	
	
	/*//download image to pc
	public static void downloadImg(String id,String imgUrl) {
		URL imageURL;
		try {
			//imageURL = new URL("https://pbs.twimg.com/profile_images/461167614/Icon_hirez.jpg");
			String []arr=null;
			if(imgUrl.contains("/")) {
				arr=imgUrl.split("/");
			} else {
				arr=imgUrl.split("\\");
			}
			imageURL = new URL(imgUrl);
			BufferedImage saveImage = ImageIO.read(imageURL);
			String name=arr[arr.length-1];
	        ImageIO.write(saveImage, "png", new File(name));
	        
	        File f = new File(name);

	        String path = f.getAbsolutePath();
	        
	        String adbpushcmd="adb -s "+id+ " push " + path + " /sdcard/DCIM";
	        System.out.println("push: " + adbpushcmd);
	        executeCommand(adbpushcmd);
	        
	        sleep(10);
	        
	        String adbreboot="adb -s "+id+" root";
	        executeCommand(adbreboot);
	        System.out.println("root: " + adbreboot);
	        sleep(10);
	        
	        String cmdKill_server,cmdStart_server;
	        cmdKill_server="adb -s "+id+" kill-server";
	        executeCommand(cmdKill_server);
	        sleep(10);
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	

		
	/*public static WebElement FindAppiumElesByIndex(AndroidDriver driver, String element, int index){
		List<WebElement> lst = driver.findElementsByClassName(element);
		if(lst.size()>0 && lst.size()>index) return lst.get(index);
		return null;
	}
	*/
	public static void clearInputText(AndroidDriver driver, WebElement ele){
		try {
			String str=ele.getText();
			for(int j=0; j< str.length();j++){
				driver.pressKeyCode(67);
				Thread.sleep(10);
			}
		} catch (Exception e) {
		}
		
	}
}
