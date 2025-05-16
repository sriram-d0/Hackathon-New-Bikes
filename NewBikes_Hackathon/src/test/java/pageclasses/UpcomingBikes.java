package pageclasses;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class UpcomingBikes {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Constructor to initialize WebDriver, WebDriverWait, and PageFactory
    public UpcomingBikes(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // Using @FindBy annotation to locate elements
    @FindBy(xpath = "//*[@id=\"headerNewVNavWrap\"]/nav/ul/li[3]/a")
    private WebElement newBikes;

    @FindBy(xpath = "//li[text()='Upcoming']")
    private WebElement upcomingBikes;

    @FindBy(xpath = "(//*[@id='zw-cmnSilder']/div[2]/a)[4]")
    private WebElement allUpcomingBikes;

    @FindBy(xpath = "//*[@id='modelList']//a[text()='Honda']")
    private WebElement hondaBikes;
    
    @FindBy(xpath = "//*[@id=\"modelList\"]//a/strong")
    private List<WebElement> bikeNames;

    @FindBy(xpath = "//*[@id=\"modelList\"]//div[@class='b fnt-15']")
    private List<WebElement> bikePrices;

    @FindBy(xpath = "//*[@id=\"modelList\"]//div[@class='clr-try fnt-14']")
    private List<WebElement> bikeLaunchDates;

    // Method to navigate to New Bikes page
    public void clickNewBikes() {
        wait.until(ExpectedConditions.visibilityOf(newBikes)).click();
    }   
    
    // Method to navigate to Upcoming Bikes page
    public void clickUpcomingBikes() {
        wait.until(ExpectedConditions.visibilityOf(upcomingBikes));
        js.executeScript("arguments[0].click();", upcomingBikes);
    }

    // Method to navigate to All Upcoming Bikes
    public void clickAllUpcomingBikes() {
        wait.until(ExpectedConditions.visibilityOf(allUpcomingBikes));
        js.executeScript("arguments[0].click();", allUpcomingBikes);
    }

    // Method to navigate to Honda Bikes
    public void clickHondaBikes() {
        wait.until(ExpectedConditions.visibilityOf(hondaBikes));
        js.executeScript("arguments[0].click();", hondaBikes);
    }

    
 // Method to get all prices
    public List<WebElement> getBikeNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(bikeNames));
        
    }
    
    // Method to get all prices
    public List<WebElement> getBikePrices() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(bikePrices));
        
    }

    // Method to get all launch dates
    public List<WebElement> getBikeLaunchDates() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(bikeLaunchDates));
        
    }
    
    public void writeToExcel(List<WebElement> names, List<WebElement> price, List<WebElement> launchdate) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        FileOutputStream file = new FileOutputStream("Upcoming Bikes " + timestamp + ".xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Details");

            for (int i = 0; i < names.size(); i++) {
                String s = price.get(i).getText();
                String p = "";

                if (s.contains("Lakh")) {
                    p = s.substring(s.indexOf("Rs.") + 4, s.indexOf("Lakh") - 1);
                } else {
                    p = s.substring(s.indexOf("Rs.") + 4);
                    if (p.contains(",")) {
                        p = p.replace(",", "");
                    }
                }

                Double n = Double.parseDouble(p);
                if (n < 4 || !(s.contains("Lakh"))) {
                    XSSFRow row = sheet.createRow(i);
                    XSSFCell cell = row.createCell(0);
                    cell.setCellValue(names.get(i).getText());
                    System.out.println(names.get(i).getText());
                    XSSFCell cell1 = row.createCell(1);
                    cell1.setCellValue(price.get(i).getText());
                    System.out.println(price.get(i).getText());
                    XSSFCell cell2 = row.createCell(2);
                    cell2.setCellValue(launchdate.get(i).getText());
                    System.out.println(launchdate.get(i).getText());
                }
            }

            workbook.write(file);
            System.out.println("Written into excel");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }
    }
}