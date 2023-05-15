package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.selenium.pom.utils.JacksonUtils.*;
import static org.testng.Assert.assertEquals;


public class MyFirstTestCase extends BaseTest {

    //@Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        StorePage storePage = new HomePage(getDriver())
                .load().getHeaderComponent()
                .navigateToStoreUsingMenu()
                .search("Blue");

        assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage
                .checkout()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    //@Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword(), ConfigLoader.getInstance().getEmail());

        StorePage storePage = new HomePage(getDriver())
                .load().getHeaderComponent()
                .navigateToStoreUsingMenu()
                .search("Blue");

        assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.clickHereToLoginLink();
        checkoutPage
                .login(user.getUsername(), user.getPassword())
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }
}
