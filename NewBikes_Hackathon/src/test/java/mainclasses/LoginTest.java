package mainclasses;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pageclasses.LoginPage;

 

 
public class LoginTest extends BaseClass{
	LoginPage login;
	@BeforeClass
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        login = new LoginPage(driver);
    }
 
    @Test(priority=17)
    public void testInvalidLogin()  {  
    	
        String msg=login.loginWithInvalidGoogleAccount();
        
        System.out.println("Error Message: " + msg);
        ExtentTest extentTest = createTest("TestClass3 - testInvalidLogin()");
    	extentTest.info("Executing testInvalidLogin method in TestClass3"); 
        Assert.assertEquals(msg, "Couldn’t find your Google Account");
        extentTest.log(Status.PASS, "testInvalidLogin Method Passed!");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    
}
