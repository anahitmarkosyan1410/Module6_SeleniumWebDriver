package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends BasePage {

    @FindBy(xpath = "//se-user-info//div[@class='bold name']")
    private WebElement userName;

    @FindBy(xpath = "//se-user-info//div[@class='text-medium bold company-name']")
    private WebElement userCompanyName;

    @FindBy(css = "div.account.bold.dropdown")
    private WebElement myProfileButton;

    @FindBy(css = "div.account.bold.dropdown a.block-link")
    private WebElement myInformationButton;

    @FindBy(css = "div.spinner")
    private WebElement loadingSpinner;

    @FindBy(xpath = "//div[@class='nav-links-container']/a[1]")
    private WebElement homeTab;

    @FindBy(css = ".spinner-overlay.global")
    private WebElement transparentFrame;

    public HomePagePf(WebDriver driver) {
        super(driver);
    }

    public HomePagePf clickHomeTab() {
        waitForElementToBeAbsent(transparentFrame);
        click(homeTab);
        return this;
    }

    public String getUserName() {
        waitForElementToBeAbsent(loadingSpinner);
        return userName.getText();
    }

    public String getUserCompanyName() {
        waitForElementToBeAbsent(loadingSpinner);
        return userCompanyName.getText();
    }

    public UserInformationPage clickMyInformationButton() {
        waitForElementToBeAbsent(loadingSpinner);
        click(myProfileButton);
        click(myInformationButton);
        return new UserInformationPage(driver);
    }
}
