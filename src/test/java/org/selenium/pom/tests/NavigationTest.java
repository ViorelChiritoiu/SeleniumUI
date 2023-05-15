package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NavigationTest extends BaseTest {

    @Test
    public void NavigateFromHomeToStoreUsingMainMenu() {
        StorePage storePage = new HomePage(getDriver())
                .load().getHeaderComponent()
                .navigateToStoreUsingMenu();
        assertEquals(storePage.getTitle(), "Store");
    }
}
