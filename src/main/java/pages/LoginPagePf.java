package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPagePf extends BasePage {

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(id = "inputEmail")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword3")
    private WebElement passwordInput;

    @FindBy(css = "[xtm-n='header::login']")
    private WebElement loginOrRegisterButton;

    public LoginPagePf(WebDriver driver) {
        super(driver);
    }

    public LoginPagePf open() {
        driver.get("https://exst-ui-uat.schneider-electric.com/");
        return this;
    }

    public LoginPagePf fillUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPagePf fillPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public HomePagePf clickLoginButton() {
        click(loginButton);
        forceWait(10000);
        return new HomePagePf(driver);
    }

    public LoginPagePf clickLoginOrRegister() {
        click(loginOrRegisterButton);
        return this;
    }
}
