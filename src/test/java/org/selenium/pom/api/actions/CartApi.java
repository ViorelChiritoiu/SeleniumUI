package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.ConfigLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartApi {

    private Cookies cookies;

    public CartApi() {
    }

    public CartApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Response addToCart(int productId, int quantity) throws IOException {

        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        Map<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);

        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl()).log().all()
                        .headers(headers)
                        .formParams(formParams)
                        .cookies(cookies)
                        .when()
                        .post("/?wc-ajax=add_to_cart")
                        .then()
                        .log().all()
                        .extract().response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add product " + productId + " to cart, HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
