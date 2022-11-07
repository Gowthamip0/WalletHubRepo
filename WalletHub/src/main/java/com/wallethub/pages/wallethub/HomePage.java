package com.wallethub.pages.wallethub;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.wallethub.reports.Report;
import com.wallethub.utils.Util;


public class HomePage {
	WebDriver driver;
	private String title = "test insurance company metatitle test";
	public HomePage(WebDriver driver) {
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(), 'Select')]")	private WebElement select_groupInsurance;
	@FindBy(xpath="//div[@class='android textarea-user']/textarea")	private WebElement input_review;
	@FindBy(xpath="//div[contains(text(), 'Submit')]")	private WebElement btn_Submit;
	
	public void isOpened() {
		Assert.assertEquals(driver.getTitle(), title, "Test Insurance Company page not properly loaded!!!!!!!!!!");
		Report.passTest(driver, "Test Insurance Company page is successfully launched");
	}

	public void giveRating(int rating) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement lbl_rating = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3/parent::div//div[@class='rating-box-wrapper']/*["+rating+"]")));
		Util.scroll(driver, lbl_rating);
		Util.hardWait(3);
		Util.mouseHover(driver, lbl_rating);
		Util.hardWait(6);
		Report.passTest(driver, "User clicks on "+rating+" star rating!!!!!!!!!!!!!!!!!");
		Util.click(driver, lbl_rating, rating+" star rating");
	}
	
	public void selectValueFromGroup(String text) {
		Util.waitForElement(driver, select_groupInsurance);
		Util.click(driver, select_groupInsurance, "Group insurance menu");
		Util.hardWait(3);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='"+text+"']"))).click();
	}
	
	public void writeReview(String review) {
		Util.sendKeys(driver, input_review, review, "Review");
		Util.click(driver, btn_Submit, "Submit");
	}
}
