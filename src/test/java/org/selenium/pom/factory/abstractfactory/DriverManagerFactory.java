package org.selenium.pom.factory.abstractfactory;

import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.ChromeDriverManager;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.FirefoxDriverManager;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriverManager();
            case FIREFOX:
                return new FirefoxDriverManager();
            default:
                throw new IllegalStateException("Invalid driver: " + driverType);
        }

    }
}
