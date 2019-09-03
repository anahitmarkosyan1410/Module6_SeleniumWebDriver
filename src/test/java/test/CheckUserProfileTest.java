package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePagePf;
import pages.LoginPagePf;
import pages.UserInformationPage;

public class CheckUserProfileTest extends BaseTest {

    @Test
    public void checkUserProfileTest() {
        login();
        checkCockpitPage();
        changeUserInformation();
        checkChanges();
    }

    private void login() {
        LoginPagePf loginPage = new LoginPagePf(driver).open().clickLoginOrRegister()
                .fillUsername("jequkonoss-5536@yopmail.com")
                .fillPassword("*****");
        loginPage.clickLoginButton();
    }

    private void checkCockpitPage() {
        UserInformationPage userInformation = new UserInformationPage(driver);
        HomePagePf homePage = new HomePagePf(driver);
        String userName = userInformation.getUserFirstName() + " " + userInformation.getUserLastName();
        String companyName = userInformation.getCompanyName();

        Assert.assertEquals(homePage.getUserName(), userName);
        Assert.assertEquals(homePage.getUserCompanyName(), companyName);
    }

    private void changeUserInformation() {
        setUserInformation();
        UserInformationPage userInformation = new UserInformationPage(driver);
        new HomePagePf(driver).clickMyInformationButton().clickEditInformationButton()
                .editFirstName(userInformation.getUserFirstName())
                .editLastName(userInformation.getUserLastName())
                .clickUpdateInformationButton();
        new UserInformationPage(driver).clickCompanyInformationTab()
                .clickEditInformationButton()
                .editCompanyName(userInformation.getCompanyName())
                .clickUpdateInformationButton();

    }

    private void checkChanges() {
        new HomePagePf(driver).clickHomeTab();
        checkCockpitPage();
    }

    private void setUserInformation() {
        UserInformationPage userInformation = new UserInformationPage(driver);
        userInformation.setCompanyName("NewCompany");
        userInformation.setUserFirstName("Test");
        userInformation.setUserLastName("Test");
    }
}
