package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {
        StorePage storePage = new StorePage(getDriver()).load().search("Blue");
        assertTrue(storePage.isLoaded("Blue"), "Searched text not found");
        assertEquals(storePage.getTitle(), "Search results: “Blue”");
    }

    @Test
    public void searchWithExactMatch() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver())
                .load()
                .searchExactMatch(product.getName());
        assertEquals(productPage.getProductTitle(), product.getName());
    }

    @Test
    public void SearchForNonExistingProduct() {
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(new FakerUtils().generateRandomName());
        assertEquals(storePage.getInfo(), "No products were found matching your selection.");
    }
}
