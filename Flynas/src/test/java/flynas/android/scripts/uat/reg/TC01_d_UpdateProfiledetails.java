package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;


import flynas.android.workflows.*;

public class TC01_d_UpdateProfiledetails extends BookingPageFlow{
	
	
	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_01_d_UpdateProfiledetails(String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//instantiating page objects
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String crntPswd =Credentials[1];
			String newPswd = randomString(10);
			
			Homepage homepage = new Homepage();
			MyProfilePage profilePage = new MyProfilePage();
			
			//only mobile number can be updated
			
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username, crntPswd);		
			homepage.select_TittleMenu();
			homepage.Click_myProfile();
			profilePage.updateMobilenum();
			homepage.select_TittleMenu();
			homepage.Click_logout();
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username, newPswd);
			
						
			Reporter.SuccessReport("TC01_d_UpdateProfiledetails", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_d_UpdateProfiledetails", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			"Validate Member Login"}};
	}

}
