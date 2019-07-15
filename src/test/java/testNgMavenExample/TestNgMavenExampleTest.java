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
	
	/* Firfox Ber compatability code
	 * 
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void f()	{
	
	System.out.println("output");
	//System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
	System.setProperty("webdriver.gecko.driver","C:/Program Files/geckodriver.exe");
	WebDriver driver=new FirefoxDriver();
	driver.get("http://thomsonreutersqa.service-now.com");
	WebElement e =driver.findElement(By.id("USER"));
	e.sendKeys("X011746");
	WebElement e1= driver.findElement(By.id("PASSWORD"));
	e1.sendKeys("Numan#220193");
	 driver.findElement(By.id("safeLoginbtn")).click();
	 driver.close();
	 }
	*/	
	
// Chrome compatability code
	 @Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	 private void testChromeSearch() throws IOException {
			// TODO Auto-generated method stub
		//	System.setProperty("webdriver.chrome.driver","C:/Users/UX011746/Desktop/FFBackup/ServiceNowQAAutomationnewbackup/SNOWQA/properties/chromedriver-2.46.exe");
		 logger = extent.createTest("Creation of a CR Ticket");	
			//WebDriver driver=new ChromeDriver();
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
	 
	
	 
	 // net code for chrome
	/* @Test
	 public void testGoogleSearch() {
	   // Optional, if not specified, WebDriver will search your path for chromedriver.
	   System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

	   WebDriver driver = new ChromeDriver();
	   driver.get("http://www.google.com/xhtml");
	   Thread.sleep(5000);  // Let the user actually see something!
	   WebElement searchBox = driver.findElement(By.name("q"));
	   searchBox.sendKeys("ChromeDriver");
	   searchBox.submit();
	   Thread.sleep(5000);  // Let the user actually see something!
	   driver.quit();
	 }*/
	

	
	@Test(priority=1,description="Creation of a CR Ticket",enabled=false)
	public void createChangeRequest() throws IOException, InterruptedException
	{
		String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
		System.setProperty("webdriver.chrome.driver",ChromeDrivers);
		WebDriver driver=new ChromeDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
	    Thread.sleep(3000);
	    ServiceNowUtils.navigateToModuleName(driver, "change");
	    crNumber = ChangeReusable.createChange(driver,1,2);
	    driver.close();
	    
		
		
	}
	
	/*@Test(priority=1,description="Approval of a Change Ticket",enabled=true)
	public void testMoveToApprovalState() throws Exception
	{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Approve Change Ticket Report");
		//logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
	    Thread.sleep(3000);
	    ServiceNowUtils.navigateToModuleName(driver, "change");
	    crNumber = ChangeReusable.createChange(driver,1,2);

		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
	    Thread.sleep(5000);
	    ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
	 //ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		 driver.close();
		 Assert.assertTrue(true);
		 logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Approve of change ticket", ExtentColor.GREEN));
		
		 
	}*/
	
	
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


