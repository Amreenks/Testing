package ReusableMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pages.ChangePage;





public class ChangeReusable {
	static String changeId=null;
	static String assignmentGroup = null;
	static String configurationItem = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForChange = null;
	static String customerImpactDuringChange = null;
	static String implementationPlan= null;
	static String testPlan = null;
	static String backoutPlan = null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;
	static String crNumber=null;
	static String assignedTo=null;
	static String configItem=null;
	static String ManTestCase=null;
	static String tara=null;
	
	
	public static String createChange(WebDriver driver, int serialnum,int cnum){	
		try{
			
				Thread.sleep(3000);
				ChangePage.getNormalLnk(driver).click();
				//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
			
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
			//	ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
			//	ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
				
				//driver.findElement(By.xpath("//button[text()='Save']")).click();
				/*crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
				ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
				ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
				WaitUtils.waitForPageToLoad(driver, 10);*/
				//ChangePage.getSubmitForPlanningBtn(driver).click();
				//Thread.sleep(50000);
				boolean b=driver.findElement(By.xpath("//button[text()='Update']")).isDisplayed();
				System.out.println(b);
				
				}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
		 System.out.println("Final"+changeId);
		return changeId;
	
	
	}

}
