/**
 * 
 */
package TestCase;
/**
 * 
 */

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.classfile.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Libs.Constants;
import Libs.ExcelUtils;
import Libs.FunctionsUtils;
import PageTest.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utils.ExtentReports.ExtentTestManager;

/**
 * @author TrungTH_CA
 *
 */
public class Appointment  {

	String port;
	String id;
	String appPackage;
	AppiumDriver<WebElement> driver;
    DesiredCapabilities cap = new DesiredCapabilities();
	String line=" ========================================================= ";
	String comment="This comment is genreated by automation test";
	
	@Test
	public void testExcel() {
		try {
			ArrayList<ArrayList<String>> acc = ExcelUtils.readExcelAllData(Constants.ExcelCommonValue.ExcelPathFile, Constants.ExcelCommonValue.ExcelFile, Constants.ExcelCommonValue.MerchantInfoSheet);
		
			System.out.println(acc);

			ArrayList<String> ac = ExcelUtils.readExcelFileAtColumn(Constants.ExcelCommonValue.ExcelPathFile, Constants.ExcelCommonValue.ExcelFile, Constants.ExcelCommonValue.MerchantInfoSheet,Constants.MerchantInfoSheet.user);
			
			System.out.println(ac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(false,"cannot open file"+ e.getStackTrace());
		}
		
	}
	
	/*@AfterClass
 	public void teadDown(){
		Quit();
	}
	
	 public void Quit(){
		String os=System.getProperty("os.name");
		String cmdWin="taskkill /F /IM node.exe";
		String cmdMac="killall -9 node";
		if(os.contains("Mac")) {
			FunctionsUtils.executeCommand(cmdMac);
		} else {
			FunctionsUtils.executeCommand(cmdWin);
		}
		
	}
	

	@BeforeClass
	@Parameters({ "port","id","appPackage","appActivity"})
	 public void setUp(String port,String id, String appPackage,String appActivity) {
		System.out.println(line+" Opening App "+line);
		installApp(id,port,appPackage,appActivity);
		System.out.println(line+" Start Testing "+line);
		
	}
	
	 private void installApp(String id, String port, String appPackage,String appActivity){
		File appdir = new File("src/main/resources/");
		File app = new File(appdir, "2018071013_2.0.0__testRelease_Appointment.apk");
		// Get version of device and uninstall appium setting and unlock if it is version 7
		String cmd = "adb -s " +id +" shell getprop ro.build.version.release";
		String osVersion = FunctionsUtils.executeCommand(cmd);

		if (osVersion.contains("7")) {
			// uninstall io.appium.settings
			cmd = "adb -s " +id + "  uninstall  io.appium.settings";
			FunctionsUtils.executeCommand(cmd);

			// uninstall io.appium.unlock
			cmd = "adb -s " +id + "  uninstall  io.appium.unlock";
			FunctionsUtils.executeCommand(cmd);
			
		}	
		
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100000);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, id);
		cap.setCapability(MobileCapabilityType.UDID, id);
		cap.setCapability(MobileCapabilityType.APP, app);
		cap.setCapability(MobileCapabilityType.FULL_RESET, true);
		//cap.setCapability(MobileCapabilityType.NO_RESET,true);
		cap.setCapability("appActivity", appActivity);
		//cap.setCapability(MobileCapabilityType.APP, "bookmyshow");
		cap.setCapability("automationName", "Appium");
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("unicodeKeyboard", "true");                                     
		cap.setCapability("resetKeyboard", "true");
		try {
			AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(Integer.parseInt(port)));
			service.start();
			service.getUrl();
			
            driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:" + port + "/wd/hub"), cap);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
	}
	
	 
	 public void clearText() {
		WebElement username=FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.EditText", 0);
		WebElement password=FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.EditText", 1);
		
		FunctionsUtils.clearInputText((AndroidDriver)driver,username);
		FunctionsUtils.clearInputText((AndroidDriver)driver,password);
	 }
	 
	 @Test(priority=1)
	 public void TC0_VerifyLoginPage() {
		//ExtentTestManager.getTest().setDescription("Verify Login UI.");
		System.out.println(line+" Verify Login UI "+line);
		FunctionsUtils.WaitPresentbyXpath(driver, "//android.widget.TextView[contains(@text,'Appointment Login')]");
		
		WebElement username=FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.EditText", 0);
		WebElement password=FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.EditText", 1);
		WebElement btnLogin=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'次へ')]"));
		Assert.assertTrue(username.isDisplayed());
		Assert.assertTrue(password.isDisplayed());
		Assert.assertTrue(btnLogin.isDisplayed());
		Assert.assertTrue(btnLogin.isEnabled());
		
	 }
	 @Test(priority=2)
	 @Parameters({"Accout", "Pass"})
	 public void TC1_loginAppSuccess(String Accout, String Pass) {
		ExtentTestManager.getTest().setDescription(" Login App Successful :" + Accout+"/"+Pass);
		System.out.println(line+" Login App Successful "+line);
		loginApp(driver,Accout,Pass);
		Assert.assertTrue(VerifyLogin(driver, "//android.widget.TextView[contains(@text,'本日の')]"),"Login Success");
		FunctionsUtils.sleep(5);
		logOut(driver);
		System.out.println(line);
    }

	 @Test(priority=3)
	 @Parameters({"Accout", "Pass"})
	 public void TC2_loginAppFailEmptyAccout(String Accout, String Pass) {
		ExtentTestManager.getTest().setDescription(" Login App Fail with Empty Acc ");
		System.out.println(line+" Login App Fail with Empty Acc "+line);
		loginApp(driver," ",Pass);
		FunctionsUtils.sleep(5);
		acceptAlert(driver);
	 }
	 
	 @Test(priority=3)
	 @Parameters({"Accout", "Pass"})
	 public void TC2_loginAppFailEmptyPass(String Accout, String Pass) {
		ExtentTestManager.getTest().setDescription(" Login App Fail with Empty Pass " );
		System.out.println(line+" Login App Fail with Empty Pass "+line);
		loginApp(driver,Accout," ");
		FunctionsUtils.sleep(5);
		acceptAlert(driver);
	 }
	 
	 @Test(priority=4)
	 @Parameters({"Accout", "Pass"})
	 public void TC3_loginAppFailWrongAcc(String Accout, String Pass) {
		ExtentTestManager.getTest().setDescription(" Login App Fail with Wrong Acc : " + Accout+"123/"+Pass);
		System.out.println(line+" Login App Fail with Wrong Acc "+line);
		loginApp(driver,Accout+"123",Pass);
		FunctionsUtils.sleep(5);
		acceptAlert(driver);
	 }
	 
	 
	 @Test(priority=5)
	 @Parameters({"Accout", "Pass"})
	 public void TC4_loginAppFailWrongPass(String Accout, String Pass) {
		 ExtentTestManager.getTest().setDescription(" Login App Fail with Wrong Pass :" + Accout+"/"+Pass+"123");
		System.out.println(line+" Login App Fail with Wrong Pass "+line);
		loginApp(driver,Accout,Pass+"123");
		FunctionsUtils.sleep(5);
		acceptAlert(driver);
	 }
	 
	 @Test(priority=6)
	 @Parameters({"Accout", "Pass"})
	 public void TC5_loginAppFailSpecialCharacter(String Accout, String Pass) {
		ExtentTestManager.getTest().setDescription(" Login App Fail with Spacial Character :!@#$%^&" + "/"+Pass);
		System.out.println(line+" Login App Fail with Spacial Character "+line);
		loginApp(driver,"!@#$%^&",Pass);
		FunctionsUtils.sleep(5);
		acceptAlert(driver);
	 }
	 
	 @Test(priority=7)
	 @Parameters({"Accout", "Pass"})
	 public void TC6_FailCaseToCheckAlert(String Accout, String Pass) {
		//ExtentTestManager.getTest().setDescription(" Login App Successful but Verify wrong info to check Alert" );
		System.out.println(line+" Login App Successful but Verify wrong info to check Alert "+line);
		loginApp(driver,Accout,Pass);
		
		Assert.assertTrue(VerifyLogin(driver, "//android.widget.TextView[contains(@text,'abc')]"), "Cannot find element");
		
		FunctionsUtils.sleep(5);
		logOut(driver);
		System.out.println(line);
    }
	 
	 public void loginApp(AppiumDriver<WebElement> driver,String acc,String pass) {
		 FunctionsUtils.WaitPresentbyXpath(driver, "//android.widget.TextView[contains(@text,'Appointment Login')]");
		 System.out.println(line+ acc + "---"+ pass+line);
		 clearText();
		 WebElement username=FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "  sandroid.widget.EditText", 0);
		 username.clear();
		 FunctionsUtils.sleep(5);
		 username.sendKeys(acc);
		 WebElement password=FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.EditText", 1);
		 password.clear();
		 FunctionsUtils.sleep(5);
		 password.sendKeys(pass);
		 WebElement btnLogin=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'次へ')]"));
		 btnLogin.click();
		
	 }
	 
	 public boolean VerifyLogin(AppiumDriver<WebElement> driver,String xpath) {
		 //android.widget.ImageView
		
		if(driver.findElement(By.xpath(xpath)).isDisplayed())return true;
		
		return false;
	 }
	 
	 public void logOut(AppiumDriver<WebElement> driver) {
		 FunctionsUtils.WaitPresentbyXpath(driver, "//android.widget.TextView[contains(@text,'本日の')]");
		 WebElement btnSettings = FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.ImageView", 1);
		 btnSettings.click();
		 
		 FunctionsUtils.WaitPresentbyXpath(driver, "//android.widget.TextView[contains(@text,'予約システムの設定')]");
		 WebElement btnlogout = FunctionsUtils.FindAppiumElesByIndex((AndroidDriver)driver, "android.widget.ImageView", 1);
		 btnlogout.click();
		 
		 FunctionsUtils.WaitPresentbyId(driver, "android:id/alertTitle");
		 driver.findElement(By.id("android:id/button1")).click();
		 
	 }

	 public void acceptAlert(AppiumDriver<WebElement> driver) {
		Assert.assertTrue(VerifyLogin(driver, "//android.widget.TextView[@resource-id='android:id/message']"));
		driver.findElement(By.id("android:id/button1")).click();
		System.out.println(line);
	 }*/
}

