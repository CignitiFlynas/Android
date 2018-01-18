package flynas.android.scripts.uat.reg;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.workflows.*;

public class TC35_b_AnonymousOneWayIntlEconomy_MMB_ModifyExtrasSeat extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"FL_WEB_35");

	@Test(dataProvider = "testData",groups={"Android"})
	public void TC_35_b_AnonymousOneWayIntlEconomy_MMB_ModifyExtrasSeat(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Adult,String Child,String infant, 
			String promo,String bookingClass,String FlightType,String totalpass,String nationality,String doctype,
			String docNumber,String naSmiles,String Mobile,String email,String SelectSeat,String paymenttype, String bookingtype,
			String charity,String Currency,String Description)throws Throwable{
				try{
					
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
					String	deptdate = pickDate(deptDate);
					String	rtrndate = pickDate(retdate);
					
					Homepage homepage = new Homepage();
					homepage.select_Bookflights("Anonymous");
					
					inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, rtrndate,Adult, Child, infant,promo,Currency);
					clickFindFlightsBtn();
					selectClass(bookingClass, tripType);
					String passenger[] = inputPassengerDetails(FlightType,totalpass,nationality,doctype,docNumber, naSmiles,Mobile,email,"","","");
		 			continueOnExtras();
		 			continueOnSeatSelection();
		 			payment(paymenttype,"");
					validate_ticketStatus();
					String PNRnumber = getReferenceNumber();
					System.out.println(PNRnumber);
					navigatetoHmPg();
					handleRatingRequest();
					homepage.select_Managebooking("Anonymous");
					searchFlightCheckin(PNRnumber,passenger[1]);
					Baggage(bookingtype,totalpass);
					Select_A_Meal();
					//Baggage_Extra();
					//Select_Meal()
					Select_lounge();
					continueOnExtras();
					selectallSeats(SelectSeat,totalpass, tripType);
					continueOnSeatSelection();
					getReferenceNumber().trim();
					
					Reporter.SuccessReport("TC35_b_AnonymousOneWayIntlEconomy_MMB_ModifyExtrasSeat", "Pass");
					
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC35_b_AnonymousOneWayIntlEconomy_MMB_ModifyExtrasSeat", "Fail");
						
					}
	}
		
		@DataProvider(name="testData")  
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] {{
		    	xls.getCellValue("Trip Type", "Value"),
		    	xls.getCellValue("Origin", "Value2"),
		    	xls.getCellValue("Destination", "Value2"),
		    	xls.getCellValue("Departure Date", "Value"),
		    	"",
		    	"",
		    	xls.getCellValue("Return Date", "Value"),
		    	xls.getCellValue("Adults Count", "Value"),
		    	xls.getCellValue("Child Count", "Value"),
		    	xls.getCellValue("Infant Count", "Value"),
		    	xls.getCellValue("Promo", "Value"),
		    	xls.getCellValue("Booking Class", "Value"),
		    	xls.getCellValue("Flight Type", "Value2"),
		    	xls.getCellValue("Total Passenger", "Value"),
		    	xls.getCellValue("Nationality", "Value"),
		    	xls.getCellValue("Document Type", "Value"),
		    	xls.getCellValue("Doc Number", "Value"),
		    	"",
    			xls.getCellValue("Mobile", "Value"),
    			xls.getCellValue("Email Address", "Value"),
    			xls.getCellValue("Select Seat", "Value"),
    			xls.getCellValue("Payment Type", "Value"),
    			"",
    			xls.getCellValue("Charity Donation", "Value"),
    			xls.getCellValue("Currency", "Value"),
    			"Validate OneWay International Economy MMB- modify extras, select business lounge"}};
	}
	
}
