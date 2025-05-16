package mainclasses;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass extends ReportGeneration{
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    //@throws MalformedURLException 
    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) throws MalformedURLException {
    	
       	if (browser.equalsIgnoreCase("chrome")) {
       		ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
    		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    	} else {
    		EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            driver = new EdgeDriver(options);
    		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    	}
        
        // Configure browser settings
        driver.get("https://www.zigwheels.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    
    // Closes the browser instance.
    
//    @AfterClass
//    public void tearDown() {
//        // Uncomment below line to quit driver after test execution
//        // driver.quit();
//    }
}
