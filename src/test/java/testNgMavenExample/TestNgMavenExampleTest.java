package testNgMavenExample;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;



import ReusableMethods.Capabilities;
import ReusableMethods.ChangeReusable;
import ReusableMethods.SafeLogin;
import ReusableMethods.ServiceNowUtils;



public class TestNgMavenExampleTest {
	
	String crNumber=null;
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void f()
	{
	System.out.println("output");
	System.setProperty("webdriver.gecko.driver","C:/Users/UX011746/Downloads/geckodriver-v0.24.0-win64/geckodriver.exe");
	WebDriver driver=new FirefoxDriver();
	driver.get("http://thomsonreutersqa.service-now.com");
	WebElement e =driver.findElement(By.id("USER"));
	e.sendKeys("X011746");
	WebElement e1= driver.findElement(By.id("PASSWORD"));
	e1.sendKeys("Numan#220193");
	 driver.findElement(By.id("safeLoginbtn")).click();
	 driver.close();
	}
	@Test(priority=1,description="Creation of a CR Ticket",enabled=true)
	public void createChangeRequest() throws IOException, InterruptedException
	{
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
	    Thread.sleep(3000);
	    ServiceNowUtils.navigateToModuleName(driver, "change");
	    crNumber = ChangeReusable.createChange(driver,1,2);
	    driver.close();
	    
		
		
	}
}


