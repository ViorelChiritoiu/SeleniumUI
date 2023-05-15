package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManagerOriginal {

    public WebDriver initializeDriver(String browser) {
        WebDriver driver;
        switch (DriverType.valueOf(browser)) {
            case CHROME:
                WebDriverManager.chromedriver().cachePath("drivers").setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("drivers").setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name:" + browser);
        }
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); this is implicit wait, don't use it
        return driver;
    }
}
