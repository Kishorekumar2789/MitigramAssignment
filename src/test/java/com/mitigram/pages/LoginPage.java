package com.mitigram.pages;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mitigram.base.HelperMethods;
import com.mitigram.base.WebBase;

public class LoginPage extends WebBase {

  private HelperMethods mHelp;

  public LoginPage() {
    PageFactory.initElements(mDriver, this);
    mHelp = new HelperMethods();
    mProperty = readProperties("ErrorMessages");
  }

  @FindBy(id = "loginBtn")
  WebElement mLoginBtn;

  @FindBy(linkText = "Forgot your password?")
  WebElement mForgotPasswordLink;

  @FindBy(linkText = "Click here to contact us")
  WebElement mContactUsLink;

  @FindBy(xpath = ".//a[@class='logo']")
  WebElement mLogo;

  @FindBy(xpath = ".//img[@class='select-img']")
  WebElement mProfilePicture;

  @FindBy(id = "Email")
  private WebElement mEmail;

  @FindBy(id = "Password")
  WebElement mPassword;

  @FindBy(xpath = ".//div[@class='error']/div/div[contains(text(),'Email is required')]")
  WebElement mErrorEmailRequired;

  @FindBy(xpath = ".//div[@class='error']/div/div[contains(text(),'Password is required')]")
  WebElement mErrorPasswordrequired;

  @FindBy(xpath = ".//div[@class='error']/div/div[contains(text(),'Invalid login attempt.')]")
  WebElement mErrorInvalidLoginAttempt;

  @FindBy(xpath = ".//div[@class='error']/div/div[contains(text(),'The Email field is not a valid e-mail address.')]")
  WebElement mErrorInvalidEmailFormat;

  public void clickLoginButton() {
    mEmail.clear();
    mPassword.clear();
    mLoginBtn.click();

    Assert.assertTrue(mErrorEmailRequired.isDisplayed());
  }

  public void loginWithValidCredentials(String email, String password) {

    if (!email.isEmpty() && !password.isEmpty() && !email.equalsIgnoreCase(null) && !password.equalsIgnoreCase(null)) {

      mEmail.sendKeys(email);
      mPassword.sendKeys(password);
      mLoginBtn.click();

    }

  }

  public void emailAndPasswordEmpty() {
    mEmail.clear();
    mPassword.clear();
    mLoginBtn.click();
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorEmailRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("emailrequired").trim()));

  }

  public void loginErrorValidation(String email, String password) {

    if (!email.isEmpty() && !password.isEmpty() && !email.equalsIgnoreCase(null) && !password.equalsIgnoreCase(null)) {

      mEmail.clear();
      mEmail.sendKeys(email);
      mPassword.clear();
      mPassword.sendKeys(password.trim());
      mLoginBtn.click();

      if (validateEmail(email)) {

        Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorInvalidLoginAttempt)
            .getText()
            .trim()
            .equals(mProperty.getProperty("invalidcredentials").trim()));

      }
      else {

        Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorInvalidEmailFormat)
            .getText()
            .trim()
            .equals(mProperty.getProperty("invalidemailformat").trim()));

      }

    }
    else if (!email.isEmpty() && password.isEmpty()) {
      mEmail.clear();
      mEmail.sendKeys(email);
      mLoginBtn.click();
      if (validateEmail(email)) {

        Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorPasswordrequired)
            .getText()
            .trim()
            .equals(mProperty.getProperty("passwordrequired").trim()));

      }
      else {

        Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorInvalidEmailFormat)
            .getText()
            .trim()
            .equals(mProperty.getProperty("invalidemailformat").trim()));
      }
    }
    else {
      mEmail.clear();
      mPassword.clear();
      mPassword.sendKeys(password.trim());
      mLoginBtn.click();
      Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorEmailRequired)
          .getText()
          .trim()
          .equals(mProperty.getProperty("emailrequired").trim()));
    }

  }

  public boolean validateEmail(String email) {
    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);

    return matcher.matches();

  }

  public void verifyContactUsLinkRedirection() {
    Assert.assertTrue(mContactUsLink.isDisplayed());

    mContactUsLink.click();

    ArrayList<String> tabs = new ArrayList<String>(mDriver.getWindowHandles());
    mDriver.switchTo().window(tabs.get(1));

    mHelp.verifyCurrentPageUrl(mDriver, mProperty.getProperty("url") + "#contact");

  }

  public void verifyForgotPasswordLinkRedirection() {
    Assert.assertTrue(mForgotPasswordLink.isDisplayed());
    mForgotPasswordLink.click();
    mHelp.verifyCurrentPageUrl(mDriver, "https://marketplace.mitigram.com/account/forgot-password");
  }

}
