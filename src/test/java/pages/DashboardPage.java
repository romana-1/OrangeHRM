package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private By profileMenu =
            By.cssSelector(".oxd-userdropdown-tab");

    private By logoutButton =
            By.xpath("//a[normalize-space()='Logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return wait.waitForUrlContains("dashboard");
    }

    public LoginPage logout() {

        wait.waitForClickability(profileMenu).click();

        wait.waitForClickability(logoutButton).click();

        return new LoginPage(driver);
    }

    public String getDashboardTitle() {
        return getPageTitle();
    }
}