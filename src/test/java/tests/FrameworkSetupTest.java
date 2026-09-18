package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigReader;

public class FrameworkSetupTest extends BaseTest {

    @Test
    public void launchBrowser() {

        driver.get(ConfigReader.getProperty("url"));

        String pageTitle = driver.getTitle();

        System.out.println("Page title: " + pageTitle);
    }
}