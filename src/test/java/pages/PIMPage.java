package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage extends BasePage {

    // ==============================
    // LOCATORS
    // ==============================

    private By pimMenu =
            By.xpath("//span[text()='PIM']");

    private By addEmployeeMenu =
            By.xpath("//a[text()='Add Employee']");

    private By employeeListMenu =
            By.xpath("//a[text()='Employee List']");

    private By firstNameField =
            By.name("firstName");

    private By lastNameField =
            By.name("lastName");

    private By employeeIdField =
            By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");

    private By saveButton =
            By.xpath("//button[@type='submit']");

    private By employeeNameSearchField =
            By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");

    private By searchButton =
            By.xpath("//button[@type='submit']");


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public PIMPage(WebDriver driver) {
        super(driver);
    }


    // ==============================
    // GO TO PIM
    // ==============================

    public void clickPIMMenu() {
        click(pimMenu);
    }


    // ==============================
    // ADD EMPLOYEE
    // ==============================

    public void clickAddEmployee() {
        click(addEmployeeMenu);
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmployeeId(String employeeId) {

        WebElement field = findVisibleElement(employeeIdField);

        field.click();

        field.sendKeys(
                org.openqa.selenium.Keys.chord(
                        org.openqa.selenium.Keys.COMMAND, "a"
                )
        );

        field.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        field.sendKeys(employeeId);
    }

    public void clickSaveButton() {
        click(saveButton);
    }
    public void addEmployee(String firstName, String lastName) {

        clickAddEmployee();

        enterFirstName(firstName);
        enterLastName(lastName);

        String uniqueEmployeeId =
                String.valueOf(10000000 + (System.currentTimeMillis() % 90000000));

        enterEmployeeId(uniqueEmployeeId);

        clickSaveButton();
    }


    // ==============================
    // VERIFY EMPLOYEE CREATED
    // ==============================

    public boolean isEmployeeCreated() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            wait.until(driver ->
                    driver.getCurrentUrl().contains("viewPersonalDetails"));

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }


    // ==============================
    // EMPLOYEE LIST
    // ==============================

    public void clickEmployeeList() {
        click(employeeListMenu);
    }


    // ==============================
    // SEARCH EMPLOYEE
    // ==============================

    public void searchEmployee(String employeeName) {

        clickEmployeeList();

        WebElement searchField =
                findVisibleElement(employeeNameSearchField);

        searchField.clear();
        searchField.sendKeys(employeeName);

        /*
         * Wait briefly for OrangeHRM autocomplete.
         */
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            By option = By.xpath(
                    "//div[@role='option']//span[contains(normalize-space(),'"
                    + employeeName
                    + "')]"
            );

            WebElement employeeOption =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(option)
                    );

            employeeOption.click();

        } catch (TimeoutException e) {

            // Continue with typed employee name
        }

        click(searchButton);

        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@role='row']")
                        ),
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//*[contains(text(),'No Records Found')]")
                        )
                )
        );
    }


    // ==============================
    // VERIFY EMPLOYEE DISPLAYED
    // ==============================

    public boolean isEmployeeDisplayed(String employeeName) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By employeeRow = By.xpath(
                "//div[@role='row'][.//*[contains(normalize-space(),'"
                + employeeName
                + "')]]"
        );

        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(employeeRow)
            );
            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }


    // ==============================
    // EDIT EMPLOYEE
    // ==============================

    public void clickEmployeeEditButton(String employeeName) {

        By editButton = By.xpath(
                "//div[@role='row'][.//*[contains(normalize-space(),'"
                + employeeName
                + "')]]//button[.//i[contains(@class,'bi-pencil-fill')]]"
        );

        click(editButton);
    }

 // ==============================
 // DELETE EMPLOYEE
 // ==============================

 public void clickEmployeeDeleteButton(String employeeName) {

     By deleteButton = By.xpath(
             "//div[@role='row'][.//*[contains(normalize-space(),'"
             + employeeName
             + "')]]//button[.//i[contains(@class,'bi-trash')]]"
     );

     click(deleteButton);
 }

 public void confirmDelete() {

     By confirmDeleteButton =
             By.cssSelector("button.oxd-button--label-danger");

     WebDriverWait wait =
             new WebDriverWait(driver, Duration.ofSeconds(20));

     WebElement button =
             wait.until(
                     ExpectedConditions.elementToBeClickable(
                             confirmDeleteButton
                     )
             );

     button.click();
 }

 public void deleteEmployee(String employeeName) {

     clickEmployeeDeleteButton(employeeName);

     confirmDelete();
 }

    public void updateFirstName(String newFirstName) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement firstName =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.name("firstName")
                        )
                );

        firstName.clear();
        firstName.sendKeys(newFirstName);
    }


    public void updateLastName(String newLastName) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement lastName =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.name("lastName")
                        )
                );

        lastName.clear();
        lastName.sendKeys(newLastName);
    }


    public void savePersonalDetails() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        By personalDetailsSaveButton = By.xpath(
                "//h6[contains(normalize-space(),'Personal Details')]"
                + "/following::button[@type='submit'][1]"
        );

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                personalDetailsSaveButton
                        )
                );

        button.click();
    }


    public void editEmployeeName(
            String employeeName,
            String newFirstName,
            String newLastName) {

        clickEmployeeEditButton(employeeName);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(driver ->
                driver.getCurrentUrl().contains("viewPersonalDetails"));

        updateFirstName(newFirstName);
        updateLastName(newLastName);

        savePersonalDetails();
    }


    // ==============================
    // DELETE EMPLOYEE
    // ==============================

    

   

    public boolean isEmployeeNotDisplayed(String employeeName) {

        By employeeRow = By.xpath(
                "//div[@role='row'][.//*[contains(normalize-space(),'"
                + employeeName
                + "')]]"
        );

        return driver.findElements(employeeRow).isEmpty();
    }
}