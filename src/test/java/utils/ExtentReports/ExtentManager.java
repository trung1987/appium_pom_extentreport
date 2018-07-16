package utils.ExtentReports;

import java.io.File;
import java.time.LocalDate;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

    private static ExtentReports extent;
    //private static ExtentTest logger;
    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
        	 String workingDir = System.getProperty("user.dir");
        	 //System.out.println("==============================================================="+workingDir);
             extent = new ExtentReports(workingDir+"\\ExtentReports\\ExtentReportResults_"+LocalDate.now().toString()+".html", true);
             extent
             .addSystemInfo("Host Name", "Active Media VN")
             .addSystemInfo("Div", "QC-Automation")
             .addSystemInfo("Tester", "To Hieu Trung")
             .addSystemInfo("Running App", "Tablet Appointment App");
             extent.loadConfig(new File(workingDir+"\\extent-config.xml"));
        }
        return extent;
    }
    
    
}
