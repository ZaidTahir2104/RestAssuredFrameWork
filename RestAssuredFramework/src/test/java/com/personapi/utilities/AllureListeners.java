package com.personapi.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Attachment;

public class AllureListeners implements ITestListener {
	 public static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
		 
	 }
    // Text attachments for Allure
    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
    public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	}
