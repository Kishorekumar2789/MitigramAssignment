package com.mitigram.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mitigram.base.WebBase;
import com.mitigram.pages.CareersPage;
import com.mitigram.pages.HomePage;
import com.mitigram.pages.LoginPage;

public class CareersTests extends WebBase {

  LoginPage mLoginpage;
  HomePage mHomepage;
  CareersPage mCareersPage;

  public CareersTests() {
    super();

  }

  @BeforeMethod
  public void setUp() {
    initialize();
    mHomepage = new HomePage();
  }

  @Test(priority = 1)
  public void verifyErrorValidaitonForm() {
    mCareersPage = mHomepage.clickCarrersLink();
    mCareersPage.verifyErrorValidation();

  }

  @Test(priority = 2)
  public void verifyUploadresume() {

    mCareersPage = mHomepage.clickCarrersLink();
    mCareersPage.verifyUploadResume();
  }
  
  @AfterMethod
  public void tearDown() {
    mDriver.quit();
  }

}
