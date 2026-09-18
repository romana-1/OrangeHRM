package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

public class InvalidLoginTest extends BaseTest {

    @Test(groups = {"regression"})
    public void invalidLoginTest() {

        driver.get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);

        loginPage
            .enterUsername("WrongUser")
            .enterPassword("WrongPassword")
            .clickLogin();

        Assert.assertEquals(
            loginPage.getInvalidCredentialsMessage(),
            "Invalid credentials",
            "Invalid login message was not displayed."
        );
    }
}
