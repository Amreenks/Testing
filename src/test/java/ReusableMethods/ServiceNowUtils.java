package ReusableMethods;

import org.openqa.selenium.WebDriver;

import com.pages.HomePage;





public class ServiceNowUtils {
	
public static void navigateToModuleName(WebDriver driver, String moduleName) throws InterruptedException {
		
	
	Thread.sleep(3000);
		
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
		//WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
		Thread.sleep(3000);
		
		HomePage.getCreateNewBtn2(driver).click();

		Frames.switchToFrameById("gsft_main", driver);	
		
	}

}
