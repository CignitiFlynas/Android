package flynas.android.scripts.uat.routes;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import flynas.android.testObjects.BookingPageLocators;
import flynas.android.workflows.BookingPageFlow;
import flynas.android.workflows.Homepage;

public class TC45_roundTripInternationalEconomy_JED_CAI extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"TestData");

	@Test(dataProvider = "testData",groups={"Economy"})
	public  void TC_45_roundTripInternationalEconomy_JED_CAI(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Audalt,String Child,String infant, String promo, 
			String bookingClass, String bundle,
			String FlightType,String totalpass,String namtionality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype,String bookingtype, 
			String charity,String Currency, String Description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//Handlepopup();
			
				String[] Credentials = pickCredentials("UserCredentials");
			
			String username =Credentials[0];
			String password =Credentials[1];

			String depDate = pickDate(deptDate);
			retdate = pickDate(retdate);
			Homepage homepage = new Homepage();
						
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username,password);
			homepage.select_Bookflights("registered");
			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, retdate,Audalt, Child, infant,promo,Currency);
 			selectClass(bookingClass, bundle);
			continueOnPsngrDtls();
			Baggage(bookingtype, totalpass);
			if(isElementDisplayedTemp(BookingPageLocators.continuebtn)==true)
			{
				click(BookingPageLocators.continuebtn, "Continue");
			}
			continueOnSeatSelection();
			payment(paymenttype,"");
			validate_ticketStatus();
			
			String PNRnumber = getReferenceNumber();
			System.out.println(PNRnumber);
			
click(BookingPageLocators.tittleHome, "Home Img");
//			if(isElementPresent(BookingPageLocators.loveFlynasApp)==true)
//			{
//				click(BookingPageLocators.noThanks, "No Thanks");
//			}
//			else
//			{
//				System.out.println("No Alert");
//			}
//			homepage.select_OnlineCheckIn("registered");
//			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
//			scroll(PNRnumber);
//			click(BookingPageLocators.manage(PNRnumber), "Manage");
//			performCheckin();
//			waitForElementPresent(BookingPageLocators.travelDocuments, "Travel Documents");
//			click(BookingPageLocators.continuebtn, "Continue");
//			waitForElementPresent(BookingPageLocators.seatSelecttionTittle, "Seat Selection");
//			click(BookingPageLocators.continuebtn, "Continue");
//			if(isElementDisplayedTemp(BookingPageLocators.ok)){
//				click(BookingPageLocators.ok, "OK");}
//			validateCheckin();
//			
			Reporter.SuccessReport("TC45_roundTripInternationalEconomy_JED_CAI", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC45_roundTripInternationalEconomy_JED_CAI", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xls.getCellValue("Trip Type", "Value2"),
	    		xls.getCellValue("Origin", "Value45"),
	    		xls.getCellValue("Destination", "Value45"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		"",
	    		"",
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Promo", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Bundle","Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("na Smiles", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		xls.getCellValue("Payment Type", "Value2"),
	    		"",
    			xls.getCellValue("Charity Donation", "Value"),
    			xls.getCellValue("Currency", "Value"),
    			"Validate oneWay International Economy_JED_CAI"}};
	}
	

}
