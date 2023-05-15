package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    //private final By productName = By.cssSelector("td[class='product-name'] a");
    //private final By checkoutBtn = By.cssSelector(".checkout-button");
    //private final By cartHeading = By.cssSelector(".has-text-align-center");

    @FindBy(how = How.CSS, using = "td[class='product-name'] a")
    @CacheLookup
    private WebElement productName;

    @FindBy(css = ".checkout-button")
    private WebElement checkoutBtn;

    @FindBy(css = ".has-text-align-center")
    private WebElement cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.textToBePresentInElementValue(cartHeading, "Cart"));
    }
}
