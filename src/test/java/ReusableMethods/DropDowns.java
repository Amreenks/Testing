package ReusableMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//import com.servicenow.genericlibraries.ReporterLogs;

//import com.servicenow.genericlibraries.ReporterLogs;

//import com.servicenow.genericlibraries.ReporterLogs;

public class DropDowns {
	
	
	public static void selectDropdownByVisibleText(WebElement element,String visibleText, String dropDownName)
	{
		try
		{
			//ReporterLogs.log("Selecting Visible Text "+visibleText+" from dropdown : "+ dropDownName,"info");
			Select sel = new Select(element);
			sel.selectByVisibleText(visibleText);
		}
		catch(Exception e)
		{
			//ReporterLogs.log("Unable to select Visible Text "+visibleText+" from dropdown: "+dropDownName + " due to exception : " + e.getCause(), "error");
		}
	}
	
	
	
	public static String  getFirstSelectedOptionName(WebElement element, String dropDownName)
	{
		String selectedAttribute="";
		try
		{
			//ReporterLogs.log("Getting the First selected attribute name from dropdown :"+dropDownName, "info");
			Select sel = new Select(element);
			selectedAttribute=sel.getFirstSelectedOption().getText();
			//ReporterLogs.log(selectedAttribute+" is the Selected Attribute in "+dropDownName, "info");
		}
		catch(Exception e)
		{
			//ReporterLogs.log("Unable to get the selected Attribute name from "+dropDownName+" dropdown" + e.getCause(), "error");
		}
		return selectedAttribute;
	}
	
	
	public static void selectDropdownByIndex(WebElement element, int index, String dropDownName)
	{
		try
		{
			//ReporterLogs.log("Selecting options using index : "+index+" from : "+ dropDownName,"info");
			Select sel = new Select(element);
			sel.selectByIndex(index);
		}
		catch(Exception e)
		{
			//ReporterLogs.log("Unable to select values from "+ dropDownName + " due to exception "+e.getCause(), "error");
		}
	}

	

}
