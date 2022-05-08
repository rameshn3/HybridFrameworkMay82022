package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{

	private Logger log=Logger.getLogger(LinkedinHomePage.class);
	
	//create a Constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="a.nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(css="a.nav__button-secondary")
	private WebElement signInLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement signinHeaderTxt;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement signInBtn;
	
	
	String linkedinHomePageTitle="LinkedIn: Log In or Sign Up";
	String linkedinSigninPageTitle="LinkedIn Login, Sign in | LinkedIn";
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("Verify the linkedin home page title:"+linkedinHomePageTitle);
		wait.until(ExpectedConditions.titleIs(linkedinHomePageTitle));
		Assert.assertEquals(driver.getTitle(), linkedinHomePageTitle);
	}
	
	public void verifyLinkedinSigninPageTitle() {
		log.debug("Verify the linkedin home page title:"+linkedinSigninPageTitle);
		wait.until(ExpectedConditions.titleIs(linkedinSigninPageTitle));
		Assert.assertEquals(driver.getTitle(), linkedinSigninPageTitle);
	}
	
	public void verifyLinkedinLogo() {
		log.debug("wait and verify the linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed());
	}
	
	public void verifyLinkedSigninHeaderText() {
		log.debug("wait and verify the linkedin signin header text");
		wait.until(ExpectedConditions.visibilityOf(signinHeaderTxt));
		Assert.assertTrue(signinHeaderTxt.isDisplayed());
	}
	
	public void clickSinginLink() throws InterruptedException {
		log.debug("clickin on signin link");
		click(signInLink);
	}
	
	public void clickSinginButton() throws InterruptedException {
		log.debug("clickin on signin button");
		click(signInBtn);
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
		log.debug("performing the login operation");
		log.debug("clear the content in username editbox");
		clearText(emailEditbox);
		log.debug("type the "+uname+" in emaileditbox");
		sendKey(emailEditbox,uname);
		log.debug("clear the content in password editbox");
		clearText(passwordEditbox);
		log.debug("type the "+pwd+" in passwordEditbox");
		sendKey(passwordEditbox,pwd);
		clickSinginButton();
		return new LinkedinLoggedinPage();
	}
	
	
	
}
