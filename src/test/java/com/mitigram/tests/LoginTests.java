package com.mitigram.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mitigram.base.GlobalConstants;
import com.mitigram.base.HelperMethods;
import com.mitigram.base.WebBase;
import com.mitigram.pages.HomePage;
import com.mitigram.pages.LoginPage;

public class LoginTests extends WebBase {

  private LoginPage loginpage;
  private HomePage homepage;
  private HelperMethods mHelp;

  public LoginTests() {
    super();
    mHelp = new HelperMethods();
  }

  @BeforeMethod
  public void setUp() {
    initialize();
    homepage = new HomePage();
  }

  @Test(priority = 1)
  public void loginValidation() {

    Assert.assertTrue(homepage.getPageTitle().equals(GlobalConstants.WELCOME_PAGETITLE));
    loginpage = homepage.clickLoginLink();

    loginpage.loginErrorValidation("username" + mHelp.generateRandomNumber(4) + "@gmail.com",
        mHelp.generateRandomString(6));
    loginpage.loginErrorValidation(mHelp.generateRandomString(6), mHelp.generateRandomString(6));
    loginpage.emailAndPasswordEmpty();
    loginpage.loginErrorValidation("username" + mHelp.generateRandomNumber(4) + "@gmail.com", "");
    loginpage.loginErrorValidation(mHelp.generateRandomString(6), "");
    loginpage.loginErrorValidation("", mHelp.generateRandomString(6));
  }

  @Test(priority = 2)
  public void verifyForgotPasswordRedirection() {
    loginpage = homepage.clickLoginLink();
    loginpage.verifyForgotPasswordLinkRedirection();
  }

  @Test(priority = 3)
  public void verifyContactUsLinkRedirection() {
    loginpage = homepage.clickLoginLink();
    loginpage.verifyContactUsLinkRedirection();
  }

  @AfterMethod
  public void tearDown() {
    mDriver.quit();
  }

}
