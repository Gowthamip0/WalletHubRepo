package com.wallethub.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wallethub.reports.Report;

public class Util {

	public static String getProperty(String key) {
		Properties prop = null;
		try {
			File file = new File(System.getProperty("user.dir") + "\\config.properties");
			FileInputStream fi = new FileInputStream(file);
			prop = new Properties();
			prop.load(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) prop.get(key);
	}

	public static void sendKeys(WebDriver driver, WebElement element, String text, String desc) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(text);
			Report.passTest("User entered " + text + " into " + desc);
		} catch (Exception e) {
			Report.failTest(driver, "User unable to enter text!!! " + e.getMessage());
		}
	}

	public static void click(WebDriver driver, WebElement element, String desc) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			Report.passTest("User clicked on " + desc);
		} catch (Exception e) {
			Report.failTest(driver, "User unable click on element!!! " + e.getMessage());
		}
	}

	public static String getRandomNumWithCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		// if (timeZone.toLowerCase().equals("utc")) {
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		/*
		 * } else {
		 * dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault())); }
		 */
		String format = dateFormat.format(new Date());
		String formatNew = format.replaceAll("[^0-9]", "");
		formatNew = formatNew.replace(":", "");
		formatNew = formatNew.replace("-", "");
		return formatNew;
	}

	public static String getText(WebDriver driver, WebElement element, String attribute) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			if (attribute.equalsIgnoreCase("text")) {
				return wait.until(ExpectedConditions.elementToBeClickable(element)).getText();
			} else {
				return wait.until(ExpectedConditions.elementToBeClickable(element)).getAttribute(attribute);
			}
		} catch (Exception e) {
			Report.failTest(driver, "User unable click on element!!! " + e.getMessage());
		}
		return null;
	}

	public static String captureScreenShot(WebDriver driver) {
		String dest = "";
		try {
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			dest = System.getProperty("user.dir") + "\\Reports\\" + getRandomNumWithCurrentDate() + ".png";
			File target = new File(dest);
			FileUtils.copyFile(src, target);
			return dest;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}

	public static void hardWait(int sec) {
		try {
			Thread.sleep(1000 * sec);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void mouseHover(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}

	public static void scroll(WebDriver driver, WebElement element) {
//		waitForElement(driver, element);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			// js.executeScript("window.scrollBy(0,-250)", "");
		} catch (NoSuchElementException e) {
			Report.failTest(element.toString() + " not found while scrolling to element.");
		}
	}
}
