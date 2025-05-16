package pageclasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

 
public class UsedCars{
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
 
    public UsedCars(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//*[@id=\"headerNewVNavWrap\"]/nav/ul/li[5]/ul")
    WebElement moreDropdownList;
 
    @FindBy(xpath = "//*[@id=\"headerNewVNavWrap\"]//li[5]")
    WebElement moreOption;
 
    @FindBy(xpath = "//*[@id=\"headerNewVNavWrap\"]/nav/ul/li[5]/ul/li[3]/a")
    WebElement usedCarsOption;
 
    @FindBy(id = "gs_input5")
    WebElement searchCity;
 
    @FindBy(xpath = "(//*[text()=\"Chennai\"])[3]")
    WebElement chennaiLocation;
    
    @FindBy(id = "usedCarCity")
    WebElement actualLocation;
 
    @FindBy(xpath = "(//*[text()=\"Popular Models\"]/following::ul)[1]")
    WebElement popularModels;
 
 
	public boolean isMoreDropdownClickable() {
		try {
			moreOption.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
 
	public boolean isMoreDropdownVisible() {
		actions.moveToElement(moreOption).pause(Duration.ofSeconds(1)).perform();
		return moreDropdownList.isDisplayed();
	}
	
 
	public boolean isUsedCarsOptionPresent() {
		actions.moveToElement(moreOption).pause(Duration.ofSeconds(1)).perform();
		return usedCarsOption.isDisplayed();
	}
	
 
	public boolean isUsedCarsOptionClickable() {
		try {
			usedCarsOption.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
 
	public boolean isCityFilterAutoOpened() {
		return searchCity.isDisplayed();
	}
	
	public boolean isCorrectCitySelected(String city) {		
		if (actualLocation.getText().equals("Chennai")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isPopularModelsDisplayed() {
		return popularModels.isDisplayed();
	}
 
    public void navigateToUsedCars() {
        wait.until(ExpectedConditions.visibilityOf(moreOption));
        actions.moveToElement(moreOption).pause(Duration.ofSeconds(1)).perform();
        usedCarsOption.click();
    }
 
    public void selectCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(searchCity));
        searchCity.sendKeys(city);
        wait.until(ExpectedConditions.visibilityOf(chennaiLocation));
        chennaiLocation.click();
    }
 
    public List<WebElement> PopularModels() {
        wait.until(ExpectedConditions.visibilityOf(popularModels));
        List<WebElement> models = popularModels.findElements(By.tagName("li"));
        return models;
    }
    
    public void writeToExcel(List<WebElement> names) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        FileOutputStream file = new FileOutputStream("Popular Car Models " + timestamp + ".xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Details");

            for (int i = 0; i < names.size(); i++) {
                
                    XSSFRow row = sheet.createRow(i);
                    XSSFCell cell = row.createCell(0);
                    cell.setCellValue(names.get(i).getText());
                    System.out.println(names.get(i).getText());                    
                }
            

            workbook.write(file);
            System.out.println("Popular car models written into excel");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }
    }
}
