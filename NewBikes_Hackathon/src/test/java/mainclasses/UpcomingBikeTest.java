package mainclasses;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageclasses.UpcomingBikes;

public class UpcomingBikeTest extends BaseClass{
	UpcomingBikes bike;
    List<WebElement> bikeNames;
    List<WebElement> bikePrices;
    List<WebElement> bikeLaunchDates;
    @BeforeClass
    public void setUp() {
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        bike = new UpcomingBikes(driver);
    }
    
    
    @Test(priority=1)
    public void searchForNewBikes() {
    	bike.clickNewBikes();
    	ExtentTest extentTest = createTest("TestClass1 - searchNewBikes()");
    	extentTest.info("Executing searchNewBikes method in TestClass1");
    	Assert.assertTrue(driver.getCurrentUrl().contains("newbikes"));
    	extentTest.log(Status.PASS, "searchNewBikes Method Passed");
    }
    
    
    @Test(priority=2,groups= {"smoke"})
    public void searchForUpcomingBikes() {
    	bike.clickUpcomingBikes();
    	ExtentTest extentTest = createTest("TestClass1 - searchForUpcomingBikes()");
    	extentTest.info("Executing searchForUpcomingBikes method in TestClass1");
    	Assert.assertTrue(driver.getCurrentUrl().contains("newbikes"));
    	extentTest.log(Status.PASS, "searchForUpcomingBikes Method Passed");
    }
    
    @Test(priority=3,groups= {"smoke"})
    public void searchForAllUpcomingBikes() {
    	bike.clickAllUpcomingBikes();
    	ExtentTest extentTest = createTest("TestClass1 - searchForAllUpcomingBikes()");
    	extentTest.info("Executing searchForAllUpcomingBikes method in TestClass1");
    	Assert.assertTrue(driver.getCurrentUrl().contains("upcoming-bikes"));
    	extentTest.log(Status.PASS, "searchForAllUpcomingBikes Method Passed!");
    }
    
    @Test(priority=4,groups= {"smoke"})
    public void searchForHondaBikes() {
    	bike.clickHondaBikes();
    	ExtentTest extentTest = createTest("TestClass1 - searchForHondaBikes()");
    	extentTest.info("Executing searchForHondaBikes method in TestClass1");
    	Assert.assertTrue(driver.getCurrentUrl().contains("upcoming-honda-bikes"));
    	extentTest.log(Status.PASS, "searchForHondaBikes Method Passed!");
    }														
    
    @Test(priority=5,groups= {"regression"})
    public void getNames() {
    	bikeNames=bike.getBikeNames();
    	ExtentTest extentTest = createTest("TestClass1 - getNames()");
    	extentTest.info("Executing getNames method in TestClass1");    	
    	 Assert.assertFalse(bikeNames.isEmpty());
    	 extentTest.log(Status.PASS, "getNames Method Passed!");
    	if(bikeNames.isEmpty()) {
    		System.out.println("No Bikes found!!");
    	}
    }
    @Test(priority=6,groups= {"regression"})
    public void getPrices() {
    	bikePrices=bike.getBikePrices();
    	ExtentTest extentTest = createTest("TestClass1 - getPrices()");
    	extentTest.info("Executing getPrices method in TestClass1"); 
    	Assert.assertFalse(bikePrices.isEmpty());
    	extentTest.log(Status.PASS, "getPrices Method Passed!");
    	if(bikePrices.isEmpty()) {
    		System.out.println("No Bike Prices found!!");
    	}
    }
    @Test(priority=7,groups= {"regression"})
    public void getLaunchDates() {
    	bikeLaunchDates=bike.getBikeLaunchDates();
    	ExtentTest extentTest = createTest("TestClass1 - getLaunchDates()");
    	extentTest.info("Executing getLaunch method in TestClass1"); 
    	Assert.assertFalse(bikeLaunchDates.isEmpty());
    	extentTest.log(Status.PASS, "getLaunchDates Method Passed!");
    	if(bikeLaunchDates.isEmpty()) {
    		System.out.println("No Bike Launch Dates found!!");
    	}
    }
    @Test(priority=8,dependsOnMethods= {"getNames","getPrices","getLaunchDates"})
    public void writeUpcomingToExcel() throws IOException{
    	bike.writeToExcel(bikeNames, bikePrices, bikeLaunchDates);  
    	ExtentTest extentTest = createTest("TestClass1 - writeUpcomingToExcel()");
    	extentTest.info("Executing writeUpcomingToExcel method in TestClass1"); 
    	Assert.assertTrue(true, "Written into excel");
    	extentTest.log(Status.PASS, "writeUpcomingToExcel Method Passed!");
    }
    

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
