import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class SeleniumWebDriverTest {
    private String userFirstName = "TestFirstName";
    private String userLastName = "TestLastName";
    private String companyName = "TestCompanyName";

    private int timeout = 60;
    private WebDriver webDriver;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod()
    private void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anahit_Markosyan\\ChromDriver\\chromedriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }

    @org.testng.annotations.Test
    public void updateUserInformationTest() {
        login();
        checkCockpitPageIsLoading();
        changePersonalInformation();
        changeCompanyInformation();
        checktChanges();
    }

    private void login() {
        webDriver.get("https://exst-ui-uat.schneider-electric.com/");
        waitForElementToBeAbsent();
        webDriver.findElement(By.cssSelector("[xtm-n='header::login']")).click();
        webDriver.findElement(By.id("inputEmail")).sendKeys("jequkonoss-5536@yopmail.com");
        webDriver.findElement(By.id("inputPassword3")).sendKeys("123456Aa");
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();
        forceWait(10000);
    }

    private void checkCockpitPageIsLoading() {
        softAssert.assertEquals(webDriver.findElement(By.xpath("//se-user-info//div[@class='bold name']")).getText(), userFirstName + " " + userLastName);
        softAssert.assertEquals(webDriver.findElement(By.xpath("//se-user-info//div[@class='text-medium bold company-name']")).getText(),companyName);
    }


    private void changePersonalInformation() {
        userFirstName = "NewFirstName";
        userLastName = "NewLastName";
        webDriver.findElement(By.cssSelector(".user-info-item .account")).click();
        webDriver.findElement(By.cssSelector("ul.account-dropdown-menu :nth-child(1) a")).click();
        waitForElementToBeAbsent();
        webDriver.findElement(By.cssSelector(".tab-content.active .btn.big.blue.btn-edit")).click();
        waitForElementToBeAbsent();
        webDriver.findElement(By.id("firstName")).clear();
        webDriver.findElement(By.id("firstName")).sendKeys(userFirstName);
        webDriver.findElement(By.id("lastName")).clear();
        webDriver.findElement(By.id("lastName")).sendKeys(userLastName);
        webDriver.findElement(By.cssSelector(".btn.big.blue.btn-accept")).click();
    }

    private void changeCompanyInformation() {
        companyName = "newCompanyName";
        waitForElementToBeAbsent();
        webDriver.findElement(By.cssSelector("ul.tabs-headers.small li:nth-child(2)")).click();
        webDriver.findElement(By.cssSelector(".tab-content.active .btn.big.blue.btn-edit")).click();
        waitForElementToBeVisible(By.id("companyName"));
        webDriver.findElement(By.id("companyName")).clear();
        webDriver.findElement(By.id("companyName")).sendKeys("Company Name");
        webDriver.findElement(By.cssSelector(".btn.big.blue.btn-accept")).click();
    }

    private void checktChanges() {
        waitForElementToBeAbsent();
        webDriver.findElement(By.xpath("//div[@class='nav-links-container']/a[1]")).click();
        waitForElementToBeVisible(By.xpath("//se-user-info//div[@class='bold name']"));
        softAssert.assertEquals(webDriver.findElement(By.xpath("//se-user-info//div[@class='bold name']")).getText(), userFirstName + " " + userLastName);
        softAssert.assertEquals(webDriver.findElement(By.xpath("//se-user-info//div[@class='text-medium bold company-name']")).getText(),companyName);
    }


    private void waitForElementToBeVisible(By by) {
        try {
            new WebDriverWait(webDriver, timeout, 10L).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (WebDriverException e) {
            throw new WebDriverException("Element is not visible");
        }
    }

    private void waitForElementToBeClickable(By by) {
        waitForElementToBeVisible(by);
        try {
            new WebDriverWait(webDriver, timeout).until(ExpectedConditions.elementToBeClickable(by));
        } catch (WebDriverException e) {
            throw new WebDriverException("Element is not clickable");
        }
    }

    private void waitForElementToBeAbsent() {
       WebElement element = webDriver.findElement(By.cssSelector(".spinner-overlay.global"));
        int time = 0;
        try {
            while (element.isDisplayed()) {
                Thread.sleep(500L);
                time++;
                if (time == timeout * 2) {
                    throw new InterruptedException();
                }
            }
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException("Element is not absent");
        }
    }

    private void forceWait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
