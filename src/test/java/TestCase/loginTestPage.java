/**
 * 
 */
package TestCase;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonPages.*;
import Libs.Constants;
import Libs.ExcelUtils;
import Libs.WaitActions;
import PageTest.BaseTest;
import utils.ExtentReports.ExtentTestManager;

/**
 * @author user
 *
 */
public class loginTestPage extends BaseTest {

	public static ArrayList<String> arr;

	
	
	@Test (priority = 0, description="Verify Login Screen")
    public void VerifyLoginScreen () throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Verify Login Screen");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Username Input field is displayed. ");
        loginPage.isUsreNameDisplayed();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify PassWord Input field is displayed.");
        loginPage.isPassDisplayed();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Login Button field is displayed." );
        loginPage.isLoginButtonDisplayed();
        loginPage.isLoginButtonAnabled();
        
        WaitActions.sleep(5);
	}
    
	@Test (priority = 1, description="Valid Login Scenario with  username and password.")
	@Parameters({"Accout", "Pass"})
    public void ValidLoginTest_ValidUserNameValidPassword (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Valid Login Scenario with  username and password.");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
        HomePage homePage = new HomePage(driver, wait);
        SettingsPage settingsPage = new SettingsPage(driver, wait);
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input username " + Accout);
        loginPage.inputUserName(Accout);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input password " + Pass);
        loginPage.inputPassWord(Pass);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Home Screen is displayed" );
        homePage.isHomeScreenDisplayed();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click Settings button" );
        homePage.clickSettingButton();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Settings Screen is displayed" );
        settingsPage.isSettingScreenDisplayed();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click SigOut button" );
        settingsPage.clickLogOutButton();
        WaitActions.sleep(5);
	}
	
	
	
	@Test (priority = 2, description="Invalid Login Scenario with username and wrong password.")
	@Parameters({"Accout", "Pass"})
    public void InvalidLoginTest_ValidUserNameInvalidPassword (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Invalid Login Scenario with username and wrong password.");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
        
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input username " + Accout);
        loginPage.inputUserName(Accout);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input password " + Pass+"123");
        loginPage.inputPassWord(Pass+"123");
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Alert \"" + loginPage.getAlertError()+"\"");
        if(loginPage.isAlertFailDisplayed()) {
        	ExtentTestManager.getTest().log(LogStatus.INFO, "Click button OK");
        	loginPage.acceptAlertError();
        }
        WaitActions.sleep(5);
	}
	
	
	@Test (priority = 3, description="Invalid Login Scenario with wrong username and correctly password.")
	@Parameters({"Accout", "Pass"})
    public void InvalidLoginTest_InvalidUserNameValidPassword (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Invalid Login Scenario with wrong username and correctly password.");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
      
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input username " + Accout+"123");
        loginPage.inputUserName(Accout+"123");
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input password " + Pass);
        loginPage.inputPassWord(Pass);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Alert \"" + loginPage.getAlertError()+"\"");
        if(loginPage.isAlertFailDisplayed()) {
        	ExtentTestManager.getTest().log(LogStatus.INFO, "Click button OK");
        	loginPage.acceptAlertError();
        }
        WaitActions.sleep(5);
	}
	
	
	@Test (priority = 4, description="Invalid Login Scenario with empty username and valid password.")
	@Parameters({"Accout", "Pass"})
    public void InvalidLoginTest_EmptyUserNameValidPassword (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Invalid Login Scenario with empty username and valid password.");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
       
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input a space to username field. ");
        loginPage.inputUserName(" ");
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input password  " + Pass);
        loginPage.inputPassWord(Pass);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Alert \"" + loginPage.getAlertError()+"\"");
        if(loginPage.isAlertFailDisplayed()) {
        	ExtentTestManager.getTest().log(LogStatus.INFO, "Click button OK");
        	loginPage.acceptAlertError();
        }
        WaitActions.sleep(5);
	}
	
	@Test (priority = 5, description="Invalid Login Scenario with valid username and empty password.")
	@Parameters({"Accout", "Pass"})
    public void InvalidLoginTest_ValidUserNameEmptyPassword (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Invalid Login Scenario with valid username and empty password.");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
       
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input  username  " + Accout);
        loginPage.inputUserName(Accout);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input a space to password  " );
        loginPage.inputPassWord(" ");
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Alert \"" + loginPage.getAlertError()+"\"");
        if(loginPage.isAlertFailDisplayed()) {
        	ExtentTestManager.getTest().log(LogStatus.INFO, "Click button OK");
        	loginPage.acceptAlertError();
        }
        WaitActions.sleep(5);
	}
	
	@Test (priority = 6, description="Invalid Login Scenario with special characters")
	@Parameters({"Accout", "Pass"})
    public void InvalidLoginTest_SpecialCharacters (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Invalid Login Scenario with special characters");
 
        //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
       
 
        //Variable
        String temp = "@#$%^!";
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input username  " + temp);
        loginPage.inputUserName(temp);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input password  " + temp);
        loginPage.inputPassWord(temp);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Alert \"" + loginPage.getAlertError()+"\"");
        if(loginPage.isAlertFailDisplayed()) {
        	ExtentTestManager.getTest().log(LogStatus.INFO, "Click button OK");
        	loginPage.acceptAlertError();
        }
        WaitActions.sleep(5);
	}
	
	
	@Test (priority = 7, description="Invalid Login Scenario with Fail Case To Check Alert")
	@Parameters({"Accout", "Pass"})
    public void InvalidLoginTest_FailCaseToCheckAlert (String Accout, String Pass) throws InterruptedException {
        //ExtentReports Description
        ExtentTestManager.getTest().setDescription("Invalid Login Scenario with Fail Case To Check Alert");
 
        //*************PAGE INSTANTIATIONS*************
      //*************PAGE INSTANTIATIONS*************
        LoginPage loginPage = new LoginPage(driver,wait);
        HomePage homePage = new HomePage(driver, wait);
        SettingsPage settingsPage = new SettingsPage(driver, wait);
 
        //*************PAGE METHODS********************
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input username " + Accout);
        loginPage.inputUserName(Accout);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Input password " + Pass);
        loginPage.inputPassWord(Pass);
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click submit button" );
        loginPage.clickSumbit();
        
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Home Screen is displayed  with text" + homePage.getHomeStatus());
        homePage.isHomeScreenDisplayedFail();
        
        WaitActions.sleep(5);
	}
}
