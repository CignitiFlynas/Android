package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;


import flynas.android.workflows.*;

public class TC01_f_UpdatePasswordInProfile extends BookingPageFlow{
	
	
	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_01_f_UpdatePasswordInProfile(String description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//instantiating page objects
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String crntPswd =Credentials[1];
			String newPswd = randomString(10);
			
			Homepage homepage = new Homepage();
			MyProfilePage profilePage = new MyProfilePage();
			
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username, crntPswd);		
			homepage.select_TittleMenu();
			homepage.Click_myProfile();
			profilePage.updatePswd(crntPswd,newPswd);
			homepage.select_TittleMenu();
			homepage.Click_logout();
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username, newPswd);
			
						
			Reporter.SuccessReport("TC01_a_memberRegistration", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_a_memberRegistration", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{"Validate Member Login"}};
	}

}
