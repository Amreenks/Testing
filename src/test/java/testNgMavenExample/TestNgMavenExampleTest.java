package testNgMavenExample;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pages.ChangePage;

/*import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.WaitUtils;*/

import ReusableMethods.Capabilities;
import ReusableMethods.ChangeReusable;
import ReusableMethods.SafeLogin;
import ReusableMethods.ServiceNowUtils;
//import pages.ChangePage;
import ReusableMethods.WaitUtils;



public class TestNgMavenExampleTest {
	
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	String crNumber=null;
	
	
	
	@BeforeTest
	public void startReport(){
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport11.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amreen Noor");
		htmlReporter.config().setDocumentTitle("Change Request");
		htmlReporter.config().setReportName("Change Request Smoke suite");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	
	 @Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	 private void testChromeSearch() throws IOException {
			// TODO Auto-generated method stub
		//	System.setProperty("webdriver.chrome.driver","C:/Users/UX011746/Desktop/FFBackup/ServiceNowQAAutomationnewbackup/SNOWQA/properties/chromedriver-2.46.exe");
		 logger = extent.createTest("Sample");	
		
		 String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
			System.setProperty("webdriver.chrome.driver",ChromeDrivers);
			WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			WebElement e =driver.findElement(By.id("USER"));
			e.sendKeys("X011746");
			WebElement e1= driver.findElement(By.id("PASSWORD"));
			e1.sendKeys("Numan#220193");
			 driver.findElement(By.id("safeLoginbtn")).click();
			 driver.quit();
			 Assert.assertTrue(true);
				logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
		}
	 
	
	 
	 
	

	
	@Test(priority=1,description="Creation of a CR Ticket",enabled=true)
	public void createChangeRequest() throws IOException, InterruptedException
	{
		 logger = extent.createTest("Creation of a CR Ticket");	
		String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
		System.setProperty("webdriver.chrome.driver",ChromeDrivers);
		WebDriver driver=new ChromeDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
	    Thread.sleep(3000);
	    ServiceNowUtils.navigateToModuleName(driver, "change");
	    crNumber = ChangeReusable.createChange(driver,1,2);
	    driver.quit();
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
		
		
	}
	
	@Test(priority=1,description="Approval of a Change Ticket",enabled=true)
	public void testMoveToApprovalState() throws Exception
	{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Approve Change Ticket Report");
		//logger = extent.createTest("Creation of a CR Ticket");
		 logger = extent.createTest("Approval of change ticket");	
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
			System.setProperty("webdriver.chrome.driver",ChromeDrivers);
			WebDriver driver=new ChromeDriver();
			
		    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
	    Thread.sleep(3000);
	    ServiceNowUtils.navigateToModuleName(driver, "change");
	    crNumber = ChangeReusable.createChange(driver,1,2);

		ChangeReusable.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusable.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusable.moveToAssessmentState(driver);
		ChangeReusable.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusable.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusable.moveToApprovalState(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusable.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
	    Thread.sleep(5000);
	    ChangeReusable.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
	 //ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		 driver.close();
		 Assert.assertTrue(true);
		 logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Approve of change ticket", ExtentColor.GREEN));
		
		 
	}
	
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		WebDriver driver = null;
		//String screenShot = CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName(result));
		if(result.getStatus() == ITestResult.FAILURE){
			
			//logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//logger.fail("Screen Shot : " + logger.addScreenCaptureFromPath(screenShot));
			
		}else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}
	}
	@AfterTest
	public void endReport(){
		extent.flush();
    }
	
	
	
}


