package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;
import utils.TestDataUtil;

public class DeleteEmployeeTest extends BaseTest {

    @Test(groups = {"regression"})
    public void deleteEmployeeTest() {

        // Open OrangeHRM
        driver.get(ConfigReader.getProperty("url"));

        // Login
        LoginPage loginPage = new LoginPage(driver);

        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard was not displayed."
        );

        // Open PIM
        PIMPage pimPage = new PIMPage(driver);

        pimPage.clickPIMMenu();

        // Generate unique employee name
        String employeeName =
                TestDataUtil.generateUniqueName("Delete");

        // Add employee
        pimPage.addEmployee(
                employeeName,
                "Employee"
        );

        Assert.assertTrue(
                pimPage.isEmployeeCreated(),
                "Employee was not created."
        );

        // Search employee
        pimPage.searchEmployee(employeeName);

        Assert.assertTrue(
                pimPage.isEmployeeDisplayed(employeeName),
                "Employee was not found before deletion."
        );

        // Delete employee
        pimPage.deleteEmployee(employeeName);

        // Search again
        pimPage.searchEmployee(employeeName);

        // Verify employee was deleted
        Assert.assertTrue(
                pimPage.isEmployeeNotDisplayed(employeeName),
                "Employee was still found after deletion."
        );
    }
}