/**
 * 
 */
package Libs;

import java.io.IOException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * @author user
 *
 */
public class KeyActions {
	//send key Enter
	public static void sendKeyEnter(AppiumDriver driver) throws IOException{
		try {
			
			((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.ENTER);
			((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.ENTER);
		
		} catch (Exception e) {
			System.out.println("Cannot send enter key!" +e.getMessage());
				
		}
	}
}
