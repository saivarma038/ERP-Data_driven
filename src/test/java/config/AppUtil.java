package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeTest
public static void setup () throws Throwable
{
	conpro = new Properties();
	//load property file 
	conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
	driver = new ChromeDriver();
	driver.manage().window().maximize();}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		Reporter.log("Browser value is not matching ", true);
		
	}
	}
@AfterTest
public static void teardown()
{
	driver.quit();
}
}


 

