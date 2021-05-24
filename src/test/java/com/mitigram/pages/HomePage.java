package com.mitigram.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mitigram.base.GlobalConstants;
import com.mitigram.base.HelperMethods;
import com.mitigram.base.WebBase;

public class HomePage extends WebBase {
  private HelperMethods mHelp;
  
  public HomePage() {
    PageFactory.initElements(mDriver, this);
    
    mHelp=new HelperMethods();
   
  }
  
  @FindBy(linkText = "Login")
  WebElement mLoginLink;
  
  @FindBy(linkText = "Careers")
  WebElement mCareersLink;
  
  
  public String getPageTitle() {
    return mDriver.getTitle();
  }
  
  
  public LoginPage clickLoginLink() {
    getPageTitle();
    Assert.assertTrue(mLoginLink.isDisplayed());
    mLoginLink.click();
    mHelp.verifyCurrentPageUrl(mDriver,GlobalConstants.LOGINURL);
    
    return new LoginPage();
  }
  
  public CareersPage clickCarrersLink() {
    Assert.assertTrue(mCareersLink.isDisplayed());
    mCareersLink.click();
    mHelp.verifyCurrentPageUrl(mDriver,mProperty.getProperty("url")+"careers");
    return new CareersPage();
  }

}
