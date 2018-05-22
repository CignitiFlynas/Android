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

public class TC03_a_AnonymousOneWayDomesticSimpleChangeDate extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData_UAT_Reg"),"TC_03_oneWayDomesticChangeDate");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_3a_AnonymousOneWayDomesticSimpleChangeDate(String tripType, String origin, String dest, 
			String deptDate, String origin2, String departure2, String retdate,String Adult,String Child,String infant, String promo, 
			String bookingClass, String bundle, String flightType,String totalpass,String nationality,String docType,String docNumber,
			String naSmiles,String Mobile,String email ,String selectSeat,String paymentType,String bookingType,String newDate,
			String charity,String currency, String description
			) throws Throwable {
		try {

			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			// Handlepopup();
		
			String depDate = pickDate(deptDate);
			Homepage homepage = new Homepage();
			
			homepage.select_Bookflights("Anonymous");
					
			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, retdate,Adult, Child, infant,promo,currency);
			clickFindFlightsBtn();
			selectClass(bookingClass, bundle);
			String[] passenger = inputPassengerDetails(flightType,totalpass,nationality,docType,docNumber, naSmiles,Mobile,email,"","","");
 			continueOnExtras();
 			cntinueRandomSeatSelection();
 			confirmRandomSeatSelection();
			payment(paymentType,"");
			validate_ticketStatus();
			String PNRnumber = getReferenceNumber();
			System.out.println(PNRnumber);
			navigatetoHmPg();
			handleRatingRequest();
			homepage.select_Managebooking("Anonymous");
			searchFlightMMB(PNRnumber, passenger[1]);
			newDate = pickDate(newDate);
			String strPNRChangeDate = changeDate(PNRnumber,email, Mobile, "", newDate, selectSeat, totalpass,bookingClass,bundle);
			
			if(strPNRChangeDate.trim().equalsIgnoreCase(PNRnumber)){
			Reporter.SuccessReport("TC03a_AnonymousOneWayDomesticSimpleChangeDate", "Pass");
			}
			else{
				Reporter.failureReport("TC03a_AnonymousOneWayDomesticSimpleChangeDate", "PNR Did not match");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC03a_AnonymousOneWayDomesticSimpleChangeDate", "Failed");
		}
	}

	@DataProvider(name="testData")
	public Object[][] createdata1() {
		return (Object[][]) new Object[][] { 
			{	
				xls.getCellValue("Trip Type", "Value"),xls.getCellValue("Origin", "Value"),xls.getCellValue("Destination", "Value"),
				xls.getCellValue("Departure Date", "Value"),"","",xls.getCellValue("Return Date", "Value"),	xls.getCellValue("Adults Count", "Value"),
				xls.getCellValue("Child Count", "Value"),xls.getCellValue("Infant Count", "Value"),	xls.getCellValue("Promo", "Value"),
				xls.getCellValue("Booking Class", "Value"),xls.getCellValue("Bundle", "Value2"),	xls.getCellValue("Flight Type", "Value"),
				xls.getCellValue("Total Passenger", "Value"),xls.getCellValue("Nationality", "Value"),xls.getCellValue("Document Type", "Value"),
				xls.getCellValue("Doc Number", "Value"),"",xls.getCellValue("Mobile", "Value"),xls.getCellValue("Email Address", "Value"),
				xls.getCellValue("Select Seat", "Value"),xls.getCellValue("Payment Type", "Value"),"",xls.getCellValue("New Date", "Value"),
				xls.getCellValue("Charity Donation", "Value"),xls.getCellValue("currency", "Value"),
				"Validate One way Domestic with one Adult Economy Change Date"}};
			}


}
