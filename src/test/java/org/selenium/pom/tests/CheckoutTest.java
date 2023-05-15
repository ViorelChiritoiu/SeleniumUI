package org.selenium.pom.tests;

import io.restassured.http.Cookies;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.selenium.pom.utils.JacksonUtils.deserializeJson;
import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi(new Cookies());
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {

        User user = new User("demouser" + new FakerUtils().generateRandomNumber(),
                "demopwd" + new FakerUtils().generateRandomNumber(),
                "demouser" + new FakerUtils().generateRandomNumber() + "@a.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);

        BillingAddress billingAddress = deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void guestCheckoutUsingCashOnDelivery() throws IOException {

        BillingAddress billingAddress = deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi(new Cookies());
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectCashOnDeliveryTransfer()
                .placeOrder();
        assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingCashOnDelivery() throws IOException {

        User user = new User("demouser" + new FakerUtils().generateRandomNumber(),
                "demopwd" + new FakerUtils().generateRandomNumber(),
                "demouser" + new FakerUtils().generateRandomNumber() + "@a.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);

        BillingAddress billingAddress = deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectCashOnDeliveryTransfer()
                .placeOrder();
        assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

}
