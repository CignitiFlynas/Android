package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.testObjects.BookingPageLocators;
import flynas.android.testObjects.HomePageLocators;
import flynas.android.workflows.BookingPageFlow;
import flynas.android.workflows.Homepage;

public class TC04_e_AnonymousOneWayIntlBusinessIntlMultiPaxCheckinFail extends BookingPageFlow {
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"TC04_oneWayDomAdultCheckin");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC0_4_e_AnonymousOneWayIntlBusinessIntlMultiPaxCheckinFail(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Adult,String Child,String infant, String promo, 
			String strBookingClass,	String FlightType,String totalpass,String nationality,String Doctype,String docNumber,
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype,String bookingtype,String newDate,
			String charity,String Currency, String Description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			// Handlepopup();
			
			String depDate = pickDate(deptDate);
			String rtrndate = pickDate(retdate);
			
			Homepage homepage = new Homepage();
					
			homepage.select_Bookflights("Anonymous");
					
			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, rtrndate,Adult, Child, infant,promo,Currency);
			clickFindFlightsBtn();
			selectClass(strBookingClass, tripType);
			String[] passenger = inputPassengerDetails(FlightType,totalpass,nationality,Doctype,docNumber, naSmiles,Mobile,email,"","","");
 			continueOnExtras();
 			continueOnSeatSelection();
 			payment(paymenttype,"");
			validate_ticketStatus();
			String PNRnumber = getReferenceNumber();
			System.out.println(PNRnumber);
			navigatetoHmPg();
			handleRatingRequest();
			homepage.select_OnlineCheckIn("Anonymous");
			searchFlightCheckin(PNRnumber,passenger[1]);
			verifyAnonymousCheckinFail();
			
			Reporter.SuccessReport("TC04_e_AnonymousOneWayIntlBusinessIntlMultiPaxCheckinFail", "Pass");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC04_e_AnonymousOneWayIntlBusinessIntlMultiPaxCheckinFail", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Origin", "Value2"),
	    		xls.getCellValue("Destination", "Value2"),
	    		xls.getCellValue("Departure Date", "Value2"),
	    		"",
	    		"",
	    		xls.getCellValue("Return Date", "Value2"),
	    		xls.getCellValue("Adults Count", "Value2"),
	    		xls.getCellValue("Child Count", "Value2"),
	    		xls.getCellValue("Infant Count", "Value2"),
	    		xls.getCellValue("Promo", "Value"),
	    		xls.getCellValue("Booking Class", "Value3"),
	    		xls.getCellValue("Flight Type", "Value2"),
	    		xls.getCellValue("Total Passenger", "Value2"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		"",
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		"",
	    		xls.getCellValue("New Date", "Value"),
    			xls.getCellValue("Charity Donation", "Value"),
    			xls.getCellValue("Currency", "Value"),
	    		"Validate One way Intl with Multi pax Checkin failure"}};
	}
	

}
