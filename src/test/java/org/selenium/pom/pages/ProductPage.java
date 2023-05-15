package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class ProductPage extends BasePage {

    private final By addToCartBtn = By.cssSelector("button[value='1215']");
    private final By productTitle = By.cssSelector(".product_title.entry-title");
    private final By alert = By.cssSelector("div[role='alert']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
    }

    public ProductPage addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public ProductPage loadProduct(String productName) {
        load("/product/" + productName + "/");
        return this;
    }

    public String getAlert() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
    }
}
