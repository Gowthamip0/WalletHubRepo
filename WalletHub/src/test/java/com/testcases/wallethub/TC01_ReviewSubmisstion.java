package com.testcases.wallethub;

import org.testng.annotations.Test;

import com.wallethub.config.BaseClass;
import com.wallethub.pages.wallethub.HomePage;
import com.wallethub.utils.Util;

public class TC01_ReviewSubmisstion extends BaseClass {

	@Test
	public void submitReview() {
		HomePage home = new HomePage(driver);
		home.isOpened();
		home.giveRating(4);		
		home.selectValueFromGroup("Health Insurance");
		home.writeReview(Util.getProperty("review"));
		
	}
}
