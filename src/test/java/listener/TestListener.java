package listener;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A TestNG listener that captures a screenshot when a test fails.
 */
public class TestListener implements ITestListener {

    /**
     * Called when a test fails. Captures a screenshot with the test name.
     * @param result Information about the failed test.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.getName());
    }

    /**
     * Captures a screenshot and saves it with a timestamped filename.
     * @param testName The name of the failed test.
     */
    private void takeScreenshot(String testName) {

        if (DriverSingleton.getDriver() == null) {
            return;
        }

        File screenshot = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);

        // Generate a timestamp for the filename
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String filename = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            // Save the screenshot file
            FileUtils.copyFile(screenshot, new File(filename));
            System.out.println("🛑 Screenshot saved: " + filename);
        } catch (IOException e) {
            // Handle any errors that occur while saving the file
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}
