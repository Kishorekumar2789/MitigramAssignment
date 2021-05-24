package com.mitigram.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer,ITestNGListener {

  private int count = 0;
  private static int maxTry = 3;

  @Override
  public boolean retry(ITestResult result) {
  
  if(count < maxTry)
  {
    count++;
  return true;
  }
  return false;
  }
}
