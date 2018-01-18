package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.workflows.*;

public class TC27_b_BookingwithInsifficientBronzePoints extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"FL_WEB_27");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_27_b_BookingwithInsifficientBronzePoints(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Adult,String Child,String infant, String promo, String strBookingClass,
			String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String SelectSeat,String paymenttype,String bookingtype, 
			String charity,String Currency, String accType,String Description
			) throws Throwable {
		try {
			//System.out.println(paymenttype);
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String deptdate = pickDate(deptDate);
			
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			
			Homepage homepage = new Homepage();
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username,password);
			homepage.select_Bookflights("registered");
			
			inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, retdate,Adult, Child, infant,promo,Currency);
			selectPayWithSmilePoint();
			clickFindFlightsBtn();
			selectClass(strBookingClass, tripType);
			continueOnPsngrDtls();
			continueOnExtras();
			continueOnSeatSelection();
			if(nasmilespayment(username,password)==false){         
			Reporter.SuccessReport("TC27_b_BookingwithInsifficientBronzePoints", "Pass");
			}
			else{
				Reporter.failureReport("TC27_b_BookingwithInsifficientBronzePoints", "Failed");
			}
		}
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC27_b_BookingwithInsifficientBronzePoints", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
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
		    		xls.getCellValue("naSmile", "Value"),
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Select Seat", "Value"),
		    		xls.getCellValue("Payment Type", "Value"),
		    		"",
	    			xls.getCellValue("Charity Donation", "Value"),
	    			xls.getCellValue("Currency", "Value"),
	    			xls.getCellValue("AccountType", "Value"),
		    		"Validate Redemption Booking with Bronze Member"}};
	}

}
