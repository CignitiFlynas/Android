package flynas.androidchrome.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.androidchrome.testObjects.BookingPageLocators;
import flynas.androidchrome.workflows.BookingPageFlow;

public class TC37_EmpLoginStaffConfmedModifyExtras extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_16");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_37_EmpLoginStaffConfmedModifyExtras(String username,String password,String bookingClass,String mobilenum,
			String paymentType,String newDate,String pickDate,String rtndate,String origin,String dest,String triptype,
			String adult,String child,String infant,String selectseat,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String 	deptdate = pickDate(pickDate);
			
			click(emplogin_lnk, "Employe Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate, "", "", rtndate,adult, child, infant,"","",paymentType);
			selectClassForStaff(bookingClass);
			waitforElement(BookingPageLocators.mobileNum);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(getText(BookingPageLocators.mobileNum,"MobileNumber")=="")
			{
				type(BookingPageLocators.mobileNum, mobilenum, "MobileNumber");
				click(BookingPageLocators.continueBtn, "Continue");
			}
			else
			{
				click(BookingPageLocators.continueBtn, "Continue");
			}
			waitforElement(BookingPageLocators.baggagetittle);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
				click(BookingPageLocators.continueBtn, "Continue");
			}else{
				System.out.println("No Baggage Page");
			}
			waitforElement(BookingPageLocators.selectseattittle);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
				click(BookingPageLocators.continueBtn, "Continue");
				if(isElementDisplayedTemp(BookingPageLocators.ok))
				{
					click(BookingPageLocators.ok, "OK");
				}
			}else{
				System.out.println("No Seat Page");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber().trim();
			searchFlight(strpnr, username, "", "");
			click(BookingPageLocators.modifyExtras, "Modify Extras");
			Baggage_Extra(triptype);
			addSportsEqpmnt(triptype);
			Select_A_Meal();
			Select_lounge();
			inputExtras("12");
			waitforElement(BookingPageLocators.manageMyBookingTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.modifySeat, "Seat Selection");
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)){
				selectallSeats(selectseat, "1", triptype);
			}else{
				System.out.println("No seat Select Page");
			}
			waitforElement(BookingPageLocators.paynow);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.paynow, "Pay Now");
			payment(paymentType, "");
			String strPNR = getReferenceNumber().trim();
			validate_ticketStatus(strPNR);
			
			
			Reporter.SuccessReport("TC37_EmpLoginStaffConfmedModifyExtras", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC37_EmpLoginStaffConfmedModifyExtras", "Failed");
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
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value1"),
	    		xls.getCellValue("Destination", "Value1"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Validate Employe Login Round Trip One Adult StaffConformed Modify Extras"}};
	}

}
