package base;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import utils.ScreenshotUtils;
import drivers.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BaseTest {
	
	protected static final Logger logger =
	LogManager.getLogger(BaseTest.class);
    
    protected WebDriver driver;
    private DriverFactory driverFactory;
    
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        logger.info("Starting browser");

        driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver();

        logger.info("Browser started successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: " + result.getName());

            ScreenshotUtils.captureScreenshot(
                    driver,
                    result.getName()
            );
        } else {
            logger.info("Test PASSED: " + result.getName());
        }

        logger.info("Closing browser");

        if (driverFactory != null) {
            driverFactory.quitDriver();
        }

        logger.info("Browser closed");
    }
}