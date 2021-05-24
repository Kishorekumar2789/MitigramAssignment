package com.mitigram.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.Random;

import com.mitigram.base.HelperMethods;
import com.mitigram.base.WebBase;

public class CareersPage extends WebBase {

  private HelperMethods mHelp;
  Random mRandom;

  public CareersPage() {
    PageFactory.initElements(mDriver, this);
    mHelp = new HelperMethods();
    
  }

  @FindBy(id = "first_name")
  WebElement mFirstName;

  @FindBy(id = "last_name")
  WebElement mLastName;

  @FindBy(id = "country")
  WebElement mCountry;

  @FindBy(id = "email")
  WebElement mEmail;

  @FindBy(id = "phone")
  WebElement mPhone;

  @FindBy(xpath = ".//div[@class='file-drop-area-content']/input[@class='file-input']")
  WebElement mSelectFileButton;

  @FindBy(id = "mat-error-0")
  WebElement mErrorFirstNameRequired;

  @FindBy(id = "mat-error-1")
  WebElement mErrorLastNameRequired;

  @FindBy(id = "mat-error-2")
  WebElement mErrorValidEmailRequired;

  @FindBy(id = "mat-error-3")
  WebElement mErrorPhoneNumberRequired;

  @FindBy(id = "mat-error-4")
  WebElement mErrorCountryRequired;

  @FindBy(id = "mat-error-5")
  WebElement mErrorValidPDFRequired;

  @FindBy(name = "submit")
  WebElement mSubmitButton;

  @FindBy(xpath = ".//h1[@class='mat-dialog-title']")
  WebElement mMitigramSuccessMessage;

  @FindBy(xpath = ".//app-introduce-yourself-success-dialog/div/p")
  WebElement mIntroductionSuccessMessage;

  @FindBy(xpath = ".//span[contains(text(),'Close')]")
  WebElement mCloseButton;

  public void verifyErrorValidation() {

    mProperty = readProperties("ErrorMessages");

    mSubmitButton.click();

    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorFirstNameRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("careespageFirstname")));
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorLastNameRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("careespageLastname")));
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorValidEmailRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("careespageEmail")));
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorPhoneNumberRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("careespagePhone")));
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorCountryRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("careespageCountry")));
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mErrorValidPDFRequired)
        .getText()
        .trim()
        .equals(mProperty.getProperty("careespageValidPDF")));

  }

  public void verifyUploadResume() {

    String mFilePath = mProperty.getProperty("upload.file.path") + "test.pdf";

    mFirstName.sendKeys("FName" + mHelp.generateRandomString(10));
    mLastName.sendKeys("Lname" + mHelp.generateRandomString(10));
    mCountry.sendKeys("Sweden");
    mEmail.sendKeys("Username" + mHelp.generateRandomNumber(5) + "@gmail.com");
    mPhone.sendKeys(String.valueOf(mHelp.generateRandomNumber(10)));

    mSelectFileButton.sendKeys(mFilePath);

    mSubmitButton.click();

    mProperty = readProperties("alertmessages");
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mMitigramSuccessMessage)
        .getText()
        .trim()
        .equals(mProperty.getProperty("ShowInterest").trim()));
    Assert.assertTrue(mHelp.visibilityOfelement(mDriver, mIntroductionSuccessMessage)
        .getText()
        .trim()
        .equals(mProperty.getProperty("introductionsubmitted").trim()));
    mCloseButton.click();
  }

  

}
