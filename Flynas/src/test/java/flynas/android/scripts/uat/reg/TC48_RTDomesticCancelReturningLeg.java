package flynas.android.scripts.uat.reg;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.workflows.*;

public class TC48_RTDomesticCancelReturningLeg extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"FL_WEB_35");

	@Test(dataProvider = "testData",groups={"Android"})
	public void TC_48_RTDomesticCancelReturningLeg(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Adult,String Child,String infant, 
			String promo,String bookingClass,String FlightType,String totalpass,String nationality,String doctype,
			String docNumber,String naSmiles,String Mobile,String email,String SelectSeat,String paymenttype, String bookingtype,
			String charity,String Currency,String Description)throws Throwable{
				try{
					
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
					
					String[] Credentials = pickCredentials("UserCredentials");
					String username =Credentials[0];
					String password =Credentials[1];
					
					String deptdate = pickDate(deptDate);
					String rtrndate = pickDate(retdate);
					
					Homepage homepage = new Homepage();
					homepage.select_TittleMenu();
					homepage.Click_login();
					homepage.Login(username,password);
					homepage.select_Bookflights("Registered");
					
					inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, rtrndate,Adult, Child, infant,promo,Currency);
					clickFindFlightsBtn();
					selectClass(bookingClass, tripType);
					continueOnPsngrDtls();
					continueOnExtras();
		 			continueOnSeatSelection();
		 			payment(paymenttype,"");
					validate_ticketStatus();
					String PNRnumber = getReferenceNumber();
					System.out.println(PNRnumber);
					navigatetoHmPg();
					handleRatingRequest();
					homepage.select_Managebooking("Registered");
					registeredUsrManageFlight(PNRnumber);
					cancelFlights("Returning");
					confirmChanges();
					verifyConfirmchanges();
					
					
					Reporter.SuccessReport("TC48_RTDomesticCancelReturningLeg", "Pass");
					
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC48_RTDomesticCancelReturningLeg", "Fail");
						
					}
	}
		
		@DataProvider(name="testData")  
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] {{
		    	xls.getCellValue("Trip Type", "Value2"),
		    	xls.getCellValue("Origin", "Value"),
		    	xls.getCellValue("Destination", "Value"),
		    	xls.getCellValue("Departure Date", "Value"),
		    	"",
		    	"",
		    	xls.getCellValue("Return Date", "Value"),
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
		    	"",
    			xls.getCellValue("Mobile", "Value"),
    			xls.getCellValue("Email Address", "Value"),
    			xls.getCellValue("Select Seat", "Value"),
    			xls.getCellValue("Payment Type", "Value"),
    			"",
    			xls.getCellValue("Charity Donation", "Value"),
    			xls.getCellValue("Currency", "Value"),
    			"Validate Returning leg cancellation in Domestic Roundtrip"}};
	}
	
}
