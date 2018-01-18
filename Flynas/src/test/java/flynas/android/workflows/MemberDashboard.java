package flynas.android.workflows;

import java.util.concurrent.TimeUnit;

import flynas.android.testObjects.BookingPageLocators;

public class MemberDashboard<RenderedWebElement> extends BookingPageLocators {
	
	public void waitforpageload() throws InterruptedException{	
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		}
	
	

}
