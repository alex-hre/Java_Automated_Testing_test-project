package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {}

    /** Creating driver */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    /** Closing driver */
    public static void closeDriver() {
        if (driver.get() != null) {
            System.out.println("[DRIVER] Closing WebDriver for thread: " + Thread.currentThread().getId());
            driver.get().quit();
            driver.remove();
        }
    }

    /** Driver creation logic */
    private static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome"); // Default: Chrome
        System.out.println("Browser: " + browser);

        WebDriver newDriver = switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
        };
        newDriver.manage().window().maximize();
        System.out.println("[DRIVER] Creating new WebDriver for thread: " + Thread.currentThread().getId());
        return newDriver;
    }
}
