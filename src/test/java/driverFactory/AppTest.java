package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunction.FunctionLibrary;
import utilites.ExcelFileUtil;



public class AppTest extends FunctionLibrary {
String inputpath ="./FileInput/LoginData.xlsx";
String outputpath ="./FileOutput/DataDrivenResults.xlsx";
ExtentReports report;
ExtentTest logger;
@Test
public void startTest() throws Throwable
{
	//define path of html report
	report = new ExtentReports("./target/ExtentReports/Login.html");
	boolean res =false;
	//create object for excel file util class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in login sheet
	int rc = xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		//start test case here
		logger =report.startTest("Validate Login");
		logger.assignAuthor("Ranga");
		//read username and password cells
		String username = xl.getCellData("Login", i, 0);
		String password = xl.getCellData("Login", i, 1);
		logger.log(LogStatus.INFO, username+"--------"+password);
		//call login method form functionlibaray with class name
		res = FunctionLibrary.adminlogin(username, password);
		if(res)
		{
			
			//if res is true write as Valid username and password into results cell
			xl.setCellData("Login", i, 2, "Valid username and password", outputpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "Pass", outputpath);
			logger.log(LogStatus.PASS, "Valid username and password");
		}
		else
		{
			//take screenshot and store
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//copy screen shot into local system
			FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"Loginpage.png"));
			//if res is false write as InValid username and password into results cell
			xl.setCellData("Login", i, 2, "Invalid username and password", outputpath);
			//write as Fail into status cell
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			logger.log(LogStatus.FAIL, "Invalid username and password");
		}
		report.endTest(logger);
		report.flush();
	}
}
}
