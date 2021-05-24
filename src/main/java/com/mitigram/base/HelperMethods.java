package com.mitigram.base;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelperMethods {

  private Random mRandom;
  private WebDriverWait mWait;

  public HelperMethods() {
    mRandom = new Random();
  }

  public void verifyCurrentPageUrl(WebDriver driver, String url) {

    Assert.assertTrue(driver.getCurrentUrl().equals(url));

  }

  public WebElement visibilityOfelement(WebDriver driver, WebElement element) {

    mWait = new WebDriverWait(driver, 60);
    mWait.until(ExpectedConditions.visibilityOf(element));

    return element;

  }

  public String generateRandomString(int length) {

    String mAllowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(mAllowedChars.charAt(mRandom.nextInt(mAllowedChars.length())));
    }

    return sb.toString();
  }

  public int generateRandomNumber(int length) {

    return mRandom.nextInt(length);
  }

}
