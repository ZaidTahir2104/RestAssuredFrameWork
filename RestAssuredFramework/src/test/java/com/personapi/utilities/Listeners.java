package com.personapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	public ExtentSparkReporter spark ;
	public ExtentReports extent;
	public ExtentTest test;
	 
	public void onStart(ITestContext testContext) {
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Rest API Testing Report");
		spark.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Project Name", "Rest Assured FrameWork");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Zaid");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed is " +result.getName());
	}
	public void onTestFailure(ITestResult result) {
		test =extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed is "+  result.getName());
		test.log(Status.FAIL, "Test Case Failed is "+  result.getThrowable());
	}
	public void onTestSkip(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
	}
	public void onFinish(ITestContext testContext) {
		extent.flush();
}
}