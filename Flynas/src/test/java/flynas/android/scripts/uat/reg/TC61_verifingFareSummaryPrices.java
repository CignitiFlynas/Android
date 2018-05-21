package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.workflows.BookingPageFlow;
import flynas.android.workflows.Homepage;

public class TC61_verifingFareSummaryPrices extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData_UAT_Reg"),"TC_01_oneWayDomesticEcoSADAD");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_61_verifingFareSummaryPrices(String tripType, String origin, String dest,String deptDate, String origin2,
			String departure2, String retdate,String Adult,String Child,String infant, String promo,String Economy,
			String Business,String description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			// Handlepopup();
			
			String[] Credentials = pickCredentials("UserCredentials");
			
			String username =Credentials[0];
			String password =Credentials[1];
		
			String depDate = pickDate(deptDate);
			String rtrndate = pickDate(retdate);
			
			Homepage homepage = new Homepage();
						
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username,password);
			homepage.select_Bookflights("registered");
			
			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, rtrndate,Adult, Child, infant,promo,"");
			clickFindFlightsBtn();
			verifingEcofarePrice(Economy);
 			verifingEcofarePrice(Business);
			
			Reporter.SuccessReport("TC61_verifingFareSummaryPrices", "Pass");
			
			}
		
		catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC61_verifingFareSummaryPrices", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xls.getCellValue("Trip Type", "Value"),
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
	    		"Economy","Business",
	    		"verifing Fare Summary Prices"}};
	}


}
