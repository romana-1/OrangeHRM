package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    protected WebElement findVisibleElement(By locator) {
        return wait.waitForVisibility(locator);
    }

    protected void type(By locator, String text) {
        WebElement element = findVisibleElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        wait.waitForClickability(locator).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

  