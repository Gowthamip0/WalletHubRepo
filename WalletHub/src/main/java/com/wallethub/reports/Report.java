package com.wallethub.reports;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.wallethub.config.BaseClass;
import com.wallethub.utils.Util;

public class Report {
	
	
	static ExtentReports extent;

	public static ExtentReports getReporter(String reportName) {
		if (extent == null) {
			ExtentSparkReporter html = new ExtentSparkReporter(reportName);
			html.config().setDocumentTitle("WalletHub extent report");
			html.config().setReportName("QA xperts");
			html.config().setTheme(Theme.STANDARD);
			extent = new ExtentReports();
			extent.attachReporter(html);
		}
		return extent;
	}
	
	public static ExtentTest startReport(String reportName, String testName, String desc) {
		return getReporter(reportName).createTest(testName, desc);
	}

	public static void passTest(String desc) {
		BaseClass.report.pass(desc);
		BaseClass.log.info(desc);
	}
	
	public static void passTest(WebDriver driver, String desc) {
		try {
			BaseClass.report.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(Util.captureScreenShot(driver)).build());
			BaseClass.log.info(desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void failTest(String desc) {
		BaseClass.report.fail(desc);
		BaseClass.log.error(desc);
	}
	
	public static void failTest(WebDriver driver,String desc) {
		try {
			BaseClass.report.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(Util.captureScreenShot(driver)).build());
			BaseClass.log.error(desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
