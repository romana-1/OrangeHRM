
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By invalidCredentialsMessage =
            By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
    
    public LoginPage clickLoginForInvalidUser() {
        click(loginButton);
        return this;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public String getInvalidCredentialsMessage() {
        return findVisibleElement(invalidCredentialsMessage).getText();
    }

    public LoginPage enterUsername(String user) {
        type(username, user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        type(password, pass);
        return this;
    }

    public DashboardPage clickLogin() {
        click(loginButton);
        return new DashboardPage(driver);
    }

    public DashboardPage login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        return clickLogin();
    }
    
     public boolean isLoginPageDisplayed() {
        return wait.waitForUrlContains("login");
    }
}
