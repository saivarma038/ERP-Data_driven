package commonfunction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
// method for login
	public  static boolean adminlogin (String user,String pass)throws Throwable
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys("user");
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys("pass");
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Valid username and password ::"+Expected+"---------"+Actual,true);
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else
		{
			String Error_message = driver.findElement(By.xpath(conpro.getProperty("ObjErrormessage"))).getText();
			Thread.sleep(2000);
			driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
			Reporter.log(Error_message+"----"+Expected+"----"+Actual,true);
			return false;
		}
	}

	
}
