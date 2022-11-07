package com.wallethub.pages.facebbok;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.wallethub.reports.Report;
import com.wallethub.utils.Util;


public class HomePage {
	WebDriver driver;
	
	private String title = "Facebook";
	private String title1 = "Facebook – log in or sign up";
	
	public HomePage(WebDriver driver) {
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(text(), 's on your mind')]")	private WebElement btn_newpost;
	@FindBy(xpath="//div[contains(@aria-label, 's on your mind')]/p")	private WebElement input_post;
	@FindBy(xpath="//span[text()='Post']")	private WebElement btn_post;
	@FindBy(xpath="//div[@role='feed']//div[@dir='auto']/div/div/div")	private WebElement lbl_newpost;
	@FindBy(xpath="//div[@aria-label='Your profile']")	private WebElement btn_logoutMenu;
	@FindBy(xpath="//*[text()='Log Out']")	private WebElement btn_Logout;
	
	public void isOpened() {
		Util.waitForElement(driver, btn_newpost);
		if(driver.getTitle().equalsIgnoreCase(title) || driver.getTitle().equalsIgnoreCase(title1)) {
			Report.passTest(driver, "Successfully loggedin!!!!!!!!!!!!");
		} else {
			Report.failTest(driver, "Loggedin failed!!!!!!!!!!!!");
		}
		
	}
	
	public void createPost(String post) {
		Util.click(driver, btn_newpost, "Post");
		Util.sendKeys(driver, input_post, post, "New post");
		Util.click(driver, btn_post, "Post");
	}
	
	public void validateNewPost(String expectedText) {
		String actualText = Util.getText(driver, lbl_newpost, "text");
		Assert.assertEquals(actualText, expectedText, "Post not properly posted!!!!!!!!!!1");
		Util.hardWait(4);
		Report.passTest(driver, "Successfully posted!!!!!!!!!!");
	}
	
	public void logOutOfFacebook() {
		Util.click(driver, btn_logoutMenu, "Logout Menu");
		Util.click(driver, btn_Logout, "Logout");
	}

}
