package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{

private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);

public LinkedinLoggedinPage() {
	PageFactory.initElements(driver, this);
}
	
@FindBy(css="div[class*='feed-identity-module']")
private WebElement profileRailCard;

@FindBy(css="img[class*='global-nav__me-photo ember-view']")
private WebElement profileImageIcon;

@FindBy(xpath="//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
private WebElement signOutLink;

@FindBy(xpath="//input[contains(@class,'search-global-typeahead__input')]")
private WebElement searchEditbox;

String linkedinLoggedinPgTitle="Feed | LinkedIn";

public void verifyLinkedinLoggedinPgTitle() {
	log.debug("Verify the linkedin loggedin page title:"+linkedinLoggedinPgTitle);
	wait.until(ExpectedConditions.titleContains(linkedinLoggedinPgTitle));
	Assert.assertTrue(driver.getTitle().contains(linkedinLoggedinPgTitle));
}


public void verifyProfileRailCard() {
	log.debug("wait and verify the linkedin loggedin page profile railcard");
	wait.until(ExpectedConditions.visibilityOf(profileRailCard));
	Assert.assertTrue(profileRailCard.isDisplayed());
}

public void doSignOut() throws InterruptedException {
	log.debug("Performing the doSignout action....");
	  click(profileImageIcon);
	  log.debug("click on Signout link");
	  click(signOutLink);
	  
}

public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
	log.debug("Performing the people search for :"+keyword);
	clearText(searchEditbox);
	log.debug("Type the search :"+keyword+" in searcheditbox");
	sendKey(searchEditbox,keyword);
	log.debug("Press the ENTER key");
	searchEditbox.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	return new SearchResultsPage(); 
}


}
