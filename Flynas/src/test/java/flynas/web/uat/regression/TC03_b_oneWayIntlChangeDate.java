package flynas.web.uat.regression;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.ActionEngine;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ConfiguratorSupport;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC03_b_oneWayIntlChangeDate extends BookingPageFlow {
	
	public static ConfiguratorSupport configProps=new ConfiguratorSupport("config.properties");
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"TC_03_oneWayDomesticChangeDate");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public void TC03b_oneWayIntlChangeDate(String strTripType, String strFlightType, String strOrigin,
			String strDestination, String strDepatureDate, String origin2,String departure2,String strReturnDate, String strTotalPessenger,
			String strAdultCount, String strChildCount, String strInfantCount, String strPromo, 
			String strBookingClass, String strNationality, String strDocumentType,	String strDocumentNum,
			String strNaSmile,  String strMobile, String strSelectSeat, String strPaymentType,String bookingtype,
			String strNewDate, String charity,String Currency, String strLastName,String description)throws Throwable{
				try{					
					
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
					
					String	deptdate = pickDate(strDepatureDate);
					String	retrndate = pickDate(strReturnDate);
					
					click(BookingPageLocators.login_lnk, "Login");
					switchtoChildWindow();
					String[] Credentials = pickCredentials("UserCredentials");
					String username =Credentials[0];
					String password =Credentials[1];					
					login(username,password);
					
					inputBookingDetails(strTripType, strOrigin, strDestination, deptdate,origin2, departure2,retrndate,
							strAdultCount, strChildCount, strInfantCount, strPromo,Currency,strPaymentType);
					selectClass(strBookingClass, strTripType);
					
					//Clicking continue button on Passenger details page
					continueOnPassengerDetails();
					
					//Clicking continue button on Baggage details page
					coninueOnBaggage();
					
					//Selecting seat
					selectSeat(strSelectSeat, bookingtype);
					//Payment
					payment(strPaymentType,"");
					
					//Capturing PNR number
					String strpnr = getReferenceNumber();
					String strPNR = strpnr.trim();
					validate_ticketStatus(strPNR);
					
					//Verifying PNR numbers
					String	newdate = pickDate(strNewDate);
					String strPNRChangeDate = changeDate(strPNR, username, strMobile, strLastName, newdate, strSelectSeat,strTotalPessenger,strBookingClass,0);
					
					//Reporting the test case status
					if(strPNRChangeDate.trim().equalsIgnoreCase(strPNR)){
						Reporter.SuccessReport("Change Flight Date", "Flight Date has changed successfully");
					}else{
						Reporter.failureReport("Change Flight Date", "Flight Date has NOT changed successfully");
					}
					
					Reporter.SuccessReport("TC03_b_oneWayIntlChangeDate", "Pass");
					driver.close();
			
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC03_b_oneWayIntlChangeDate", "Fail");
						driver.close();
					}
	}
		
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] {{
		    	xls.getCellValue("Trip Type", "Value"),
		    	xls.getCellValue("Flight Type", "Value2"),
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
		    	xls.getCellValue("Nationality", "Value"),
		    	xls.getCellValue("Document Type", "Value"),
		    	xls.getCellValue("Doc Number", "Value"),
		    	xls.getCellValue("na Smiles", "Value"),
    			xls.getCellValue("Mobile", "Value"),
    			xls.getCellValue("Select Seat", "Value"),
    			xls.getCellValue("Payment Type", "Value"),
    			"",
    			xls.getCellValue("New Date", "Value"),
    			xls.getCellValue("Charity Donation", "Value"),
    			"",
    			xls.getCellValue("username", "Value"),
    			xls.getCellValue("password", "Value"),
    			xls.getCellValue("Last Name", "Value"),
    			"Validate One Way International ChangeDate"
    			}};
	}
	
}
