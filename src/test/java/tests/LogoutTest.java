package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LogoutTest extends BaseTest {

    @Test(groups = {"regression"})
    public void validLogoutTest() {

        driver.get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);

        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        Assert.assertTrue(dashboardPage.isDashboardDisplayed());

        loginPage = dashboardPage.logout();

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "Login page was not displayed after logout."
        );
    }
}