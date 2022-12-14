package Com.Utility;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseClass implements ITestListener {
	
	ExtentReports extent=ExtentReportGenrator.gerReport();        //call kel
	ThreadLocal<ExtentTest> extenttest=new 	ThreadLocal<ExtentTest>();    //object creation
	
	public void onTestStart(ITestResult result) {
		Library.test=extent.createTest(result.getTestClass().getName() +"=="+ result.getMethod().getMethodName());
		extenttest.set(Library.test);
		
	}

	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, "Test Case pass");
		extenttest.get().addScreenCaptureFromBase64String(getcapture(),"Test Case Pass ScreenShot");
	}

	public void onTestFailure(ITestResult result) {
		extenttest.get().log(Status.FAIL, "Test Case Fail");
		extenttest.get().addScreenCaptureFromBase64String(getcapture(),"Test Case Fail ScreenShot");
	}

	public void onTestSkipped(ITestResult result) {
		extenttest.get().log(Status.SKIP, "Test Case Skip");
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		
	}
	
	public String getcapture() {
		String file=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return file;
	}
	
	
	
	
	
}
