package com.testcases.facebook;

import org.testng.annotations.Test;

import com.wallethub.config.BaseClass;
import com.wallethub.pages.facebbok.HomePage;
import com.wallethub.pages.facebbok.LoginPage;
import com.wallethub.utils.Util;

public class TC01_NewPost extends BaseClass {
 
	@Test
	public void createNewPost() {
		LoginPage log = new LoginPage(driver);
		log.isOpened();
		log.logIntoFacebook(Util.getProperty("username"), Util.getProperty("password"));
		HomePage home = new HomePage(driver);
		home.isOpened();
		home.createPost(Util.getProperty("post"));
		home.validateNewPost(Util.getProperty("post"));
		home.logOutOfFacebook();
		log.isOpened();
	}
}
