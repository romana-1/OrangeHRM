
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;
import utils.TestDataUtil;

public class EditEmployeeTest extends BaseTest {

    @Test(groups = {"regression"})
    public void editEmployeeTest() {

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

        // Create PIM Page
        PIMPage pimPage = new PIMPage(driver);

        // Go to PIM
        pimPage.clickPIMMenu();

        // Create unique employee name
        String employeeName =
                TestDataUtil.generateUniqueName("Edit");
        
        // Create employee
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
                "Employee was not found."
        );

        // Edit employee
        pimPage.editEmployeeName(
                employeeName,
                "UpdatedEdit",
                "Employee"
        );

        Assert.assertTrue(
                pimPage.isEmployeeCreated(),
                "Employee was not updated successfully."
        );
    }
}
