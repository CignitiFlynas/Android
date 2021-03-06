package flynas.web.uat.errorMessages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC30_validatingErrorMsgforRecheckIN extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"TC04_oneWayDomAdultCheckin");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_30_validatingErrorMsgforRecheckIN(String tripType, String origin, String dest,String deptDate,
			String origin2,String departure2,String retdate, String strTolPass, String Adult,String Child,String infant,
			String promo, String strBookingClass,String FlightType,String totalpass,String nationality,String Doctypr,
			String docNumber,String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype, 
			String bookingtype,String Charity,String Currency,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String depDate = pickDate(deptDate);
			
			inputBookingDetails(tripType, origin, dest, depDate, origin2,departure2,retdate,Adult, Child, infant,promo,Currency,paymenttype);
			selectClass(strBookingClass, tripType);
			String lastname[]=inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,"","","");
			System.out.println(lastname);
			Baggage_Extra(tripType);
			clickContinueBtn();
			selectSeat(SelectSeat,bookingtype);
			payment(paymenttype,"");
			String strpnr = getReferenceNumber();
			String PNR = strpnr.trim();
			System.out.println("PNR**********"+PNR);
			validate_ticketStatus(PNR);
			searchFlightCheckin(PNR, email, "", "");
			performCheckin(SelectSeat,paymenttype,strTolPass);
			validateCheckin();
			searchFlightCheckin(PNR, email, "", "");
			if(isElementDisplayedTemp(BookingPageLocators.ErrorMsg1)){
				String ErrorMsg = getText(BookingPageLocators.ErrorMsg1, "Error Message");
				if(ErrorMsg.contains("All the passengers listed in the booking have already completed online check in for this flight.")){
					Reporter.SuccessReport("Validating Error Message For WCI", "Successfully Verified:"+ ErrorMsg);
				}else{
					Reporter.failureReport("Validating Erroe Message For WCI", "Expected Error Message not Came");
				}
				
			}
			
			Reporter.SuccessReport("TC30_validatingErrorMsgforRecheckIN", "Passed");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC30_validatingErrorMsgforRecheckIN", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		"",
	    		"",
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Promo", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		"1234567890",
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Credit Card",
	    		"",
    			xls.getCellValue("Charity Donation", "Value"),"",
	    		"Validate Error Message For RecheckIN"}};
	}

}