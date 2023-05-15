package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
public class CheckoutPage extends BasePage {
    private final By firstnameFld = By.id("billing_first_name");
    private final By lastnameFld = By.id("billing_last_name");
    private final By addressLineOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.className("showlogin");
    private final By userNameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By cashOnDeliveryRadioBtn = By.id("payment_method_cod");

    private final By productName = By.cssSelector("td[class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameFld));
        element.clear();
        element.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameFld));
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        element.clear();
        element.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        element.clear();
        element.sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeFld));
        element.clear();
        element.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        //wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    private CheckoutPage waitForLoginBtnDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(String username, String password) {
        return enterUserName(username)
                .enterPassword(password)
                .clickLoginBtn()
                .waitForLoginBtnDisappear();
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!element.isSelected()) {
            element.click();
        }
        return this;
    }

    public CheckoutPage selectCashOnDeliveryTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryRadioBtn)).click();
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage selectState(String stateName) {
        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .enterAddressLineOne(billingAddress.getAddressLineOne())
                .enterCity(billingAddress.getCity())
                .selectState(billingAddress.getState())
                .enterPostCode(billingAddress.getPostalCode())
                .enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage load() {
        load("/checkout");
        return this;
    }

    public String getProductName() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
        }
    }
}
