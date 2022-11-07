package com.wallethub.pages.facebbok;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.wallethub.reports.Report;
import com.wallethub.utils.Util;



public class LoginPage  {
	WebDriver driver;
	
	private String title = "Facebook – log in or sign up";
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")	private WebElement input_username;
	@FindBy(id="pass")	private WebElement input_password;
	@FindBy(name="login")	private WebElement btn_login;
	
	
	
	public void isOpened() {
		Assert.assertEquals(driver.getTitle(), title, "LoginPage not properly loaded!!!!!!!!!!");
		Report.passTest(driver, "LoginPage is successfully launched");
	}
	
	public void logIntoFacebook(String username, String password) {
		Util.sendKeys(driver, input_username, username, "User name");
		Util.sendKeys(driver, input_password, password, "Password");
		Util.click(driver, btn_login, "Login");
	}
	
}
