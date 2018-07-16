package PageTest;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentTest;

import Libs.FunctionsUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseTest {
   // public WebDriver driver;
	public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;
    public ExtentTest rp;
    
    public AppiumDriver<WebElement> getDriver() {
        return driver;
    }

    @BeforeTest
    @Parameters({ "port","id","appPackage","appActivity", "appName"})
    public void setup (String port,String id, String appPackage,String appActivity,String appName) {
    	/*String id ="H6NPCV040630VU6";
    	String port ="4726";
    	String appActivity ="com.active.appointment.MainActivity";
    	String appPackage ="com.active.appointment.dev";*/
    	File appdir = new File("src/main/resources/");
		File app = new File(appdir, appName);
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
		DesiredCapabilities cap = new DesiredCapabilities();
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
        	Assert.assertTrue(false, "Cannot install app due to " + e.getStackTrace());
        }
    }

    @AfterTest
    public void teardown () {
    	//driver.quit();
    	String os=System.getProperty("os.name");
		String cmdWin="taskkill /F /IM node.exe";
		String cmdMac="killall -9 node";
		if(os.contains("Mac")) {
			FunctionsUtils.executeCommand(cmdMac);
		} else {
			FunctionsUtils.executeCommand(cmdWin);
		}
		
    }

	
}
