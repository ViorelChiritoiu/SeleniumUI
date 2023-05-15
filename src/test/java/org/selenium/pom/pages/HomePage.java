package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.HeaderComponent;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {

    private HeaderComponent headerComponent;
    private ProductThumbnail productThumbnail;

    public HomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage load() {
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }
}
