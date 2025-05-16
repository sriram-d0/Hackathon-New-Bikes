package pageclasses;

import java.time.Duration;
import java.util.Set;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
 

 
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
 
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 
    @FindBy(xpath = "//*[@id='forum_login_title_lg']")
    WebElement loginButton;
 
    @FindBy(xpath = "//./div[6]/div/span[2]")
    WebElement loginGoogle;
    
    @FindBy(xpath="//./div/div/div[1]/div/div[1]/div/div[1]/div")
    WebElement loginEmail;
 
    @FindBy(xpath = "//*[@id='identifierNext']//button")
    WebElement loginSubmit;
 
    // This element is dynamic and appears after login attempt
    @FindBy(xpath = "//*[@class=\"Ekjuhf Jj6Lae\"]" )
    WebElement errorText;
 
    public String loginWithInvalidGoogleAccount() {
        loginButton.click();
        
        // Click the Google login button
        WebElement googleButton = wait.until(ExpectedConditions.elementToBeClickable(loginGoogle));
        googleButton.click();
 
        // Switch to the new window
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String winHandle : allWindows) {
            if (!winHandle.equals(parentWindow)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }
 
        // Enter invalid email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='email']"))).sendKeys("asdf@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='identifierNext']/div/button"))).click();
 
       // loginSubmit.click();
        String res = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='Ekjuhf Jj6Lae']"))).getText();
    	return res;

    }
 
    
}