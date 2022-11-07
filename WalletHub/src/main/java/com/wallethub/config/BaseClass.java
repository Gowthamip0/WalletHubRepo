package com.wallethub.config;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.wallethub.reports.Report;
import com.wallethub.utils.Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	
	public static ExtentTest report;
	public static Logger log = LogManager.getLogger(BaseClass.class.getName());
	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	
	@BeforeTest
	public void beforeTest() {
		
	}
	
	@BeforeMethod
	@Parameters({"application"})
	public void beforeMethod(Method method, @Optional("url") String application) {
		String browser = Util.getProperty("browser");
		String url = Util.getProperty(application);
		if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--disable-notifications");
//			System.setProperty("webdriver.chrome.driver", "D:\\Workspace\\Workspace_Java\\Workspace_Lab\\WalletHub\\Drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			System.out.println("Invalid browser!!!!!!!!!!!!");
		}
		report = Report.startReport(System.getProperty("user.dir")+"\\Reports\\Report.html", method.getName(), "").assignCategory("Web").assignAuthor("QA xperts");
		report.info("<<<<<<<<<< Execution started >>>>>>>>>>");
		report.pass(browser + " launched!!!!!!");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		log.info(browser+ " launched.");
		log.info(url+" opened.");
		report.pass(url+" opened.");
		
	}
	
	@AfterMethod
	public void afterMethod() {
		report.info("<<<<<<<<<< Execution complted >>>>>>>>>>");
		if (driver != null) {
			driver.quit();
		}
		Report.getReporter(System.getProperty("user.dir")+"\\Reports\\Report.html").flush();
	}
	
	@AfterSuite
	public void afterSuite() {
		
	}
	
	

}
