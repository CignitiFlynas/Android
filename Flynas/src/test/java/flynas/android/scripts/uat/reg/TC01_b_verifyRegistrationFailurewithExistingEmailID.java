package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;


import flynas.android.workflows.*;

public class TC01_b_verifyRegistrationFailurewithExistingEmailID extends BookingPageFlow{
	
	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_01_b_verifyRegistrationFailurewithExistingEmailID(String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//instantiating page objects
			
			String[] Credentials = pickCredentials("UserCredentials");
			String userId =Credentials[0];
			
			Homepage homepage = new Homepage();
			homepage.select_TittleMenu();
			homepage.Click_Register();
			
			RegistrationPage RegisterPg = new RegistrationPage();
			String username = RegisterPg.register(userId,"Adult"); // Registering a new Adult member
			if(username==null)			
			Reporter.SuccessReport("TC01_b_verifyRegistrationFailurewithExistingEmailID", "Pass");
			else
			Reporter.failureReport("TC01_b_verifyRegistrationFailurewithExistingEmailID", "Failed");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_b_verifyRegistrationFailurewithExistingEmailID", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{"Validate Registration failure with existing email ID"}};
	}

}
