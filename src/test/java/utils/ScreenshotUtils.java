package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String folderPath = "screenshots";
        String filePath = folderPath + "/" + testName + ".png";

        try {

            Path folder = Paths.get(folderPath);

            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            Files.copy(
                    source.toPath(),
                    Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}