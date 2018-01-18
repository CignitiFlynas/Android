package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;


import flynas.android.workflows.*;

public class TC01_c_verifyChildRegistrationNbookingFailure extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"TC_02_oneWayDomesticBusiness");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_01_c_verifyChildRegistrationNbookingFailure(String tripType, String origin, String dest,
			String depDate, String origin2, String departure2, String retdate, String Adult,
			String Child, String infant,String promo, String Currency,String bookingClass,
			String Description) throws Throwable {
		try {
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
				//instantiating page objects
				
				Homepage homepage = new Homepage();
				
				homepage.select_TittleMenu();
				homepage.Click_Register();
				RegistrationPage RegisterPg = new RegistrationPage();
				String username = RegisterPg.register("","Child"); // Registering a new Child member
				if(username!=null){
				homepage.select_Bookflights("registered");
				inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, retdate,Adult, Child, infant,promo,Currency);
				clickFindFlightsBtn();
	 			selectClass(bookingClass, tripType);
	 			continueOnPsngrDtls();
				
				Reporter.SuccessReport("TC01_c_verifyChildRegistrationNbookingFailure", "Pass");
				}
				else 
					Reporter.failureReport("Child TC01_c_verifyChildRegistrationNbookingFailure", "Failed");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_c_verifyChildRegistrationNbookingFailure", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{	xls.getCellValue("Trip Type", "Value"),
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
		    		"",
		    		xls.getCellValue("Booking Class", "Value"),
	    			"Verify Child Registration and child Booking failure"}};
	}

}
