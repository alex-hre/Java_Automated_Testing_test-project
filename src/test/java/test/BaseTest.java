package test;

import driver.DriverSingleton;
import listener.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Base test class that provides setup and teardown logic for WebDriver.
 * All test classes should extend this class to reuse WebDriver initialization and cleanup.
 */
@Listeners(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;

    /**Initializing Driver*/
    @BeforeMethod
    void setUp() {
        driver = DriverSingleton.getDriver();
    }

    /**Closing Driver*/
    @AfterMethod
    void tearDown() {
        DriverSingleton.closeDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
