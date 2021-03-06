package flynas.androidchrome.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.androidchrome.testObjects.BookingPageLocators;
import flynas.androidchrome.workflows.BookingPageFlow;

public class TC15_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_15");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_15_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel(String username,String password,String bookingClass,String mobilenum,
			String paymentType,String newDate,String pickDate,String origin,String dest,String triptype,String adult,String child,
			String infant,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String depdat = pickDate(pickDate);
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			click(emplogin_lnk, "Employe Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, depdat, "", "", "",adult, child, infant,"","",paymentType);
			selectClassForStaff(bookingClass);
			waitforElement(BookingPageLocators.mobileNum);
			if(getText(BookingPageLocators.mobileNum,"MobileNumber").length() == 0)
			{
				type(BookingPageLocators.mobileNum, mobilenum, "MobileNumber");
				Thread.sleep(2000);
				click(BookingPageLocators.continueBtn, "Continue");
			}
			else
			{
				click(BookingPageLocators.continueBtn, "Continue");
			}
			/*Thread.sleep(3000);
			click(BookingPageLocators.continueBtn, "Continue");
			Thread.sleep(5000);
			click(BookingPageLocators.continueBtn, "Continue");
			Thread.sleep(5000);
			click(BookingPageLocators.ok, "OK");*/
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			
			String strPNRChangeDate = changeDate(strPNR, username, mobilenum, "", newDate, "","",bookingClass,0);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			System.out.println(strPNRChangeDate);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			if(strPNRChangeDate.trim().equalsIgnoreCase(strPNR)){
				Reporter.SuccessReport("Change Flight Date", "Flight Date has changed successfully");
			}else{
				Reporter.SuccessReport("Change Flight Date", "Flight Date has NOT changed successfully");
			}
			waitforElement(BookingPageLocators.Home);
			click(BookingPageLocators.Home, "Home");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			driver.navigate().refresh();
			waitforElement(BookingPageLocators.bookflighttittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[contains(text(),'"+strPNR+"')]")));
			click(BookingPageLocators.manageMyBookings(strPNR), "ManageMyBookings");
			cancelFlight("All");
			
			Reporter.SuccessReport("TC15_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC15_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("EmployeEmail", "Value"),
	    		xls.getCellValue("Password", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		"Validate Employe Login Oneway One Adult StaffStandBy ChangFlight Cancel"}};
	}
}
