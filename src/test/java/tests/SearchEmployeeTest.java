package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;
import utils.TestDataUtil;

public class SearchEmployeeTest extends BaseTest {

	@Test(groups = {"regression"})
	public void searchEmployeeTest() {

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

	    PIMPage pimPage = new PIMPage(driver);

	    pimPage.clickPIMMenu();

	    String employeeName =
	            TestDataUtil.generateUniqueName("Search");

	    pimPage.addEmployee(
	            employeeName,
	            "Employee"
	    );

	    Assert.assertTrue(
	            pimPage.isEmployeeCreated(),
	            "Employee was not created."
	    );

	    pimPage.searchEmployee(employeeName);

	    Assert.assertTrue(
	            pimPage.isEmployeeDisplayed(employeeName),
	            "Employee was not found."
	    );  
	}
}
	