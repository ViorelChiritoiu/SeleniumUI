package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.DataProviders;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("epic 1")
@Feature("Add to cart")
public class AddToCartTest extends BaseTest {

    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234467")
    @Description("this is the description")
    @Story("Add to cart from Store page")
    @Test(description = "add product to cart from store page")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test
    public void addToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        String productNameSeparatedByDash = product.getName().toLowerCase().replaceAll(" ", "-");
        ProductPage productPage = new ProductPage(getDriver()).loadProduct(productNameSeparatedByDash).addToCart();
        assertTrue(productPage.getAlert().contains("“" + product.getName() +"” has been added to your cart."));
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class)
    public void addToCartFeaturedProducts(Product product) {
        CartPage cartPage = new HomePage(getDriver())
                .load().getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        assertEquals(cartPage.getProductName(), product.getName());
    }
}
