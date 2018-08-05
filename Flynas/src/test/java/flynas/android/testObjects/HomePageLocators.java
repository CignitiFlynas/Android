package flynas.android.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HomePageLocators extends ActionEngine{
	
	//Menu locators
	public static By tittleMenu = By.xpath("//*[@resource-id='com.flynas.android.app:id/titleMenu']");
	public static By settingsIcn = By.xpath("//*[@resource-id='com.flynas.android.app:id/ivSettings']");
	public static By login = By.xpath("//*[@resource-id='com.flynas.android.app:id/navDrawMemberName']");
	public static By email = By.xpath("//*[@resource-id='com.flynas.android.app:id/loginViewEmailText']");
	public static By pasword = By.xpath("//*[@resource-id='com.flynas.android.app:id/loginViewPasswordText']");
	public static By Login_btn = By.xpath("//*[@resource-id='com.flynas.android.app:id/loginViewLoginBtn']");
	public static By logout_btn = By.xpath("//*[@text='Log Out']");
	public static By register = By.xpath("//*[@resource-id='com.flynas.android.app:id/navDrawRegister']");
	public static By myProfile = By.xpath("//*[@resource-id='com.flynas.android.app:id/navDrawMyProfile']");
	
	
	//Icon Locators
	public static By bookFlights = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgTabBookUnloggedin']");
	public static By onlineCheckin = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgTabCheckinUnloggedin']");
	public static By manageBookings = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgTabManageUnloggedin']");
	
	public static By rgstrdUsrIcnBF = By.id("com.flynas.android.app:id/vgTabBook");
	public static By rgstrdUsrIcnCI = By.id("com.flynas.android.app:id/vgTabCheckin");
	public static By rgstrdUsrIcnMMB = By.id("com.flynas.android.app:id/vgTabManage");
	public static By rgstrdUsrIcnBP = By.id("com.flynas.android.app:id/vgTabBoardingPasses");
	public static By rgstrdUsrIcnHm = By.id("com.flynas.android.app:id/vgTabHome");
	
	 //acces device location buttons
	public static By denyBtn=By.id("com.android.packageinstaller:id/permission_deny_button");
	public static By allowBtn=By.id("com.android.packageinstaller:id/permission_allow_button");
	
			 
	
	
	
}
