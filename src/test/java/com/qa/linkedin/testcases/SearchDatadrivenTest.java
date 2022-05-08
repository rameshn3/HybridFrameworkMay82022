package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDatadrivenTest extends TestBase {
private Logger log =Logger.getLogger(SearchDatadrivenTest.class);
private LinkedinHomePage lHmPg;
private LinkedinLoggedinPage llPg;
private SearchResultsPage srPg;
private String excelPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
  
@Test
public void doLoginTest() throws IOException, InterruptedException {
	log.debug("Started executing the doLoginTest()...");
	lHmPg.verifyLinkedinHomePageTitle();
	lHmPg.verifyLinkedinLogo();
	lHmPg.clickSinginLink();
	lHmPg.verifyLinkedinSigninPageTitle();
	lHmPg.verifyLinkedSigninHeaderText();
	llPg=lHmPg.doLogin(readPropertyValue("uname"), readPropertyValue("pwd"));
	llPg.verifyLinkedinLoggedinPgTitle();
}

@Test(dataProvider = "dp",dependsOnMethods= {"doLoginTest"})
  public void doSearhTest(String s) throws InterruptedException {
	log.debug("started executin gthe searchTest for:"+s);
	llPg.verifyProfileRailCard();
	srPg=llPg.doPeopleSearch(s);
	Thread.sleep(1000);
	long count=srPg.getResultsCount();
	log.debug("search results count for:"+s+" is:"+count);
	srPg.clickOnHomeTab();
	
  }

  @DataProvider
  public Object[][] dp() throws InvalidFormatException, IOException {
     Object[][] data=new ExcelUtils().getTestData(excelPath, "Sheet1");
    return data;
  }
  @BeforeClass
  public void initializeObjects() {
	log.debug("initilize all the page classes");
	lHmPg=new LinkedinHomePage();
	llPg=new LinkedinLoggedinPage();
	srPg=new SearchResultsPage();
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  log.debug("started executing the signout action");
	  llPg.doSignOut();
  }

}
