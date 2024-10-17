package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginFunctionalityTest extends BaseTest {


    @BeforeMethod
    public void pageSetup() {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }

    @Test (priority = 10)
    public void successfulLoginWithValidUsernameAndValidPassword() {
        String validUsername = excelReader.getStringData("Sheet1", 1, 0);
        String validPassword = excelReader.getStringData("Sheet1", 1, 1);
        homepagePage.inputUsername(validUsername);
        homepagePage.inputPassword(validPassword);
        homepagePage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
        Assert.assertTrue(secureLoginPage.getLogOutButton().isDisplayed());
        Assert.assertTrue(secureLoginPage.getSuccessfulMessage().isDisplayed());
        Assert.assertTrue(secureLoginPage.getSuccessfulMessage().getText().contains("You logged into a secure area!"));
    }

    @Test (priority = 20)
    public void unsuccessfulLoginWithInvalidUsernameAndValidPassword() {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String invalidUsername = excelReader.getStringData("Sheet1", i, 2);
            String validPassword = excelReader.getStringData("Sheet1", 1, 1);
            homepagePage.inputUsername(invalidUsername);
            homepagePage.inputPassword(validPassword);
            homepagePage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
            Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
            Assert.assertTrue(homepagePage.getFlashMessage().getText().contains("Your username is invalid!"));
        }
    }

    @Test (priority = 30)
    public void unsuccessfulLoginWithValidUsernameAndInvalidPassword() {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String validUsername = excelReader.getStringData("Sheet1", 1, 0);
            String invalidPassword = excelReader.getStringData("Sheet1", i, 3);
            homepagePage.inputUsername(validUsername);
            homepagePage.inputPassword(invalidPassword);
            homepagePage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
            Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
            Assert.assertTrue(homepagePage.getFlashMessage().getText().contains("Your password is invalid!"));
        }
    }

    @Test (priority = 40)
    public void unsuccessfulLoginWithInvalidUsernameAndInvalidPassword() {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String invalidUsername = excelReader.getStringData("Sheet1", i, 2);
            String invalidPassword = excelReader.getStringData("Sheet1", i, 3);
            homepagePage.inputUsername(invalidUsername);
            homepagePage.inputPassword(invalidPassword);
            homepagePage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
            Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
            Assert.assertTrue(homepagePage.getFlashMessage().getText().contains("Your username is invalid!"));
        }
    }

    @Test (priority = 50)
    public void unsuccessfulLoginWithEmptyUsernameAndEmptyPassword() {
        String emptyUsername = "";
        String emptyPassword = "";
        homepagePage.inputUsername(emptyUsername);
        homepagePage.inputPassword(emptyPassword);
        homepagePage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
        Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
        Assert.assertTrue(homepagePage.getFlashMessage().getText().contains("Your username is invalid!"));
    }
}
