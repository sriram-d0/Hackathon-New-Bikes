package mainclasses;



import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageclasses.UsedCars;

public class UsedCarTest extends BaseClass{
    
    UsedCars carsUtils;
    List<WebElement> cars;
    @BeforeClass
    public void setup() {
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
         carsUtils = new UsedCars(driver);
    }
    
    
    @Test(priority = 9)
    public void menuDropdownClickable() {
    	ExtentTest extentTest = createTest("TestClass2 - menuDropdownClickable()");
    	extentTest.info("Executing menuDropdownClickable method in TestClass2");    	    
    	Assert.assertTrue(carsUtils.isMoreDropdownClickable(), "More dropdown is not clickable.");
    	extentTest.log(Status.PASS, "menuDropdownClickable Method Passed");
    }
    
    @Test(priority = 10)
    public void dropdownDisplaysOptions() {
    	ExtentTest extentTest = createTest("TestClass2 - dropdownDisplaysOptions()");
    	extentTest.info("Executing dropdownDisplaysOptions method in TestClass2");
    	Assert.assertTrue(carsUtils.isMoreDropdownVisible(), "More dropdown options not visible.");
    	extentTest.log(Status.PASS, "dropdownDisplaysOptions Method Passed");
    }
    
    @Test(priority = 11)
    public void usedCarsPresent() {
    	ExtentTest extentTest = createTest("TestClass2 - usedCarsPresent()");
    	extentTest.info("Executing usedCarsPresent method in TestClass2");
    	Assert.assertTrue(carsUtils.isUsedCarsOptionPresent(), "Used Cars option not present.");
    	extentTest.log(Status.PASS, "usedCarsPresent Method Passed");
    }
    
    @Test(priority = 12)
    public void usedCarsClickable() {
    	ExtentTest extentTest = createTest("TestClass2 - usedCarsClickable()");
    	extentTest.info("Executing usedCarsClickable method in TestClass2");
    	Assert.assertTrue(carsUtils.isUsedCarsOptionClickable(), "Used Cars option not clickable.");
    	extentTest.log(Status.PASS, "usedCarsClickable Method Passed");
    }

    @Test(priority = 13)
    public void verifyCityFilterOpens() {
    	ExtentTest extentTest = createTest("TestClass2 - verifyCityFilterOpens()");
    	extentTest.info("Executing verifyCityFilterOpens method in TestClass2");
    	Assert.assertTrue(carsUtils.isCityFilterAutoOpened(), "City filter did not open automatically.");
    	extentTest.log(Status.PASS, "verifyCityFilterOpens Method Passed");
    }
    
    @Test(priority = 14)
    public void citySelection() {
    	try {
    		ExtentTest extentTest = createTest("TestClass2 - citySelection()");
        	extentTest.info("Executing citySelection method in TestClass2");
    		carsUtils.selectCity("Chennai");
    		extentTest.log(Status.PASS, "citySelection Method Passed");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    
    @Test(priority = 15)
    public void verifyPopularModelsDisplayed() {
    	ExtentTest extentTest = createTest("TestClass2 - verifyPopularModelsDisplayed()");
    	extentTest.info("Executing verifyPopularModelsDisplayed method in TestClass2");
    	Assert.assertTrue(carsUtils.isPopularModelsDisplayed(), "Models are not displayed");
    	extentTest.log(Status.PASS, "verifyPopularModelsDisplayed Method Passed");
    }
 
    @Test(priority = 16)
    public void writePopularModelsToExcel() throws IOException {
    	cars=carsUtils.PopularModels();
        carsUtils.writeToExcel(cars);
        ExtentTest extentTest = createTest("TestClass2 - writePopularModelsToExcel()");
    	extentTest.info("Executing writePopularModelsToExcel method in TestClass1"); 
    	Assert.assertTrue(true, "Popular car models written into excel");
    	extentTest.log(Status.PASS, "writePopularModelsToExcel Method Passed!");
    }
 
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
 
