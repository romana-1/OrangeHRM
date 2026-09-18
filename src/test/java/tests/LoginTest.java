
 package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void validLoginTest() {

        driver.get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);

        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        Assert.assertTrue(
        	    dashboardPage.isDashboardDisplayed(),
        	    "Dashboard was not displayed."
        	);
    }
}      