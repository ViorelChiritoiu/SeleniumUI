package org.selenium.pom.factory.abstractfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {


    @Override
    protected void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("drivers").setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
