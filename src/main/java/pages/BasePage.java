package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private static final int TIMEOUT = 10;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            throw new WebDriverException("Element is not visible");
        }
    }

    protected void waitForElementToBeClickable(WebElement element) {
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            throw new WebDriverException("Element is not clickable");
        }
    }

    public void waitForElementToBeAbsent(WebElement element) {
        int time = 0;
        try {
            while (element.isDisplayed()) {
                Thread.sleep(500L);
                time++;
                if (time == TIMEOUT * 2) {
                    throw new InterruptedException();
                }
            }
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException("Element is not absent");
        }
    }

    protected void forceWait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
}
