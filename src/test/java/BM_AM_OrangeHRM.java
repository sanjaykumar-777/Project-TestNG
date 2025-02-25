import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utility.HighLighter;

import java.time.Duration;


public class BM_AM_OrangeHRM {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/Users/sanjaykumar/Desktop/UiAutomation/src/drivers/chromedriver");
        driver = new ChromeDriver();
        //automatically setup chromedriver
//        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("1. Open Chrome & Application");

    }

    @Test(priority = 1)
    public void signIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textUserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        HighLighter.highlightElement(driver, textUserName);
        textUserName.sendKeys("Admin");

        WebElement textPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        HighLighter.highlightElement(driver, textPassword);
        textPassword.sendKeys("admin123");

        WebElement buttonLogin = driver.findElement(By.xpath("//button[text()=' Login ']"));
        HighLighter.highlightElement(driver, buttonLogin);
        buttonLogin.click();

        System.out.println("2. Sign In");

    }

    @Test(priority = 2, dependsOnMethods = {"signIn"})
    public void userSearch ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuAdmin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Admin']")));
//        WebElement menuAdmin = driver.findElement(By.xpath("(//a[@class='oxd-main-menu-item active'])[1]"));
        HighLighter.highlightElement(driver, menuAdmin);
        menuAdmin.click();

        WebElement textUserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")));
//        WebElement textUserName = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
        HighLighter.highlightElement(driver, textUserName);
        textUserName.sendKeys("Admin");

        WebElement buttonSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Search']")));
//        WebElement buttonSearch = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
        HighLighter.highlightElement(driver, buttonSearch);
        buttonSearch.click();

        System.out.println("3. Search For User");
    }

    @Test(priority = 3, dependsOnMethods = {"userSearch"})
    public void userSignOut ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-userdropdown-name']")));
        userDropDown.click();

        WebElement linkLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Logout']")));
        HighLighter.highlightElement(driver,linkLogout);
        linkLogout.click();

        System.out.println("4. Sign Out");
    }

    @AfterMethod
    public void tearDown ()
    {
        System.out.println("5. Close Chrome & Application");
        driver.quit();
    }



}
