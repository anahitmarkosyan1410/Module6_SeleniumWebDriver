package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserInformationPage extends BasePage {

    private String userFirstName = "TestFirstName";

    private String userLastName = "TestLastName";

    private String companyName = "Company";

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(css = ".tab-content.active .btn.big.blue.btn-edit")
    private WebElement editInformationButton;

    @FindBy(css = ".btn.big.blue.btn-accept")
    private WebElement updateInformationButton;

    @FindBy(css = ".spinner-overlay.global")
    private WebElement transparentFrame;

    @FindBy(id = "companyName")
    private WebElement companyNameInput;

    @FindBy(xpath = "//ul[@class='tabs-headers tabs bold small']//li[normalize-space()= 'Company Information']")
    private WebElement companyInformationTab;

    public UserInformationPage(WebDriver driver) {
        super(driver);
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UserInformationPage editFirstName(String firstName) {
        type(firstNameInput, firstName);
        return this;
    }

    public UserInformationPage editLastName(String lastName) {
        type(lastNameInput, lastName);
        return this;
    }

    public UserInformationPage clickUpdateInformationButton() {
        waitForElementToBeAbsent(transparentFrame);
        click(updateInformationButton);
        return this;
    }

    public UserInformationPage clickEditInformationButton() {
        click(editInformationButton);
        return this;
    }

    public UserInformationPage editCompanyName(String companyName) {
        type(companyNameInput, companyName);
        return this;
    }

    public UserInformationPage clickCompanyInformationTab() {
        waitForElementToBeAbsent(transparentFrame);
        click(companyInformationTab);
        return this;
    }
}
